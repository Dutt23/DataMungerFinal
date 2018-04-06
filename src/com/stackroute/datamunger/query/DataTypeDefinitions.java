package com.stackroute.datamunger.query;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * implementation of DataTypeDefinitions class. This class contains a method getDataTypes() 
 * which will contain the logic for getting the datatype for a given field value. This
 * method will be called from QueryProcessors.   
 * In this assignment, we are going to use Regular Expression to find the 
 * appropriate data type of a field. 
 * Integers: should contain only digits without decimal point 
 * Double: should contain digits as well as decimal point 
 * Date: Dates can be written in many formats in the CSV file. 
 * However, in this assignment,we will test for the following date formats('dd/mm/yyyy',
 * 'mm/dd/yyyy','dd-mon-yy','dd-mon-yyyy','dd-month-yy','dd-month-yyyy','yyyy-mm-dd')
 */
public class DataTypeDefinitions {

	//method stub
	public static Object getDataType(String input) throws ParseException{
	
		 // checking for Integer
        if(input.matches("[^0-9]")){
            getIntegerValue(input);
        }    
      // checking for floating point numbers
        else if(input.matches("[0-9].")) {
            getDoubleValue(input);
        }
      // checking for date format yyyy-mm-dd
        else if(input.matches("(^(19|20)\\d\\d[- /.](0[1-9]|1[012])[- /.](0[1-9]|[12][0-9]|3[01])$)")){
            getDateValue(input);
        }
        
        else if(input.matches("")) {
            return null;
        }
      
     return input;
  }

  private static Date getDateValue(String input) {
       SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-mm-dd");
       //String date = input;
       Date date1;
      try {
          date1 = formatter1.parse(input);
          return date1;
      } catch (ParseException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
          
          return null;
      }
      
  }

  private static Double getDoubleValue(String input) {
      Double db = Double.parseDouble(input);
      return db;
      
  }

  private static Integer getIntegerValue(String input) {
      Integer i = Integer.parseInt(input);
      return i;
      
  }
				
	}
	

	

