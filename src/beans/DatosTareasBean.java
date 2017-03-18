package beans;

import java.sql.ResultSet;

import comun.Constantes;
import comun.Utilidades;

public class DatosTareasBean {

	private int id;
	private int idTarea;        // Identificador de la tarea a la que hace referencia este datos
    private String fechaInicio; // Formato: dd/mm/yyyy hh:mm
    private String fechaFin;
    private boolean nueva;      //flag para saber si la tarea se ha leido de la bbdd (false) o si se ha creado desde la aplicacion (true)
    
    public DatosTareasBean(){
    	id          = 0;
    	idTarea     = 0;
    	fechaInicio = Constantes.VACIO;
    	fechaFin    = Constantes.VACIO;
    	nueva       = true;
    }


    public DatosTareasBean(int iId, int iIdTarea, String strFechaInicio, String strFechaFin){
    	id          = iId;
    	idTarea     = iIdTarea;
    	fechaInicio = strFechaInicio;
    	fechaFin    = strFechaFin;
    	nueva       = true;
    }


	/**
	 * Metodo para parsear los datos de una tarea cuyo origen es un ResulSet
	 * @param rs - objeto con los datos de la tarea
	 * @return tarea
	 * @throws Exception 
	 */
	public DatosTareasBean parse(final ResultSet rs) throws Exception {
		try{
			id          = rs.getInt("ID");
			idTarea     = rs.getInt("ID_TAREA");
			fechaInicio = rs.getString("FECHA_INICIO");
			fechaFin    = rs.getString("FECHA_FIN");
	    	nueva       = false;
		}
		catch (Exception e) {
			throw new Exception("Error al parsear los DATOS de la tarea");
		}

		return this;
	}

    
	/**
	 * @return the id
	 */
	public final int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public final void setId(int id) {
		this.id = id;
	}

	
	/**
	 * @return the idTarea
	 */
	public final int getIdTarea() {
		return idTarea;
	}
	/**
	 * @param idTarea the idTarea to set
	 */
	public final void setIdTarea(int idTarea) {
		this.idTarea = idTarea;
	}

	
	/**
	 * @return the fechaInicio
	 */
	public final String getFechaInicio() {
		return fechaInicio;
	}
	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public final void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public final int getFInicio() throws Exception {
		int resultado = 0;
		
		try{
			resultado  = Utilidades.obtenerFecha(fechaInicio);
		}
		catch (Exception e) {
			throw new Exception("Error al convertir la fecha de inicio");
		}

		return resultado;
	}

	
	/**
	 * @return the fechaFin
	 */
	public final String getFechaFin() {
		return fechaFin;
	}
	/**
	 * @param fechaFin the fechaFin to set
	 */
	public final void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int getFFin() throws Exception {
		int resultado = 0;
		
		try{
			resultado  = Utilidades.obtenerFecha(fechaFin);
		}
		catch (Exception e) {
			throw new Exception("Error al convertir la fecha de fin");
		}

		return resultado;
	}


	/**
	 * @return the nueva
	 */
	public final boolean isNueva() {
		return nueva;
	}
	/**
	 * @param nueva the nueva to set
	 */
	public final void setNueva(boolean nueva) {
		this.nueva = nueva;
	}

	
}
