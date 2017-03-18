package beans;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.Vector;

import comun.Constantes;

public class TareaBean {

    private int    id;
    private String nombre;
    private String descripcion;
    private String codigo1;
    private String codigo2;
    private String codigo3;
    private boolean bajaLogica; //para saber si la tarea esta de alta (visible) o no
    private boolean nueva;      //flag para saber si la tarea se ha leido de la bbdd (false) o si se ha creado desde la aplicacion (true)


    public TareaBean(){
    	id          = 0;
    	nombre      = Constantes.VACIO;
    	descripcion = Constantes.VACIO;
    	codigo1     = Constantes.VACIO;
    	codigo2     = Constantes.VACIO;
    	codigo3     = Constantes.VACIO;
    	bajaLogica  = false;
    	nueva       = true;
    }
    
    public TareaBean(int iID, String strNombre, String strDesc){
    	id          = iID;
    	nombre      = strNombre;
    	descripcion = strDesc;
    	codigo1     = Constantes.VACIO;
    	codigo2     = Constantes.VACIO;
    	codigo3     = Constantes.VACIO;
    	bajaLogica  = false;
    	nueva       = true;
    }


    public TareaBean(String strNombre, String strDesc, String cod1, String cod2, String cod3){
    	id          = -1;
    	nombre      = strNombre.trim();
    	descripcion = strDesc.trim();
    	codigo1     = cod1.trim();
    	codigo2     = cod2.trim();
    	codigo3     = cod3.trim();
    	bajaLogica  = false;
    	nueva       = true;
    }

	
	/**
	 * Metodo para parsear los datos de una tarea cuyo origen es un ResulSet
	 * @param rs - objeto con los datos de la tarea
	 * @return tarea
	 * @throws Exception 
	 */
	public TareaBean parse(final ResultSet rs) throws Exception {
		try{
			id          = rs.getInt("ID");
			nombre      = rs.getString("NOMBRE");
			descripcion = rs.getString("DESCRIPCION");
			codigo1     = rs.getString("CODIGO1");
			codigo2     = rs.getString("CODIGO2");
			codigo3     = rs.getString("CODIGO3");
			bajaLogica  = rs.getBoolean("BAJA_LOGICA");
			nueva       = false;
		}
		catch (Exception e) {
			throw new Exception("Error al parsear los datos de la tarea");
		}

		return this;
	}
    

    /**
     * Metodo para convertir las propiedades del objeto en un array de datos
     * @return - arrays con los valores del objeto
     * @throws Exception
     */
	public Collection<? extends String> getTareasListado() throws Exception {
		Vector<String> resultado = null;

		try{
			resultado = new Vector<String>();
			resultado.add(String.valueOf(id));
			resultado.add(nombre.trim());
			resultado.add(descripcion.trim());
			resultado.add(codigo1.trim());
			resultado.add(codigo2.trim());
			resultado.add(codigo3.trim());
			resultado.add(String.valueOf(bajaLogica));
			resultado.add(String.valueOf(nueva));
		}
		catch (Exception e) {
			throw new Exception("TareaBean::getTareasListado(): " + e.getMessage());
		}

		return resultado;
	}

	/**
	 * Metodo para mostar los datos de la tarea
	 */
	public String info() {
		StringBuffer resultado = new StringBuffer();
		resultado.append("ID:      ").append(String.valueOf(id)).append("\n");
		resultado.append("Nombre:  ").append(nombre).append("\n");
		resultado.append("Descrip: ").append(descripcion).append("\n");
		resultado.append("Codigo1: ").append(codigo1).append("\n");
		resultado.append("Codigo2: ").append(codigo2).append("\n");
		resultado.append("Codigo3: ").append(codigo3).append("\n");
		resultado.append("Baja:    ").append(String.valueOf(bajaLogica));
		resultado.append("Nueva:   ").append(String.valueOf(nueva));
		return resultado.toString();
	}
	
	

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }


    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    public String getDescripcion() {
        return descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


	/**
	 * @return the codigo1
	 */
	public final String getCodigo1() {
		return codigo1;
	}
	/**
	 * @param codigo1 the codigo1 to set
	 */
	public final void setCodigo1(String codigo1) {
		this.codigo1 = codigo1;
	}


	/**
	 * @return the codigo2
	 */
	public final String getCodigo2() {
		return codigo2;
	}
	/**
	 * @param codigo2 the codigo2 to set
	 */
	public final void setCodigo2(String codigo2) {
		this.codigo2 = codigo2;
	}

	
	/**
	 * @return the codigo3
	 */
	public final String getCodigo3() {
		return codigo3;
	}
	/**
	 * @param codigo3 the codigo3 to set
	 */
	public final void setCodigo3(String codigo3) {
		this.codigo3 = codigo3;
	}


	/**
	 * @return the bajaLogica
	 */
	public final boolean isBajaLogica() {
		return bajaLogica;
	}
	/**
	 * @param bajaLogica the bajaLogica to set
	 */
	public final void setBajaLogica(boolean bajaLogica) {
		this.bajaLogica = bajaLogica;
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
