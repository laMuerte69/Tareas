package Ventanas;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Principal {

    private int ancho;
    private int alto;

    
    /**
     * Constructor por defecto
     */
    public Principal(){
	ancho     = 400;
	alto      = 200;
    }
    
    
    /**
     * Contructor con parametros basicos de la ventana
     * @param iAncho - ancho de la ventana
     * @param iAlto  - alto de la ventana
     */
    public Principal(int iAncho, int iAlto){
	ancho   = iAncho;
	alto    = iAlto;
    }


    /**
     * Metodo para crear la ventana de la aplicacion
     */
    public void crear(){

	JFrame ventana = new JFrame();

	//--------------------------------------------------------------------//
	// Datos de la ventana
	//--------------------------------------------------------------------//
	configurarVentana(ventana);

	
	//--------------------------------------------------------------------//
	// Botones de la ventana
	//--------------------------------------------------------------------//
	crearBotones(ventana);


	//--------------------------------------------------------------------//
	// ComboBox de la ventana
	//--------------------------------------------------------------------//
	crearListaTareas(ventana);


	//--------------------------------------------------------------------//
	// Tiempo de la ventana
	//--------------------------------------------------------------------//
	crearTiempo(ventana);
    }



    private void configurarVentana(JFrame ventana) {
	//Configuracion basica
	ventana.setSize(ancho, alto); //400 width and 500 height  
	ventana.setLayout(null);      //using no layout managers  
	ventana.setVisible(true);     //making the frame visible
	
	//Icono de la ventana
	Image icon = Toolkit.getDefaultToolkit().getImage("C:\\Workspace_solvencia\\Tareas\\Resources\\icono.png");
	ventana.setIconImage(icon); 
    }


    private void crearBotones(JFrame ventana) {
	JButton btnPlay = new JButton("Play");  
	btnPlay.setBounds(10,10,60,30);
	ventana.add(btnPlay);
    }


    private void crearListaTareas(JFrame ventana) {
	Vector<String> lstTareas = new Vector<String>();  
	lstTareas.add("Tarea 1");  
        lstTareas.add("Tarea 2");  
        lstTareas.add("Tarea 3");  
        lstTareas.add("Tarea 4");  
	
        JComboBox  cbxTareas = new JComboBox (lstTareas);  
	cbxTareas.setBounds(80,10,250,30);
	ventana.add(cbxTareas);
    }
    
    
    private void crearTiempo(JFrame ventana) {
	JLabel  lbTiempo = new JLabel("12:31");
	lbTiempo.setBounds(350,10,100,30);
	ventana.add(lbTiempo);
    }
}
