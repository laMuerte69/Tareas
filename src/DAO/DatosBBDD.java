package DAO;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import beans.DatosTareasBean;
import beans.TareaBean;
import database.ConexionBBDD;

public class DatosBBDD {
	private final static String CLASE = DatosBBDD.class.getName();
	protected static Logger log = Logger.getLogger(CLASE);
	private ConexionBBDD bbdd;

	public DatosBBDD() throws Exception{
		try{
			bbdd = new ConexionBBDD();

			//Creamos las tablas en el caso de que no existan
			bbdd.crearTablasBBDD();
		}
		catch (Exception e) {
			throw new Exception("Error al crear la conexion con la BBDD: " + e.getMessage());
		}
	}


	/**
	 * Metodo para guardar las tareas en la BBDD
	 * @param tarea - objeto con los datos de la tarea
	 * @throws Exception 
	 */
	public void guardarTareas(final TareaBean tarea) throws Exception {
		if(tarea.isNueva()){
			if(bbdd.insertarTarea(tarea) != 1){
				throw new Exception("Error al guardar la tarea (INS): " + tarea.info());
			}
		}
		else{
			if(bbdd.actualizarTarea(tarea) != 1){
				throw new Exception("Error al guardar la tarea (UPD): " + tarea.info());
			}
		}
	}



	/**
	 * Metodo para guardar los datos de la tarea en la BBDD
	 * @param datosTarea
	 * @throws Exception 
	 */
	public void guardarDatosTarea(final DatosTareasBean datosTarea) throws Exception {
		if(datosTarea.isNueva()){
			if(bbdd.insertarDatosTarea(datosTarea) != 1){
				throw new Exception("Error al guardar los datos de la tarea (INS)");
			}
		}
		else{
			if(bbdd.actualizarDatosTarea(datosTarea) != 1){
				throw new Exception("Error al guardar los datos de la tarea (UPD)");
			}
		}
	}

	/**
	 * Metodo para cargar las tareas desde BBDD
	 * @return
	 * @throws Exception 
	 */
	public HashMap<Integer, TareaBean> cargarTareas() throws Exception {
		HashMap<Integer, TareaBean> resultado = null;
		ResultSet rs = null;
		
		try{
			resultado = new HashMap<Integer, TareaBean>();
			rs        = bbdd.obtenerTareas();
			
			while (rs.next()) {
	            TareaBean tarea = new TareaBean().parse(rs);
	            resultado.put(tarea.getId(), tarea);
	        }
		}
		catch (Exception e) {
			throw new Exception(CLASE + "::cargarTareas(): " + e.getMessage());
		}

		return resultado;
	}

	
	/**
	 * Metodo para cargar los datos de las tareas desde BBDD
	 * @return
	 * @throws Exception 
	 */
	public ArrayList<DatosTareasBean> cargarDatosTareas() throws Exception {
		ArrayList<DatosTareasBean> resultado = null;
		ResultSet rs = null;
		
		try{
			resultado = new ArrayList<DatosTareasBean>();
			rs        = bbdd.obtenerDatosTareas();
			
			while (rs.next()) {
				DatosTareasBean tarea = new DatosTareasBean().parse(rs);
	            resultado.add(tarea);
	        }
		}
		catch (Exception e) {
			throw new Exception(CLASE + "::cargarDatosTareas(): " + e.getMessage());
		}
		return resultado;
	}
	
	
}
