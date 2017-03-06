package beans;

import java.util.Calendar;

import comun.TipoFiltro;
import comun.Utilidades;

public class FiltroDatosTareaBean {
	private Calendar fechaInicio;
	private Calendar fechaFin;
	private TipoFiltro tipoFiltro;
	
	public FiltroDatosTareaBean(){
		fechaInicio = null;
		fechaFin    = null;
		tipoFiltro  = TipoFiltro.NADA;
	}


	/**
	 * Metodo para obtener la informacion basica del filtro
	 * @return String con los datos del filtro
	 */
	public String getInfo() {
		StringBuffer resultado = new StringBuffer();

		if(null != fechaInicio){
			resultado.append("Fecha inicio: " + formatoFecha(fechaInicio)).append("\n");	
		}

		if(null != fechaFin){
			resultado.append("Fecha fin   : " + formatoFecha(fechaFin)).append("\n");
		}

		return resultado.toString();
	}


	/**
	 * Metodo para obtener la fecha con el formato dd/MM/yyyy
	 * @param fecha - fecha a parsear
	 * @return String con los datos de la fecha
	 */
	private String formatoFecha(final Calendar fecha) {
		return fecha.get(Calendar.DAY_OF_MONTH) + "/" + (fecha.get(Calendar.MONTH) + 1) + "/" + fecha.get(Calendar.YEAR);
	}


	/**
	 * Metodo para saber si hay algun filtro
	 * @return
	 */
	public boolean hayFiltros() {
		boolean resultado = false;
		if(null != fechaInicio){
			resultado = true;	
		}

		if(null != fechaFin){
			resultado = true;
		}

		return resultado;
	}


	public final Calendar getFechaInicio() {
		return fechaInicio;
	}
	public final void setFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public int gefFechaInicio() {
		int resultado = 0;
		if(null != fechaInicio ){
			resultado = Utilidades.fechaNumerica(fechaInicio.get(Calendar.YEAR), fechaInicio.get(Calendar.MONTH) + 1, fechaInicio.get(Calendar.DAY_OF_MONTH));
		}
		return resultado;
	}

	
	public final Calendar getFechaFin() {
		return fechaFin;
	}
	public final void setFechaFin(Calendar fechaFin) {
		this.fechaFin = fechaFin;
	}
	public int gefFechaFin() {
		int resultado = 0;
		if(null != fechaFin ){
			resultado = Utilidades.fechaNumerica(fechaFin.get(Calendar.YEAR), fechaFin.get(Calendar.MONTH) + 1, fechaFin.get(Calendar.DAY_OF_MONTH));
		}
		return resultado;
	}


	/**
	 * @return the tFiltro
	 */
	public final TipoFiltro getTipoFiltro() {
		return tipoFiltro;
	}
	/**
	 * @param tipoFiltro the tipoFiltro to set
	 */
	public final void setTipoFiltro(TipoFiltro tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}
	
	
}
