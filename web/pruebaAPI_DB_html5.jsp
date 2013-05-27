<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html manifest="archivos.manifest">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
                    tx.executeSql("SELECT COUNT(*) FROM tabla",[],function(result){
                        cargarDatos();
                    },function(tx,error){
                        tx.executeSql("CREATE TABLE tabla(id REAL UNIQUE, campo1 TEXT)",[],function(result){
                            cargarDatos();
                        });
                    });
                });
            }
            function cargarDatos(){
                db.transaction(function(tx){
                    tx.executeSql("SELECT id,campo1 FROM tabla", [], function(tx, result) {
                        for(var i=0;i<result.rows.length;i++){
                            var row=result.rows.item(i);
                            var actual=new Tabla();
                            alert(row['id']);
                            alert(campo1=row['campo1']);
                        }
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
                initBD();
                alert("crear");
                db.transaction(function (tx) 
                {
                    tx.executeSql("INSERT INTO tabla (id, campo1) VALUES (?, ?)", [2.0, "prueba"]);
                });
                alert("correcto");
            }
            crear();
            
            function Tabla(){}
            Tabla.prototype={
                set id(e){
                    this._id=e;
                },
                get id(){
                    return this._id;
                },
                set campo1(e){
                    this._campo1=e;
                },
                get campo1(){
                    return this._campo1;
                }
            }
            
            
        </script>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
</html>
