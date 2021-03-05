<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina">

    <title>三维模型图像管理系统-注册</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        
    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">
    <style>
		.hiddenword{
			margin:0px;
			padding:0px;
			color:crimson;
			font-size:12px;
			display:none;
		}
	  </style>

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>

  <body>
  
      <!-- **********************************************************************************************************************************************************
      主部分
      *********************************************************************************************************************************************************** -->
	  <div id="login-page">
	  	<div class="container">
      		<!-- 注册框 -->
	      	<div class="col-md-12">
		      <form id="form_register" class="form-login" method="post" action="SignUpServlet">
		        <h2 class="form-login-heading">请注册</h2>
		        <div class="login-wrap">
		            <input id="username" name="username" type="text" class="form-control" placeholder="用户名" onfocus="setrepeat()" onblur="checkrepeat()" autofocus >
		            <input type="hidden" id="repeat" value="">
		            <div id="repeatusername" class="hiddenword" style="display:block"></div>
		            <div id="hidusername" class="hiddenword">* 您输入的用户名不能为空</div>
		            <br>
		            <input id="realname" name="realname" type="text" class="form-control" placeholder="姓名" onblur="checkrealname()">
		            <div id="hidrealname" class="hiddenword">* 您输入的姓名不能为空</div>
		            <br>
		            <input id="password" name="password" type="password" class="form-control" placeholder="密码" onblur="checkpwd()">
		            <div id="hidpwd" class="hiddenword">* 您输入的密码长度应在6-12位之间</div>
		            <br>
		            <input id="ispassword" name="ispassword" type="password" class="form-control" placeholder="确认密码" onblur="checkrepwd()">
		            <div id="hidispwd" class="hiddenword">* 您输入的密码不一致</div>
		            <br>
		            <input id="email" name="email" type="email" class="form-control" placeholder="邮箱" onblur="checkemail()">
		            <div id="hidemail" class="hiddenword">* 您输入的邮箱有误</div>
		            <br>
		            <button class="btn btn-theme btn-block" type="button" onclick="checkSignup()"><i class="fa fa-lock"></i>&nbsp;&nbsp;注册</button>
		            <hr>
		            <div class="registration">
		                <a class="" href="login.jsp">
		                    	点击返回登录
		                </a>
		            </div>
		        </div>
		      </form>	 
		      </div> 	
	  	</div>
	  </div>
    
    <!--footer start-->
      <footer class="footer" style="margin-top:30px">
          <div class="text-center" style="color: white">
              2019&nbsp;&nbsp;-&nbsp;&nbsp;三维模型图像管理系统
              Copyright © SDNU. All rights reserved.
          </div>
      </footer>
      <!--footer end-->

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>

    <!--BACKSTRETCH-->
    <!-- You can use an image of whatever size. This script will stretch to fit in any screen size.-->
    <script type="text/javascript" src="assets/js/jquery.backstretch.min.js"></script>
    
    <script>
		//背景图片
        $.backstretch("assets/img/bg_signup.jpg", {speed: 500});
		
		/*
		 * 输入正确性验证
		 */
		 
		//用户名验证
		function checkusername(){
			var username=document.getElementById("username").value.toString();
			if(username==""){
				document.getElementById("hidusername").style.display="block";
				return false;
			}
			else{
				document.getElementById("hidusername").style.display="none";
				return true;
			}
			
		}
		//姓名验证
		function checkrealname(){
			var realname=document.getElementById("realname").value.toString();
			if(realname==""){
				document.getElementById("hidrealname").style.display="block";
				return false;
			}
			else{
				document.getElementById("hidrealname").style.display="none";
				return true;
			}
			
		}
		
		//邮箱验证
		function checkemail(){
			var email=document.getElementById("email").value.toString(); 
			var str1=/^([0-9A-Za-z\-_\.]+)@([0-9a-z]+\.[a-z]{2,3}(\.[a-z]{2})?)$/g;
			if(!str1.test(email)){
				document.getElementById("hidemail").style.display="block";
				return false;
				}
			else{
				document.getElementById("hidemail").style.display="none";
				return true;
				}
			}
		
		//密码长度验证
		function checkpwd(){
			var password=document.getElementById("password").value.toString();
			if(password.length<6||password==""){
				document.getElementById("hidpwd").style.display="block";
				return false;
				}
			else{
				document.getElementById("hidpwd").style.display="none";
				return true;
				}
			}
		
		//重新输入密码验证
		function checkrepwd(){
			var password=document.getElementById("password").value.toString();
			var ispassword=document.getElementById("ispassword").value.toString();
			if(password!=ispassword){
				document.getElementById("hidispwd").style.display="block";
				return false;
				}
			else{
				document.getElementById("hidispwd").style.display="none";
				return true;
				}
			}
		
		//注册验证
		function checkSignup(){
			var repeat = $("#repeat").val();
			if(repeat=="true"){	// 用户名不重复时才能提交
				if(checkusername()&&checkpwd()&&checkrepwd()&&checkemail()){
					form_register.submit();
				}else{
					checkusername();
					checkrealname();
					checkpwd();
					checkrepwd();
					checkemail();
				}				
			}
		}
		
		clearreapt()
		
		//设置隐藏文本框中信息
		function setrepeat(){
			$("#repeat").val(false);
		}
		
		//用户名重复验证
		function checkrepeat(){
			if(checkusername()){
				var username = document.getElementById("username").value;
				$.ajax({
					url : '${pageContext.request.contextPath}/SignUpServlet',
					type : 'post',
					data : {"username":username,"method":"ajax"},	// 请求的时候向后台发送的参数
					success : function(data) {
						document.getElementById("hidusername").style.display="none";
						if(data != ""){ // 用户名重复
							$("#repeat").val(false);
							$("div #repeatusername").html(data);
							return false;
						}else{ // 用户名不重复
							$("#repeat").val(true);
							$("div #repeatusername").html("");
							return true;							
						}
					},
					error : function() {
						alert('ajax请求失败');
						return false;
					}
				});				
			}
		}
    </script>
  </body>
</html>

