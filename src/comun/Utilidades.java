package comun;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import logica.GestorTareas;

public class Utilidades {

	/**
	 * Metodo para obtener la fecha actual
	 * @return fecha actual en formato cadena
	 * @throws Exception
	 */
	public static String obtenerFecha() throws Exception{
		String resultado = Constantes.VACIO;

		try{
			Calendar c1 = GregorianCalendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");
			resultado = sdf.format(c1.getTime());
		}
		catch (Exception e) {
			throw new Exception("Utilidades::obtenerFecha(): " + e.getMessage());
		}
		
		return resultado;
	}
	
	/**
	 * Metodo para obtener la hora actual
	 * @return hora actual en formato cadena
	 * @throws Exception
	 */
	public static String obtenerHora() throws Exception{
		String resultado = Constantes.VACIO;

		try{
			Calendar c1 = GregorianCalendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			resultado = sdf.format(c1.getTime());
		}
		catch (Exception e) {
			throw new Exception("Utilidades::obtenerFecha(): " + e.getMessage());
		}
		
		return resultado;
	}
	
	/**
	 * Metodo para actualizar las tareas del combox del panel principal
	 * @param objGT 
	 * @throws Exception 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void actualizarCBXTareas(JPanel panelPrincipal, GestorTareas objGT) throws Exception {
		
		Vector<String> vTareas = objGT.obtenerTareasCbox();
		boolean primeraVezBtnPlay = true;
		
		for (Component comp : panelPrincipal.getComponents()) {
			if(comp.getName().equals("cbxLstTareas")){
				JComboBox cbx = (JComboBox) comp;

				cbx.removeAllItems();
				if(vTareas.size() > 0){
					cbx.setEnabled(true);

					for(String tarea: vTareas){
						cbx.addItem(tarea);
					}
				}
				else{
					cbx.setEnabled(false);
				}
				
				break;
			}
			else if(comp.getName().equals("btnPlay") && primeraVezBtnPlay){
				JButton btnPlay   = (JButton) comp;
				primeraVezBtnPlay = false;

				if(vTareas.size() > 0){
					btnPlay.setEnabled(true);
				}
				else{
					btnPlay.setEnabled(false);
				}
			}
		}
	}
}
