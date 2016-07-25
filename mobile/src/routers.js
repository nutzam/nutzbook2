module.exports = function (router) {
  router.map({
    '/login': {
      name: 'login',
      component: require('./components/Login.vue')
    },
    '/register': {
      name: 'register',
      component: require('./components/Register.vue')
    },
    '/home': {
      name: 'home',
      component: require('./components/Home.vue'),
      auth: true
    }
  })

  router.afterEach(function (transition) {
    console.log('成功浏览到: ' + transition.to.path)
  })
}
