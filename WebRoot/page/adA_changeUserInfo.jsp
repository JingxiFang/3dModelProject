<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page language="java" import="service.UserInfoService"%>
<%@ page language="java" import="domain.UserInfo"%>
<%@ page language="java" import="javax.servlet.http.HttpSession"%>
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
              	  <center><span class="badge bg-important">A类管理员</span></center>
					<h5 class="centered">${manager.m_name}</h5>
              	  	
              	  	<li class="mt">
                      <a href="adA_index.jsp">
                          <i class="fa fa-home"></i>
                          <span>主页</span>
                      </a>
                  </li>	
                  
                  <li class="sub-menu">
                      <a class="active" href="../SearchUserInfo">
                          <i class="fa fa-user" aria-hidden="true"></i>
                          <span>用户管理</span>
                      </a>
                  </li>
                  
                  <li class="sub-menu">
                      <a href="adA_changePwd.jsp" >
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
      		<%
				List<UserInfo> userList = new ArrayList<UserInfo>();
		  		UserInfoService userService = new UserInfoService();
		  		String u_id=request.getParameter("u_id");
				userList = userService.findUserByid(u_id);
			%>
      <section id="main-content">
          <section class="wrapper">
          	<!-- BASIC FORM ELELEMNTS -->
          	<div class="row mt">
          		<div class="col-lg-12">
                  <div class="form-panel">
                  	  <h4 class="mb"><i class="fa fa-angle-right"></i> 修改用户信息</h4>
                      <form class="form-horizontal style-form" method="post" action="UpdateUserInfoByid">
                      			<% for(int i=0;i<userList.size();i++){
                                	  UserInfo user=userList.get(i);
                                  %>
                          <div class="form-group">
                              <label class="col-lg-2 col-sm-2 control-label">账号：</label>
                              <div class="col-lg-10">
                                  <p class="form-control-static">
                                  <%=user.getU_id() %>
                                  <input type="hidden" name="u_id" value="<%=user.getU_id() %>">
                                  </p>
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">姓名：</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" name="u_name" value="<%=user.getU_name() %>">
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">邮箱：</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" name="u_email" value="<%=user.getU_email() %>">
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">电话：</label>
                              <div class="col-sm-10">
                                  <input type="text" class="form-control" name="u_phone" value="<%=user.getU_phone() %>">
                              </div>
                          </div>
                          <div class="form-group">
                              <label class="col-sm-2 col-sm-2 control-label">权限：</label>
                              <div class="col-sm-10">
                                  <select class="form-control" name="u_state">
									  <option value="4" <%=user.getU_state()==4?"selected":"" %>>病人</option>
									  <option value="1" <%=user.getU_state()==1?"selected":"" %>>医生</option>
									  <option value="2" <%=user.getU_state()==2?"selected":"" %>>普通技术人员</option>
									  <option value="3" <%=user.getU_state()==3?"selected":"" %>>高级技术人员</option>
								  </select>
                              </div>
                          </div>
                          <%} %>
                          
                             <button type="submit" class="btn btn-primary ">提  交</button>
                             <button type="button" class="btn btn-default pull-right" onclick="javascrtpt:window.location.href='SearchUserInfo'">取  消</button>
                      </form>
                  </div>
          		</div><!-- col-lg-12-->      	
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
