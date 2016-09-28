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
public class NameGenerator {
	
	private HashMap<String, String> map = new HashMap<String, String>();
//	private String randomValue;
    int stringColumnCount = 0;

	
    public NameGenerator () {
    	
    	String vorName = null;
		String nachName = null;
		int i;

        try {
            File f = new File("c:\\git\\Praktikum_FhirHapi\\myProject\\Dokumente\\Vorname_Nachname.xls");
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
            
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }// 
    
// randomises gender with surname
    public String randomiseGenderVorname() {
    	
    	Random generator = new Random();
    	Object[] genderVornamen = map.keySet().toArray();
        String vorName = (String) genderVornamen[generator.nextInt(genderVornamen.length)];
    	return vorName;
    	
    }
    public String randomiseNachname() {
    	
        Random generator = new Random();
        Object[] nachNamen = map.values().toArray();
        String nachName = (String) nachNamen[generator.nextInt(nachNamen.length)];
        return nachName;
    }


    
}
