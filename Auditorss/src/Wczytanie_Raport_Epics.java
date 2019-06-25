import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class Wczytywanie_Raport_Epics{
    public static String Ean_foto_check(String poprawny_ean) throws IOException{
        
        FileInputStream file = new FileInputStream(new File("Raport_Epics.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(file);
		Sheet sheet = wb.getSheetAt(0);
		int rowend = sheet.getLastRowNum()+1;
		String ean_foto = "Brak zdjęcia";
		Cell cell_2;
		Row row;
		
	
		//Czytanie eanow
		for(int i = 6; i<rowend; i++){
			row = sheet.getRow(i);
			cell_2 = row.getCell(1);
			if(cell_2 != null){
				if(cell_2.toString().contains(poprawny_ean)){
					wb.close();
					return "Zdjecie na Epics";
				}
			}
        }

		wb.close();
		return ean_foto;
	}
}