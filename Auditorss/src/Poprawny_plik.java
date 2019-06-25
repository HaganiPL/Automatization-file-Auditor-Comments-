import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class Poprawny_plik {
    public static void Generator_pliku_poprawnego(Cell[][] cell2, int row1, int ilosckolumn) throws IOException{
    	
    	
        XSSFWorkbook wb = new XSSFWorkbook();
		Sheet sheet = wb.createSheet("Poprawiony_plik");
		Cell[][] cell = new Cell[row1][ilosckolumn];
		int x = 0;
		int z = 0;
		
		System.out.println("Zapisywanie produktow tylko z dobrym komentarzem");
		//Zapisanie do pliku tylko produkty z dobtym komentarzem ankietera
        for(int i = 0; i<row1; i++){
			if(cell2[i][8].getStringCellValue().contains("Zdj") || cell2[i][8].getStringCellValue().contains("Niemon") || cell2[i][8].getStringCellValue().contains("Wyja")){	
				if(x==row1){
					x=0;
				}
				Row row = sheet.createRow(x);
					for(int j = 0; j<ilosckolumn; j++){
						if(z==ilosckolumn){
							z=0;
						}

						cell[i][j] = row.createCell(z);

						if(cell2[i][j]!=null) {
							switch(cell2[i][j].getCellType()) {
								case STRING:
								cell[i][j].setCellValue(cell2[i][j].getStringCellValue());
								break;
								
								case NUMERIC:
								cell[i][j].setCellValue(cell2[i][j].getNumericCellValue());
								break;
								
								default:
								break;
							}
						}
						z++;
					}
				x++;
			}
		}

        FileOutputStream fileOut2 = new FileOutputStream("Plik_DA_poprawiony.xlsx");
        wb.write(fileOut2);

        System.out.println("\nZakonczono czytanie wierszy tylko z odpowiednimi komentarzami.");
        System.out.println("Prosze pobrac Externale z pliku Plik_DA_poprawiony (kolumna C) \n\nProsze uzyskac: \n- Raport Epics (zapisac jako Raport_Epics.xlsx) \n- Raport ISS (zapisac jako Raport_ISS.xlsx) \n\ni nacisnac ENTER");
        Scanner reader2 = new Scanner(System.in);
		reader2.nextLine();
        
		//Deklaracja zmiennych
		int rowend = sheet.getLastRowNum();
		Row row;
		Cell cell_2;
		long czas_rozpoczecia = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis());
		for(int i = 0; i<rowend; i++){
			
			row = sheet.getRow(i);

			//Wstawianie numeru processing grupy
			cell_2 = row.createCell(9);
			cell_2.setCellValue(Wczytywanie_siec_adres.Dopisz_processing_grupe(row.getCell(7)));


			//Dodawanie do kolumny R, że dany produkt znajduje się w CS lub Lac weekly
			cell_2 = row.createCell(17);
			cell_2.setCellValue(CS_LW.CS_LW_CHECK(row.getCell(2).toString()));

			//Wstawienie do K bucketersi jak jest komentarz ankietera, ze "Niemonitorowane"
			if(row.getCell(17).toString().contains("CS")){
			}else{
				cell_2 = row.createCell(10); //Kolumna K
				if(row.getCell(8).toString().contains("Niem")){
					cell_2.setCellValue("Sprawdzic czy bucket i ewentualnie przerzucic");
				}
			}

			//Jezeli produkt nie jest w CS lub LW to sprawdza czy sa zdjecia
			if(row.getCell(17).toString().contains("CS")){
			}else{
				
				//Dopisuje o jaka dokladnie processing grupe chodzi w kolumnie M
				cell_2 = row.createCell(12);
				cell_2.setCellValue(Wczytywanie_Raport_Processing_group.Proc_group_check(row.getCell(9).toString()));

				//Wstawianie info o zdjeciu dla LAC do kolumny L // Kolumna 
				cell_2 = row.createCell(11);
				if(row.getCell(1).toString().contains("LAC")){
					cell_2.setCellValue(Wczytywanie_LAC_FOTO.Lac_foto_check(row.getCell(2).toString().replaceAll("[Z|z|L|l|x|X|B|b|X|x|q|Q|e|E|t|T|a|A|s|S|p|P|y|Y|u|U|i|I|r|R]", "0")));
				}
				//Jak EAN ma zdjecie to zapisuje do kolumny L ze posiada
				cell_2 = row.getCell(11);
				if(row.getCell(1).toString().contains("EAN") || row.getCell(1).toString().contains("UPC")){
					cell_2.setCellValue(Wczytywanie_Raport_Epics.Ean_foto_check(row.getCell(2).toString()));
				}

				//Dodawanie do kolumny A informacji o DA DISPATCH
				cell_2 = row.createCell(16);
				cell_2.setCellValue(Wczytywanie_Raport_ISS.Flaga_DA_DISPATCH(row.getCell(2).toString(), row.getCell(1).toString(), row.getCell(12).toString()));
			
			}
			
			if(row.getCell(10, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().contains("bucket") || row.getCell(17, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().contains("CS") || row.getCell(11).toString().contains("Brak")){
			}else{
				//Wyciaga numer processing grupy
				cell_2 = row.createCell(13); //kolumna N
				cell_2.setCellValue(Wczytywanie_Raport_ISS.Wyciaganie_GIC(row.getCell(2).toString(), row.getCell(1).toString(), row.getCell(12).toString()));
			
			}
			
			if(row.getCell(11, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().contains("Brak") || row.getCell(17, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().contains("CS")){
			}else{
				//Dopisanie numeru GIC
				cell_2 = row.createCell(14); //kolumna N
				cell_2.setCellValue(Wczytywanie_Raport_ISS.Wczytaj_GIC(row.getCell(2).toString(), row.getCell(1).toString(), row.getCell(12, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString()));
				
				//Dopisanie czy numer gic PG jest out czy in marketowa
				cell_2 = row.createCell(15); //kolumna P
				cell_2.setCellValue(Wczytywanie_PG_CONSO.Dopisz_IN_OUT_MARKET(row.getCell(14).toString()));
			}
			//Uzupelnianie kolumny "AKCJA"
			cell_2 = row.getCell(10);
			if(cell_2.toString().contains("bucke") || row.getCell(17).toString().contains("CS")){
			}
			else{
				if(row.getCell(0).toString().contains("DA Dispatched") && (row.getCell(13).toString().contains("STANDARD"))){
					
					cell_2.setCellValue("Prosze zdjac flage DA_DISPATCH");
				}
					if(row.getCell(13, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().contains("PGI") && row.getCell(1,  MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().contains("EAN") && (row.getCell(8,  MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().contains("Zdj") ||  row.getCell(8, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().contains("Wyja")) && row.getCell(15,  MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().contains("IN")){
						cell_2.setCellValue("Do zakodowania");
				}
				if(row.getCell(13).toString().contains("PGI") && row.getCell(1).toString().contains("LAC") && (row.getCell(8).toString().contains("Zdj") ||  row.getCell(8).toString().contains("Wyja"))){
					cell_2.setCellValue("Do podlaczenia/zakodowania");

				}
				if(row.getCell(13).toString().contains("PGI") && row.getCell(15).toString().contains("OUT") && row.getCell(0).toString().contains("DA Dispatched")){
					cell_2.setCellValue("Produkt znajduje sie w PG niemonitorowanym i ma zalozona flage DA DISPATCH. Prosze o analize i o ewentualnie wykonanie zmian");
				}
				
			}

			System.out.println("Wiersz numer: " + i + "/" + rowend);
		}
		
		long czas_zakonczenia = TimeUnit.MILLISECONDS.toMinutes(System.currentTimeMillis());
		System.out.println("\nAnaliza trwala " + (czas_zakonczenia-czas_rozpoczecia) + " minut\n");

		//Zapisanie i zamkniecie pliku
        FileOutputStream fileOut = new FileOutputStream("Plik_DA_poprawiony.xlsx");
        wb.write(fileOut);
        fileOut.flush();
        wb.close();
    } 
}