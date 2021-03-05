<%@page import="domain.FileOfModel"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page language="java" import="service.Model_3dService"%>
<%@ page language="java" import="domain.Model_3d"%>
<%@ page language="java" import="domain.Sign"%>
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
  <div class="">
      <!-- **********************************************************************************************************************************************************
      TOP BAR CONTENT & NOTIFICATIONS
      *********************************************************************************************************************************************************** -->
      
      
      <!-- **********************************************************************************************************************************************************
      MAIN CONTENT
      *********************************************************************************************************************************************************** -->
      <!--main content start-->

           <!-- 查询图像 -->
            <div style="padding: 0px;margin: 0px" class="row mt">
          		<div style="padding: 0px;" class="col-lg-12">
                  
                      <div style="padding: 0px;" class="col-lg-9">
                  		<div id="div_canvas">
                  		<canvas id="myCanvas" min-width="1025px" height="604px"
                  		 onmousemove="cnvs_getCoordinates(event)" onmouseout="cnvs_clearCoordinates()"
							onmousedown="setPoint1(event);" onmouseup="setPoint2(event);">
						  糟了加载不出来
						</canvas>
                   		</div>
                    
						<br />
						<br />
						<div id="xycoordinates">
						</div>
						<div id="point1">
						</div>
						<div id="point2">
						</div>
						
					  </div><!-- /col-lg-9 END SECTION MIDDLE -->

					<div class="col-lg-3 panel">
                    <!--COMPLETED ACTIONS DONUTS CHART-->
                    
						<h3><a href="javascript:history.back(-1)" class="">
                   			<i class="fa fa-arrow-left fa-lg "></i> 返回</a>
                   		</h3>
                    
                          <h3><a href="javascript:mark()" class="">
                   			<i class="fa fa-arrow-right fa-lg "></i> 标记此页</a>
                   		</h3>
                      <h3>标记说明</h3>                                
                      <!-- First Action -->
                     <div id="signShowDiv" name="signShowDiv">
                   
                   
                    </div>
                      <!-- End Action -->
                      <div class="col-lg-12 text-center">
                      	<div style="padding: 15px 0px">
							<span>没有更多内容了...</span>
                     	</div>
                      </div>

                    
                      
                  </div><!-- /col-lg-3 -->
		  
				</div><!-- col-lg-12-->      	
          	</div><!-- /row -->
            <!-- 查询图像 end -->
      
      <!--main content end-->
      <!--footer start-->
      <footer class="site-footer">
          <div class="text-center" style="color: white">
              2019&nbsp;&nbsp;-&nbsp;&nbsp;三维模型图像管理系统
              Copyright © SDNU. All rights reserved.
          </div>
      </footer>
      <!--footer end-->
  </div>

    <!-- js placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
    <script src="assets/js/jquery-1.8.3.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script class="include" type="text/javascript" src="assets/js/jquery.dcjqaccordion.2.7.js"></script>
    <script src="assets/js/jquery.scrollTo.min.js"></script>
    <script src="assets/js/jquery.nicescroll.js" type="text/javascript"></script>
    <script src="assets/js/jquery.sparkline.js"></script>


    <!--common script for all pages-->
  <!--   <script src="assets/js/common-scripts.js"></script> -->
    
    <script type="text/javascript" src="assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="assets/js/gritter-conf.js"></script>

    <!--script for this page-->
    <script src="assets/js/sparkline-chart.js"></script>    
	<script src="assets/js/zabuto_calendar.js"></script>	
	
	<!-- canvas -->
	<script type="text/javascript" src="assets/js/sql.js"></script>
	<script type="text/javascript" src="assets/js/jquery.js"></script>
	<script type="text/javascript" src="assets/js/jquery.mousewheel.js"></script>
	<script type="text/javascript">
       
		
		/*背景图片*/
		var list = [];
		var nowId = 0;
		//标记图像参数
		var shapeParams=[]
		//用于右侧标记显示
		var msg=[];
		//标记集合长度：每个图片对应几个标记
		var signCount=[];
		//标记描述数组
		var desArr=[];
		<%
		List<FileOfModel> fileList = new ArrayList<FileOfModel>();
		fileList = (List<FileOfModel>)request.getAttribute("fileList");
		
		for(int i=0;i<fileList.size();i++){
		%>
			list[<%=i%>]='<%=fileList.get(i).getFile_root()%>';
			msg[<%=i%>]="";
			shapeParams[<%=i%>]="";
			signCount[<%=i%>]=0;
			desArr[<%=i%>]=0;
			//循环遍历标签
			<% 
			int j=0;
			double len=0;
			double S=0;
			for(Sign sign:fileList.get(i).getSignList()){
			     j++;
			     len= Math.sqrt( Math.pow(sign.getEndX()-sign.getStartX(), 2)+Math.pow(sign.getEndY()-sign.getStartY(), 2));
			     if(sign.getSharpType()==0){
			    	 S=len*Math.PI;
			     }
			    	 
			%>
			msg[<%=i%>]+=" <div class='desc'> <div class='col-md-3'>"+
				"<span class='label label-primary' style='font-size:12px'>编号<%=j%></span>"+
          		"</div><div class='col-md-9'>"+
          		"<p><muted>位置：</muted> </p>"+
          		"<p><muted>起点：</muted> <span>startX:<%=sign.getStartX()%></span> <span>startY:<%=sign.getStartY()%></span></p>"+
          		"<p><muted>终点：</muted> <span>endX:<%=sign.getEndX()%></span> <span>endY:<%=sign.getEndY()%></span></p>"+
          		"<p><muted>长度or 半径长度：</muted> <span><%=len%></span> </p>"+
          		
          		"<p><muted>面积：</muted> <span><%=S%></span> </p>"+
          		
          		"<p><b>描述：</b><%=sign.getDescription()%></p>"+
          		"</div><div class='col-md-12 text-center'>"+
          		"<p><span class='badge bg-success'><%=sign.getTag_id()%></span></p>"+
          		"</div>	</div>";
          		desArr[<%=i%>]+=<%=sign.getDescription()%>+"#end.#";
          		shapeParams[<%=i%>]+="<%=sign.getSharpType()%>,<%=sign.getStartX()%>,<%=sign.getStartY()%>,<%= sign.getEndX()%>,<%= sign.getEndY()%>,<%=sign.getSharpColor()%>/";
			<%}%>
			signCount[<%=i%>]=<%=j%>; 
		<%}%>
		
		$("#div_canvas").css("background", "url("+list[0]+") no-repeat");
		
		//切换上一张图片
	 	function preImg() {
	 		var tmp = (Number(nowId) - Number(1));
			if (tmp > -1) {
				$("#div_canvas").css("background", "url("+list[tmp]+") no-repeat");
				nowId = Number(nowId) - Number(1);
				
				//变换标记信息
				document.getElementById("signShowDiv").innerHTML=msg[tmp];
				
				//画出标记
				//1.清空画布
				shapeClearLocal();
				//2.判断是否存在标记  如果存在就画出 如果不存在不执行
				if(signCount[tmp]>0){
					//将字符串分割
					var params=shapeParams[tmp].split("/");
					var t=0;
					//alert(params.length);
					for(t;t<params.length-1;t++){
						strs=params[t].split(",");
						drawShape(strs[0], strs[1], strs[2], strs[3], strs[4], strs[5]);
					}
				}	
			}
		}
		
		//切换下一张图片
		function nextImg() {
			var tmp = (Number(nowId) + Number(1));
			if (tmp < list.length) {
				$("#div_canvas").css("background", "url("+list[tmp]+") no-repeat");
				nowId = Number(nowId) + Number(1);
				document.getElementById("signShowDiv").innerHTML=msg[tmp];
				shapeClearLocal();
				//判断是否存在标记  如果存在就画出 如果不存在不执行
				if(signCount[tmp]>0){
					//将字符串分割
					var params=shapeParams[tmp].split("/");
					var t=0;
					for(t;t<params.length-1;t++){
						strs=params[t].split(",");
						drawShape(strs[0], strs[1], strs[2], strs[3], strs[4], strs[5]);
					}
				}	
			}
		}
		
		/*
		 * 给页面绑定滑轮滚动事件  
		 */
		document.onkeydown = function(e) {
			e = window.event || e;
			switch (e.keyCode) {
			case 37: //左键
				preImg();
				break;
			case 38: //向上键
				preImg();
				break;
			case 39: //右键
				nextImg();
				break;
			case 40: //向下键
				nextImg();
				break;
			default:break;
			}
		}

		/*
		 * 给页面绑定滑轮滚动事件  
		 */
		$(function() {
			$('#div_canvas').mousewheel(function(event, delta) {
				if (delta > 0) //当滑轮向上滚动时  
				{
					preImg();
					console.log('up');
				}
					
				else if (delta < 0) { //当滑轮向下滚动时
					nextImg();
					console.log('down');
				}  

				event.stopPropagation();
				event.preventDefault();
			});
		});

		
		var c = document.getElementById("myCanvas");
        var cxt = c.getContext("2d");
	
		/*背景图片*/
		$("#div_canvas").css("background", "url(assets/img/bg_login.jpg) no-repeat");
		
		/* canvas */
		//宽度
		$("#myCanvas").attr("width", $("#div_canvas").width());
		
	
		//清空
        function cnvs_clearCoordinates() {
            document.getElementById("xycoordinates").innerHTML = "";
        }
     
		//清除当前页面显示的内容
		function shapeClearLocal(){
			
			//清除画布画线
            document.getElementById("myCanvas").width = document.getElementById("myCanvas").width;
            cxt.clearRect(0, 0, 900, 600);
		}
		//绘制
        function drawShape(type, startX, startY, endX, endY, shapeColor) {
            var c = document.getElementById("myCanvas");
            var cxt = c.getContext("2d");

            cxt.fillStyle = shapeColor;
            cxt.strokeStyle = shapeColor;

            if (type == 0) {
                cxt.beginPath();
                cxt.moveTo(startX, startY);
                cxt.lineTo(endX, endY);
                cxt.stroke();
            } else {
                if (type == 1) {
                    cxt.beginPath();
                    cxt.arc(startX, startY, Math.sqrt((startX - endX) * (startX - endX) + (endY - startY) * (endY - startY)), 0, Math.PI * 2, false);
					cxt.stroke();
                    cxt.closePath();
                } else{
					
				}
            }
        }
		
		
		
		function mark(){
			//放入到web数据库中
			//这两个数组的长度应该是相同的
			var params=shapeParams[nowId].split("/");
			var des=desArr[nowId].split("#end.#");
			var t=0;
			for(t;t<params.length-1;t++){
				strs=params[t].split(",");
				
				//drawShape(, );
				addShape(strs[0], strs[1], strs[2], strs[3], strs[4],  0, 0, strs[5],1,des[t]);
			
				
			}
			// function drawShape(type, startX, startY, endX, endY, shapeColor) {
			window.location.href = "page/doctor_markRmd.jsp?fileurl="+list[nowId];
		}
	
		
    </script>
  </body>
</html>