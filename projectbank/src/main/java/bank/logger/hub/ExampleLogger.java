package bank.logger.hub;


import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.LogManager;  
import org.apache.log4j.Logger;  
import org.apache.log4j.PropertyConfigurator;

public class ExampleLogger {

		 private static final Logger logger = LogManager.getLogger(ExampleLogger.class);  
	 public static void main(String[] args)throws IOException, SQLException {  
	  // basic log4j configurator  
	  PropertyConfigurator.configure("log4j.properties");  

logger.debug("Sample debug message");
logger.fatal("Sample fatal message");     
logger.info("Sample info message");
      logger.error("Sample error message");
      
      
    
	 }
	 
}
