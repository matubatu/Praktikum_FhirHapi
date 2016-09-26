package dataGenerators;

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

/**
 * Read from Excel document, save it in HashMap, randomize it, give a city-postcode back
 */
public class PLZ_StadtGenerator {
	
	private HashMap<String, String> map = new HashMap<String, String>();
	private String randomValue;
	
    public PLZ_StadtGenerator () {
    	
    	String stadt = null;
		String plz = null;
		int i;

        try {
        	File f = new File("c:\\Users\\matubatu\\Desktop\\PLZ_Stadt.xls");
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
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }// 
    
    /**
     * Generate key from HashMap value 
     */
    private Object getKeyFromValue(Map<String, String> hm, Object value) {
        for (Object o : hm.keySet()) {
          if (hm.get(o).equals(value)) {
            return o;
          }
        }
        return null;
      }
    
    /**
     * Gives a randomized postcode-city back
     * @return postcode-City
     */
    public String randomiseCity() {
    	
    	Random generator = new Random();
    	Object[] values = map.values().toArray();
    	randomValue = (String) values[generator.nextInt(values.length)];
    	return randomValue;
    	
    }
    public String randomisePostcode() {
    	
    	String postCode = (String) getKeyFromValue(map, randomValue);
    	return postCode; 
    }
    
}
