import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Format_wyjasnienia_ankieterow {
	
	public static void Format() throws FileNotFoundException, IOException {
		
		 FileInputStream file = new FileInputStream(new File("Wyjasnienia ankieterow.xlsx"));
	     XSSFWorkbook wb = new XSSFWorkbook(file);
	     Sheet sheet = wb.getSheetAt(0);
	     Row row = sheet.getRow(0);
	     Cell cell;
	     CellStyle style = wb.createCellStyle();
	     style = wb.createCellStyle();
	     style.setFillForegroundColor(IndexedColors.GOLD.getIndex());
	     style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
	     
	     //Dodawanie koloru do naglowkow
	     for(int i = 0; i<6; i++) {
		     cell = row.getCell(i);
		     cell.setCellStyle(style);
		     sheet.autoSizeColumn(i);
	     }
	    
	    FileOutputStream fileOut = new FileOutputStream("Wyjasnienia ankieterow.xlsx");
        wb.write(fileOut);
        fileOut.flush();
        wb.close();
	}
}
