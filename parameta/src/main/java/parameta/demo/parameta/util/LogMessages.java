package parameta.demo.parameta.util;

public class LogMessages {

    /** ====================================================
                     LOGS DE ÉXITO
    	==================================================== */
    public static final String ENTITY_SAVE_SUCCESS = "Registro guardado exitosamente con ID %s";
    public static final String ENTITY_UPDATE_SUCCESS = "Registro actualizado exitosamente con ID %s";
    public static final String ENTITY_DELETE_SUCCESS = "Registro eliminado exitosamente con ID %s";
    public static final String ENTITY_FOUND = "Registro encontrado con ID %s";
    public static final String ENTITY_LIST_SUCCESS = "Listado de registros obtenido correctamente. Total: %s";

    /** ====================================================
                     LOGS DE ERROR
    	==================================================== */
    public static final String ENTITY_SAVE_ERROR = "Error al guardar : %s";
    public static final String ENTITY_SAVE_ERROR_EMPTY = "No se pudo guardar el registro. Respuesta vacía del servicio";
    public static final String ENTITY_UPDATE_ERROR = "Error al actualizar el registro con ID %s: %s";
    public static final String ENTITY_DELETE_ERROR = "Error al eliminar %s con ID %s: %s";
    public static final String ENTITY_NOT_FOUND = "Registro no encontrado con ID %s";
    public static final String ENTITY_LIST_ERROR = "Error al obtener el listado de registros: %s";
    public static final String ENTITY_NOT_CONTENT = "No se han encontrado registros";
    public static final String OBJECT_NOT_NULL = "El objeto no puede ser nulos";

    /** ====================================================
                     LOGS DE AUDITORÍA
    ==================================================== */
    public static final String REQUEST_RECEIVED = "Solicitud recibida: Método=%s URL=%s IP=%s";
    public static final String REQUEST_BODY = "Cuerpo de la solicitud: %s";
    public static final String RESPONSE_SENT = "Respuesta enviada: Código=%s Cuerpo=%s";
    
    /** ====================================================
	    			CUSTOM MESSAGE
		==================================================== */
    public static final String INVALID_AGE = "Edad no permitida para ser registrado como empleado";
    public static final String ERROR_DATE_BIRTH = "Verifica que la fecha de nacimiento sea correcta";
}
