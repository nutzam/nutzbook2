<template>
    <div>
      <group>
        <x-input :min=6 :max=12 is-type=text :value.sync="register_username" title="用户名"></x-input>
        <x-input :min=1 :max=24 is-type=password :value.sync="register_password" title="密码"></x-input>
      </group>
      <toast :show.sync="register_ok" >注册成功</toast>
      <toast :show.sync="register_fail" >{{register_message}}</toast>
      <x-button type="warn" @click="doRegister">注册</x-button>
    </div>
</template>
<style>
  @import '~vux/dist/vux.css';
</style>
<script>
  import Group from 'vux/dist/components/group'
  import Cell from 'vux/dist/components/cell'
  import XInput from 'vux/dist/components/x-input'
  import Toast from 'vux/dist/components/toast'
  import XButton from 'vux/dist/components/x-button'

  export default {
    components: {
      Group, Cell, XInput, Toast, XButton
    },
    data () {
      return {
        register_ok: false,
        register_fail: false,
        register_username: 'system',
        register_password: '1',
        register_captche: '',
        register_message: ''
      }
    },
    methods: {
      doRegister () {
        var data = {
          'username': this.register_username,
          'password': this.register_password,
          'captche': this.register_captche
        }
        this.$http.post('/api/user/register', data).then(
          function (resp) {
            console.info(resp)
            if (resp.ok) {
              var re = resp.json()
              if (re.ok) {
                this.register_ok = true
              } else {
                this.register_message = re.msg || '未知错误'
                this.register_fail = true
              }
            } else {
              this.register_message = '未知错误'
              this.register_fail = true
            }
          },
          function (re) {
            this.register_message = '通信故障'
            this.register_fail = true
          }
        )
      }
    }
  }
</script>
