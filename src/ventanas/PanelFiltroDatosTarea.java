package ventanas;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import beans.FiltroDatosTareaBean;
import comun.Constantes;

public class PanelFiltroDatosTarea extends JDialog {

	private static final long serialVersionUID = 3016549765400325443L;
	private final static String CLASE = PanelFiltroDatosTarea.class.getName();
	protected static Logger log = Logger.getLogger(CLASE);

	private FiltroDatosTareaBean filtroCopia;
	private FiltroDatosTareaBean filtro;
	private JTextField txtDiaFini;
	private JTextField txtMesFini;
	private JTextField txtAnyoFini;
	private JTextField txtDiaFfin;
	private JLabel label_2;
	private JTextField txtMesFfin;
	private JLabel label_3;
	private JTextField txtAnyoFfin;
	private JButton btnAceptar;
	private JButton btnCancelar;
	private JLabel lblEspacio;
	private JCheckBox chckbxFechaFin;
	private JCheckBox chckbxFechaInicio;
	
	/**
	 * Create the panel.
	 */
	public PanelFiltroDatosTarea(FiltroDatosTareaBean objFiltro) {
		setTitle("Filtro");
		filtroCopia = objFiltro;
		filtro = objFiltro;
		
		setResizable(false);
		setAlwaysOnTop(true);

		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{83, 22, 4, 22, 4, 38, 35, 71, 22, 4, 22, 4, 38, 0};
		gridBagLayout.rowHeights = new int[]{23, 20, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		chckbxFechaInicio = new JCheckBox("Fecha Inicio");
		chckbxFechaInicio.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JCheckBox chk = (JCheckBox) e.getComponent();

				if(chk.isSelected()){
					habilitarFechaInicio();
				}
				else{
					deshabilitarFechaInicio();
				}
			}

			private void deshabilitarFechaInicio() {
				txtDiaFini.setEditable(false);
				txtMesFini.setEditable(false);
				txtAnyoFini.setEditable(false);
				txtDiaFini.setText("dd");
				txtMesFini.setText("mm");
				txtAnyoFini.setText("yyyy");
			}

			private void habilitarFechaInicio() {
				txtDiaFini.setEditable(true);
				txtMesFini.setEditable(true);
				txtAnyoFini.setEditable(true);
				txtDiaFini.setText(Constantes.VACIO);
				txtMesFini.setText(Constantes.VACIO);
				txtAnyoFini.setText(Constantes.VACIO);
			}
		});
		GridBagConstraints gbc_chckbxFechaInicio = new GridBagConstraints();
		gbc_chckbxFechaInicio.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxFechaInicio.gridx = 0;
		gbc_chckbxFechaInicio.gridy = 0;
		getContentPane().add(chckbxFechaInicio, gbc_chckbxFechaInicio);
		
		chckbxFechaFin = new JCheckBox("Fecha Fin");
		chckbxFechaFin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JCheckBox chk = (JCheckBox) e.getComponent();

				if(chk.isSelected()){
					habilitarFechaInicio();
				}
				else{
					deshabilitarFechaInicio();
				}
			}

			private void deshabilitarFechaInicio() {
				txtDiaFfin.setEditable(false);
				txtMesFfin.setEditable(false);
				txtAnyoFfin.setEditable(false);
				txtDiaFfin.setText("dd");
				txtMesFfin.setText("mm");
				txtAnyoFfin.setText("yyyy");
			}

			private void habilitarFechaInicio() {
				txtDiaFfin.setEditable(true);
				txtMesFfin.setEditable(true);
				txtAnyoFfin.setEditable(true);
				txtDiaFfin.setText(Constantes.VACIO);
				txtMesFfin.setText(Constantes.VACIO);
				txtAnyoFfin.setText(Constantes.VACIO);
			}
		});
		GridBagConstraints gbc_chckbxFechaFin = new GridBagConstraints();
		gbc_chckbxFechaFin.insets = new Insets(0, 0, 5, 5);
		gbc_chckbxFechaFin.gridx = 7;
		gbc_chckbxFechaFin.gridy = 0;
		getContentPane().add(chckbxFechaFin, gbc_chckbxFechaFin);
		
		txtDiaFini = new JTextField();
		txtDiaFini.setText("dd");
		txtDiaFini.setEditable(false);
		GridBagConstraints gbc_txtDd = new GridBagConstraints();
		gbc_txtDd.fill = GridBagConstraints.BOTH;
		gbc_txtDd.insets = new Insets(0, 0, 5, 5);
		gbc_txtDd.gridx = 1;
		gbc_txtDd.gridy = 1;
		getContentPane().add(txtDiaFini, gbc_txtDd);
		txtDiaFini.setColumns(2);
		
		JLabel label = new JLabel("/");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 2;
		gbc_label.gridy = 1;
		getContentPane().add(label, gbc_label);
		
		txtMesFini = new JTextField();
		txtMesFini.setText("mm");
		txtMesFini.setEditable(false);
		GridBagConstraints gbc_txtMm = new GridBagConstraints();
		gbc_txtMm.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMm.insets = new Insets(0, 0, 5, 5);
		gbc_txtMm.gridx = 3;
		gbc_txtMm.gridy = 1;
		getContentPane().add(txtMesFini, gbc_txtMm);
		txtMesFini.setColumns(2);
		
		JLabel label_1 = new JLabel("/");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 4;
		gbc_label_1.gridy = 1;
		getContentPane().add(label_1, gbc_label_1);
		
		txtAnyoFini = new JTextField();
		txtAnyoFini.setText("yyyy");
		txtAnyoFini.setEditable(false);
		GridBagConstraints gbc_txtYyyy = new GridBagConstraints();
		gbc_txtYyyy.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtYyyy.insets = new Insets(0, 0, 5, 5);
		gbc_txtYyyy.gridx = 5;
		gbc_txtYyyy.gridy = 1;
		getContentPane().add(txtAnyoFini, gbc_txtYyyy);
		txtAnyoFini.setColumns(4);
		
		txtDiaFfin = new JTextField();
		txtDiaFfin.setText("dd");
		txtDiaFfin.setEditable(false);
		GridBagConstraints gbc_txtDd_1 = new GridBagConstraints();
		gbc_txtDd_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtDd_1.insets = new Insets(0, 0, 5, 5);
		gbc_txtDd_1.gridx = 8;
		gbc_txtDd_1.gridy = 1;
		getContentPane().add(txtDiaFfin, gbc_txtDd_1);
		txtDiaFfin.setColumns(2);
		
		label_2 = new JLabel("/");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.anchor = GridBagConstraints.EAST;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 9;
		gbc_label_2.gridy = 1;
		getContentPane().add(label_2, gbc_label_2);
		
		txtMesFfin = new JTextField();
		txtMesFfin.setEditable(false);
		txtMesFfin.setText("mm");
		GridBagConstraints gbc_txtMm_1 = new GridBagConstraints();
		gbc_txtMm_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtMm_1.insets = new Insets(0, 0, 5, 5);
		gbc_txtMm_1.gridx = 10;
		gbc_txtMm_1.gridy = 1;
		getContentPane().add(txtMesFfin, gbc_txtMm_1);
		txtMesFfin.setColumns(2);
		
		label_3 = new JLabel("/");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.anchor = GridBagConstraints.EAST;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 11;
		gbc_label_3.gridy = 1;
		getContentPane().add(label_3, gbc_label_3);
		
		txtAnyoFfin = new JTextField();
		txtAnyoFfin.setEditable(false);
		txtAnyoFfin.setText("yyyy");
		GridBagConstraints gbc_txtYyyy_1 = new GridBagConstraints();
		gbc_txtYyyy_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtYyyy_1.insets = new Insets(0, 0, 5, 0);
		gbc_txtYyyy_1.gridx = 12;
		gbc_txtYyyy_1.gridy = 1;
		getContentPane().add(txtAnyoFfin, gbc_txtYyyy_1);
		txtAnyoFfin.setColumns(4);
		
		lblEspacio = new JLabel("Espacio");
		GridBagConstraints gbc_lblEspacio = new GridBagConstraints();
		gbc_lblEspacio.insets = new Insets(0, 0, 5, 5);
		gbc_lblEspacio.gridx = 5;
		gbc_lblEspacio.gridy = 2;
		getContentPane().add(lblEspacio, gbc_lblEspacio);
		
		btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent arg0) {
				String msgErr = Constantes.VACIO;
				boolean error = false;
				Date fecha = new Date();
				int dd = 0;
				int mm = 0;
				int yy = 0;

				if(chckbxFechaInicio.isSelected()){
					try{
						if(txtDiaFini.getText().length() > 2){
							throw new Exception("Hay mas digitos de los permitidos");
						}

						dd = Integer.parseInt(txtDiaFini.getText());
					}
					catch (Exception e) {
						msgErr = "El valor del \"Dia\" de la fecha de inicio es erróneo";
						error = true;
					}

					try{
						if(!error){
							if(txtMesFini.getText().length() > 2){
								throw new Exception("Hay mas digitos de los permitidos");
							}
							mm = Integer.parseInt(txtMesFini.getText());
						}
					}
					catch (Exception e) {
						msgErr = "El valor del \"Mes\" de la fecha de inicio es erróneo";
						error = true;
					}

					try{
						if(!error){
							if(txtAnyoFini.getText().length() > 4){
								throw new Exception("Hay mas digitos de los permitidos");
							}
							yy = Integer.parseInt(txtAnyoFini.getText());
						}
					}
					catch (Exception e) {
						msgErr = "El valor del \"Año\" de la fecha de inicio es erróneo";
						error = true;
					}
					
					try {
						if(!error){
							validadFecha(dd, mm, yy);
							fecha = new SimpleDateFormat("dd/mm/YYYY").parse(dd + "/" + mm + "/" + yy);
							filtro.setFechaInicio(fecha);
						}
						else{
							JOptionPane.showMessageDialog( null, msgErr, "Error fecha inicio", JOptionPane.ERROR_MESSAGE);
						}
					} catch (ParseException e) {
						msgErr = "Error al crear la fecha de inicio: " + e.getMessage();
						JOptionPane.showMessageDialog( null, msgErr, "Error fecha inicio", JOptionPane.ERROR_MESSAGE);
						error = true;
					} catch (Exception e) {
						msgErr = "Error al validar la fecha de inicio: " + e.getMessage();
						JOptionPane.showMessageDialog( null, msgErr, "Error fecha inicio", JOptionPane.ERROR_MESSAGE);
						error = true;
					}
				}

				if(chckbxFechaFin.isSelected()){
					try{
						if(txtDiaFfin.getText().length() > 2){
							throw new Exception("Hay mas digitos de los permitidos");
						}
						dd = Integer.parseInt(txtDiaFfin.getText());
					}
					catch (Exception e) {
						msgErr = "El valor del \"Dia\" de la fecha de fin es erróneo";
						error = true;
					}

					try{
						if(!error){
							if(txtMesFfin.getText().length() > 2){
								throw new Exception("Hay mas digitos de los permitidos");
							}
							mm = Integer.parseInt(txtMesFfin.getText());
						}
					}
					catch (Exception e) {
						msgErr = "El valor del \"Mes\" de la fecha de fin es erróneo";
						error = true;
					}

					try{
						if(!error){
							if(txtAnyoFfin.getText().length() > 4){
								throw new Exception("Hay mas digitos de los permitidos");
							}
							yy = Integer.parseInt(txtAnyoFfin.getText());
						}
					}
					catch (Exception e) {
						msgErr = "El valor del \"Año\" de la fecha de fin es erróneo";
						error = true;
					}
					
					try {
						if(!error){
							validadFecha(dd, mm, yy);
							fecha = new SimpleDateFormat("dd/mm/YYYY").parse(dd + "/" + mm + "/" + yy);
							filtro.setFechaFin(fecha);
						}
						else{
							JOptionPane.showMessageDialog( null, msgErr, "Error fecha fin", JOptionPane.ERROR_MESSAGE);
						}
					} catch (ParseException e) {
						msgErr = "Error al crear la fecha de fin: " + e.getMessage();
						JOptionPane.showMessageDialog( null, msgErr, "Error fecha fin", JOptionPane.ERROR_MESSAGE);
						error = true;
					} catch (Exception e) {
						msgErr = "Error al validar la fecha de fin: " + e.getMessage();
						JOptionPane.showMessageDialog( null, msgErr, "Error fecha fin", JOptionPane.ERROR_MESSAGE);
						error = true;
					}
				}

				if(!error){
					log.info("Aceptar [filtro]: " + filtro.getInfo());
					dispose();
				}
				else{
					log.log(Level.SEVERE, msgErr);
				}
			}

			/**
			 * Metodo para validar una fecha
			 * @param dd - dia del mes
			 * @param mm - numero del mes
			 * @param yy - año
			 * @throws Exception 
			 */
			private void validadFecha(int dd, int mm, int yy) throws Exception {
				try{
					if (yy < 1900) {
			            throw new IllegalArgumentException("Año inválido.");
			        } 

			        LocalDate today = LocalDate.of(yy, mm, dd);
			        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

			        log.log(Level.INFO, "Fecha: " + formatter.format(today));
				}
				catch (Exception e) {
					throw new Exception("La fecha introducida no es válida");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 3;
		getContentPane().add(btnAceptar, gbc_btnNewButton);
		
		btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				filtro = filtroCopia;
				dispose();
			}
		});
		GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
		gbc_btnCancelar.insets = new Insets(0, 0, 0, 5);
		gbc_btnCancelar.gridx = 7;
		gbc_btnCancelar.gridy = 3;
		getContentPane().add(btnCancelar, gbc_btnCancelar);
		filtro = objFiltro;
		
		// Controles generales de la ventana
		setModal(true);
    	setSize(440, 140);
        setLocation(450, 150);
    	setVisible(true);
	}

}
