package Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

// http://stackoverflow.com/questions/16458640/how-to-group-the-values-which-are-in-excel-to-a-hashmap

public class ReadExcelData {
	
    public static void main(String[] args) {
    	
    	String stadt = null;
		String plz = null;
		int i;

		HashMap<String, String> map = new HashMap<String, String>();
    	
        try {
        	File f = new File("c:\\Users\\matubatu\\Desktop\\Map.xls");
            FileInputStream file = new FileInputStream(f);

//Get the workbook instance for XLS file 
            HSSFWorkbook workbook = new HSSFWorkbook(file);
//Get first sheet from the workbook
            HSSFSheet sheet = workbook.getSheetAt(0);
//Iterate through each rows from first sheet
            Iterator<Row> rowIterator = sheet.iterator();
            
            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();

                //For each row, iterate through each columns
                Iterator<Cell> cellIterator = row.cellIterator();
                
                while(cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    switch(cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                           i= (int) cell.getNumericCellValue();
                           plz = String.valueOf( i ); 
                           break;
                        case Cell.CELL_TYPE_STRING:
                        	stadt = cell.getStringCellValue(); 
                           	break;
                    }
//                    System.out.println(""); 
                }
                map.put(plz, stadt);
            }
            file.close();
            System.out.println("File Closed \n");
            
//Random value out
            // http://stackoverflow.com/questions/929554/is-there-a-way-to-get-the-value-of-a-hashmap-randomly-in-java
            Random generator = new Random();
            Object[] values = map.values().toArray();
            String randomValue = (String) values[generator.nextInt(values.length)];
            
            String plzString = (String) getKeyFromValue(map, randomValue);
            System.out.println("plz: " + plzString + " " + randomValue);
//            System.out.println("plz: " + getKeyFromValue(map, randomValue) + " " + randomValue);
             
// PRINT OUT ALL          
//            Iterator<Integer> keySetIterator = map.keySet().iterator();
//
//            while(keySetIterator.hasNext()){
//              Integer key = keySetIterator.next();
//              System.out.println("plz: " + key + " Stadt: " + map.get(key));
//            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }// main
    
    private static Object getKeyFromValue(Map<String, String> hm, Object value) {
        for (Object o : hm.keySet()) {
          if (hm.get(o).equals(value)) {
            return o;
          }
        }
        return null;
      }
    
}
