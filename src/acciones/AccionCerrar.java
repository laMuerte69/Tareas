package acciones;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JFrame;

import logica.GestorTareas;

public class AccionCerrar {
	private final static String CLASE = AccionCerrar.class.getName();
	protected static Logger log = Logger.getLogger(CLASE);

    private JFrame ventana;
    private GestorTareas objGT;

    public AccionCerrar(JFrame objVentana, GestorTareas gesTareas){
    	ventana = objVentana;
    	objGT   = gesTareas;
    }

    /**
     * Metodo para caputar el evento cuando se pulsa el boton'x' de cerrar la ventana
     * @return
     * @throws Exception
     */
    public WindowListener btnCerrar() throws Exception {
		WindowListener listener = null;
	
		try{
		    listener = new WindowAdapter() {
		    	public void windowClosing(WindowEvent windowEvent){
		    		try {
		    			objGT.guardarTareasBBDD();
				    } catch (Exception e) {
				    	log.log(Level.SEVERE, e.getMessage());
				    }
		    		ventana.dispose();
		    	}        
		    };
		}
		catch (Exception e) {
		    throw new Exception(CLASE + "::btnCerrar(): " + e.getMessage());
		}
		
		return listener;
    }
    
    
}
