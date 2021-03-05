<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="Dashboard">
    <meta name="keyword" content="Dashboard, Bootstrap, Admin, Template, Theme, Responsive, Fluid, Retina"> 

    <title>三维模型图像管理系统</title>

    <!-- Bootstrap core CSS -->
    <link href="assets/css/bootstrap.css" rel="stylesheet">
    <!--external css-->
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="assets/js/bootstrap-datepicker/css/datepicker.css" />
    <link rel="stylesheet" type="text/css" href="assets/js/bootstrap-daterangepicker/daterangepicker.css" />
        
    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <script type="text/javascript">
	function clearLabel() {
		$('#newpwd1').val('');
		$('#newpwd').val('');
		$('#oldpwd').val('');
	}

	
    function check() {
		var oldpwd = document.getElementById("oldpwd").value;
		var newpwd1 = document.getElementById("newpwd1").value;
		var newpwd = document.getElementById("newpwd").value;

		if (newpwd1 != newpwd) {
			document.getElementById("msg").innerHTML = "新密码两次不一致！";
			return false;
		}else if(newpwd == "" || newpwd1 == ""|| oldpwd == ""){
			document.getElementById("msg").innerHTML = "请输入密码！";
			return false;
		}else if(newpwd.length < 6){
			document.getElementById("msg").innerHTML = "密码长度必须大于5！";
			return false;
		}else if(newpwd.length > 12){
			document.getElementById("msg").innerHTML = "密码长度必须小于12！";
			return false;
		}
		return true;
		}
    
    function commit(){
    	if(check()){
    		document.getElementById("updateform").action = "${pageContext.request.contextPath}/userupdatepwd";
    		document.getElementById("updateform").submit();
    		
    		
    	}
    	return true;
    }

	</script>
  </head>

  <body>

  <section id="container" >
      <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
      <!--header start-->
      <%@ include file="header.jsp"%>
      <!--header end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN SIDEBAR MENU
      *********************************************************************************************************************************************************** -->
      <!--sidebar start-->
      <aside>
          <div id="sidebar"  class="nav-collapse ">
              <!-- sidebar menu start-->
              <ul class="sidebar-menu" id="nav-accordion">
              
              	  <p class="centered"><a href=""><img src="assets/img/ui-sam.jpg" class="img-circle" width="60"></a></p>
              	  <center><span class="badge bg-success">用户编辑员</span></center>
					<h5 class="centered">${userInfo.u_name}</h5>
              	  	
              	  	<li class="mt">
                      <a href="editor_index.jsp">
                          <i class="fa fa-home"></i>
                          <span>主页</span>
                      </a>
                  </li>
              	  		
                  <li class="sub-menu">
                      <a href="../SearchModel_3d">
                         <i class="fa fa-eye" aria-hidden="true"></i>
                          <span>图像浏览</span>
                      </a>
                  </li>

				   <li class="sub-menu">
                      <a class="active" href="../SearchModel_3d" >
                          <i class="fa fa-picture-o" aria-hidden="true"></i>
                          <span>图像选择上传</span>
                      </a>
                  </li>
                  
                  <li class="sub-menu">
                      <a href="../SearchModel_3d" >
                          <i class="fa fa-key" aria-hidden="true"></i>
                          <span>图像编辑</span>
                      </a>
                  </li>
                 
                  
                  <li class="sub-menu">
                      <a class="active" href="editor_changePwd.jsp" >
                          <i class="fa fa-cogs"></i>
                          <span>修改密码</span>
                      </a>
                  </li>
                  
                  <li class="sub-menu">
                      <a href="javascript:;" >
                          <i class="fa fa-tasks"></i>
                          <span>待扩展</span>
                      </a>
                      <ul class="sub">
                          <li><a  href="">子选项1</a></li>
                          <li><a  href="">子选项2</a></li>
                      </ul>
                  </li>
              </ul>
              <!-- sidebar menu end-->
          </div>
      </aside>
      <!--sidebar end-->
      
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
          	<!-- INPUT MESSAGES -->
          	
          	<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
                  	  <h3 class="mb"><i class="fa fa-angle-right"></i>修改密码</h3>
                          <form id="updateform" method="post">
                     
                              <div class="form-group">
                                  <label class="col-sm-2 control-label col-lg-2">旧密码：</label>
                                  <div class="col-lg-10">
                                      <input id="oldpwd" name="oldpwd" type="password" class="form-control">
                                  </div>
                              </div>
 
                              <div class="form-group">
                                  <label class="col-sm-2 control-label col-lg-2">新密码：</label>
                                  <div class="col-lg-10">
                                      <input id="newpwd1" name="newpwd1" type="password" class="form-control">
                                  </div>
                              </div>

                              <div class="form-group">
                                  <label class="col-sm-2 control-label col-lg-2">重复新密码：</label>
                                  <div class="col-lg-10">
                                      <input id="newpwd" name="newpwd" type="password" class="form-control">
                                      <span id="msg" style="color: red;font-size:14px">${errMsg}</span>
                                  </div>
                              </div>

                              <div class=" add-task-row">
                                  <a  id="commit" href="javascript:commit()"class="btn btn-primary">确认修改</a>
                                  <a  class="btn btn-default pull-right" onclick="clearLabel()">清空输入</a>
                              </div>

                         </form>
          			</div><!-- /form-panel -->
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row -->
        	 
		</section><! --/wrapper -->
      </section><!-- /MAIN CONTENT -->

      <!--main content end-->
      <!--footer start-->
      <%@ include file="footer.jsp"%>
      <!--footer end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="assets/js/jquery.scrollTo.min.js"></script>
    <script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>


    <!--common script for all pages-->
    <script src="assets/js/common-scripts.js"></script>

    <!--script for this page-->
    <script src="assets/js/jquery-ui-1.9.2.custom.min.js"></script>

	<!--custom switch-->
	<script src="assets/js/bootstrap-switch.js"></script>
	
	<!--custom tagsinput-->
	<script src="assets/js/jquery.tagsinput.js"></script>
	
	<!--custom checkbox & radio-->
	
	<script type="text/javascript" src="assets/js/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
	<script type="text/javascript" src="assets/js/bootstrap-daterangepicker/date.js"></script>
	<script type="text/javascript" src="assets/js/bootstrap-daterangepicker/daterangepicker.js"></script>
	
	<script type="text/javascript" src="assets/js/bootstrap-inputmask/bootstrap-inputmask.min.js"></script>
	
	
	<script src="assets/js/form-component.js"></script>    
    
    
  <script>
      //custom select box

      $(function(){
          $('select.styled').customSelect();
      });

  </script>

  </body>
</html>
