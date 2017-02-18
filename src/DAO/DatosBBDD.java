package DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

import beans.DatosTareasBean;
import beans.TareaBean;

public class DatosBBDD {
	private final static String CLASE = DatosBBDD.class.getName();
	protected static Logger log = Logger.getLogger(CLASE);

	public DatosBBDD(){
		//TODO Crear la conexion
	}

	/**
	 * Metodo para guardar las tareas en la BBDD
	 * @param value
	 */
	public void guardarTareas(final TareaBean value) {
		// TODO implementar
	}

	/**
	 * Metodo para guardar los datos de la tarea en la BBDD
	 * @param datosTarea
	 */
	public void guardarDatosTarea(final DatosTareasBean datosTarea) {
		// TODO implementar
	}

	/**
	 * Metodo para cargar las tareas desde BBDD
	 * @return
	 * @throws Exception 
	 */
	public HashMap<Integer, TareaBean> cargarTareas() throws Exception {
		HashMap<Integer, TareaBean> resultado = new HashMap<Integer, TareaBean>();
		
		try{
			// TODO implementar
			
			// -----------------------------------------------------------
			// TODO ELiminar estas lineas:
			// -----------------------------------------------------------
			TareaBean t0 = new TareaBean(0, "Tarea 0", "Tarea Dummy 0");
			TareaBean t1 = new TareaBean(1, "Tarea 1", "Tarea Dummy 1");
			TareaBean t2 = new TareaBean(2, "Tarea 2", "Tarea Dummy 2");
			TareaBean t3 = new TareaBean(3, "Tarea 3", "Tarea Dummy 3");
			resultado.put(t0.getId(), t0);
			t1.setBajaLogica(true);
			resultado.put(t1.getId(), t1);
			resultado.put(t2.getId(), t2);
			t3.setBajaLogica(true);
			resultado.put(t3.getId(), t3);
			// -----------------------------------------------------------
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
		ArrayList<DatosTareasBean> resultado = new ArrayList<DatosTareasBean>();
		
		try{
			// TODO implementar
		}
		catch (Exception e) {
			throw new Exception(CLASE + "::cargarDatosTareas(): " + e.getMessage());
		}
		return resultado;
	}
	
}
