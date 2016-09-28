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

import dataGenerators.Record;

public class KH_AddressGenerator {
	
	HashMap<Integer, Record> map = new HashMap<Integer, Record>();
	public enum ExcelElement {   HOSPITAL_NAME, LINE, CITY, STATE, POSTCODE }

	public KH_AddressGenerator () {
    	
		int i;
		int columnCount = 0;
		int rowCount = 0;
		
        try {
        	
        	File f = new File("c:\\git\\Praktikum_FhirHapi\\myProject\\Dokumente\\KH_Adresse.xls");
            FileInputStream file = new FileInputStream(f);

            HSSFWorkbook workbook = new HSSFWorkbook(file);
            HSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();
            
            while(rowIterator.hasNext()) {
            	
                Row row = rowIterator.next();
                
                rowCount++;
                columnCount = 0;
                
				String hospitalName = null;
				String line = null;
				String city = null;
				String state = null;
				String postCode = null;
				Record record = new Record();

                Iterator<Cell> cellIterator = row.cellIterator();
                
                while(cellIterator.hasNext()) {

                	Cell cell = cellIterator.next();
                	
            		columnCount++;
            		
					switch(columnCount) {
					case 1:
						record.setHospitalName(cell.getStringCellValue());
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
                map.put(rowCount, record);
            }
            file.close();
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }// const 

	/**
	 * Provides i. elements of the List
	 */
	public String getElement(ExcelElement cell, int i) {
		
		String element = null;
		
		switch(cell) {
			case HOSPITAL_NAME:
				element = map.get(i).getHospitalName();
				break;
			case LINE:
				element = map.get(i).getLine();
			   	break;
			case CITY:
				element = map.get(i).getCity();
				break;
			case STATE:
				element = map.get(i).getState();
				break;
			case POSTCODE:
				element = map.get(i).getPostCode();
				break;
        }
		return element;
		
	}
	
}

