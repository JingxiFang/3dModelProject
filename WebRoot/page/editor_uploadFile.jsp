<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="en">
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
    <link rel="stylesheet" type="text/css" href="assets/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css" href="assets/js/gritter/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="assets/lineicons/style.css">    
    
    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">
    
    <!-- fileinput 文件上传 -->
    <link href="assets/css/fileinput.css" media="all" rel="stylesheet" type="text/css"/>
	<link href="assets/themes/explorer-fas/theme.css" media="all" rel="stylesheet" type="text/css"/>

    <script src="assets/js/chart-master/Chart.js"></script>
    
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
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
              
              	  <p class="centered"><a href="profile.jsp"><img src="assets/img/ui-sam.jpg" class="img-circle" width="60"></a></p>
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
                      <a href="editor_changePwd.jsp" >
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
      
      
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
     
     <%
	     //获取modelid的值
	     String modelId=request.getParameter("modelId");
     %>
 
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">
				<div class="row mt">
          		<div class="col-lg-12">
          			<div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> 图片上传 <%=modelId %></h4>
                  	  
     				<form id="uploadImg" class="form-inline" role="form" enctype="multipart/form-data" 
                      	action="../FileUploadServlet?modelId=<%=modelId%>" method="post">
                      	
							<div class="file-loading">
							  
								<input id="file-zh" name="filepic" type="file" multiple>
							</div>
                      </form>
                  	  
                  	  
                  	  
                     
          			</div><!-- /form-panel -->
          		</div><!-- /col-lg-12 -->
          	</div><!-- /row -->
          </section>
      </section>

      <!--main content end-->
      <!--footer start-->
     <%@ include file="footer.jsp"%>
      <!--footer end-->
  </section>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/jquery-1.8.3.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="assets/js/jquery.scrollTo.min.js"></script>
    <script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="assets/js/jquery.sparkline.js"></script>


    <!--common script for all pages-->
    <script src="assets/js/common-scripts.js"></script>
    
    <script type="text/javascript" src="assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="assets/js/gritter-conf.js"></script>

    <!--script for this page-->
    <script src="assets/js/sparkline-chart.js"></script>    
	<script src="assets/js/zabuto_calendar.js"></script>
	
	<!-- 文件上传 -->	
	<script src="assets/js/fileinput.js" type="text/javascript"></script>
    <script src="assets/js/locales/zh.js" type="text/javascript"></script>
    <script src="assets/themes/fas/theme.js" type="text/javascript"></script>
    <script src="assets/themes/explorer-fas/theme.js" type="text/javascript"></script>
    
    
    <script>
	  /**********    文件上传    *********/
	 
	  $('#file-zh').fileinput({
        theme: 'fas',
        language: 'zh',
        allowedFileExtensions: ['jpg', 'png', 'gif','tif']
	  });
		
		$("#kv-explorer").on('filepreupload', function(event, data, previewId, index) {
		        	$("#uploadImg").submit();
		        });
	  /**********    end 文件上传    *********/
	  </script>
	
	
  

  </body>
</html>
