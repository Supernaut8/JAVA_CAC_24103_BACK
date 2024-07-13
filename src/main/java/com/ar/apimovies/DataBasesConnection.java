package com.ar.apimovies;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// public class DataBasesConnection {

//     public static Connection getConnection() {
//         String hosts = "127.0.0.1"; // localhost
//         String port = "3306";
//         String USER = "root";
//         String PASS = "";

//         // driver de la conexion a la base de datos
//         String CONTROLADOR = "com.mysql.cj.jdbc.Driver";

//         // nombre de la base de datos
//         String dbName = "com_24103";

//         Connection connection;

//         try {
//             Class.forName(CONTROLADOR);

//             String URL = "jdbc:mysql://" + hosts + ":"+ port +"/"+ dbName;

//             connection = DriverManager.getConnection(URL, USER, PASS);
//         } catch (Exception e) {
//             connection = null;
//         }

//         return connection;
//     }
// }

public class DataBasesConnection {

    private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/app_movies";
    private static final String USER = "root";
    private static final String PASS = "oktubre";

    private Connection connection; //Objeto Connection para manejar la conexión a la base de datos

    static {
        try {
            Class.forName(CONTROLADOR);
            System.out.println("Driver cargado correctamente");
        } catch (ClassNotFoundException error) {
            System.out.println("Error al cargar Driver JDBC");
            error.printStackTrace();
        }
    }

    public Connection conectar() {

        Connection conexion = null;

        try {
            conexion = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Conexion establecida");
        } catch (SQLException e) {
            System.out.println("La conexion no se pudo establecer");
            e.printStackTrace();
        }

        return conexion;
    }

    public void close() {
        try {
            //verificar si la conexión no es nula y está abierta, entonces cerrarla
            if(connection != null && !connection.isClosed()) {
                connection.close(); // Cierra la conexión a la base de datos
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Imprimir el error en caso de problemas al cerrar la conexión
        }
    }
}
