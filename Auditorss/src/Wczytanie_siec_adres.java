import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class Wczytywanie_siec_adres{
    public static String Dopisz_processing_grupe(Cell cell) throws IOException{
        
        FileInputStream file = new FileInputStream(new File("SIEC_ADRES.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(file);
		Sheet sheet = wb.getSheetAt(0);
		int rowend = sheet.getLastRowNum();
		rowend++; 
		String proc_grupa = "Nie znalazlem";
		Row row;

		//Czytanie opisu i numeru processing grupy
		for(int i = 0; i<rowend; i++){
			row = sheet.getRow(i);

			if(row.getCell(0) != null && cell != null){
				if(row.getCell(0).toString().contains(cell.toString())){
					wb.close();
					return row.getCell(2).toString();
				}
			}
		}

		wb.close();
		return proc_grupa;
	}
}