package logica;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import beans.DatosTareasBean;
import beans.TareaBean;
import comun.MemoriaCompartida;

public class GestorTareas {

	private final static String CLASE = GestorTareas.class.getName();
	protected static Logger log = Logger.getLogger(CLASE);
    private MemoriaCompartida memoria;

    public GestorTareas(){
    	memoria = MemoriaCompartida.getInstance();
    }
    
    
    /**
     * Metodo para crear una nueva tarea
     * @param nombre      - nombre de la tarea
     * @param descripcion - descripcion de la tarea
     * @throws Exception 
     */
    public void nuevaTarea (final String nombre, String descripcion) throws Exception{
    	int       id    = -1;
    	TareaBean tarea = null;

		try{
		    
		    //obtenemos el mayor ID
		    for (Entry<Integer, TareaBean> objTarea : memoria.getHmTareas().entrySet()) {
		    	if(id < objTarea.getKey()){
		    		id = objTarea.getKey();
		    	}
		    }

		    tarea = new TareaBean(id + 1, nombre, descripcion);
		    memoria.addTarea(tarea);
		    log.log(Level.INFO, "Se ha dado de alta la tarea: " + nombre);
		}
		catch (Exception e) {
		    throw new Exception("Error al crear una tarea nueva: " + e.getMessage());
		}
    }
    
    
    /**
     * Metodo para dar de alta nuevas tareas
     * @param tarea - objeto con los datos de la tarea
     * @throws Exception
     */
    public void nuevaTarea(TareaBean tarea) throws Exception{
    	try{
    	    memoria.addTarea(tarea);
    	}
    	catch (Exception e) {
    	    throw new Exception("Error al crear una tarea nueva: " + e.getMessage());
    	}
    }


    /**
     * Metodo para obtener una lista con el nombre de todas las tareas de la aplicacion
     * @return lista de tareas
     * @throws Exception
     */
    public Vector<String> obtenerTareasCbox() throws Exception{
    	Vector<String> resultado = null;

		try{
		    resultado = new Vector<String>();
		    for (Entry<Integer, TareaBean> objTarea : memoria.getHmTareas().entrySet()) {
		    	if(!objTarea.getValue().isBajaLogica()){
		    		resultado.add(objTarea.getValue().getNombre());
		    	}
		    }

		    log.log(Level.INFO, "Se han cargado en el CBOX las tareas");
		}
		catch (Exception e) {
		    throw new Exception("Error al obtener el listado de tareas: " + e.getMessage());
		}

		return resultado;
    }


    /**
     * Metodo para obtener las tareas que se van a mostrar en el tab del listado
     * @return lista de tareas
     * @throws Exception
     */
    public HashMap<Integer, TareaBean> obtenerTareasListado() throws Exception{
    	return memoria.getHmTareas();
    }

    /**
     * Metodo para obtener la tarea a traves de su ID
     * @param id - identificador de la tarea
     * @return objeto con los datos de la tarea
     */
    public TareaBean obtenerTarea(int id){
    	return memoria.getHmTareas().get(id);
    }
    
    /**
     * Metodo para obtener la tarea a traves de su nombre
     * @param nombreTarea - Nombre de la tarea
     * @return Id de la tarea
     * @throws Exception 
     */
    public int obtenerTarea(String nombreTarea) throws Exception {
    	int id = -1;

		try{
			for (Entry<Integer, TareaBean> objTarea : memoria.getHmTareas().entrySet()) {
				if(objTarea.getValue().getNombre().equals(nombreTarea)){
					id = objTarea.getKey();
					break;
				}
			}
		}
		catch (Exception e) {
			throw new Exception(CLASE + "::obtenerTarea("+nombreTarea+"): " + e.getMessage());
		}

		return id;
	}


    /**
     * Metodo para comprobar que existe la tarea en el listado de tareas
     * @param tarea - refencia a la tarea que se desea comprobar
     * @return true: la tarea existe; false: la tarea no existe
     * @throws Exception
     */
	public boolean existeTarea(final TareaBean tarea) throws Exception {
		boolean resultado = false;

		try{
			for (Entry<Integer, TareaBean> objTarea : memoria.getHmTareas().entrySet()) {
				if(objTarea.getValue().getNombre().toUpperCase().trim().equals(tarea.getNombre().toUpperCase().trim())){
					resultado = true;
					break;
				}
			}
		}
		catch (Exception e) {
			throw new Exception(CLASE + "::existeTarea("+tarea.getNombre()+"): " + e.getMessage());
		}

		return resultado;
	}


	/**
	 * Metodo para obtener el maximo ID del listado de datos de las tareas
	 * @return
	 */
	public int getMaximoIdDatosTarea() {
		return memoria.getIdMaxDatosTareas();
	}


	/**
	 * Añadimos los datos de una nueva tarea a la lista
	 * @param datosTarea - datos de una nueva tarea
	 */
	public void setDatosTarea(final DatosTareasBean datosTarea) {
		memoria.addDatosTarea(datosTarea);
	}


    /**
     * Metodo para guardar las tareas y sus datos en la BBDD
     * @throws Exception 
     */
    public void guardarTareasBBDD() throws Exception{

    	try{
		    //obtenemos el mayor ID
		    for (Entry<Integer, TareaBean> objTarea : memoria.getHmTareas().entrySet()) {
		    	memoria.getObjBBDD().guardarTareas(objTarea.getValue());
		    }
    		log.log(Level.INFO, "Se han guardado las tareas");
    	}
    	catch (Exception e) {
    		throw new Exception("Error al guardar tareas en BBDD: " + e.getMessage());
    	}

    	try{
    		for (DatosTareasBean datosTarea : memoria.getLstDatosTareas()) {
		    	memoria.getObjBBDD().guardarDatosTarea(datosTarea);
		    }
    		log.log(Level.INFO, "Se han guardado los datos de las tareas");
    	}
    	catch (Exception e) {
    		throw new Exception("Error al guardar los datos de las tareas en BBDD: " + e.getMessage());
    	}
    }


    /**
     * Metodo para cargar las tareas y los datos de las tareas desde BBDD
     * @throws Exception
     */
	public void cargarTareasBBDD() throws Exception {

    	try{
    		memoria.setHmTareas(memoria.getObjBBDD().cargarTareas());
    	}
    	catch (Exception e) {
    		throw new Exception("Error al cargar tareas de BBDD: " + e.getMessage());
    	}

    	try{
    		memoria.setLstDatosTareas(memoria.getObjBBDD().cargarDatosTareas());
    	}
    	catch (Exception e) {
    		throw new Exception("Error al cargar los datos de las tareas de BBDD: " + e.getMessage());
    	}
		
	}

}
