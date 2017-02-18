package Ventanas;

import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

import beans.TareaBean;
import logica.GestorTareas;

public class ModeloTabla implements TableModel{

	private final static String CLASE = ModeloTabla.class.getName();
	protected static Logger log = Logger.getLogger(CLASE);
    private final String[] COLUMNAS = {"ID", "Tarea", "Descripcion"};
    private int numeroColumnas;
    
    // Lista con los datos. Cada elemento de la lista es una instancia de TareaBean
    @SuppressWarnings("rawtypes")
	private LinkedList datos;
    
    // Lista de suscriptores. El JTable será un suscriptor de este modelo de datos
    @SuppressWarnings("rawtypes")
	private LinkedList listeners;

    
    @SuppressWarnings("rawtypes")
	public ModeloTabla(){
    	numeroColumnas = COLUMNAS.length;
    	datos     = new LinkedList();
    	listeners = new LinkedList();
    }


    /**
     * Metodo para obtener el numero de columnas de la tabla
     */
    public int getColumnCount() {
        return numeroColumnas;
    }


    public int getRowCount() {
        return datos.size();
    }
    
    /** Returns the value for the cell at <code>columnIndex</code> and
     * <code>rowIndex</code>.
     *
     * @param	rowIndex	the row whose value is to be queried
     * @param	columnIndex 	the column whose value is to be queried
     * @return	the value Object at the specified cell
     *
     */
    public Object getValueAt(int rowIndex, int columnIndex) {
        TareaBean tarea = null;
        
        try{
	        // Se obtiene la persona de la fila indicada
	        tarea = (TareaBean)(datos.get(rowIndex));
	        
	        // Se obtiene el campo apropiado según el valor de columnIndex
	        switch (columnIndex)
	        {
	            case 0:
	                return new Integer(tarea.getId());
	            case 1:
	                return tarea.getNombre();
	            case 2:
	                return tarea.getDescripcion();
//	            case 3:
//	                return tarea.getCodigo1();
//	            case 4:
//	                return tarea.getCodigo2();
//	            case 5:
//	                return tarea.getCodigo3();
	            default:
	                return tarea;
	        }
        }
        catch (Exception e) {
        	log.log(Level.SEVERE, CLASE + "::getValueAt(): "  + e.getMessage());
		}

		return tarea;
    }
    
    /**
     * Borra del modelo la persona en la fila indicada 
     */
    public void borraTarea (int posicionFila){
    	try{
	        // Se borra la fila 
	        datos.remove(posicionFila);
	        
	        // Y se avisa a los suscriptores, creando un TableModelEvent...
	        TableModelEvent evento = new TableModelEvent (this, posicionFila, posicionFila, 
	            TableModelEvent.ALL_COLUMNS, TableModelEvent.DELETE);
	        
	        // ... y pasándoselo a los suscriptores
	        avisaSuscriptores (evento);
    	} catch (Exception e) {
        	log.log(Level.SEVERE, CLASE + "::borraTarea(): "  + e.getMessage());
		}
    }
    
    /**
     * Anyade una tarea al final de la tabla
     */
    @SuppressWarnings("unchecked")
	public void anyadeTarea (TareaBean nuevaTarea){
    	try{
	        // Anyade la persona al modelo 
	        datos.add (nuevaTarea);
	        new GestorTareas().nuevaTarea(nuevaTarea);
	        
	        // Avisa a los suscriptores creando un TableModelEvent...
	        TableModelEvent evento = new TableModelEvent (this, this.getRowCount()-1,
	            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
	
	        // ... y avisando a los suscriptores
	        avisaSuscriptores (evento);
    	} catch (Exception e) {
    		log.log(Level.SEVERE, CLASE + "::anyadeTarea(): "  + e.getMessage());
		}
    }


    /**
     * Metodo para rehabilitar una tarea que estaba dada de baja
     * @param tarea
     */
	@SuppressWarnings("unchecked")
	public void rehabiliarTarea(TareaBean tarea) {
    	try{
	        // Anyade la persona al modelo 
	        datos.add (tarea);
	        
	        // Avisa a los suscriptores creando un TableModelEvent...
	        TableModelEvent evento = new TableModelEvent (this, this.getRowCount()-1,
	            this.getRowCount()-1, TableModelEvent.ALL_COLUMNS, TableModelEvent.INSERT);
	
	        // ... y avisando a los suscriptores
	        avisaSuscriptores (evento);
    	} catch (Exception e) {
    		log.log(Level.SEVERE, CLASE + "::rehabiliarTarea(): "  + e.getMessage());
		}
	}

    
    /** Adds a listener to the list that is notified each time a change
     * to the data model occurs.
     *
     * @param	l		the TableModelListener
     *
     */
    @SuppressWarnings("unchecked")
	public void addTableModelListener(TableModelListener l) {
        // Añade el suscriptor a la lista de suscriptores
        listeners.add (l);
    }


    /** Returns the most specific superclass for all the cell values
     * in the column.  This is used by the <code>JTable</code> to set up a
     * default renderer and editor for the column.
     *
     * @param columnIndex  the index of the column
     * @return the common ancestor class of the object values in the model.
     *
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public Class getColumnClass(int columnIndex) {
        // Devuelve la clase que hay en cada columna.
        switch (columnIndex)
        {
            case 0:
                // La columna cero contiene el ID de la tarea
                return Integer.class;
            case 1:
                // La columna uno contiene el nombre de la tarea
                return String.class;
            case 2:
                // La columna dos contine la descripcion
                return String.class;
//            case 3:
//                // La columna dos contine el codigo 1 de la tarea
//                return String.class;
//            case 4:
//                // La columna dos contine el codigo 2 de la tarea
//                return String.class;
//            case 5:
//                // La columna dos contine el codigo 3 de la tarea
//                return String.class;
            default:
                // Devuelve una clase Object por defecto.
                return Object.class;
        }
    }
    
    /** Returns the name of the column at <code>columnIndex</code>.  This is used
     * to initialize the table's column header name.  Note: this name does
     * not need to be unique; two columns in a table can have the same name.
     *
     * @param	columnIndex	the index of the column
     * @return  the name of the column
     *
     */
    public String getColumnName(int columnIndex){
    	if(columnIndex >= COLUMNAS.length){
    		return null;
    	}
        return COLUMNAS[columnIndex];
    }
    
    /** Returns true if the cell at <code>rowIndex</code> and
     * <code>columnIndex</code>
     * is editable.  Otherwise, <code>setValueAt</code> on the cell will not
     * change the value of that cell.
     *
     * @param	rowIndex	the row whose value to be queried
     * @param	columnIndex	the column whose value to be queried
     * @return	true if the cell is editable
     * @see #setValueAt
     *
     */
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        // Permite que la celda sea editable.
        return false;
    }
    
    /** Removes a listener from the list that is notified each time a
     * change to the data model occurs.
     *
     * @param	l		the TableModelListener
     *
     */
    public void removeTableModelListener(TableModelListener l) {
        // Elimina los suscriptores.
        listeners.remove(l);
    }
    
    /** Sets the value in the cell at <code>columnIndex</code> and
     * <code>rowIndex</code> to <code>aValue</code>.
     *
     * @param	aValue		 the new value
     * @param	rowIndex	 the row whose value is to be changed
     * @param	columnIndex 	 the column whose value is to be changed
     * @see #getValueAt
     * @see #isCellEditable
     *
     */
    public void setValueAt(Object aValue, int rowIndex, int columnIndex){

    	TareaBean tarea = null;

    	try {
    		// Obtiene la persona de la fila indicada
    		tarea = (TareaBean)(datos.get(rowIndex));

	        // Cambia el campo de Persona que indica columnIndex, poniendole el 
	        // aValue que se nos pasa.
	        switch (columnIndex)
	        {
	            case 0:
	                tarea.setId(((Integer)aValue).intValue());
	                break;
	            case 1:
	                tarea.setNombre((String)aValue);
	                break;
	            case 2:
	                tarea.setDescripcion((String)aValue);
	                break;
//	            case 3:
//	                tarea.setCodigo1((String)aValue);
//	                break;
//	            case 4:
//	            	tarea.setCodigo2((String)aValue);
//	                break;
//	            case 5:
//	            	tarea.setCodigo3((String)aValue);
//	                break;
	            default:
	                break;
	        }
	        
	        // Avisa a los suscriptores del cambio, creando un TableModelEvent ...
	        TableModelEvent evento = new TableModelEvent (this, rowIndex, rowIndex, columnIndex);
	
	        // ... y pasandoselo a los suscriptores.
			avisaSuscriptores (evento);
		} catch (Exception e) {
			log.log(Level.SEVERE, CLASE + "::setValueAt(): "  + e.getMessage());
		}
    }
    
    /**
     * Pasa a los suscriptores el evento.
     * @throws Exception 
     */
    private void avisaSuscriptores (TableModelEvent evento) throws Exception
    {
        int i = 0;
        
        try{
        	// Bucle para todos los suscriptores en la lista, se llama al metodo
        	// tableChanged() de los mismos, pasándole el evento.
        	for (i=0; i<listeners.size(); i++)
        		((TableModelListener)listeners.get(i)).tableChanged(evento);
        }
        catch (Exception e) {
        	throw new Exception(CLASE + "::avisaSuscriptores(): " + e.getMessage());
		}
    }


    /**
     * Metodo para obtener la fila (posicion) cuyo id se el de la tarea
     * @param id - identificador de la tarea
     * @return posicion de la lista de tareas
     * @throws Exception 
     */
	public int getFila(int id) throws Exception {
        int posicion = 0;
        
        try{
        	for (int i=0; i<datos.size(); i++){
        		if( ((TareaBean) datos.get(i)).getId() == id){
        			posicion = i;
        			break;
        		}
        	}
        }
        catch (Exception e) {
        	throw new Exception(CLASE + "::getFila("+id+"): " + e.getMessage());
		}
        
        return posicion;
	}
}
