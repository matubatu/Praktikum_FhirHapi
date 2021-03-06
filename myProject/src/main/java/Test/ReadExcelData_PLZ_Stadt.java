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

public class ReadExcelData_PLZ_Stadt {
	
    public static void main(String[] args) {
    	
    	String stadt = null;
		String plz = null;
		int i;

		HashMap<String, String> map = new HashMap<String, String>();
    	
        try {

        	File f = new File("c:\\git\\Praktikum_FhirHapi\\myProject\\Dokumente\\Map.xls");
            FileInputStream file = new FileInputStream(f);

            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            
            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();

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
                }
                map.put(plz, stadt);
            }
            file.close();
            System.out.println("File Closed \n");
            
//Random value out

            Random generator = new Random();
            Object[] values = map.values().toArray();
            String randomValue = (String) values[generator.nextInt(values.length)];
            
            String plzString = (String) getKeyFromValue(map, randomValue);
            System.out.println("plz: " + plzString + " " + randomValue);
             
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }// main
    
    /**
     * gives back key from value 
     */
    private static Object getKeyFromValue(Map<String, String> hm, Object value) {
        for (Object o : hm.keySet()) {
          if (hm.get(o).equals(value)) {
            return o;
          }
        }
        return null;
      }
    
}
