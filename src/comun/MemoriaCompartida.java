package comun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import DAO.DatosBBDD;
import beans.DatosTareasBean;
import beans.TareaBean;

public class MemoriaCompartida {

    private static MemoriaCompartida    instance;       // instancia unica de la clase
    private ArrayList<DatosTareasBean>  lstDatosTareas; // hashMap con las tareas
    private HashMap<Integer, TareaBean> hmTareas;       // hashMap con las tareas
    private boolean redimensionarVentana;
    private DatosBBDD objBBDD;
	private int idMaxDatosTareas; //maximo ID del listado de datos de Tareas



    /**
     * Constructor de la clase
     */
    public MemoriaCompartida(){
		instance         = null;
		lstDatosTareas   = new ArrayList<DatosTareasBean>();
		hmTareas         = new HashMap<Integer, TareaBean>();
		redimensionarVentana = false;
		objBBDD          = new DatosBBDD();
    	idMaxDatosTareas = 0;
    }
    

    /**
     * Instancia unica de la clase
     * @return
     */
    public static MemoriaCompartida getInstance () {
		if(instance == null){
		    instance = new MemoriaCompartida();
		}
	
		return instance;
    }


    /**
     * Metodo para crear una tarea nueva
     * @param nombre - nombre de la tarea
     */
    public void addTarea(TareaBean tarea) {
    	int idTarea = 0;

    	if(0 > tarea.getId()){
    		idTarea = obtenerMaximoID() + 1;
    		tarea.setId(idTarea);
    	}
    	//si no existe el id
    	else if(null == hmTareas.get(tarea.getId())){
    		idTarea = tarea.getId();
    	}
    	else{
    		idTarea = obtenerMaximoID() + 1;
    		tarea.setId(idTarea);
    	}
    	
    	hmTareas.put(idTarea, tarea);
    }


    @SuppressWarnings({ "rawtypes", "unchecked" })
	private int obtenerMaximoID() {
    	int max = 0;
    	Map mapOrdenado = new TreeMap(hmTareas);
    	
    	Iterator it = mapOrdenado.keySet().iterator();
    	while(it.hasNext()){
    		max = (Integer) it.next();
    	}
		return max;
	}


	/**
     * Metodo para canalizar y escribir trazas en la consola
     * @param mensaje
     */
    public void escribirTraza(String mensaje){
    	System.out.println(mensaje);
    }


    // --------------------- GETTER / SETTER ---------------------------------//

    public HashMap<Integer, TareaBean> getHmTareas() {
        return hmTareas;
    }
    public void setHmTareas(HashMap<Integer, TareaBean> hmTareas) {
        this.hmTareas = hmTareas;
    }


    /**
	 * @return the lstDatosTareas
	 */
	public final ArrayList<DatosTareasBean> getLstDatosTareas() {
		return lstDatosTareas;
	}
	/**
	 * @param lstDatosTareas the lstDatosTareas to set
	 */
	public final void setLstDatosTareas(final ArrayList<DatosTareasBean> lstDatosTareas) {
		int maxId = 0;

		this.lstDatosTareas = lstDatosTareas;
		
		for (DatosTareasBean tarea : lstDatosTareas) {
			if(maxId < tarea.getId()){
				maxId = tarea.getId();
			}
		}
		idMaxDatosTareas = maxId;
	}
	public final void addDatosTarea(final DatosTareasBean datosTarea) {
		this.lstDatosTareas.add(datosTarea);
		if(idMaxDatosTareas < datosTarea.getId()){
			idMaxDatosTareas = datosTarea.getId();
		}
	}


	public boolean isRedimensionarVentana() {
        return redimensionarVentana;
    }
    public void setRedimensionarVentana(boolean redimensionarVentana) {
        this.redimensionarVentana = redimensionarVentana;
    }


	/**
	 * @return the objBBDD
	 */
	public final DatosBBDD getObjBBDD() {
		return objBBDD;
	}


	/**
	 * @return the idMaxDatosTareas
	 */
	public final int getIdMaxDatosTareas() {
		return idMaxDatosTareas;
	}
	
}
