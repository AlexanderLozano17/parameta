package demo.soap.util;

import java.time.LocalDate;
import java.time.Period;

import javax.xml.datatype.XMLGregorianCalendar;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class FunctionUtils {

	/**
	 * 
	 * @param joinDate
	 * @return
	 */
    public static String calculateVinculationTime(LocalDate joinDate) {
        if (joinDate == null) return "Fecha de vinculación no disponible";

        LocalDate today = LocalDate.now();
        Period period = Period.between(joinDate, today);

        return String.format("%d años, %d meses y %d días",
                period.getYears(), period.getMonths(), period.getDays());
    }
	         
    /**
     * 
     * @param birthDate
     * @return
     */
    public static String calculateAge(LocalDate birthDate) {
        if (birthDate == null) return "Fecha de nacimiento no disponible";

        LocalDate today = LocalDate.now();
        if (birthDate.isAfter(today)) return "Fecha de nacimiento inválida";

        Period period = Period.between(birthDate, today);
        return String.format("%d años, %d meses y %d días",
                period.getYears(), period.getMonths(), period.getDays());
    }
    
    /**
     * 
     * @param birthDate
     * @return
     */
    public static int currentAge(LocalDate birthDate) {
        if (birthDate == null || birthDate.isAfter(LocalDate.now())) return -1;
        return Period.between(birthDate, LocalDate.now()).getYears();
    }
    
    /**
     * 
     * @param object
     */
    public static void printJsonPretty(Object object) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule()); // soporte para Java 8 date/time
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS); // para que use el formato ISO (yyyy-MM-dd)
            mapper.enable(SerializationFeature.INDENT_OUTPUT); // activa el "pretty print"

            String prettyJson = mapper.writeValueAsString(object);
            System.out.println(prettyJson);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    /**
     * 
     * @param xmlDate
     * @return
     */
    public static LocalDate toLocalDate(XMLGregorianCalendar xmlDate) {
        return xmlDate.toGregorianCalendar().toZonedDateTime().toLocalDate();
    }
}
