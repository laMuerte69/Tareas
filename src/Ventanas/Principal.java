package Ventanas;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import acciones.AccionCerrar;
import acciones.AccionMas;
import comun.Constantes;
import comun.Utilidades;
import logica.GestorTareas;

public class Principal {

    private int ancho;
    private int alto;
    private GestorTareas objGT;
    private JButton      btnPlay;
	private JComboBox<String> cbxTareas;

    
    /**
     * Constructor por defecto
     */
    public Principal(){
    	ancho     = 400;
    	alto      = 200;
    	objGT     = new GestorTareas();
    	btnPlay   = null;
    	cbxTareas = new JComboBox<String> ();
    }
    
    
    /**
     * Contructor con parametros basicos de la ventana
     * @param iAncho - ancho de la ventana
     * @param iAlto  - alto de la ventana
     */
    public Principal(int iAncho, int iAlto){
    	ancho     = iAncho;
    	alto      = iAlto;
    	objGT     = new GestorTareas();
    	btnPlay   = null;
    	cbxTareas = new JComboBox<String> ();
    }


    /**
     * Metodo para crear la ventana de la aplicacion
     * @throws Exception 
     */
    public void crear() throws Exception{

	JFrame ventana = null;
	JPanel panelPrincipal = null;

		try{
			ventana = new JFrame("Tareas");
			panelPrincipal = new JPanel(new FlowLayout(FlowLayout.LEFT));
			
			obtenerDatosTareas();
			
	
			//--------------------------------------------------------------------//
			// Datos de la ventana
			//--------------------------------------------------------------------//
			//Configuracion basica
			ventana.setSize(ancho, alto); //400 width and 500 height  
			ventana.setResizable(false);
			ventana.setLayout(null);      //using no layout managers
	
			//Cierre ventana
			ventana.addWindowListener(new AccionCerrar(ventana, objGT).btnCerrar());
		
			//Icono de la ventana
			Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Workspace_solvencia\\Tareas\\Resources\\icono.png");
			ventana.setIconImage(icon); 
	
	
			//--------------------------------------------------------------------//
			// Datos del panel principal
			//--------------------------------------------------------------------//
			panelPrincipal.setBackground(Color.cyan);
	
	
			//--------------------------------------------------------------------//
			// Botones de la ventana
			//--------------------------------------------------------------------//
			crearBotones(panelPrincipal);
	
	
			//--------------------------------------------------------------------//
			// ComboBox de la ventana
			//--------------------------------------------------------------------//
			crearListaTareas(panelPrincipal);
		
		
			//--------------------------------------------------------------------//
			// Tiempo de la ventana
			//--------------------------------------------------------------------//
			crearTiempo(panelPrincipal);
			
			//--------------------------------------------------------------------//
			// Boton "+" para acceder a mas funcionalidades
			//--------------------------------------------------------------------//
			crearBotonMas(ventana, panelPrincipal);
		
	
			//Añadimos el panel con los botones principales
			panelPrincipal.setVisible(true);
			panelPrincipal.setBounds(Constantes.X_PANEL, Constantes.X_PANEL_PRINCIPAL, Constantes.ANCHO_PANEL, Constantes.ALTO_PANEL_PRINCIPAL);
			ventana.add(panelPrincipal);

			ventana.setLocation(400, 150);
			ventana.setVisible(true);     //making the frame visible
		}
		catch (Exception e) {
			String msgErr = "Principal::Crear() " + e.getMessage();
			throw new Exception(msgErr);
		}
    }


	private void crearBotones(JPanel panelSuperior) throws Exception {
		try{
			btnPlay = new JButton("Play");
			btnPlay.setName("btnPlay");
			
			if(cbxTareas.getItemCount() == 0){
				btnPlay.setEnabled(false);
			}
			else{
				btnPlay.setEnabled(true);    			
			}
			
			panelSuperior.add(btnPlay);
		}
		catch (Exception e) {
			throw new Exception("Principal::crearBotones(): " + e.getMessage());
		}
    }


    /**
     * Metodo para crear el elemento comboBox del panel principal
     * @param panelSuperior - referencia al panel principal que contiene los elementos
     * @throws Exception
     */
    private void crearListaTareas(JPanel panelSuperior) throws Exception {

    	try{
    		cbxTareas.setName("cbxLstTareas");
    		cbxTareas.setPreferredSize(new Dimension(300, 25));

    		if(cbxTareas.getItemCount() == 0){
    			cbxTareas.setEnabled(false);
    		}
    		else{
    			cbxTareas.setEnabled(true);    			
    		}

    		panelSuperior.add(cbxTareas);
    	}
    	catch (Exception e) {
			throw new Exception("Principal::crearListaTareas(): " + e.getMessage());
		}
    }
    
    
    /**
     * Metodo para crear la etiqueta del panel principal que marca la hora
     * @param panelSuperior - referencia al panel principal que contiene los elementos
     * @throws Exception
     */
    private void crearTiempo(JPanel panelSuperior) throws Exception {
		JLabel  lbTiempo = new JLabel("00:00");
		lbTiempo.setName("lblTiempo");
		lbTiempo.setText(Utilidades.obtenerHora());
		panelSuperior.add(lbTiempo);
    }


    private void crearBotonMas(JFrame ventana, JPanel panelSuperior) throws Exception {
		JButton btnMas = null;
	
		try{
		    btnMas = new JButton("+");
		    btnMas.setName("btnMas");
		    btnMas.addActionListener(new AccionMas(ventana, panelSuperior, objGT).redimensionar());
		    btnMas.setActionCommand("btnMas");
		    panelSuperior.add(btnMas);
		}
		catch (Exception e) {
		    String msgErr = "Error al redimiensionar la ventana: " + e.getMessage();
		    throw new Exception(msgErr);
		}
    }


    /**
     * Metodo para obtener los datos de las tareas
     * @throws Exception 
     */
    private void obtenerDatosTareas() throws Exception {
		try{
			objGT.cargarTareasBBDD();
			
			//Actualizamos el TBX
			for(String tarea: objGT.obtenerTareasCbox()){
				cbxTareas.addItem(tarea);
			}
			
			//Actualizamos el listado de datos tareas
			//TODO implementar actualizar listado datos tareas
		}
		catch (Exception e) {
			String msgErr = "Principal::obtenerDatosTareas() " + e.getMessage();
			throw new Exception(msgErr);
		}
	}

}
