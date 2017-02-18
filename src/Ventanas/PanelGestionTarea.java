package Ventanas;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

import beans.TareaBean;

public class PanelGestionTarea extends JDialog {

	private static final long serialVersionUID = 8809426482289658615L;
	private final static String CLASE = PanelGestionTarea.class.getName();
	protected static Logger log = Logger.getLogger(CLASE);
	private final int ALTO = 50;

	private TareaBean  tareaCopia;
	private TareaBean  tarea;
	private JTextField tbxTarea;
	private JTextField tbxDescripcion;
	private JTextField tbxCodigo1;
	private JTextField tbxCodigo3;
	private JTextField tbxCodigo2;
	private JTextField tbxID;
	private JButton btnModificar;
	private JButton btnAceptar;
	private JButton btnEliminar;
	private JButton btnCancelar;
	private JButton btnAceptarModificar;
	private JButton btnCancelarModificar;

	/**
	 * Create the panel.
	 */
	public PanelGestionTarea(TareaBean objTarea) {
		setResizable(false);
		setAlwaysOnTop(true);

		tareaCopia = objTarea;
		tarea      = objTarea;

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths  = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights    = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights    = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblDatosTarea = new JLabel("DATOS TAREA");
		GridBagConstraints gbc_lblDatosTarea = new GridBagConstraints();
		gbc_lblDatosTarea.gridwidth = 4;
		gbc_lblDatosTarea.insets = new Insets(0, 0, 5, 0);
		gbc_lblDatosTarea.gridx = 1;
		gbc_lblDatosTarea.gridy = 0;
		getContentPane().add(lblDatosTarea, gbc_lblDatosTarea);
		
		JLabel lblId = new JLabel("ID:");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.WEST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 1;
		gbc_lblId.gridy = 1;
		getContentPane().add(lblId, gbc_lblId);
		
		tbxID = new JTextField(100);
		tbxID.setEditable(false);
		GridBagConstraints gbc_tbxID = new GridBagConstraints();
		gbc_tbxID.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbxID.anchor = GridBagConstraints.WEST;
		gbc_tbxID.insets = new Insets(0, 0, 5, 5);
		gbc_tbxID.gridx = 2;
		gbc_tbxID.gridy = 1;
		getContentPane().add(tbxID, gbc_tbxID);
		tbxID.setText(String.valueOf(tarea.getId()));
		
		JLabel lblTarea = new JLabel("Tarea:");
		GridBagConstraints gbc_lblTarea = new GridBagConstraints();
		gbc_lblTarea.anchor = GridBagConstraints.WEST;
		gbc_lblTarea.insets = new Insets(0, 0, 5, 5);
		gbc_lblTarea.gridx = 3;
		gbc_lblTarea.gridy = 1;
		getContentPane().add(lblTarea, gbc_lblTarea);
		
		tbxTarea = new JTextField(100);
		tbxTarea.setEditable(false);
		GridBagConstraints gbc_tbxTarea = new GridBagConstraints();
		gbc_tbxTarea.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbxTarea.insets = new Insets(0, 0, 5, 0);
		gbc_tbxTarea.gridx = 4;
		gbc_tbxTarea.gridy = 1;
		getContentPane().add(tbxTarea, gbc_tbxTarea);
		tbxTarea.setText(tarea.getNombre());
		
		JLabel lblDescripcion = new JLabel("Descripcion:");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.anchor = GridBagConstraints.WEST;
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.gridx = 1;
		gbc_lblDescripcion.gridy = 2;
		getContentPane().add(lblDescripcion, gbc_lblDescripcion);
		
		tbxDescripcion = new JTextField(200);
		tbxDescripcion.setEditable(false);
		GridBagConstraints gbc_tbxDescripcion = new GridBagConstraints();
		gbc_tbxDescripcion.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbxDescripcion.gridwidth = 3;
		gbc_tbxDescripcion.anchor = GridBagConstraints.WEST;
		gbc_tbxDescripcion.insets = new Insets(0, 0, 5, 0);
		gbc_tbxDescripcion.gridx = 2;
		gbc_tbxDescripcion.gridy = 2;
		getContentPane().add(tbxDescripcion, gbc_tbxDescripcion);
		tbxDescripcion.setText(tarea.getDescripcion());
		
		JLabel lblCodigo = new JLabel("Codigo 1:");
		GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
		gbc_lblCodigo.anchor = GridBagConstraints.WEST;
		gbc_lblCodigo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigo.gridx = 1;
		gbc_lblCodigo.gridy = 3;
		getContentPane().add(lblCodigo, gbc_lblCodigo);
		
		tbxCodigo1 = new JTextField(100);
		tbxCodigo1.setEditable(false);
		GridBagConstraints gbc_tbxCodigo1 = new GridBagConstraints();
		gbc_tbxCodigo1.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbxCodigo1.insets = new Insets(0, 0, 5, 5);
		gbc_tbxCodigo1.gridx = 2;
		gbc_tbxCodigo1.gridy = 3;
		getContentPane().add(tbxCodigo1, gbc_tbxCodigo1);
		tbxCodigo1.setText(tarea.getCodigo1());
		
		JLabel lblCodigo_1 = new JLabel("Codigo 2:");
		GridBagConstraints gbc_lblCodigo_1 = new GridBagConstraints();
		gbc_lblCodigo_1.anchor = GridBagConstraints.WEST;
		gbc_lblCodigo_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigo_1.gridx = 3;
		gbc_lblCodigo_1.gridy = 3;
		getContentPane().add(lblCodigo_1, gbc_lblCodigo_1);
		
		tbxCodigo2 = new JTextField(100);
		tbxCodigo2.setEditable(false);
		GridBagConstraints gbc_tbxCodigo2 = new GridBagConstraints();
		gbc_tbxCodigo2.insets = new Insets(0, 0, 5, 0);
		gbc_tbxCodigo2.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbxCodigo2.gridx = 4;
		gbc_tbxCodigo2.gridy = 3;
		tbxCodigo2.setPreferredSize(new Dimension(40, ALTO));
		getContentPane().add(tbxCodigo2, gbc_tbxCodigo2);
		tbxCodigo2.setText(tarea.getCodigo2());
		
		JLabel lblCodigo_2 = new JLabel("Codigo 3:");
		GridBagConstraints gbc_lblCodigo_2 = new GridBagConstraints();
		gbc_lblCodigo_2.anchor = GridBagConstraints.WEST;
		gbc_lblCodigo_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigo_2.gridx = 1;
		gbc_lblCodigo_2.gridy = 4;
		getContentPane().add(lblCodigo_2, gbc_lblCodigo_2);
		
		tbxCodigo3 = new JTextField(100);
		tbxCodigo3.setEditable(false);
		GridBagConstraints gbc_tbxCodigo3 = new GridBagConstraints();
		gbc_tbxCodigo3.fill = GridBagConstraints.HORIZONTAL;
		gbc_tbxCodigo3.insets = new Insets(0, 0, 5, 5);
		gbc_tbxCodigo3.gridx = 2;
		gbc_tbxCodigo3.gridy = 4;
		tbxCodigo3.setPreferredSize(new Dimension(40, ALTO));
		getContentPane().add(tbxCodigo3, gbc_tbxCodigo3);
		tbxCodigo3.setText(tarea.getCodigo3());
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				log.info("Modificar tarea \n" + tarea.info());
				dispose();
			}
		});
		GridBagConstraints gbc_btnAceptar = new GridBagConstraints();
		gbc_btnAceptar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptar.gridx = 1;
		gbc_btnAceptar.gridy = 6;
		getContentPane().add(btnAceptar, gbc_btnAceptar);
		
		btnModificar = new JButton("Modificar");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tbxTarea.setEditable(true);
				tbxDescripcion.setEditable(true);
				tbxCodigo1.setEditable(true);
				tbxCodigo2.setEditable(true);
				tbxCodigo3.setEditable(true);
				
				btnAceptar.setVisible(false);
				btnCancelar.setVisible(false);
				btnModificar.setVisible(false);
				btnEliminar.setVisible(false);
				btnAceptarModificar.setVisible(true);
				btnCancelarModificar.setVisible(true);
			}
		});
		GridBagConstraints gbc_btnModificar = new GridBagConstraints();
		gbc_btnModificar.insets = new Insets(0, 0, 0, 5);
		gbc_btnModificar.gridx = 2;
		gbc_btnModificar.gridy = 6;
		getContentPane().add(btnModificar, gbc_btnModificar);
		
		btnAceptarModificar = new JButton("Aceptar");
		btnAceptarModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tbxTarea.setEditable(false);
				tbxDescripcion.setEditable(false);
				tbxCodigo1.setEditable(false);
				tbxCodigo2.setEditable(false);
				tbxCodigo3.setEditable(false);
				
				btnAceptar.setVisible(true);
				btnCancelar.setVisible(true);
				btnModificar.setVisible(true);
				btnEliminar.setVisible(true);
				btnAceptarModificar.setVisible(false);
				btnCancelarModificar.setVisible(false);
				
				tarea.setNombre(tbxTarea.getText());
				tarea.setDescripcion(tbxDescripcion.getText());
				tarea.setCodigo1(tbxCodigo1.getText());
				tarea.setCodigo2(tbxCodigo2.getText());
				tarea.setCodigo3(tbxCodigo3.getText());
				
				log.info("Modificar tarea (ACEPTAR) \n" + tarea.info());
			}
		});
		GridBagConstraints gbc_btnAceptarModificar = new GridBagConstraints();
		gbc_btnAceptarModificar.insets = new Insets(0, 0, 0, 5);
		gbc_btnAceptarModificar.gridx = 2;
		gbc_btnAceptarModificar.gridy = 6;
		getContentPane().add(btnAceptarModificar, gbc_btnAceptarModificar);
		
		btnCancelarModificar = new JButton("Cancelar");
		btnCancelarModificar.setVisible(false);
		btnCancelarModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tbxTarea.setEditable(false);
				tbxDescripcion.setEditable(false);
				tbxCodigo1.setEditable(false);
				tbxCodigo2.setEditable(false);
				tbxCodigo3.setEditable(false);
				
				btnAceptar.setVisible(true);
				btnCancelar.setVisible(true);
				btnModificar.setVisible(true);
				btnEliminar.setVisible(true);
				btnAceptarModificar.setVisible(false);
				btnCancelarModificar.setVisible(false);
				
				tbxTarea.setText(tarea.getNombre());
				tbxDescripcion.setText(tarea.getDescripcion());
				tbxCodigo1.setText(tarea.getCodigo1());
				tbxCodigo2.setText(tarea.getCodigo2());
				tbxCodigo3.setText(tarea.getCodigo3());
				
				log.info("Modificar tarea (CANCELAR) \n" + tarea.info());
			}
		});
		GridBagConstraints gbc_btnCancelarModificar = new GridBagConstraints();
		gbc_btnCancelarModificar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelarModificar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelarModificar.gridx = 4;
		gbc_btnCancelarModificar.gridy = 6;
		getContentPane().add(btnCancelarModificar, gbc_btnCancelarModificar);
		
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tarea.setBajaLogica(true);
				log.info("Eliminar tarea \n" + tarea.info());
				dispose();
			}
		});
		GridBagConstraints gbc_btnEliminar = new GridBagConstraints();
		gbc_btnEliminar.insets = new Insets(0, 0, 0, 5);
		gbc_btnEliminar.gridx = 3;
		gbc_btnEliminar.gridy = 6;
		getContentPane().add(btnEliminar, gbc_btnEliminar);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tarea = tareaCopia;
				dispose();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.gridx = 4;
		gbc_btnCancelar.gridy = 6;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		
		// Controles generales de la ventana
		setModal(true);
    	setSize(380, 190);
        setLocation(500, 200);
    	setVisible(true);
	}

}
