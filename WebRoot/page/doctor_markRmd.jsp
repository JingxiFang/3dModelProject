<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
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
                  		<canvas id="myCanvas" min-width="100px" height="600px" 
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


		  <!-- **********************************************************************************************************************************************************
		  RIGHT SIDEBAR CONTENT
		  *********************************************************************************************************************************************************** -->                  
                  
                  <div class="col-lg-3 panel">
                    <!--COMPLETED ACTIONS DONUTS CHART-->
                    
						<h3><a href="doctor_markRmd.html" class="">
                   			<i class="fa fa-arrow-left fa-lg "></i> 返回</a>
                   		</h3>
                    
                      <form class="form-horizontal tasi-form" action="#" method="post">
                                                            
                      <!-- First Action -->
                      <div class="col-lg-12 ds">
                      	<h3>标记形状</h3>
                      	<fieldset style="padding: 5px 25px" class="form-group">
							<label class="radio radio-inline"><input type="radio" id="shapeline" name="shape" value="0" checked="checked" />直线</label>
							<label class="radio radio-inline"><input type="radio" id="shapecircle" name="shape" value="1" />圆</label>
							
							<br><br>
							<span class="btn btn-theme02 col-md-4" onClick="shapeRestore()">撤销</span>
							<span class="col-md-4" ></span>
							<span class="btn btn-theme02 col-md-4" onClick="shapeClear()">全部清除</span>

						</fieldset>
                      </div>
                      
                      
                      
                      <!-- Second Action -->
                      <div class="col-lg-12 ds">
                      	<h3>标记颜色</h3>
                      	<fieldset style="padding: 15px 5px">
							<input id="shapecolor" type="color" name="points" value="#000000" />
						</fieldset>
                      </div>
                      
                      
                      <!-- Third Action -->
                      <div class="col-lg-12 ds">
                      	<h3>标记类型</h3>
                      	<div style="padding: 15px 0px">
							<select class="form-control">
								<option>123</option>
								<option>123</option>
								<option>123</option>
							</select>
                     	</div>
                      </div>
                      
                      
                      <!-- 弹框 -->
                      	<div id="ShowState_Form" class="modal hide">         
							 <div class="modal-header">
								 <button data-dismiss="modal" class="close" type="button"></button>
							 </div>
							   <div class="modal-body">
								<div id="img_state">
									######隐藏部分######
								</div>
							</div>
						</div>
                     	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
							<div class="modal-dialog">
								
								<div class="modal-content">
									<div class="modal-header">
										<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
										<h4 class="modal-title" id="myModalLabel">添加标记</h4>
									</div>
									<div class="modal-body">
										<form class="form-group form-horizontal tasi-form" method="post">
											<p>
											<h4>标记类型</h4>
												<select id="mark_state" class="form-control">
													<option>123</option>
													<option>123</option>
													<option>123</option>
												</select>
										  </p>
										  <p>
										  	<h4>标记备注</h4>
											<textarea rows="3" cols="20" name="describe" id="mark_describe" class="form-control input-infowidth"></textarea>
										  </p>
										  </form>
									</div>
									<div class="modal-footer">
										<button type="button" class="btn btn-default" data-dismiss="modal">取消
										</button>
										<button type="button" class="btn btn-primary" onClick="addState()">
											提交
										</button>
									</div>
								</div><!-- /.modal-content -->
							</div><!-- /.modal -->
						</div>
                      	<!-- 弹框end -->
                      
                      
                      
                      <!-- Fouth Action -->
                      <div class="col-lg-12 text-center">
                      	<div style="padding: 15px 0px">
							<button class="btn btn-theme form-control" onclick="submitMark()">提交</button>
						  
                     	</div>
                      </div>

                      </form>
                      
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
    <script src="assets/js/common-scripts.js"></script>
    
    <script type="text/javascript" src="assets/js/gritter/js/jquery.gritter.js"></script>
    <script type="text/javascript" src="assets/js/gritter-conf.js"></script>

    <!--script for this page-->
    <script src="assets/js/sparkline-chart.js"></script>    
	<script src="assets/js/zabuto_calendar.js"></script>	
	
	<!-- canvas -->
	<script type="text/javascript" src="assets/js/sql.js"></script>
	
	<script type="text/javascript">
        var point1X;
        var point1Y;
        var point1flg = false;
		var restoreflg= true;
        var result;
		var mark_state="";//标记类型
		var mark_describe="";//标记描述
		
		var c = document.getElementById("myCanvas");
        var cxt = c.getContext("2d");
		
		
		/*背景图片*/
		$("#div_canvas").css("background", "url(../<%=request.getParameter("fileurl")%>) no-repeat");
		
		

		/* canvas */
		//宽度
		$("#myCanvas").attr("width", $("#div_canvas").width());
		
		//标记代码
        function cnvs_getCoordinates(e) {
            if (point1flg) {
				if(restoreflg){
					//获取当前位置
					x = e.clientX;
					y = e.clientY;
					//显示当前位置
					document.getElementById("xycoordinates").innerHTML = "Coordinates: (" + x + "," + y + ")";

					cxt.strokeStyle = document.getElementById("shapecolor").value;
					//cxt.save();


					//清除画布画线
					//document.getElementById("myCanvas").width = document.getElementById("myCanvas").width;
					cxt.clearRect(0, 0, 1200, 600);
				}
                
				showShapes();

                //cxt.restore();
            }
        }
		
		/* 记录之前绘制的图片 */
		function showShapes(){
			//根据webdb绘制图形
			result = getShapes();//查询数据库
			for (var i = 0; i < result.rows.length; i++) {
				drawShape(result.rows.item(i)['type'], result.rows.item(i)['startX'], result.rows.item(i)['startY'], result.rows.item(i)['endX'], result.rows.item(i)['endY'], result.rows.item(i)['shapeColor']);
				
			}
			
			
		}

		//清空
        function cnvs_clearCoordinates() {
            document.getElementById("xycoordinates").innerHTML = "";
        }

		//获取焦点
        function setPoint1(e) {
            point1X = e.clientX;
            point1Y = e.clientY;

            point1flg = true;
        }

        function setPoint2(e) {
            //绘制面板
            var c = document.getElementById("myCanvas");
            var cxt = c.getContext("2d");
			

            if (document.getElementById("shapeline").checked) {
                //直线
                drawShape(0, point1X, point1Y, e.clientX, e.clientY, document.getElementById("shapecolor").value);
				$("#myModal").modal();
                addShape(0, point1X, point1Y, e.clientX, e.clientY, 0, 0, document.getElementById("shapecolor").value,mark_state,mark_describe);
            } else {
                //圆
                if (document.getElementById("shapecircle").checked) {
                    drawShape(1, point1X, point1Y, e.clientX, e.clientY, document.getElementById("shapecolor").value);
					$("#myModal").modal();
                    addShape(1, point1X, point1Y, e.clientX, e.clientY, 0, 0, document.getElementById("shapecolor").value,mark_state,mark_describe);
                } else{
					
				}
            }

            point1flg = false;
        }
		
		//添加标记类型
		function addState(){
			//获取标记内容
			mark_state=document.getElementById("mark_state").value;
			mark_describe=document.getElementById("mark_describe").value;
			
			//关闭模态框
         	$("#myModal").modal('hide');
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
		
		//撤销
		function shapeRestore(){
			//清除画布画线
            document.getElementById("myCanvas").width = document.getElementById("myCanvas").width;
            cxt.clearRect(0, 0, 900, 600);
			
			delShape();//sql.js
			result = getShapes();
			
			showShapes();
		
		}
		
		//全部清除
		function shapeClear(){
			delAllShape();
			//清除画布画线
            document.getElementById("myCanvas").width = document.getElementById("myCanvas").width;
            cxt.clearRect(0, 0, 900, 600);
		}
		//向setvlet提交数据
		function submitMark(){
			//result 存放web数据库中的数据
			result = getShapes();
			//创建一个数组 存放
			var arrayMark=new Array();
			//该数组存放每个字段的名字（可以没有）
			var title=new Array();	
			title[0]="type";
			title[1]="startX";				
			title[2]="startY";
			title[3]="endX";
			title[4]="endY"
			title[5]="shapeColor";
			title[6]="markstate";
			title[7]="markdescribe";
			arrayMark[0]=title;
			//循环result中的数据到arrayMark中
			for (var i = 0; i < (result.rows.length)+1; i++) {
				var mark=new Array();
				mark[0]=result.rows.item(i)['type'];
				mark[1]=result.rows.item(i)['startX'];
				mark[2]=result.rows.item(i)['startY'];
				mark[3]=result.rows.item(i)['endX'];
				mark[4]=result.rows.item(i)['endY'];
				mark[5]=result.rows.item(i)['shapeColor'];
				mark[6]=result.rows.item(i)['markstate'];
				mark[7]=result.rows.item(i)['markdescribe'];
				//将每一列添加进arrayMark中
				arrayMark[i+1]=mark;
				//alert(1);
			}
			//异步传送
			$.ajax({
				url : '../MarkServlet',
				type : 'post',//请求类型get或者post
				data : {"shapes":JSON.stringify(arrayMark)},
				success : function(data) {//customer_dialog
					alert('成功');
					location.reload(true);
				},
				error : function() {
					alert('ajax请求失败');
				}
				

			});
			
			
			
		}
		window.onload = function() { 
			showShapes();
		}
		
    </script>
	

  </body>
</html>
