<template>
    <div>
      <group>
        <x-input :min=6 :max=12 is-type=text :value.sync="login_username" title="用户名"></x-input>
        <x-input :min=1 :max=24 is-type=password :value.sync="login_password" title="密码"></x-input>
        <x-input :min=6 :max=24 is-type=text :value.sync="login_captche" title="验证码"></x-input>
      </group>
      <toast :show.sync="login_ok" >登陆成功</toast>
      <toast :show.sync="login_fail" >{{login_message}}</toast>
      <x-button type="warn" @click="doLogin">登陆</x-button>
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
        login_ok: false,
        login_fail: false,
        login_username: 'system',
        login_password: '1',
        login_captche: '',
        login_message: ''
      }
    },
    methods: {
      doLogin () {
        var data = {
          'username': this.login_username,
          'password': this.login_password,
          'captche': this.login_captche
        }
        this.$http.post('/api/user/login', data).then(
          function (resp) {
            console.info(resp)
            if (resp.ok) {
              var re = resp.json()
              if (re.ok) {
                this.login_ok = true
              } else {
                this.login_message = re.msg || '未知错误'
                this.login_fail = true
              }
            } else {
              this.login_message = '未知错误'
              this.login_fail = true
            }
          },
          function (re) {
            this.login_message = '通信故障'
            this.login_fail = true
          }
        )
      }
    }
  }
</script>
