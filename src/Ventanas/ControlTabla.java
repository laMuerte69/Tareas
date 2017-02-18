package Ventanas;

import java.util.logging.Level;
import java.util.logging.Logger;

import beans.TareaBean;

public class ControlTabla {
	private final static String CLASE = ControlTabla.class.getName();
	protected static Logger log = Logger.getLogger(CLASE);

    // El modelo de la tabla
    private ModeloTabla modelo = null;
    
    
    /**
     * Constructor. Se le pasa el modelo, al que añade varios elementos y
     * se lo guarda.
     */
    public ControlTabla(ModeloTabla modelo)
    {
        this.modelo = modelo;
    }
    
    /**
     * Añade una fila en el modelo, al final del mismo
     */
    public void anhadeFila (TareaBean tarea){
        modelo.anyadeTarea(tarea);
    }
    
    /** Elimina la primera fila del modelo */
    public void borraFila () {
        if (modelo.getRowCount() > 0)
           modelo.borraTarea(0);
    }

    /**
     * Metodo para borrar la tarea cuyo identificador el ID de la tabla
     * @param id
     */
	public void borraFila(int id) {
		int fila = -1;

		try{
			fila = modelo.getFila(id);
			
			if (modelo.getRowCount() > 0 && fila >= 0){
				modelo.borraTarea(fila);
			}
			else{
				throw new Exception("Error sistema. No se puede borrar la fila " + fila);
			}
		}
		catch (Exception e) {
			log.log(Level.SEVERE, CLASE + "::borraFila(" + id + "): "  + e.getMessage());
		}
	}


	/**
	 * Metodo para rehabilitar una tarea que estaba de baja
	 * @param tarea
	 */
	public void reHabilitarFila(TareaBean tarea) {
		modelo.rehabiliarTarea(tarea);
	}
}
