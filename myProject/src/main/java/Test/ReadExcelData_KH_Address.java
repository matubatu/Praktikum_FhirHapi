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

import dataGenerators.Record;

public class ReadExcelData_KH_Address {
	
    public static void main(String[] args) { 
    	
		int i;

		HashMap<String, Record> map = new HashMap<String, Record>();
    	
		int stringColumnCount = 0;
        try {
        	File f = new File("c:\\Users\\matubatu\\Desktop\\KH_Adresse_x.xls");
            FileInputStream file = new FileInputStream(f);

            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            
            while(rowIterator.hasNext()) {
            	
                Row row = rowIterator.next();
                
                stringColumnCount = 0;
				String hospitalName = null;
				String line = null;
				String city = null;
				String state = null;
				String postCode = null;
				Record record = new Record();

                Iterator<Cell> cellIterator = row.cellIterator();
                
                while(cellIterator.hasNext()) {

                	Cell cell = cellIterator.next();
                	
                		stringColumnCount++;
                		
						switch(stringColumnCount) {
						case 1:
							hospitalName = cell.getStringCellValue();
							break;
						case 2:
							record.setLine(cell.getStringCellValue()); 
						   	break;
						case 3:
							record.setCity(cell.getStringCellValue()); 
							break;
						case 4:
							record.setState(cell.getStringCellValue()); 
							break;
						case 5:
							i= (int) cell.getNumericCellValue();
							postCode = String.valueOf( i );
							record.setPostCode(postCode);
							break;
                        }
                }
                map.put(hospitalName, record);
            }
            file.close();
            
            for (Map.Entry<String, Record> entry : map.entrySet()) {
                String key = entry.getKey();
                Record value = entry.getValue();
                System.out.println("key: " + key + "\n " + value);
            }
			
			
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
		
    }// main
}

