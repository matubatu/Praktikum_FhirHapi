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
import java.util.Set;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ReadExcelData_Names {
	
    public static void main(String[] args) {
    	
    	String vorName = null;
		String nachName = null;
		int i;

		HashMap<String, String> map = new HashMap<String, String>();
        int stringColumnCount = 0;

        try {
        	File f = new File("c:\\git\\Praktikum_FhirHapi\\myProject\\Dokumente\\names10.xls");
            FileInputStream file = new FileInputStream(f);

            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            
            while(rowIterator.hasNext()) {
                Row row = rowIterator.next();
                stringColumnCount=0;

                Iterator<Cell> cellIterator = row.cellIterator();
                
                while(cellIterator.hasNext()) {

                    Cell cell = cellIterator.next();

                    stringColumnCount++;
                        
                        switch(stringColumnCount) {
                        case 1:
                            vorName = cell.getStringCellValue();
                            
//                            String gender = vorName.substring(0,1);
//                            System.out.println(gender +"  "+vorName);
                            break;
                        case 2:
                            nachName = cell.getStringCellValue();
                            break;
                        }
                }
                map.put(vorName, nachName);
            }
            file.close();
            System.out.println("File Closed \n");
            
            Random generator = new Random();
            Object[] keys = map.keySet().toArray();
            Object[] values = map.values().toArray();

            String vor = (String) keys[generator.nextInt(keys.length)];
            String nach = (String) values[generator.nextInt(values.length)];
            
//            String gender = vor.substring(1);
            System.out.println("G: " + vor.substring(0, 1) + " N: "+ vor.substring(1) + " " + nach);
            // Neveket neten ätnezni, javitani
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }// main
    
    
}
