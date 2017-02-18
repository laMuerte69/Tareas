

public class Tareas {

    /**
     * @param args
     */
    public static void main(String[] args) {

    	ventanas.Principal ventana = null;
		
		try {
			ventana = new ventanas.Principal(500, 100);
		    ventana.crear();
	
		} catch (Exception e) {
		    System.out.println("Problemas en la aplicacion: " + e.getMessage());
		    e.printStackTrace();
		}
    }

}
