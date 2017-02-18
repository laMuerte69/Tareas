package acciones;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import beans.DatosTareasBean;
import comun.Constantes;
import comun.Utilidades;
import logica.GestorTareas;

public class AccionBotonPlay {

	private final static String CLASE = AccionBotonPlay.class.getName();
	protected static Logger log = Logger.getLogger(CLASE);
    private GestorTareas objGT;
    private JPanel       panelPrincipal;
    private DatosTareasBean dTarea;
    private AccionMas objAccionMas;

	
	public AccionBotonPlay(JPanel panelSuperior, GestorTareas gt, AccionMas accionMas) throws Exception{
		objGT          = gt;
		panelPrincipal = panelSuperior;
		dTarea         = null;
		objAccionMas   = accionMas;
	}
	
	
	public ActionListener tiempoTarea() throws Exception {
		ActionListener resultado = null;

		try{
			resultado = new ActionListener() {

		        @Override
		        public void actionPerformed(ActionEvent e) {
		        	try {
			            JButton botonPlay = (JButton) e.getSource();
		
			            if(botonPlay.getActionCommand().equals("btnPlay") && botonPlay.isEnabled()){
			            	if(Constantes.BTN_PLAY.equals(botonPlay.getText())){
			            		if(!btnMasEstaDesplegado()){
				            		botonPlay.setText(Constantes.BTN_STOP);
				            		iniciarTiempoTarea();
			            		}
			            		else{
			            			JOptionPane.showMessageDialog(panelPrincipal, "No se puede contabilizar el tiempo de la tarea cuando las opciones estan desplegadas", "Error pantalla", JOptionPane.WARNING_MESSAGE);
			            		}
			            	}
			            	else{
			            		botonPlay.setText(Constantes.BTN_PLAY);
			            		pararTiempoTarea();
			            	}
			            }
					} catch (Exception e1) {
    	        		String msgErr = CLASE + "::tiempoTarea(): "  + e1.getMessage();
    	        		JOptionPane.showMessageDialog(panelPrincipal, msgErr, "Error tiempoTarea() !!", JOptionPane.ERROR_MESSAGE);
    	        		log.log(Level.SEVERE, msgErr);
					}
		        }

		        /**
		         * Metodo para saber si el boton mas esta desplegado
		         * @return true: esta desplegado; false: esta contraido
		         */
		        private boolean btnMasEstaDesplegado() {
					boolean resultado = false;
					for (Component comp : panelPrincipal.getComponents()) {
						if(comp.getName().equals("btnMas")){
							if(Constantes.BTN_MAS_MENOS.equals(((JButton) comp).getText())){
								resultado = true;
							}
							break;
						}
					}

					return resultado;
				}

				/**
		         * Metodo para parar el tiempo de la tarea y habilitar el resto de controles
		         * @throws Exception 
		         */
				@SuppressWarnings("unchecked")
				private void pararTiempoTarea() throws Exception {
					try{

						for (Component comp : panelPrincipal.getComponents()) {
							//desbloqueamos el CBX
							if(comp.getName().equals("cbxLstTareas")){
								JComboBox<String> cbxTareas = (JComboBox<String>) comp;
								cbxTareas.setEnabled(true);
							}
							//desbloqueamos el btnMas
							else if(comp.getName().equals("btnMas")){
								JButton btnMas = (JButton) comp;
								btnMas.setEnabled(true);
							}
						}
						
						//Paramos el tiempo
						dTarea.setFechaFin(Utilidades.obtenerFecha());
						objGT.setDatosTarea(dTarea);
						objAccionMas.getControlLstDatoTareas().anyadeFila(dTarea);
						dTarea = null;
					}
					catch (Exception e) {
						throw new Exception(CLASE + "::pararTiempoTarea(): " + e.getMessage());
					}
				}

				/**
		         * Metodo para iniciar el tiempo de la tarea y bloquear el resto de controles
				 * @throws Exception 
		         */
				@SuppressWarnings("unchecked")
				private void iniciarTiempoTarea() throws Exception {
					String nombreTarea = Constantes.VACIO;

					try{

						for (Component comp : panelPrincipal.getComponents()) {
							//bloqueamos el CBX
							if(comp.getName().equals("cbxLstTareas")){
								JComboBox<String> cbxTareas = (JComboBox<String>) comp;
								cbxTareas.setEnabled(false);
								nombreTarea = (String) cbxTareas.getSelectedItem();
							}
							//bloqueamos el btnMas
							else if(comp.getName().equals("btnMas")){
								JButton btnMas = (JButton) comp;
								btnMas.setEnabled(false);
							}
						}
						
						//Iniciamos el tiempo
						dTarea = new DatosTareasBean();
						dTarea.setId(objGT.getMaximoIdDatosTarea() + 1);
						dTarea.setIdTarea(objGT.obtenerTarea(nombreTarea));
						dTarea.setFechaInicio(Utilidades.obtenerFecha());
					}
					catch (Exception e) {
						throw new Exception(CLASE + "::iniciarTiempoTarea(): " + e.getMessage());
					}
				}
		    };
		}
		catch (Exception e) {
		    String msgErr = CLASE + "::tiempoTarea(): " + e.getMessage();
		    throw new Exception(msgErr);
		}

	return resultado;
    }
}
