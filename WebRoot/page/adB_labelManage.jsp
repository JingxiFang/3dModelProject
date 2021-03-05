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
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" crossorigin="anonymous">
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
                      <a href="../SearchModel_3d">
                          <i class="fa fa-file-text" aria-hidden="true"></i>
                          <span>三维图像文件管理</span>
                      </a>
                  </li>
				  <li class="sub-menu">
                      <a class="active" href="adB_labelManage.jsp">
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

          <h3><i class="fa fa-angle-right"></i> 标签管理</h3>
           <!-- 查询标签 -->
            <div class="row mt">
          		<div class="col-lg-12">
                  <div class="content-panel">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> 标签查找</h4>
                      <form id="formUpd" class="form-horizontal style-form" method="post" action="#">
                          <div class="form-group">
                             <div class="col-md-1"><!-- 用来补充空隙 --></div>
                              <div class="col-md-9">
                                  	<input id="picid" type="text" class="form-control" name="picid" placeholder="输入标签名称">
                              </div>
                              <div class="col-md-2">
                                  <button type="button" class="btn btn-primary" onClick="">点击查找</button>
                              </div>
                          </div>
                          <div id="divalert" style="display:none" class="alert alert-danger alert-dismissable">
								<button type="button" class="close" data-dismiss="alert"
										aria-hidden="true">
									&times;
								</button>
								错误！请重新输入。
							</div>
                         
                      </form>
                  </div>
          		</div><!-- col-lg-12-->      	
          	</div><!-- /row -->
            <!-- 查询标签 end -->
             
             <!-- 标签列表 -->
              <div class="row mt">
              <div class="col-lg-12">
                      <div class="content-panel">
						  <h4><i class="fa fa-angle-right"></i> 标签列表</h4>
                          <section id="no-more-tables">
                              <table class="table table-bordered table-striped table-condensed cf">
                                  <thead class="cf">
                                  <tr>
                                      <th>编号</th>
                                      <th>标签名</th>
									  <th>标签含义</th>
                                      <th>操作</th>
                                  </tr>
                                  </thead>
                                  <tbody>
                                  <tr>
                                      <td data-title="编号">1</td>
                                      <td data-title="标签名">标签1</td>
                                      <td data-title="标签含义">标签1用来巴拉巴拉</td>
                                      <td>
                                      <a class="btn btn-primary btn-xs" onclick="" href="adB_labelManageDetail.jsp">
                                      	<i class="fa fa-cog" aria-hidden="true" ></i>
										  </a>
                                      <button class="btn btn-danger btn-xs" onclick="isDelete(deluserid)">
                                       	<i class="fa fa-times" aria-hidden="true"></i>
                                       	</button>
                                      </td>
                                  </tr>
                                  <tr>
                                      <td data-title="编号">2</td>
                                      <td data-title="标签名">标签1</td>
                                      <td data-title="标签含义">标签1用来巴拉巴拉</td>
                                      <td>
                                      <a class="btn btn-primary btn-xs" onclick="" href="adB_labelManageDetail.jsp">
                                      	<i class="fa fa-cog" aria-hidden="true" ></i>
										  </a>
                                      <button class="btn btn-danger btn-xs" onclick="isDelete(deluserid)">
                                       	<i class="fa fa-times" aria-hidden="true"></i>
                                       	</button>
                                      </td>
                                  </tr>
                                  <tr>
                                      <td data-title="编号">3</td>
                                      <td data-title="标签名">标签1</td>
                                      <td data-title="标签含义">标签1用来巴拉巴拉</td>
                                      <td>
                                      <a class="btn btn-primary btn-xs" onclick="" href="adB_labelManageDetail.jsp">
                                      	<i class="fa fa-cog" aria-hidden="true" ></i>
										  </a>
                                      <button class="btn btn-danger btn-xs" onclick="isDelete(deluserid)">
                                       	<i class="fa fa-times" aria-hidden="true"></i>
                                       	</button>
                                      </td>
                                  </tr>
                                  <tr>
                                      <td data-title="编号">4</td>
                                      <td data-title="标签名">标签1</td>
                                      <td data-title="标签含义">标签1用来巴拉巴拉</td>
                                      <td>
                                      <a class="btn btn-primary btn-xs" onclick="" href="adB_labelManageDetail.jsp">
                                      	<i class="fa fa-cog" aria-hidden="true" ></i>
										  </a>
                                      <button class="btn btn-danger btn-xs" onclick="isDelete(deluserid)">
                                       	<i class="fa fa-times" aria-hidden="true"></i>
                                       	</button>
                                      </td>
                                  </tr>
                                  <tr>
                                      <td data-title="编号">5</td>
                                      <td data-title="标签名">标签1</td>
                                      <td data-title="标签含义">标签1用来巴拉巴拉</td>
                                      <td>
                                      <a class="btn btn-primary btn-xs" onclick="" href="adB_labelManageDetail.jsp">
                                      	<i class="fa fa-cog" aria-hidden="true" ></i>
										  </a>
                                      <button class="btn btn-danger btn-xs" onclick="isDelete(deluserid)">
                                       	<i class="fa fa-times" aria-hidden="true"></i>
                                       	</button>
                                      </td>
                                  </tr>
                                  <tr>
                                      <td data-title="编号">6</td>
                                      <td data-title="标签名">标签1</td>
                                      <td data-title="标签含义">标签1用来巴拉巴拉</td>
                                      <td>
                                      <a class="btn btn-primary btn-xs" onclick="" href="adB_labelManageDetail.jsp">
                                      	<i class="fa fa-cog" aria-hidden="true" ></i>
										  </a>
                                      <button class="btn btn-danger btn-xs" onclick="isDelete(deluserid)">
                                       	<i class="fa fa-times" aria-hidden="true"></i>
                                       	</button>
                                      </td>
                                  </tr>
                                  </tbody>
                              </table>
                          </section>
                      </div><!-- /content-panel -->
                  </div><!-- /col-lg-12 -->
              </div><!-- /row -->
               <!-- 标签列表 end -->
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
