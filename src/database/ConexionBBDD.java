package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConexionBBDD {

	private final static String CLASE = ConexionBBDD.class.getName();
	protected static Logger log = Logger.getLogger(CLASE);
	private static final String RUTA_BBDD = ".\\BBDD\\TareasBBDD.db";

	 
	/**
	 * Metodo para crear la conexion de la BBDD
	 * @return objeto con la conexion de la BBDD o null
	 * @throws Exception
	 */
	public static Connection conectar() throws Exception{
		return conectarA(RUTA_BBDD);
	}


	/**
	 * Conecta a una base de datos en la ruta pasada como argumento.
	 * Si el archivo señalado en la ruta no existe, se crea.
	 * @param ruta - La ruta de la DB a conectar.
	 * @return La conexión a la ruta.
	 * @throws Exception 
	**/
	private static Connection conectarA(String ruta) throws Exception{
		Connection conn = null;
		try {
			//Cargamos el driver de SQLITE.
			Class.forName("org.sqlite.JDBC");
		}
		catch (ClassNotFoundException e) {
			String msgErr = "Error al crear el driver de conexion de la BBDD: " + e.getMessage();
			log.log(Level.SEVERE, msgErr);
			throw new Exception(msgErr);
		}

		try {
			//Aqui se obtiene la conexion:
			conn = DriverManager.getConnection("jdbc:sqlite:" + ruta);
			log.log(Level.INFO, "Conexión realizada correctamente a la BBDD: " + ruta);
		}
		catch (SQLException e) {
			String msgErr = "Error al conectarse a la BBDD: " + e.getMessage();
			log.log(Level.SEVERE, msgErr);
			throw new Exception(msgErr);
		}

		return conn;
	}

}
