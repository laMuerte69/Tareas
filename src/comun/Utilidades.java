package comun;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import org.apache.commons.lang3.StringUtils;

import logica.GestorTareas;

public class Utilidades {
	private final static String CLASE = Utilidades.class.getName();

	/**
	 * Metodo para obtener la fecha actual
	 * @return fecha actual en formato cadena
	 * @throws Exception
	 */
	public static String obtenerFecha() throws Exception{
		String resultado = Constantes.VACIO;

		try{
			Calendar c1 = GregorianCalendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
			resultado = sdf.format(c1.getTime());
		}
		catch (Exception e) {
			throw new Exception(CLASE + "::obtenerFecha(): " + e.getMessage());
		}
		
		return resultado;
	}

	public static int obtenerFecha(String fechaInicio) throws Exception {
		int resultado = 0;

		try{
			String f   = fechaInicio.substring(0, fechaInicio.indexOf(" "));
			String []v = f.split("/");
			resultado = fechaNumerica(Integer.parseInt(v[2]), Integer.parseInt(v[1]), Integer.parseInt(v[0]));
		}
		catch (Exception e) {
			throw new Exception(CLASE + "::obtenerFecha("+fechaInicio+"): " + e.getMessage());
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
			throw new Exception(CLASE + "::obtenerFecha(): " + e.getMessage());
		}
		
		return resultado;
	}
	

	/**
	 * Metodo para formatear la hora
	 * @param hora - valor numerico de la hora
	 * @param minutos - valor numerico de los minutos
	 * @return hora formateada HH:mm
	 */
	public static String obtenerHora(long hora, long minutos) {
		return lpad(hora, 2) + ":" + lpad(minutos, 2);
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


	/**
	 * Metood para obtener el valor de la fecha en formato numerico
	 * @param year  - año
	 * @param month - mes
	 * @param day   - dia
	 * @return
	 */
	public static int fechaNumerica(int year, int month, int day) {
		return year * 10000 + month * 100 + day;
	}


	/**
	 * Metodo para poner ceros a la izda (rellenar con 0)
	 * @param valor - valor a poner
	 * @param tam   - tamaño final de la cadena
	 * @return resultado
	 */
	public static String lpad(long valor, int tam) {
		return StringUtils.leftPad(String.valueOf(valor), tam, '0');
	}
}
