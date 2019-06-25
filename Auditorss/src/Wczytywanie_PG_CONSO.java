import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class Wczytywanie_PG_CONSO{
    public static String Dopisz_IN_OUT_MARKET(String GIC) throws IOException{
        
        FileInputStream file = new FileInputStream(new File("SG_PG_CONSO_POLAND.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(file);
		Sheet sheet = wb.getSheetAt(4);
		int rowend = sheet.getLastRowNum();
		rowend++; 
		String Market_in_out = "IN MARKET";
		Row row;

		//Czytanie GIC outmarket z pliku 
		for(int i = 0; i<rowend; i++){
			row = sheet.getRow(i);

			if(row.getCell(1) != null){
				if(row.getCell(1).toString().contains(GIC)){
					wb.close();
					return "OUT MARKET";
				}
			}
		}

		wb.close();
		return Market_in_out;
	}
}