<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="domain.Model_3d"%>
<%@ page language="java" import="service.Model_3dService"%>
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
                      <a class="active" href="../SearchModel_3d">
                         <i class="fa fa-eye" aria-hidden="true"></i>
                          <span>图像浏览</span>
                      </a>
                  </li>

				  <li class="sub-menu">
                      <a href="../SearchModel_3d"" >
                          <i class="fa fa-picture-o" aria-hidden="true"></i>
                          <span>图像选择上传</span>
                      </a>
                  </li>
                  
                  <li class="sub-menu">
                      <a href="../SearchModel_3d"" >
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
      <!--sidebar end-->
      
      
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->
      <section id="main-content">
          <section class="wrapper">

           <!-- 查询图像 -->
            <div class="row mt">
          		<div class="col-lg-12">
                  <div class="content-panel">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> 图像查找</h4>
                      <form id="formUpd" class="form-horizontal style-form" method="post" action="../SearchModel_3d">
                          <div class="form-group">
                             <div class="col-md-1"><!-- 用来补充空隙 --></div>
                             <div class="col-md-9">
                                  	<input id="username" type="text" class="form-control" name="username" placeholder="输入用户名字" value="">
                              </div>
                              <div class="col-md-2">
                                   <input type="submit" class="btn btn-primary" value="查找"/>
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
            <!-- 查询图像 end -->
             
             <!-- 图像列表 -->
             
              <div class="row">
                  <div class="col-lg-12 main-chart">
                      <div class="row mt">
                      <!-- SERVER STATUS PANELS -->
                  <div class="col-md-12">
                      <div class="content-panel">
                          <table class="table table-striped table-advance table-hover">
	                  	  	  
                              <thead>
                              <tr>
                                
                                  <th><i class="fa fa-bullhorn"></i> 模型编号</th>
                                  <th class="hidden-phone"><i class="fa fa-question-circle"></i> 病人名字</th>
                                   <th class="hidden-phone"><i class="fa fa-question-circle"></i> 年龄</th>
                                    <th class="hidden-phone"><i class="fa fa-question-circle"></i> 性别</th>
                                   <th class="hidden-phone"><i class="fa fa-phone" aria-hidden="true"></i> 联系方式</th>
                                  <th class="hidden-phone"><i class="fa fa-clock-o" aria-hidden="true"></i> 上传时间</th>
                                  <th><i class="fa fa-bookmark"></i> 操作</th>
                                  
                              </tr>
                              </thead>
                              <tbody>
	 				<%
	 				
	          		
			          		
	 						List<Model_3d> model_3dList = new ArrayList<Model_3d>();
							
							model_3dList =(List<Model_3d>)session.getAttribute("modelList");
						
							for(int i=0;i<model_3dList.size();i++){
								Model_3d model_3d = model_3dList.get(i);
	 				%>
                         
                              <tr>
                                  <td><%=model_3d.getModel_id() %></td>
                                  <td><%=model_3d.getUpload_user_id_obj().getU_name()%></td>
                                  <td><%=model_3d.getUpload_user_id_obj().getU_age()%></td>
                                  <td><%=model_3d.getUpload_user_id_obj().getU_sex()%></td>
                                  <td><%=model_3d.getUpload_user_id_obj().getU_phone()%></td>
                                  <td><%=model_3d.getUpload_time() %></td>
                                  <td>
                                      <button class="btn btn-primary btn-xs"  data-toggle="tooltip" data-placement="bottom" title="点击编辑该模型" onClick="picReUpload(<%=model_3d.getModel_id()%>)"><i class="fa fa-tag" aria-hidden="true"></i></button><!-- 需要一个铅笔图标 -->
                                      <button class="btn btn-info btn-xs"   data-toggle="tooltip" data-placement="bottom" title="点击下载该模型" onClick="picDown(<%=model_3d.getModel_id()%>)"><i class="fa fa-download" aria-hidden="true"></i></button> <!-- 需要一个下载图标 -->
                                  </td>
                              </tr>
                   <%} %>   
                     </tbody>
                          </table>
                      </div><!-- /content-panel -->
                  </div><!-- /col-md-12 -->
                   	
                    </div><!-- /row -->
                    
                  </div><!-- /col-lg-9 END SECTION MIDDLE -->
                  
              </div><!--/row -->
              
               <!-- 图像列表 end -->
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
	
	<script>
	//图片放大  
	function picPlus(source){
		  $("#myModal").find("#model_img").html("<image src='"+source+"' class='carousel-inner img-responsive img-rounded' />");
          $("#myModal").modal();
	  }
	  //图片下载
	   function picDown(modelId){
		 // alert(modelId);
		  window.open("../FileDownServlet?modelId="+modelId);				
	  }
	  //图片线下编辑后上传
	  function picReUpload(modelID){
		  //获取图片的model 
		  window.location.href="editor_uploadFile.jsp?modelId="+modelID;
		  //跳转界面
		  
	  }
	  </script>
	
  

  </body>
</html>
