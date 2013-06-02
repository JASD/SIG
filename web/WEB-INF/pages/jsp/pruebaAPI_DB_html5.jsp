<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html manifest="archivos.manifest">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script type="text/javascript" src="js/MD5Enc.js"></script>
        <script type="text/javascript">          
            try{
                if(window.openDatabase){
                    db=openDatabase("BD_SIG_Cek_navegador","1.0", "Ejemplo uso de la bad",200000);
                    if(db){
                        alert("bd creada exitosament");
                    }
                }else{
                    alert("no se ha podido abrir la bd 1");
                }
            }catch(err){
                db=null;
                alert("no se ha podido abrir la bd 2");
            }
                        
            function initBD(){
                db.transaction(function(tx){
                    tx.executeSql("CREATE TABLE tabla(id REAL UNIQUE, campo1 TEXT)",[],function(result){
                    },function(tx,errores){
                        alert("ocurrio un error 1")
                    });
                },function(tx,errores){
                    alert("ocurrio un error 2")
                });
            }
            function cargarDatos(){
                db.transaction(function(tx){
                    tx.executeSql("SELECT id,campo1 FROM tabla", [], function(tx, result) {
                        var cad="";
                        for(var i=0;i<result.rows.length;i++){
                            var row=result.rows.item(i);
                            cad+=md5(row['campo1'])+",";
                            cad+=row['id']+",";
                            cad+=row['campo1']+"<br>";
                        }
                        //CARGA LOS DATOS ALMACENADOS EN LA BD EN UN DIV 
                        document.getElementById("inf").innerHTML=cad;
                        if (!result.rows.length){
                            alert("no hay notas");
                        }
                    },function(tx, error) {
                        alert('Failed to retrieve notas from database - ' + error.message);
                        return;
                    });
                });
            }
            
            function crear(){
                db.transaction(function (tx) 
                {
                    tx.executeSql("INSERT INTO tabla (id, campo1) VALUES (?, ?)", [document.getElementById("id").value, document.getElementById("campo1").value]);
                });
                cargarDatos();
            }
            
            
            initBD();
            cargarDatos();
        </script>
    <form>
        <input type="button" value="crear" onclick="crear()">
        <br>
        Numero de registro: 
        <input type="text" id="id">
        <br>
        Cadena a guardar: 
        <input type="text" id="campo1">
        <br>
        <div id="inf">
        </div>
    </form>
</head>
<body>
    <h1>Hello World!</h1>
</body>
</html>
