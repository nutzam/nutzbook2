$(function () {
   var vue_login = new Vue({
       el : "#loginform",
       data : {},
       methods : {
           doLogin : function () {
               console.log("doLogin");
           },
           doCaptcha :function () {
               console.log("doCaptcha");
           }
       }
   }); 
});