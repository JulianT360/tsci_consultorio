package conexion;

import util.Constantes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionSql {
	 Connection conexion = null;
	  
     public Connection conectar() {
        try{
	        conexion = DriverManager
                    .getConnection(Constantes.URL_CONEXION, Constantes.USER_CONEXION, Constantes.PSW_CONEXION);
	        System.out.println(Constantes.CONEXION_OK);

        } catch (SQLException e) {
            System.out.println(Constantes.CONEXION_ERR);
        } catch (Exception e) {
            System.out.println(Constantes.CONEXION_ERR);
        }
         return conexion;
    }

	public Connection getConnection() {
		return conexion;
	}  

}
