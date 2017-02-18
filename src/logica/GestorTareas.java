package logica;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

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
     * Metodo para guardar todas las tareas
     * @throws Exception 
     */
    public void guardarTareasBBDD() throws Exception{
    	//TODO: implementar
    	//	guardar las tareas en la BBDD o en el fichero de tareas
    	try{
	    
    		log.log(Level.INFO, "Se han guardado las tareas");
    	
    	}
    	catch (Exception e) {
    		throw new Exception("Error al guardar tareas en BBDD: " + e.getMessage());
    	}
    }


	public void cargarTareasBBDD() throws Exception {

		
    	try{
    		//TODO: Cargar aqui los datos de las tareas de la bbDD
    	}
    	catch (Exception e) {
    		throw new Exception("Error al cargar tareas de BBDD: " + e.getMessage());
    	}
		
	}
}
