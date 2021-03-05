var db = openDatabase('webShapeDB', '1.0', 'ShapeDB', 2 * 1024 * 1024);//初始化数据库

var msg;
var shapeObj;
var id;

//创建记录图形信息的表 shapes
db.transaction(function (tx) {
    tx.executeSql('CREATE TABLE IF NOT EXISTS Shapes (id unique, type,startX,startY,endX,endY,shapeWidth,shapeHeight,shapeColor,time,markstate,markdescribe)');
    //tx.executeSql('DELETE FROM Shapes');
    alert("Ok0");
    //msg = '<p>Log.</p>';
    //document.querySelector('#status').innerHTML = msg;
});

//新增图形
function addShape(type, startX, startY, endX, endY, shapeWidth, shapeHeight, shapeColor, mark_state, mark_describe) {
    //var id = G();
    id = newGuid();
	var time= new Date();

    strInsertSQL = 'INSERT INTO Shapes (id,type,startX,startY,endX,endY,shapeWidth,shapeHeight,shapeColor,time,markstate,markdescribe) VALUES ("' + id + '","' + type + '","' + startX + '","' + startY + '","' + endX + '","' + endY + '","' + shapeWidth + '","' + shapeHeight + '","' + shapeColor + '","' + time + '","' + mark_state + '","' + mark_describe + '")';

    db.transaction(function (tx) {
        tx.executeSql(strInsertSQL);
    });
    
    alert(1);
}


//撤销 撤销最近时间绘制图形
function delShape(){
	del_sql='DELETE FROM Shapes WHERE time = (select MAX(time) from Shapes)';
	  
	db.transaction(function (tx) {
        tx.executeSql(del_sql);
    });
}

//删除所有
function delAllShape(){
	delAll_sql='DELETE FROM Shapes ';
	
	db.transaction(function (tx) {
        tx.executeSql(delAll_sql);
    });
}

//查询全部图形
function getShapes() {

    db.transaction(function (tx) {
        tx.executeSql('SELECT * FROM Shapes', [], function (tx, results) {
            shapeObj = results;
        }, null);
    });
    
    alert(shapeObj.rows.lenght);
    return shapeObj;
}

//生成随机数
function G() {
    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
}

//生成随机id
function newGuid() {
    var guid = "";
    for (var i = 1; i <= 32; i++) {
        var n = Math.floor(Math.random() * 16.0).toString(16);
        guid += n;
        if ((i == 8) || (i == 12) || (i == 16) || (i == 20))
            guid += "-";
    }
    return guid;
}