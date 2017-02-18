package comun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import beans.TareaBean;

public class MemoriaCompartida {

    private static MemoriaCompartida instance;    // instancia unica de la clase

    private ArrayList<TareaBean>       lstTareas; // listado con todas las tareas del fichero
    private HashMap<Integer, TareaBean> hmTareas; // hashMap con las tareas vigentes (tareas que se muestran en el comboBox)
    private boolean redimensionarVentana;



    /**
     * Constructor de la clase
     */
    public MemoriaCompartida(){
		instance  = null;
		lstTareas = new ArrayList<TareaBean>();
		hmTareas  = new HashMap<Integer, TareaBean>();
		redimensionarVentana = false;
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
    public ArrayList<TareaBean> getLstTareas() {
        return lstTareas;
    }
    public void setLstTareas(ArrayList<TareaBean> lstTareas) {
        this.lstTareas = lstTareas;
    }


    public HashMap<Integer, TareaBean> getHmTareas() {
        return hmTareas;
    }
    public void setHmTareas(HashMap<Integer, TareaBean> hmTareas) {
        this.hmTareas = hmTareas;
    }


    public boolean isRedimensionarVentana() {
        return redimensionarVentana;
    }
    public void setRedimensionarVentana(boolean redimensionarVentana) {
        this.redimensionarVentana = redimensionarVentana;
    }
    
}
