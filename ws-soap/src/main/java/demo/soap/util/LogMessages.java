package demo.soap.util;

public class LogMessages {

    /** ====================================================
                     LOGS DE ÉXITO
    	==================================================== */
    public static final String ENTITY_SAVE_SUCCESS = "Registro guardado exitosamente con ID %s";
   
    /** ====================================================
                     LOGS DE ERROR
    	==================================================== */
    public static final String ENTITY_SAVE_ERROR = "Error al guardar : %s";
    
    
    /** ====================================================
		    		LOGS DE WARN
		==================================================== */
    public static final String NOT_EXIST_PERSON = "No se encuentra el registro de la persona con dni %s";
    public static final String EMPLOYEE_RESGISTER  = "La persona con DNI %s, se encuentra registrada como empleado desde la fecha %s";
    public static final String NEW_EMPLOYEE_RESGISTER  = "Se ha registrado un nuevo empleado con id %s";
    public static final String ERROR_EMPLOYEE_RESGISTER  = "No se ha logrado registrar el empleado";
    public static final String PERSON_NOT_REGISTER  = "La persona con DNI %s no se encuentra registrada";
}
