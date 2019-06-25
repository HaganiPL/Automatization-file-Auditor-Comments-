import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class Wczytywanie_Raport_ISS{
    public static String Wyciaganie_GIC(String ext_iss, String cell_ext_type, String proc_grupa) throws IOException{
        
        FileInputStream file = new FileInputStream(new File("Raport_ISS.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(file);
		Sheet sheet = wb.getSheetAt(0);
        int rowend = sheet.getLastRowNum();
        rowend++;
        String GIC = "Nie znalazlem poziomu danego externala";
        Row row;
        Row row2 = sheet.getRow(0);
        String max_seq = "0";

        int ilosckolumn = row2.getLastCellNum();
        int seq = 0;
        int external= 0;
        int processing_grupa= 0;
        int level= 0;
        //Sprawdzanie numerów kolumn 
        for(int i = 0; i<ilosckolumn; i++){
            if(row2.getCell(i).toString().contains("Seq")){
                seq = i;
            }
            if(row2.getCell(i).toString().equals("External Code")){
                external = i;
            }
            if(row2.getCell(i).toString().contains("Processing")){
                processing_grupa = i;
            }
            if(row2.getCell(i).toString().contains("Level")){
                level = i;
            }
        }

		//Sprawdzanie największej seq dla Laca z parametru
		if(cell_ext_type.contains("LAC")){
            for(int i = 0; i<rowend; i++){
                row = sheet.getRow(i);
                if(row.getCell(external,MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().contains(ext_iss) && row.getCell(processing_grupa,MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().equals(proc_grupa)){
                    if((Double.parseDouble(row.getCell(seq).toString()) > Double.parseDouble(max_seq))){
                        max_seq = row.getCell(seq).toString();
                    }
                }
            }
        }
        

        //Wypisanie dla danego laca i danej seq poziomu
        if(cell_ext_type.contains("LAC")){
            for(int i = 0; i<rowend; i++){
                row = sheet.getRow(i);
	                if(row.getCell(external, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().contains(ext_iss) && row.getCell(seq, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().equals(max_seq) && row.getCell(processing_grupa, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().equals(proc_grupa)){
	                    GIC = row.getCell(level, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString();
	                    wb.close();
	                    return GIC;
	                }
	                
            }
        }else{
            for(int i = 0; i<rowend; i++){
                row = sheet.getRow(i);
                if(row.getCell(external).toString().contains(ext_iss)){
                    GIC = row.getCell(level).toString();
                    wb.close();
                    return GIC;
                }
            }
        }
        
		wb.close();
		return GIC;
    }

    public static String Flaga_DA_DISPATCH(String ext_iss, String cell_ext_type, String proc_grupa) throws IOException {

        FileInputStream file = new FileInputStream(new File("Raport_ISS.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(file);
		Sheet sheet = wb.getSheetAt(0);
        int rowend = sheet.getLastRowNum();
        Row row2 = sheet.getRow(0);
        rowend++;
        String DA_DISPATCH = "Nie znalazlem flagi DA_DISPATCH";
        Row row;
        String max_seq = "0";


        int ilosckolumn = row2.getLastCellNum();
        int seq = 0;
        int external= 0;
        int processing_grupa= 0;
        int dispatch = 0;
        //Sprawdzanie numerów kolumn 
        for(int i = 0; i<ilosckolumn; i++){
            if(row2.getCell(i).toString().contains("Seq")){
                seq = i;
            }
            if(row2.getCell(i).toString().equals("External Code")){
                external = i;
            }
            if(row2.getCell(i).toString().contains("Processing")){
                processing_grupa = i;
            }
            if(row2.getCell(i).toString().contains("Level")){
            }
            if(row2.getCell(i).toString().contains("Dispatch")){
                dispatch = i;
            }
        }
		//Sprawdzanie największej seq dla Laca z parametru
		if(cell_ext_type.contains("LAC")){
            for(int i = 0; i<rowend; i++){
                row = sheet.getRow(i);
                if(row.getCell(external).toString().contains(ext_iss) && row.getCell(processing_grupa).toString().contains(proc_grupa)){
                    if(Double.parseDouble(row.getCell(seq).toString()) > Double.parseDouble(max_seq)){
                        max_seq = row.getCell(seq).toString();
                    } 
                }
            }
        }
            

        //Wypisanie dla danego laca i danej seq poziomu
        if(cell_ext_type.contains("LAC")){
            for(int i = 0; i<rowend; i++){
                row = sheet.getRow(i);
                if(row.getCell(external, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().contains(ext_iss) && row.getCell(seq, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().equals(max_seq) && row.getCell(processing_grupa, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().equals(proc_grupa)){
                    DA_DISPATCH = row.getCell(dispatch, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString();
                    wb.close();
                    return DA_DISPATCH;
                }
            }
        }else{
            for(int i = 0; i<rowend; i++){
                row = sheet.getRow(i);
                if(row.getCell(external).toString().contains(ext_iss)){
                    DA_DISPATCH = row.getCell(dispatch).toString();
                    wb.close();
                    return DA_DISPATCH;
                }
            }
        }
        
		wb.close();
		return DA_DISPATCH;
    }

    public static String Wczytaj_GIC(String ext_iss, String cell_ext_type, String proc_grupa) throws IOException {
        
        FileInputStream file = new FileInputStream(new File("Raport_ISS.xlsx"));
        XSSFWorkbook wb = new XSSFWorkbook(file);
		Sheet sheet = wb.getSheetAt(0);
        int rowend = sheet.getLastRowNum();
        rowend++;
        Row row2 = sheet.getRow(0);
        String  Numer_GIC = "Nie znalazlem GIC";
        Row row;
        String max_seq = "0";


        int ilosckolumn = row2.getLastCellNum();
        int seq = 0;
        int external= 0;
        int processing_grupa= 0;
        // Sprawdzanie numerów kolumn 
        for(int i = 0; i<ilosckolumn; i++){
            if(row2.getCell(i).toString().contains("Seq")){
                seq = i;
            }
            if(row2.getCell(i).toString().equals("External Code")){
                external = i;
            }
            if(row2.getCell(i).toString().contains("Processing")){
                processing_grupa = i;
            }
            if(row2.getCell(i).toString().contains("Level")){
            }
            if(row2.getCell(i).toString().contains("Dispatch")){
            }
        }


		//Sprawdzanie największej seq dla Laca z parametru
		if(cell_ext_type.contains("LAC")){
            for(int i = 0; i<rowend; i++){
                row = sheet.getRow(i);
                if(row.getCell(external) != null){
                    if(row.getCell(external).toString().contains(ext_iss) && row.getCell(processing_grupa).toString().equals(proc_grupa)){
                        if(Double.parseDouble(row.getCell(seq).toString()) > Double.parseDouble(max_seq)){
                            max_seq = row.getCell(seq).toString();
                        } 
                    }
                }
            }
        }
            

        //Wypisanie dla danego laca i danej seq poziomu
        if(cell_ext_type.contains("LAC")){
            for(int i = 0; i<rowend; i++){
                row = sheet.getRow(i);
                if(row.getCell(external, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().contains(ext_iss) && row.getCell(seq, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().equals(max_seq) && row.getCell(processing_grupa, MissingCellPolicy.CREATE_NULL_AS_BLANK).toString().equals(proc_grupa)){
                    Numer_GIC = row.getCell(0).toString();
                    wb.close();
                    return Numer_GIC;
                }
            }
        }else{
            for(int i = 0; i<rowend; i++){
                row = sheet.getRow(i);
                if(row.getCell(external) != null){
                    if(row.getCell(external).toString().contains(ext_iss)){
                        Numer_GIC = row.getCell(0).toString();
                        wb.close();
                        return Numer_GIC;   
                    }
                }
            }
        }
        
		wb.close();
		return Numer_GIC;
        


    }
    
}