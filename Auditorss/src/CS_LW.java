import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class CS_LW{
    public static String CS_LW_CHECK(String external) throws IOException{
        
        FileInputStream file = new FileInputStream(new File("CS_LW.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(file);
		Sheet sheet = wb.getSheetAt(0);
		int rowend = sheet.getLastRowNum()+1;
		String CS_LW = "";
		Row row;
	
		//Czytanie lacow
		for(int i = 0; i<rowend; i++){
			row = sheet.getRow(i);
			if(row.getCell(0) != null){
				if(row.getCell(0).toString().contains(external)){
					wb.close();
					return "CS_LW";
				}
			}
		}

		wb.close();
		return CS_LW;
	}
}