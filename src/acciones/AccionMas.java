package acciones;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

import beans.TareaBean;
import comun.Constantes;
import comun.MemoriaCompartida;
import comun.Utilidades;
import logica.GestorTareas;
import tablas.ControlLstDatoTareas;
import tablas.ControlTabla;
import tablas.ControlTablaRehabilitar;
import tablas.ModeloTabla;
import tablas.TablaModeloLstDatoTareas;
import tablas.TablaModeloRehabiliar;
import ventanas.PanelAltaTareas;
import ventanas.PanelGestionTarea;

public class AccionMas {

	private final static String CLASE = AccionMas.class.getName();
	protected static Logger log = Logger.getLogger(CLASE);

    private MemoriaCompartida memoria;
    private GestorTareas objGT;
    private JFrame       ventana;
    private Dimension    dimension;
    private JPanel       panelSecundario;
    private JPanel       panelPrincipal;
    private boolean      primeraVez;
    private ModeloTabla  modelo;
    private ControlTabla control;
    private TablaModeloRehabiliar    tablaModeloRehabilitar;
    private TablaModeloLstDatoTareas modeloLstDatoTareas;
    private ControlTablaRehabilitar controlRehabilitar;
    private ControlLstDatoTareas    controlLstDatoTareas;
    private JTable       tabla;
    private JTable       tablaRehabilitar;
    private JTable       tablaLstDatoTareas;
    private JScrollPane  scrollListadoTareas;
    private JScrollPane  scrollLstRehabilitarTareas;
    private JScrollPane  scrollListadoDatosTareas;


    public AccionMas(JFrame objVentana, JPanel panelSuperior, GestorTareas gt) throws Exception{
		memoria             = MemoriaCompartida.getInstance();
		ventana             = objVentana;
		objGT               = gt;
		dimension           = ventana.getSize();
		panelSecundario     = new JPanel(new FlowLayout(FlowLayout.LEFT));
		panelSecundario.setVisible(false);
		primeraVez          = true;
		modelo              = new ModeloTabla();
		control             = new ControlTabla(modelo);
		scrollListadoTareas = new JScrollPane();
		scrollLstRehabilitarTareas = new JScrollPane();
		scrollListadoDatosTareas   = new JScrollPane();
		panelPrincipal      = panelSuperior;

		tablaModeloRehabilitar = new TablaModeloRehabiliar(objGT);
		controlRehabilitar     = new ControlTablaRehabilitar(tablaModeloRehabilitar);
		
		modeloLstDatoTareas    = new TablaModeloLstDatoTareas(objGT);
		controlLstDatoTareas   = new ControlLstDatoTareas(modeloLstDatoTareas);
		      
    }


	public ActionListener redimensionar() throws Exception {
		ActionListener resultado = null;

		try{
			resultado = new ActionListener() {

		        @Override
		        public void actionPerformed(ActionEvent e) {
		            JButton botonMas = (JButton) e.getSource();
	
		            if(botonMas.getActionCommand().equals("btnMas")){
		            	if(memoria.isRedimensionarVentana()){
	        	        	memoria.setRedimensionarVentana(false);
	        	        	try {
	        	        		encogerVentana(botonMas);
	        	        	} catch (Exception e1) {
	        	        		String msgErr = CLASE + "::redimensionar()[encogerVentana]: "  + e1.getMessage();
	        	        		JOptionPane.showMessageDialog(panelSecundario, msgErr, "Error redimensionar() !!", JOptionPane.ERROR_MESSAGE);
	        	        		log.log(Level.SEVERE, msgErr);
	        	        	}
		            	}
	        	        else{
	        	        	memoria.setRedimensionarVentana(true);
	        	        	try {
	        	        		agrandarVentana(botonMas);
	        	        	} catch (Exception e1) {
	        	        		String msgErr = CLASE + "::redimensionar()[agrandarVentana]: "  + e1.getMessage();
	        	        		JOptionPane.showMessageDialog(panelSecundario, msgErr, "Error redimensionar() !!", JOptionPane.ERROR_MESSAGE);
	        	        		log.log(Level.SEVERE, msgErr);
	        	        	}
	        	        }
		            }
		        }
		    };
		}
		catch (Exception e) {
		    throw new Exception(CLASE + "::redimensionar(): " + e.getMessage());
		}

	return resultado;
    }
    

    /**
     * Metodo para encoger la ventana
     * @param botonMas 
     * @throws Exception 
     */
    private void encogerVentana(JButton botonMas) throws Exception {
		try{
		    ventana.setSize(dimension);
		    botonMas.setText(Constantes.BTN_MAS_MAS);
		    
		    //Configuramos el panel secundario
		    panelSecundario.setVisible(false);
		}
		catch (Exception e) {
		    throw new Exception(CLASE + "::encogerVentana(): " + e.getMessage());
		}
    }


    /**
     * Metodo para agrandar la ventana
     * @param botonMas 
     * @throws Exception 
     */
    private void agrandarVentana(JButton botonMas) throws Exception {
		try{
		    double x = dimension.getWidth();
		    double y = dimension.getHeight();
	
		    botonMas.setText(Constantes.BTN_MAS_MENOS);
		    ventana.setSize((int)x, (int)y * 3);
		    
		    //Configuramos el panel secundario
		    panelSecundario.setVisible(true);
		    if(primeraVez){
		    	primeraVez = false;
		    	panelSecundario.setBackground(Color.WHITE);
		    	panelSecundario.setBounds(Constantes.X_PANEL, Constantes.X_PANEL_SECUNDARIO, Constantes.ANCHO_PANEL, Constantes.ALTO_PANEL_SECUNDARIO);
		    	
		    	crearPanelSecundario(panelSecundario);
		    	ventana.add(panelSecundario);
		    	
		    	cargarDatosTablas();
		    }
		}
		catch (Exception e) {
		    throw new Exception(CLASE + "::encogerVentana(): " + e.getMessage());
		}
	    
    }


    /**
     * Metodo para cargar los datos en las tablas de listados
     * @throws Exception 
     */
    private void cargarDatosTablas() throws Exception {
		try{
			//Tareas
			for(Entry<Integer, TareaBean> objTarea : objGT.obtenerTareasListado().entrySet()){
				//Actualizamos el listado de tareas
				if(!objTarea.getValue().isBajaLogica()){
					control.anhadeFila(objTarea.getValue(), true);
				}
			}
		}
		catch (Exception e) {
			throw new Exception(CLASE + "::cargarDatosTablas(): " + e.getMessage());
		}
	}


    /**
     * Metodo para crear el panel secundario (debajo del panel principal
     * @param panel - referencial al panel secundario
     * @throws Exception
     */
	private void crearPanelSecundario(JPanel panel) throws Exception {
		JTabbedPane tabPrincipal = null;
		
		try{
			tabPrincipal = new JTabbedPane();
			tabPrincipal.setName("tabPrincipal");

			tabListadoTareas(tabPrincipal);
			tabAltaTareas(tabPrincipal);
			tabRehabilitarTareas(tabPrincipal);
			tabListadoDatosTareas(tabPrincipal);
			
			panel.add(tabPrincipal);
		}
		catch (Exception e) {
			throw new Exception(CLASE + "::crearPanelSecundario(): " + e.getMessage());
		}
	}


	/**
	 * Metodo para crear el tab de listado de tareas
	 * @param tabPrincipal - referencia al JTab donde irá insertado este tab 
	 * @throws Exception
	 */
	private void tabListadoTareas(JTabbedPane tabPrincipal) throws Exception {
		JPanel tabListadoTareas  = null;
		
		try{
			tabListadoTareas = new JPanel();
			tabListadoTareas.setName("tabListadoTareas");
			tabListadoTareas.setPreferredSize(new Dimension(Constantes.LARGO_TAB_PANEL_SEC, Constantes.ALTO_TAB_PANEL_SEC));
			
			panelListadoTareas(tabListadoTareas);
			
			tabPrincipal.addTab("Listado tareas", tabListadoTareas);
		}
		catch (Exception e) {
			throw new Exception(CLASE + "::tabListadoTareas(): " + e.getMessage());
		}
	}


	/**
	 * Metodo para crear el tab donde se daraán de alta las tareas
	 * @param tabPrincipal - referencia al JTab donde irá insertado este tab 
	 * @throws Exception
	 */
	private void tabAltaTareas(JTabbedPane tabPrincipal) throws Exception {
		JPanel tabAltaTareas      = new JPanel();
		PanelAltaTareas panelAlta = null;
		
		try{
			panelAlta = new PanelAltaTareas(control, objGT, panelPrincipal);
			panelAlta.setName("tabAltaTareas");
			tabAltaTareas.setPreferredSize(new Dimension(Constantes.LARGO_TAB_PANEL_SEC, Constantes.ALTO_TAB_PANEL_SEC));
			
	        //Añadimos el nuevo Panel al tab
	        tabAltaTareas = panelAlta;

			tabPrincipal.addTab("Alta tareas", tabAltaTareas);
		}
		catch (Exception e) {
			throw new Exception(CLASE + "::tabAltaTareas(): " + e.getMessage());
		}
	}



	/**
	 * Metodo para rehabilitar tareas que se han dado de baja
	 * @param tabPrincipal
	 * @throws Exception 
	 */
	private void tabRehabilitarTareas(JTabbedPane tabPrincipal) throws Exception {
		
		try{
			tablaRehabilitar = new JTable(tablaModeloRehabilitar);
			tablaRehabilitar.setName("tabRehabilitarTareas");

			tablaRehabilitar.getColumnModel().getColumn(0).setPreferredWidth(50);
			tablaRehabilitar.getColumnModel().getColumn(1).setPreferredWidth(200);
			tablaRehabilitar.getColumnModel().getColumn(2).setPreferredWidth(195);
			
			tablaRehabilitar.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) 
			      {
			         int fila    = tablaRehabilitar.rowAtPoint(e.getPoint());
			         int columna = tabla.columnAtPoint(e.getPoint());

			         if(2 == columna && fila > -1){
			        	 int id = (Integer) tablaModeloRehabilitar.getValueAt(fila,0);
			        	 TareaBean tarea = objGT.obtenerTarea(id);

			        	 //Añadimos la tarea de la tabla listado
						 control.reHabilitarFila(tarea);

						 //Eliminamos la tarea a la tabla de rehabilitar
						 controlRehabilitar.borraFila(tarea.getId());


				         try {
							Utilidades.actualizarCBXTareas(panelPrincipal, objGT);
				         } catch (Exception e1) {
				        	 String msgErr = "Error al actualizar el combo de tareas: " + e1.getMessage();
				        	 JOptionPane.showMessageDialog(panelSecundario, msgErr, "Error tabRehabilitarTareas() !!", JOptionPane.ERROR_MESSAGE);
				        	 log.log(Level.SEVERE, msgErr);
				         }

			        	 log.log(Level.INFO, "Rehabilitar Tarea ["+fila+"]["+columna+"]: " + tarea.getNombre());
			         }
			      }
			});

			scrollLstRehabilitarTareas.setViewportView(tablaRehabilitar);
			scrollLstRehabilitarTareas.setColumnHeaderView (tablaRehabilitar.getTableHeader());
			scrollLstRehabilitarTareas.setPreferredSize(new Dimension(Constantes.ANCHO_TABLA_TAREAS, Constantes.ALTO_TABLA_TAREAS));

			tabPrincipal.addTab("Rehabilitar tareas", scrollLstRehabilitarTareas);
		}
		catch (Exception e) {
			throw new Exception(CLASE + "::tabRehabilitarTareas(): " + e.getMessage());
		}
	}
	

	/**
	 * Metodo para crear el tab donde se listaran los datos de las tareas
	 * @param tabPrincipal - referencia al JTab donde ira insertado este tab 
	 * @throws Exception
	 */
	private void tabListadoDatosTareas(JTabbedPane tabPrincipal) throws Exception {
		JPanel tabListadoDatosTareas  = null;

		try{
			tabListadoDatosTareas = new JPanel();
			tabListadoDatosTareas.setName("tabListadoDatosTareas");
			tabListadoDatosTareas.setPreferredSize(new Dimension(Constantes.LARGO_TAB_PANEL_SEC, Constantes.ALTO_TAB_PANEL_SEC));
			
			panelListadoDatosTareas(tabListadoDatosTareas);

			tabPrincipal.addTab("Datos tareas", tabListadoDatosTareas);
			
		}
		catch (Exception e) {
			throw new Exception(CLASE + "::tabListadoDatosTareas(): " + e.getMessage());
		}
	}


	/**
	 * Metodo para crear el panel que gestiona las tareas
	 * @param tabAltaTareas - panel donde se insertara la tabla de las tareas
	 * @throws Exception
	 */
	private void panelListadoTareas(JPanel tabAltaTareas) throws Exception {
		try{
			// Se crea el JScrollPane, el JTable y se pone la cabecera...
			tabla = new JTable(modelo);
			tabla.setName("tablaListadoTareas");

			tabla.getColumnModel().getColumn(0).setPreferredWidth(50);
			tabla.getColumnModel().getColumn(1).setPreferredWidth(150);
			tabla.getColumnModel().getColumn(2).setPreferredWidth(245);
			
			tabla.addMouseListener(new MouseAdapter() {
				public void mouseClicked(MouseEvent e) 
			      {
			         int fila    = tabla.rowAtPoint(e.getPoint());
			         int columna = 0;
//			         int columna = tabla.columnAtPoint(e.getPoint());
			         
			         if ((fila > -1) && e.getClickCount() == 2){
			        	int id = (Integer) modelo.getValueAt(fila,columna);

			        	TareaBean tarea = objGT.obtenerTarea(id);
						new PanelGestionTarea(tarea);

						if(tarea.isBajaLogica()){
							//Eliminamos la tarea de la tabla listado
							control.borraFila(id);
							//Añadimos la tarea a la tabla de rehabilitar
							controlRehabilitar.anyadeFila(tarea);
						}
			         
				         try {
							Utilidades.actualizarCBXTareas(panelPrincipal, objGT);
				         } catch (Exception e1) {
				        	 String msgErr = "Error al actualizar el combo de tareas: " + e1.getMessage();
				        	 JOptionPane.showMessageDialog(panelSecundario, msgErr, "Error panelListadoTareas() !!", JOptionPane.ERROR_MESSAGE);
				        	 log.log(Level.SEVERE, msgErr);
				         }
			         }
			      }
			});

			scrollListadoTareas.setViewportView(tabla);
			scrollListadoTareas.setColumnHeaderView (tabla.getTableHeader());
			scrollListadoTareas.setPreferredSize(new Dimension(Constantes.ANCHO_TABLA_TAREAS, Constantes.ALTO_TABLA_TAREAS));

			tabAltaTareas.add(scrollListadoTareas);	        
		}
		catch (Exception e) {
			throw new Exception(CLASE + "::panelListadoTareas(): " + e.getMessage());
		}
	}


	/**
	 * Metodo para crear el panel que muestra los datos de las tareas
	 * @param tabListadoDatosTareas - panel donde se insertara la tabla de los datos de las tareas
	 * @throws Exception
	 */
	private void panelListadoDatosTareas(JPanel tabListadoDatosTareas) throws Exception {
		try{
			// Se crea el JScrollPane, el JTable y se pone la cabecera...
			tablaLstDatoTareas = new JTable(modeloLstDatoTareas);
			tablaLstDatoTareas.setName("tablaListadoDatosTareas");

			tablaLstDatoTareas.getColumnModel().getColumn(0).setPreferredWidth(40);
			tablaLstDatoTareas.getColumnModel().getColumn(1).setPreferredWidth(200);
			tablaLstDatoTareas.getColumnModel().getColumn(2).setPreferredWidth(105);
			tablaLstDatoTareas.getColumnModel().getColumn(3).setPreferredWidth(105);

			scrollListadoDatosTareas.setViewportView(tablaLstDatoTareas);
			scrollListadoDatosTareas.setColumnHeaderView (tablaLstDatoTareas.getTableHeader());
			scrollListadoDatosTareas.setPreferredSize(new Dimension(Constantes.ANCHO_TABLA_TAREAS, Constantes.ALTO_TABLA_TAREAS));

			tabListadoDatosTareas.add(scrollListadoDatosTareas);	        
		}
		catch (Exception e) {
			throw new Exception(CLASE + "::panelListadoDatosTareas(): " + e.getMessage());
		}
	}


	/**
	 * @return the controlLstDatoTareas
	 */
	public final ControlLstDatoTareas getControlLstDatoTareas() {
		return controlLstDatoTareas;
	}
	
}
