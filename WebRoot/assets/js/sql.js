var db = openDatabase('webShapeDB', '1.0', 'ShapeDB', 2 * 1024 * 1024);

var msg;
var shapeObj;
var id;

db.transaction(function (tx) {
    tx.executeSql('CREATE TABLE IF NOT EXISTS Shapes (id unique, type,startX,startY,endX,endY,shapeWidth,shapeHeight,shapeColor,time)');
    tx.executeSql('DELETE FROM Shapes');

    //msg = '<p>Log.</p>';
    //document.querySelector('#status').innerHTML = msg;
});

function addShape(type, startX, startY, endX, endY, shapeWidth, shapeHeight, shapeColor) {
    //var id = G();
    id = newGuid();
	var time= new Date();

    strInsertSQL = 'INSERT INTO Shapes (id,type,startX,startY,endX,endY,shapeWidth,shapeHeight,shapeColor,time) VALUES ("' + id + '","' + type + '","' + startX + '","' + startY + '","' + endX + '","' + endY + '","' + shapeWidth + '","' + shapeHeight + '","' + shapeColor + '","' + time + '")';

    db.transaction(function (tx) {
        tx.executeSql(strInsertSQL);
    });
}

function delShape(){
	del_sql='DELETE FROM Shapes WHERE id="'+id+'"';
	
	db.transaction(function (tx) {
        tx.executeSql(del_sql);
    });
}

function getShapes() {

    db.transaction(function (tx) {
        tx.executeSql('SELECT * FROM Shapes', [], function (tx, results) {
            shapeObj = results;
        }, null);
    });

    return shapeObj;
}

function G() {
    return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
}

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