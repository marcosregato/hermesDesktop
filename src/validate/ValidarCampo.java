package validate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ValidarCampo {

    Logger log = LogManager.getLogger(ValidarCampo.class.getName());

    public boolean validarCampoTxt(String valor) {
        boolean expression = true;
        if (valor.isEmpty()) {
            expression = false;
        }
        return expression;
    }

    public boolean validarCampoNumb(int valor) {
        try {
            boolean expression = false;
            if (valor > 0) {
                expression = true;
            }
            return expression;
        } catch (Exception e) {
            log.info(e.getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }

    public boolean validarCampoData(String valor) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate d = LocalDate.parse(valor, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public boolean validarCampoEmail(String valor) {
        try {
            boolean expression = false;
            if (valor.contains("@")) {
                expression = true;
            }
            return expression;
        } catch (Exception e) {
            log.info(e.getClass().getName() + ": " + e.getMessage());
        }
        return false;
    }

    public boolean validarCampoVazio(String valor) {
        try {
            boolean expression = false;
            if (valor.length() > 5) {
                expression = true;
            }
            return expression;
        } catch (Exception e) {
            log.info(e.getClass().getName() + ": " + e.getMessage());

        }
        return false;
    }
    
    public String limparCampo(String valor) {
        try {
            if (valor.length() > 5) {
                return "";
            }
           
        } catch (Exception e) {
            log.info(e.getClass().getName() + ": " + e.getMessage());

        }
        return null;
    }
}
