package com.nttdata.Logback;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Random;

/**
 * 
 * @author Daniel Cosa Cosias
 *
 */

public class App 
{
	/** LOG */
	private static Logger LOG = LoggerFactory.getLogger(App.class);
	
	/**
	 * Metodo Principal
	 * 
	 * @param args
	 */
	
    public static void main( String[] args )
    {
    	LOG.info("Inicio de ejecucion");
    	
        String[] matriculas = new String[1500];
        matriculas = randomizadorMatriculas(matriculas);
        
        try {
        	//Validacion
        	for(int i = 0; i < matriculas.length; i++) {
        		if(checkMatricula(matriculas[i])) {
        			LOG.info("Matricula {} correcta", matriculas[i]);
        		} else {
        			LOG.warn("Matricula {} incorrecta", matriculas[i]);
        		}
        	}
        } catch (Exception e) {
        	LOG.error("Se ha producido un error, descripcion: {}", e.getCause());
        } finally {
        	LOG.info("Fin de ejecucion");
        }
    }
    
    /**
	 * Metodo para crear matriculas que rellenen el array creado en main
	 * 
	 * @param args
	 */
    
    private static String[] randomizadorMatriculas(String[] arr) {
    	
    	Random random = new Random();
    	for(int i = 0; i < arr.length; i++) {
    		arr[i] = Integer.toString(random.nextInt(1, 9999));
    		arr[i] = arr[i].concat(RandomStringUtils.randomAlphabetic(3).toUpperCase());
    	}
    	return arr;
    }
    
    /**
     * (Validacion) Metodo para verificar que la matricula (española) sea correcta
     * 
     * @param matricula
     */
    
    private static boolean checkMatricula(String matricula) {
    	if(matricula == null || matricula.length() != 7) {
    		return false;
    	}
    	
    	for(int i=0; i<4; i++) {
    		if(!Character.isDigit(matricula.charAt(i))) {
    			return false;
    		}
    	}
    	
    	for(int i=4; i<matricula.length(); i++) {
    		if(!Character.isAlphabetic(matricula.charAt(i)) || matricula.charAt(i) == 'A' || matricula.charAt(i) == 'E' || matricula.charAt(i) == 'I' ||
    		   matricula.charAt(i) == 'O' || matricula.charAt(i) == 'U' || matricula.charAt(i) == 'Q' || matricula.charAt(i) == 'Ñ') {
    			return false;
    		}
    	}
    	return true;
    }
}
