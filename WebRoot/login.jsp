<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>三维模型图像管理系统-登录</title>

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
 <%
 	String parmsg = (String)request.getAttribute("msg");
 	String msg = parmsg==null?"":parmsg;
 	String parname=(String)request.getAttribute("username");
   	String username = parname==null?"":parname;
   	String paruserType=(String)request.getAttribute("userType");
   	String userType = paruserType==null?"":paruserType;
   %>
	  <div id="login-page">
	  	<div class="container">
	  	
    		<!-- 提示文本 -->
     		<div class="col-md-8">
     			<h2 style="margin-top: 100px;color:white"><i class="fa fa-picture-o" aria-hidden="true"></i> 三维模型图像管理系统</h2>
     			<hr style="color: aliceblue;border-color: aliceblue">
     			<h5 style="margin-top: 30px;color:white">图像识别&nbsp;&nbsp;&nbsp;图像处理&nbsp;&nbsp;&nbsp;图像xx</h5>
			</div>
     		<!-- 提示文本 end -->
     		
      		<!-- 登录框 -->
	      	<div class="col-md-4">
		      <form name="formT" class="form-login" action="./LoginServlet" method="post">
		        <h2 class="form-login-heading">请登录</h2>
		        <div class="login-wrap">
					<div id="errorWrapper" style="size:12px;color:crimson;padding-bottom:5px;padding-left:1px;height:20px;margin:0;">
					<%=msg%></div>
		            <input type="text" class="form-control" placeholder="用户名" autofocus id="username" name="username" value="<%=username%>" onblur="checkusername()">
		            <div id="hidusername" class="hiddenword">* 您输入的用户名不能为空</div>
		            <br>
		            <input type="password" class="form-control" placeholder="密码" id="password" name="password" value="" onblur="checkpwd()">
		            <div id="hidpwd" class="hiddenword">* 您输入的密码不能为空</div> 
		            <div class="checkbox" style="margin-top:12px;height:26px;"> 
						<label style="padding-left:1px;"> 
							<input type="radio" name="userType" value="user" 
								<%=userType.equals("user")?"checked":""%> checked>&nbsp;用&nbsp;&nbsp;户
						</label> 
						<label> 
							<input type="radio" name="userType" value="manager" 
								<%=userType.equals("manager")?"checked":""%>>&nbsp;管&nbsp;理&nbsp;员
						</label>
						<label style="float:right;padding-top:1.8px;"> 
							<a data-toggle="modal" href=""> 忘记密码&nbsp;?</a>
						</label>
					</div>
					<button class="btn btn-theme btn-block" type="button" onclick="checkSignin()"><i class="fa fa-lock"></i>&nbsp;&nbsp;登录</button>
		            <hr>
		            
		            <div class="login-social-link centered">
		            <p>(后期加)</p>
	                <p>(通过其他方式登录系统</p>
		                <button class="btn btn-primary" type="submit"><i class="fa fa-qq"></i> &nbsp;QQ&nbsp;&nbsp;</button>
		                <button class="btn btn-success" type="submit"><i class="fa fa-weixin"></i> &nbsp;微信&nbsp;&nbsp;</button>
		                <button class="btn btn-warning" type="submit"><i class="fa fa-weibo"></i> &nbsp;微博&nbsp;&nbsp;</button>
		            </div>
		            <div class="registration">
		                还没有注册&nbsp;?&nbsp;<br/>
		                <a class="" href="signup.jsp">
		                    点击创建一个账号
		                </a>
		            </div>
		
		        </div>
		        <!-- 登录框 end -->
		
		          <!-- Modal -->
		          <div aria-hidden="true" aria-labelledby="myModalLabel" role="dialog" tabindex="-1" id="myModal" class="modal fade">
		              <div class="modal-dialog">
		                  <div class="modal-content">
		                      <div class="modal-header">
		                          <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
		                          <h4 class="modal-title">忘记密码 ?</h4>
		                      </div>
		                      <div class="modal-body">
		                          <p>请输入您的邮箱来重置密码.</p>
		                          <input type="text" name="email" placeholder="邮箱地址" autocomplete="off" class="form-control placeholder-no-fix">
		
		                      </div>
		                      <div class="modal-footer">
		                          <button data-dismiss="modal" class="btn btn-default" type="button">取消</button>
		                          <button class="btn btn-theme" type="button">发送</button>
		                      </div>
		                  </div>
		              </div>
		          </div>
		          <!-- modal -->
		
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
        $.backstretch("assets/img/bg_login.jpg", {speed: 500});
		
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
		
		//密码长度验证
		function checkpwd(){
			var password=document.getElementById("password").value.toString();
			if(password==""){
				document.getElementById("hidpwd").style.display="block";
				return false;
				}
			else{
				document.getElementById("hidpwd").style.display="none";
				return true;
				}
			}
		
		//注册验证
		function checkSignin(){
				if(checkusername()&&checkpwd()){
					formT.submit();
				}else{
					checkusername();
					checkpwd();
				}				
		}
    </script>


  </body>
</html>
