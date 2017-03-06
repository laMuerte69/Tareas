package acciones;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import beans.DatosTareasBean;
import beans.FiltroDatosTareaBean;
import tablas.ControlLstDatoTareas;
import ventanas.PanelFiltroDatosTarea;

public class AccionBotonFiltroLstDatosTareas {
	private final static String CLASE = AccionBotonFiltroLstDatosTareas.class.getName();
	protected static Logger log = Logger.getLogger(CLASE);
	
	private ArrayList<DatosTareasBean> lstDatosTareas;
	private ControlLstDatoTareas control;
	private JPanel panelPadre;

	public AccionBotonFiltroLstDatosTareas(JPanel tabListadoDatosTareas,
			ArrayList<DatosTareasBean> objLstDatosTareas, ControlLstDatoTareas controlLstDatoTareas) {
		panelPadre     = tabListadoDatosTareas;
		lstDatosTareas = objLstDatosTareas;
		control        = controlLstDatoTareas;
	}


	public MouseListener filtroTareas() {
		MouseListener resultado = null;

		try{
			resultado = new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					
					try{
						FiltroDatosTareaBean filtro = new FiltroDatosTareaBean();
						int fInicio = 0;
						int fFin    = 0;
						
						new PanelFiltroDatosTarea(filtro);
						if(filtro.hayFiltros()){
							fInicio = filtro.gefFechaInicio();
							fFin    = filtro.gefFechaFin();
	
							control.borraTabla();
							for(DatosTareasBean dTarea: lstDatosTareas){
								
								if(fInicio <= dTarea.getFInicio() && fFin >= dTarea.getFFin()){
									control.anyadeFila(dTarea);
								}
								//TODO voy por aqui
							}
						}
					} catch (Exception e1) {
			    		String msgErr = CLASE + "filtroTareas::mouseClicked(): "  + e1.getMessage();
			    		JOptionPane.showMessageDialog(panelPadre, msgErr, "Error mouseClicked() !!", JOptionPane.ERROR_MESSAGE);
			    		log.log(Level.SEVERE, msgErr);
					}
				}
			};
//			
		} catch (Exception e) {
    		String msgErr = CLASE + "::filtroTareas(): "  + e.getMessage();
    		JOptionPane.showMessageDialog(panelPadre, msgErr, "Error filtroTareas() !!", JOptionPane.ERROR_MESSAGE);
    		log.log(Level.SEVERE, msgErr);
    		resultado = null;
		}
		
		return resultado;
	}
}
