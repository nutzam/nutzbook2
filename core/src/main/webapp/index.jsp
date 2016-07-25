<%--
  Created by IntelliJ IDEA.
  User: wendal
  Date: 2016/7/21
  Time: 23:37
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hi</title>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta name="referrer" content="always"/>
</head>
<body>
<div id="app">
    <app-nav></app-nav>
    <app-view>
        <app-sidebar></app-sidebar>
        <app-content></app-content>
    </app-view>
</div>
<div id="loginform">
    <form>
        <input type="text" v-model="username">
        <input type="password" v-model="password">
        <input type="text" v-model="captcha">
        <img src="${base}/base/tools/captcha" v-on:click="doCaptcha">
    </form>
    <button v-on:click="doLogin">登陆</button>
</div>
</body>
<script type="application/javascript">
    var urlbase = "${base}";
    if (console)
        console.info("hi, urlbase="+urlbase);
</script>
<script src="${base}/rs/js/jquery-3.1.0.js"></script>
<script src="${base}/rs/js/vue.js"></script>
<script src="${base}/rs/js/index.js"></script>
<script type="application/javascript">
</script>
</html>
