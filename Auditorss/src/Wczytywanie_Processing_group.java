import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class Wczytywanie_Raport_Processing_group{
    public static String Proc_group_check(String numer_proc_grupy) throws IOException{
        
        FileInputStream file = new FileInputStream(new File("PROCESSING GROUP POLAND.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(file);
		Sheet sheet = wb.getSheetAt(0);
		int rowend = sheet.getLastRowNum()+1;
        String nazwa_proc_grupy = "Nie znaleziono Proc.grupy";
        Row row;
    
		//Czytanie kodow i nazw processing grup
		for(int i = 0; i<rowend; i++){
			row = sheet.getRow(i);

            if(row.getCell(1) !=null){
                if(row.getCell(1).toString().contains(numer_proc_grupy)){
                    wb.close();
                    if(row.getCell(2).toString().contains("SIMPLY MARKET")){
                        return "ELEA";
                    }
                    if(row.getCell(2).toString().contains("SUPER-PHARM")){
                        return "SUPERPHARM";
                    }else
                        return row.getCell(2).toString();
                }
            }
        }

		wb.close();
		return nazwa_proc_grupy;
	}
}