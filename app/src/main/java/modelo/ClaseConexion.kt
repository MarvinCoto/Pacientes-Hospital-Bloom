package modelo

import java.sql.Connection
import java.sql.DriverManager

class ClaseConexion {

    fun cadenaConexion(): Connection? {

        try {
            val url = "jdbc:oracle:thin:@10.10.0.53:1521:xe"
            val usuario = ""
            val contrasena = ""

            val connection = DriverManager.getConnection(url, usuario, contrasena)
            return connection
        }catch (e: Exception){
            println("error: $e")
            return null
        }
    }
}