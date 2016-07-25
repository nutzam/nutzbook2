<template>
  <div class="app">
    <p>
      {{ welcome }}
    </p>
    <a v-link="{ name: 'login' }">登陆</a>
    <a v-link="{ name: 'register' }">注册</a>
    <a v-link="{ name: 'home' }">用户中心</a>
    <router-view></router-view>
    <p>
  </div>
</template>

<script>
  export default {
    components: {
    },
    data () {
      return {
        welcome: '欢迎使用Nutzbook V2'
      }
    }
}
</script>

<style>
html {
  height: 100%;
}
@import '~vux/dist/vux.css';
</style>
