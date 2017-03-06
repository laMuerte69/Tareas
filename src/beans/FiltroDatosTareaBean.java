package beans;

import java.util.Date;

import comun.Utilidades;

public class FiltroDatosTareaBean {
	private Date fechaInicio;
	private Date fechaFin;
	
	public FiltroDatosTareaBean(){
		fechaInicio = null;
		fechaFin = null;
	}


	/**
	 * Metodo para obtener la informacion basica del filtro
	 * @return String con los datos del filtro
	 */
	public String getInfo() {
		StringBuffer resultado = new StringBuffer();

		if(null != fechaInicio){
			resultado.append("Fecha inicio: " + fechaInicio.toString()).append("\n");	
		}

		if(null != fechaFin){
			resultado.append("Fecha fin   : " + fechaFin.toString()).append("\n");
		}

		return resultado.toString();
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


	public final Date getFechaInicio() {
		return fechaInicio;
	}
	public final void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	@SuppressWarnings("deprecation")
	public int gefFechaInicio() {
		int resultado = 0;
		if(null != fechaInicio ){
			resultado = Utilidades.fechaNumerica(fechaInicio.getYear(), fechaInicio.getMonth(), fechaInicio.getDay());
		}
		return resultado;
	}

	
	public final Date getFechaFin() {
		return fechaFin;
	}
	public final void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	@SuppressWarnings("deprecation")
	public int gefFechaFin() {
		int resultado = 0;
		if(null != fechaFin ){
			resultado = Utilidades.fechaNumerica(fechaFin.getYear(), fechaFin.getMonth(), fechaFin.getDay());
		}
		return resultado;
	}
	
	
}
