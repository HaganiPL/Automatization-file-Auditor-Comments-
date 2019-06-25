import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class Wczytywanie_LAC_FOTO{
    public static String Lac_foto_check(String poprawny_lac) throws IOException{
        
        FileInputStream file = new FileInputStream(new File("LAC_FOTO.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(file);
		Sheet sheet = wb.getSheetAt(0);
		int rowend = sheet.getLastRowNum()+1;
		String lac_foto = "Brak zdjecia";
		Row row;
	
		//Czytanie lacow
		for(int i = 0; i<rowend; i++){
			row = sheet.getRow(i);
			if(row.getCell(0) != null){
				if(row.getCell(0).toString().contains(poprawny_lac)){
					wb.close();
					return "Zdjecie na dysku";
				}
			}
		}

		wb.close();
		return lac_foto;
	}
}