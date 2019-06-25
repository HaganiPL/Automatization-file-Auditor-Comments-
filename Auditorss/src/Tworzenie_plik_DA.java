import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class Tworzenie_plik_DA{
    public static void Plik_dla_DA() throws IOException{
        
        FileInputStream file = new FileInputStream(new File("Plik_DA_poprawiony.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(file);

        Sheet sheet = wb.getSheetAt(0);
        Row row3 = sheet.getRow(0);
        int row1 = sheet.getLastRowNum();
        row1++;
		int row2 = sheet.getFirstRowNum();
       
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
            }//koniec drugiej petli	
        
        wb.close();

        //Tworzenie pliku dla koderow
        XSSFWorkbook wb2 = new XSSFWorkbook();
        Sheet sheet2 = wb2.createSheet("Brak_PL_FOTO");
        String[] kolumny = new String[] {"Auditor", "Shop_Code", "Typ kodu", "External Code", "Opis"};
        Cell naglowki;
        Cell wartosci;
        Row row;
        row = sheet2.createRow(0);

        //Tworzenie naglowkow
        for(int i = 0; i<5;i++){

            row = sheet2.getRow(0);
            naglowki = row.createCell(i);
            naglowki.setCellValue(kolumny[i]);
        }

        int z = 1;
        //Dodawanie pliku koncowego dla RD kolejnych wierszy
        for(int i = 0; i<row1; i++){
            row3 = sheet.getRow(i);
            if(row3.getCell(10) == null || row.getCell(8).toString().contains("Niemonitorowane")){

            }else{
                if(cell2[i][11].toString().contains("Brak")){

                    row = sheet2.createRow(z);
                    wartosci = row.createCell(0);
                    wartosci.setCellValue(cell2[i][0].toString()); //Numer ankietera
                    wartosci = row.createCell(1);
                    wartosci.setCellValue(cell2[i][4].toString()); //Kod sklepu
                    wartosci = row.createCell(2);
                    wartosci.setCellValue(cell2[i][1].toString()); //Typ kodu
                    wartosci = row.createCell(3);
                    wartosci.setCellValue(cell2[i][2].toString()); //External kod
                    wartosci = row.createCell(4);
                    wartosci.setCellValue(cell2[i][3].toString()); //Opis
                    z++;
                }
                
            }
        }
        FileOutputStream fileOut = new FileOutputStream("Plik_DA_BRAK_FOTO.xlsx");
        wb2.write(fileOut);
        fileOut.flush();
        wb2.close();
    }
    
}