package Ventanas;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import beans.TareaBean;
import comun.Constantes;
import comun.Utilidades;
import logica.GestorTareas;

public class PanelAltaTareas extends JPanel {
	
	private static final long serialVersionUID = -3623138103184899729L;
	private final static String CLASE = PanelAltaTareas.class.getName();
	protected static Logger log = Logger.getLogger(CLASE);

	private JTextField tbxNombreTarea;
	private JTextField tbxDescripcion;
	private JTextField tbxCodigo1;
	private JTextField tbxCodigo3;
	private JTextField tbxCodigo2;
	private JButton btnAltaTarea;
	private JButton btnLimpiar;
	private JLabel lblVacio;
	private JPanel panelPrincipal;
	private ControlTabla control;
	private GestorTareas objGestorTareas;

	/**
	 * Create the panel.
	 * @param objGT - referencia al gestor de tareas
	 * @param control 
	 */
	public PanelAltaTareas(ControlTabla controlTab, GestorTareas objGT, JPanel pnlPrincipal) {
		control         = controlTab;
		objGestorTareas = objGT;
		panelPrincipal  = pnlPrincipal;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths  = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights    = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights    = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		lblVacio = new JLabel("           ");
		GridBagConstraints gbc_lblAaaa = new GridBagConstraints();
		gbc_lblAaaa.insets = new Insets(0, 0, 5, 5);
		gbc_lblAaaa.gridx  = 1;
		gbc_lblAaaa.gridy  = 0;
		add(lblVacio, gbc_lblAaaa);
		
		JLabel lblNomTarea = new JLabel("Nombre Tarea:");
		GridBagConstraints gbc_lblNomTarea = new GridBagConstraints();
		gbc_lblNomTarea.anchor = GridBagConstraints.WEST;
		gbc_lblNomTarea.insets = new Insets(0, 0, 5, 5);
		gbc_lblNomTarea.gridx  = 1;
		gbc_lblNomTarea.gridy  = 1;
		add(lblNomTarea, gbc_lblNomTarea);
		
		tbxNombreTarea = new JTextField();
		GridBagConstraints gbc_tbxNombreTarea = new GridBagConstraints();
		gbc_tbxNombreTarea.anchor = GridBagConstraints.WEST;
		gbc_tbxNombreTarea.insets = new Insets(0, 0, 5, 5);
		gbc_tbxNombreTarea.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbxNombreTarea.gridx = 2;
		gbc_tbxNombreTarea.gridy = 1;
		add(tbxNombreTarea, gbc_tbxNombreTarea);
		tbxNombreTarea.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.WEST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 1;
		gbc_lblDescripcion.gridy = 2;
		add(lblDescripcion, gbc_lblDescripcion);
		
		tbxDescripcion = new JTextField();
		GridBagConstraints gbc_tbxDescripcion = new GridBagConstraints();
		gbc_tbxDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_tbxDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbxDescripcion.gridx = 2;
		gbc_tbxDescripcion.gridy = 2;
		add(tbxDescripcion, gbc_tbxDescripcion);
		tbxDescripcion.setColumns(10);
		
		JLabel lblCampo = new JLabel("Campo 1:");
		GridBagConstraints gbc_lblCampo = new GridBagConstraints();
		gbc_lblCampo.anchor = GridBagConstraints.WEST;
		gbc_lblCampo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCampo.gridx = 1;
		gbc_lblCampo.gridy = 3;
		add(lblCampo, gbc_lblCampo);
		
		tbxCodigo1 = new JTextField();
		GridBagConstraints gbc_tbxCodigo1 = new GridBagConstraints();
		gbc_tbxCodigo1.insets = new Insets(0, 0, 5, 5);
		gbc_tbxCodigo1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbxCodigo1.gridx = 2;
		gbc_tbxCodigo1.gridy = 3;
		add(tbxCodigo1, gbc_tbxCodigo1);
		tbxCodigo1.setColumns(10);
		
		JLabel lblCampo_1 = new JLabel("Campo 2:");
		GridBagConstraints gbc_lblCampo_1 = new GridBagConstraints();
		gbc_lblCampo_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCampo_1.gridx = 4;
		gbc_lblCampo_1.gridy = 3;
		add(lblCampo_1, gbc_lblCampo_1);
		
		tbxCodigo2 = new JTextField();
		GridBagConstraints gbc_tbxCodigo2 = new GridBagConstraints();
		gbc_tbxCodigo2.insets = new Insets(0, 0, 5, 5);
		gbc_tbxCodigo2.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbxCodigo2.gridx = 5;
		gbc_tbxCodigo2.gridy = 3;
		add(tbxCodigo2, gbc_tbxCodigo2);
		tbxCodigo2.setColumns(10);
		
		JLabel lblCampo_2 = new JLabel("Campo 3:");
		lblCampo_2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblCampo_2 = new GridBagConstraints();
		gbc_lblCampo_2.anchor = GridBagConstraints.WEST;
		gbc_lblCampo_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblCampo_2.gridx = 1;
		gbc_lblCampo_2.gridy = 4;
		add(lblCampo_2, gbc_lblCampo_2);
		
		tbxCodigo3 = new JTextField();
		GridBagConstraints gbc_tbxCodigo3 = new GridBagConstraints();
		gbc_tbxCodigo3.insets = new Insets(0, 0, 5, 5);
		gbc_tbxCodigo3.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbxCodigo3.gridx = 2;
		gbc_tbxCodigo3.gridy = 4;
		add(tbxCodigo3, gbc_tbxCodigo3);
		tbxCodigo3.setColumns(10);
		
		btnLimpiar = new JButton("Limpiar");
		btnLimpiar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				limpiar();
			}
		});
		
		btnAltaTarea = new JButton("Alta tarea");
		btnAltaTarea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nombre = Constantes.VACIO;
				String descrp = Constantes.VACIO;
				TareaBean tarea = null;

				try{
					nombre = tbxNombreTarea.getText();
					descrp = tbxDescripcion.getText();
					
					if(nombre.isEmpty()){
						JOptionPane.showMessageDialog(null, "El campo \"Nombre Tarea\" no puede estar vacio");
					}
					else if(descrp.isEmpty()){
						JOptionPane.showMessageDialog(null, "El campo \"Descripcion\" no puede estar vacio");
					}
					else{
						tarea = new TareaBean(nombre, descrp, tbxCodigo1.getText(), tbxCodigo2.getText(), tbxCodigo3.getText());
						control.anhadeFila(tarea);
						Utilidades.actualizarCBXTareas(panelPrincipal, objGestorTareas);
						limpiar();
					}
				}
				catch (Exception ex) {
					log.log(Level.SEVERE, "Error al recuperar los datos de alta de la tarea: " + ex.getMessage());
				}
			}
		});
		GridBagConstraints gbc_btnAltaTarea = new GridBagConstraints();
		gbc_btnAltaTarea.insets = new Insets(0, 0, 5, 5);
		gbc_btnAltaTarea.gridx = 2;
		gbc_btnAltaTarea.gridy = 6;
		add(btnAltaTarea, gbc_btnAltaTarea);
		GridBagConstraints gbc_btnLimpiar = new GridBagConstraints();
		gbc_btnLimpiar.insets = new Insets(0, 0, 5, 5);
		gbc_btnLimpiar.gridx = 4;
		gbc_btnLimpiar.gridy = 6;
		add(btnLimpiar, gbc_btnLimpiar);

	}

	
	/**
	 * Metodo para borrar el contenido de las cajas
	 */
	private void limpiar(){
		tbxNombreTarea.setText(Constantes.VACIO);
		tbxDescripcion.setText(Constantes.VACIO);
		tbxCodigo1.setText(Constantes.VACIO);
		tbxCodigo3.setText(Constantes.VACIO);
		tbxCodigo2.setText(Constantes.VACIO);
	}
		
	
	/**
	 * @return the tbxNombreTarea
	 */
	public final JTextField getTbxNombreTarea() {
		return tbxNombreTarea;
	}

	/**
	 * @return the tbxDescripcion
	 */
	public final JTextField getTbxDescripcion() {
		return tbxDescripcion;
	}

	/**
	 * @return the tbxCodigo1
	 */
	public final JTextField getTbxCodigo1() {
		return tbxCodigo1;
	}

	/**
	 * @return the tbxCodigo3
	 */
	public final JTextField getTbxCodigo3() {
		return tbxCodigo3;
	}

	/**
	 * @return the tbxCodigo2
	 */
	public final JTextField getTbxCodigo2() {
		return tbxCodigo2;
	}

	/**
	 * @return the btnAltaTarea
	 */
	public final JButton getBtnAltaTarea() {
		return btnAltaTarea;
	}


	/**
	 * @return the btnLimpiar
	 */
	public final JButton getBtnLimpiar() {
		return btnLimpiar;
	}

	
	
}
