import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class Wczytanie_DA{
    public static void wczytaj_surowy_plik() throws IOException{
        
        FileInputStream file = new FileInputStream(new File("Plik_DA.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(file);

        Sheet sheet = wb.getSheetAt(0);
        Row row3 = sheet.getRow(0);
        int row1 = sheet.getLastRowNum();
        row1++;
		int row2 = sheet.getFirstRowNum();
        
        Row row5 = sheet.getRow(0);
        int ilosckolumn = row5.getLastCellNum();
       

        Cell[][] cell2 = new Cell[row1][row3.getLastCellNum()];

        for(int i = row2; i<row1; i++) //i to licznik wiersza
			{
				Row row = sheet.getRow(i);
				
				for(int j = row.getFirstCellNum(); j<row.getLastCellNum(); j++) //j to licznik kolumn
				{
					
					Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
					
					switch(cell.getCellType())
					{

					case STRING:
					cell2[i][j] = cell;
					
					break;
					
					case NUMERIC:
						cell2[i][j] = cell;
						
						break;
					case BLANK:
						cell2[i][j] = cell;
						
						break;
					case BOOLEAN:
						cell2[i][j] = cell;
						
						break;
					case ERROR:
						cell2[i][j] = cell;
						
						break;
					case FORMULA:
						cell2[i][j] = cell;
						
						break;
					case _NONE:
						cell2[i][j] = cell;
						
						break;
					default:
						break;
						
					} //koniec switch
				}//koniec petli
            }//koniec drugiej pętli	
      Poprawny_plik.Generator_pliku_poprawnego(cell2, row1, ilosckolumn);
        
        wb.close();
    }
    
}