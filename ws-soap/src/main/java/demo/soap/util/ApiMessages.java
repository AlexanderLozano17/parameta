package demo.soap.util;

/**
 * La clase ApiMessages es una clase de constantes que define los mensajes
 * estándar de respuesta para una API. Está diseñada para centralizar los
 * mensajes utilizados en las respuestas HTTP
 */
public final class ApiMessages {
	
    private ApiMessages() {}
    
    /** ====================================================
		    			API MESSAGE SUCCESS
		==================================================== */
    public static final String SUCCESS = "success";
    public static final String ERROR = "error";
    
    /** ====================================================
                      API MESSAGE SUCCESS
    	==================================================== */
    public static final String SAVE_SUCCESS = "Registro guardado correctamente";
    /** ====================================================
                      API MESSAGE ERROR
    	==================================================== */
    public static final String SAVE_ERROR = "Error al guardar el registro. Verifique los datos enviados.";
    
}
