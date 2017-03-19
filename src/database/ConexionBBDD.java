package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import beans.DatosTareasBean;
import beans.TareaBean;

public class ConexionBBDD {

	private final static String CLASE = ConexionBBDD.class.getName();
	protected static Logger log = Logger.getLogger(CLASE);
	private static final String RUTA_BBDD = ".\\BBDD\\TareasBBDD.db";

	private Connection conn; //datos de la conexion a la bbdd


	/**
	 * Metodo para crear la conexion de la BBDD
	 * @return objeto con la conexion de la BBDD o null
	 * @throws Exception
	 */
	public ConexionBBDD() throws Exception{
		conn = null;
		
		conectarA(RUTA_BBDD);
	}


	/**
	 * Conecta a una base de datos en la ruta pasada como argumento.
	 * Si el archivo señalado en la ruta no existe, se crea.
	 * @param ruta - La ruta de la DB a conectar.
	 * @return La conexión a la ruta.
	 * @throws Exception 
	**/
	private void conectarA(String ruta) throws Exception{

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
	}

	
	/**
	 * Metodo para crear las tablas de la aplicacion
	 * @param conn - conexion a la BBDD
	 * @throws Exception
	 */
	public void crearTablasBBDD() throws Exception{
		Statement      sta = null;
		StringBuffer query = new StringBuffer();

		try {
			sta = conn.createStatement();

			query.append("CREATE TABLE TAREA( ");
			query.append("ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
			query.append("NOMBRE VARCHAR2(40),");
			query.append("DESCRIPCION  VARCHAR2(80),");
			query.append("CODIGO1 VARCHAR2(12),");
			query.append("CODIGO2 VARCHAR2(12),");
			query.append("CODIGO3 VARCHAR2(12), ");
			query.append("BAJA_LOGICA BOOLEAN); ");

			sta.execute(query.toString());
			log.log(Level.INFO, "Se ha creado la tabla \"Tarea\"");
			
			query = new StringBuffer();
			query.append("CREATE TABLE DATOS( ");
			query.append("ID INTEGER PRIMARY KEY AUTOINCREMENT, ");
			query.append("ID_TAREA INTEGER,");
			query.append("FECHA_INICIO VARCHAR2(8),");
			query.append("FECHA_FIN    VARCHAR2(8), ");
			query.append("DURACION     VARCHAR2(5), ");
			query.append("FOREIGN KEY(ID_TAREA) REFERENCES TAREA(ID)); ");
			

			sta.execute(query.toString());
			log.log(Level.INFO, "Se ha creado la tabla \"Datos\"");

		} catch (SQLException e) {
			if(!e.getMessage().contains("already exists")){
				throw new Exception("Error al crear las tablas");	
			}
		}
	}


	/**
	 * Metodo para guardar los datos de la tarea en la bbdd
	 * @param tarea - objeto con los datos de la tarea
	 * @return - resultado del proceso de guardado
	 * @throws Exception
	 */
	public int insertarTarea(final TareaBean tarea) throws Exception {
		int         resultado = 0;
		StringBuffer consulta = new StringBuffer();
		Statement    sta      = null;

		try{
			consulta.append("INSERT INTO TAREA ");
			consulta.append("(NOMBRE, DESCRIPCION, CODIGO1, CODIGO2, CODIGO3, BAJA_LOGICA) VALUES ");
			consulta.append("('").append(tarea.getNombre()).append("' , '");
			consulta.append(tarea.getDescripcion()).append("' , '");
			consulta.append(tarea.getCodigo1()).append("' , '");
			consulta.append(tarea.getCodigo2()).append("' , '");
			consulta.append(tarea.getCodigo3()).append("' , '");
			consulta.append(tarea.isBajaLogica()).append("'");
			consulta.append(");");
			
			log.log(Level.CONFIG, consulta.toString());
			sta = conn.createStatement();
			resultado = sta.executeUpdate(consulta.toString());
		}
		catch (Exception e) {
			throw new Exception("Se ha producido un error al guardar la tarea: " + tarea.info());
		}
		
		return resultado;
	}


	/**
	 * Metodo para actualizar una tarea en BBDD
	 * @param tarea - objeto con los datos de la tarea a actualizar
	 * @return resultado del proceso
	 * @throws Exception 
	 */
	public int actualizarTarea(final TareaBean tarea) throws Exception {
		int         resultado = 0;
		StringBuffer consulta = new StringBuffer();
		Statement    sta      = null;

		try{
			consulta.append("UPDATE TAREA SET ");
			consulta.append("NOMBRE = \'").append(tarea.getNombre()).append("\', ");
			consulta.append("DESCRIPCION = \'").append(tarea.getDescripcion()).append("\', ");
			consulta.append("CODIGO1 = \'").append(tarea.getCodigo1()).append("\', ");
			consulta.append("CODIGO2 = \'").append(tarea.getCodigo2()).append("\', ");
			consulta.append("CODIGO3 = \'").append(tarea.getCodigo3()).append("\', ");
			consulta.append("BAJA_LOGICA = \'").append(tarea.isBajaLogica()?1:0).append("\' ");
			consulta.append("WHERE ID = \'").append(tarea.getId()).append("\';");
			
			sta = conn.createStatement();
			resultado = sta.executeUpdate(consulta.toString());
		}
		catch (Exception e) {
			throw new Exception("Se ha producido un error al actualizar la tarea: " + tarea.info());
		}
		
		return resultado;
	}


	/**
	 * Metodo para recuperar las tareas de BBDD
	 * @return resulSet con los datos recuperados de la BBDD
	 * @throws Exception
	 */
	public ResultSet obtenerTareas() throws Exception {
		ResultSet resultado = null;
		String    consulta  = "SELECT * FROM TAREA ORDER BY ID ASC;";
		Statement sta       = null;

		try{
			sta = conn.createStatement();
			resultado = sta.executeQuery(consulta);
		}
		catch (Exception e) {
			throw new Exception("Se ha producido un error al obtener las tareas: " + e.getMessage());
		}
		
		return resultado;
	}


	/**
	 * Metodo para recuperar las tareas de BBDD
	 * @return resulSet con los datos recuperados de la BBDD
	 * @throws Exception
	 */
	public ResultSet obtenerDatosTareas() throws Exception {
		ResultSet resultado = null;
		String    consulta  = "SELECT * FROM DATOS ORDER BY ID ASC;";
		Statement sta       = null;

		try{
			sta = conn.createStatement();
			resultado = sta.executeQuery(consulta);
		}
		catch (Exception e) {
			throw new Exception("Se ha producido un error al obtener los datos de las tareas: " + e.getMessage());
		}
		
		return resultado;
	}


	/**
	 * Metodo para guardar los datos de la tarea en la bbdd
	 * @param tarea - objeto con los datos de la tarea
	 * @return - resultado del proceso de guardado
	 * @throws Exception
	 */
	public int insertarDatosTarea(DatosTareasBean datosTarea) throws Exception {
		int         resultado = 0;
		StringBuffer consulta = new StringBuffer();
		Statement    sta      = null;

		try{
			consulta.append("INSERT INTO DATOS ");
			consulta.append("(ID, ID_TAREA, FECHA_INICIO, FECHA_FIN, DURACION) VALUES ");
			consulta.append("('").append(datosTarea.getId()).append("' , '");
			consulta.append(datosTarea.getIdTarea()).append("' , '");
			consulta.append(datosTarea.getFechaInicio()).append("' , '");
			consulta.append(datosTarea.getFechaFin()).append("' , '");
			consulta.append(datosTarea.getDuracion()).append("'");
			consulta.append(");");

			log.log(Level.CONFIG, consulta.toString());
			sta = conn.createStatement();
			resultado = sta.executeUpdate(consulta.toString());
		}
		catch (Exception e) {
			throw new Exception("Se ha producido un error al guardar los datos de la tarea");
		}

		return resultado;
	}


	/**
	 * Metodo para actualizar una tarea en BBDD
	 * @param tarea - objeto con los datos de la tarea a actualizar
	 * @return resultado del proceso
	 * @throws Exception 
	 */
	public int actualizarDatosTarea(DatosTareasBean datosTarea) throws Exception {
		int         resultado = 0;
		StringBuffer consulta = new StringBuffer();
		Statement    sta      = null;

		try{
			consulta.append("UPDATE DATOS SET ");
			consulta.append("ID = \'").append(datosTarea.getId()).append("\', ");
			consulta.append("ID_TAREA = \'").append(datosTarea.getIdTarea()).append("\', ");
			consulta.append("FECHA_INICIO = \'").append(datosTarea.getFechaInicio()).append("\', ");
			consulta.append("FECHA_FIN = \'").append(datosTarea.getFechaInicio()).append("\', ");
			consulta.append("DURACION = \'").append(datosTarea.getDuracion()).append("\' ");
			consulta.append("WHERE ID = \'").append(datosTarea.getId()).append("\';");

			sta = conn.createStatement();
			resultado = sta.executeUpdate(consulta.toString());
		}
		catch (Exception e) {
			throw new Exception("Se ha producido un error al actualizar los datos de la tarea");
		}

		return resultado;
	}

}
