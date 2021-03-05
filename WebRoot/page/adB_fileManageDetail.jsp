<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-CN">
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
    <link rel="stylesheet" type="text/css" href="assets/css/zabuto_calendar.css">
    <link rel="stylesheet" type="text/css" href="assets/js/gritter/css/jquery.gritter.css" />
    <link rel="stylesheet" type="text/css" href="assets/lineicons/style.css">    
    
    <!-- Custom styles for this template -->
    <link href="assets/css/style.css" rel="stylesheet">
    <link href="assets/css/style-responsive.css" rel="stylesheet">

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
              
              	  <p class="centered"><a href=""><img src="assets/img/ui-sam.jpg" class="img-circle" width="60"></a></p>
              	  <center><span class="badge bg-info">B类管理员</span></center>
					<h5 class="centered">${manager.m_name}</h5>
              	  
              	  <li class="mt">
                      <a href="adB_index.jsp">
                          <i class="fa fa-home"></i>
                          <span>主页</span>
                      </a>
                  </li>	
                  <li class="sub-menu">
                      <a class="active" href="../SearchModel_3d">
                          <i class="fa fa-file-text" aria-hidden="true"></i>
                          <span>三维图像文件管理</span>
                      </a>
                  </li>
				  <li class="sub-menu">
                      <a href="adB_labelManage.jsp">
                          <i class="fa fa-tags" aria-hidden="true"></i>
                          <span>标签字典管理</span>
                      </a>
                  </li>
                  <li class="sub-menu">
                      <a href="adB_changePwd.jsp" >
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

           
             
             <!-- 图像修改 -->
             <div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> 修改图像信息</h4>
                      <form class="form-horizontal style-form" method="get">
                          <div class="form-group">
                              <label class="col-lg-2 col-sm-2 control-label">图像编号：</label>
                              <div class="col-lg-10">
                                  <p class="form-control-static">email@example.com</p>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">图像名称：</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control">
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">部位：</label>
                              <div class="col-sm-10">
                                  <select class="form-control">
									  <option>腿</option>
									  <option>腰</option>
									  <option>胳膊</option>
								  </select>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">标签：</label>
                              <div class="col-sm-10">
                                  <label>
									  <input name="pic_label" type="checkbox" checked class="list-child" value=""  />
									  <span class="badge bg-success">标签1</span>
                                  </label>
                                  
                                  <label>
									  <input name="pic_label" type="checkbox" checked class="list-child" value=""  />
									  <span class="badge bg-success">标签2</span>
                                  </label>
                                  
                                  <label>
									  <input name="pic_label" type="checkbox" class="list-child" value=""  />
									  <span class="badge bg-success">标签3</span>
                                  </label>
                                  
                                  <label>
									  <input name="pic_label" type="checkbox" class="list-child" value=""  />
									  <span class="badge bg-success">标签4</span>
                                  </label>
                              </div>
                          </div>
                             <button type="submit" class="btn btn-primary ">提  交</button>
                      </form>
                  </div>
          		</div><!-- col-lg-12-->      	
          	</div><!-- /row -->
               <!-- 图像修改 end -->
               
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
	
	
  

  </body>
</html>
