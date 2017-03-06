package tablas;

import java.util.logging.Level;
import java.util.logging.Logger;

import beans.DatosTareasBean;

public class ControlLstDatoTareas {

	private final static String CLASE = ControlLstDatoTareas.class.getName();
	protected static Logger log = Logger.getLogger(CLASE);

	private TablaModeloLstDatoTareas modelo;
	
	public ControlLstDatoTareas(TablaModeloLstDatoTareas modeloLstDatoTareas) {
		modelo = modeloLstDatoTareas;
	}

    
    /**  
     *  Elimina la primera fila del modelo
     */
    public void borraFila () {
        if (modelo.getRowCount() > 0)
           modelo.borraTarea(0);
    }


    /**
     * Metodo para borrar toda la tabla de datos de tareas
     */
    public void borraTabla () {
        if (modelo.getRowCount() > 0){
        	modelo.borraTabla();
        }
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
	 * Metodo para añadir una nueva fila a la tabla de rehabilitar
	 * @param tarea - referencia al objeto con los datos de la tarea
	 */
	public void anyadeFila(final DatosTareasBean tarea) {
		try{
			modelo.anyadeTarea(tarea);
		}
		catch (Exception e) {
			log.log(Level.SEVERE, CLASE + "::anyadeFila(" + tarea.getId() + "): "  + e.getMessage());
		}
	}


	/**
	 * Metodo para refrescar la tabla cuando esta vacia
	 */
	public void refresh() {
		try{
			if(modelo.getRowCount() == 0){
				modelo.refresh();
			}
		}
		catch (Exception e) {
			log.log(Level.SEVERE, CLASE + "::refresh(): "  + e.getMessage());
		}
	}
}
