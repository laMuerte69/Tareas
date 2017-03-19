package beans;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import comun.Constantes;
import comun.Utilidades;

public class DatosTareasBean {

	private int id;
	private int idTarea;        // Identificador de la tarea a la que hace referencia este datos
    private String fechaInicio; // Formato: dd/MM/yyyy HH:mm
    private String fechaFin;
    private String duracion;    // Formato: hh:mm
    private boolean nueva;      // flag para saber si la tarea se ha leido de la bbdd (false) o si se ha creado desde la aplicacion (true)
    
    public DatosTareasBean(){
    	id          = 0;
    	idTarea     = 0;
    	fechaInicio = Constantes.VACIO;
    	fechaFin    = Constantes.VACIO;
    	duracion    = "00:00";
    	nueva       = true;
    }


    public DatosTareasBean(int iId, int iIdTarea, String strFechaInicio, String strFechaFin){
    	id          = iId;
    	idTarea     = iIdTarea;
    	fechaInicio = strFechaInicio;
    	fechaFin    = strFechaFin;
    	duracion    = "00:00";
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
			duracion    = rs.getString("DURACION");
	    	nueva       = false;
		}
		catch (Exception e) {
			throw new Exception("Error al parsear los DATOS de la tarea");
		}

		return this;
	}


	/**
	 * Metodo para contar el tiempo desde que se inicia la tarea hasta que se finaliza 
	 * @throws Exception 
	 */
	public void contarTiempo() throws Exception {

        long diff         = 0;
        long diffMinutos  = 0;
        long restominutos = 0; 
        long diffHoras    = 0;
        SimpleDateFormat sdf = null;
        Calendar cinicio  = null;
        Calendar cfinal   = null;

		try{
			cinicio = Calendar.getInstance();
	        cfinal  = Calendar.getInstance();

            sdf     = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            cinicio.setTime(sdf.parse(fechaInicio));
            cfinal.setTime(sdf.parse(fechaFin));
 
            diff = cfinal.getTimeInMillis() - cinicio.getTimeInMillis();
 
            // calcular la diferencia en minutos
            diffMinutos =  Math.abs (diff / (60 * 1000));
            restominutos = diffMinutos%60;
 
            // calcular la diferencia en horas 
            diffHoras =   (diff / (60 * 60 * 1000));
 
            duracion = Utilidades.obtenerHora(diffHoras, restominutos);
		}
		catch (Exception e) {
			throw new Exception("Error al contar la duracion de la tarea: " + e.getMessage());
		}
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


	/**
	 * @return the duracion
	 */
	public final String getDuracion() {
		return duracion;
	}

	
}
