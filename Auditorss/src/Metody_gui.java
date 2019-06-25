import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Font;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Metody_gui {

    public void Tworz_gui(){

        JFrame frame = new JFrame();
        JPanel tlo = new JPanel(new BorderLayout());
        JPanel east = new JPanel();
        JLabel naglowek = new JLabel("Wyjasnienia ankieterow", JLabel.CENTER);
        JLabel rozkaz = new JLabel("Zaladuj nastepujace pliki: ");
        JButton button = new JButton("Next");

        naglowek.setFont(new Font("Serif", Font.BOLD, 20));
		east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
		east.add(new JLabel("    "));
		east.add(rozkaz);
		east.add(new JLabel("Plik DA"));
		east.add(new JLabel("LAC_FOTO"));
		east.add(new JLabel("PROCESSING GROUP POLAND"));
		east.add(new JLabel("SG_PG_CONSO_POLAND"));
		east.add(new JLabel("SIEC_ADRES"));
		east.add(new JLabel("CS_LW"));
		east.add(new JLabel("    "));
        east.add(button);
        
		tlo.add(naglowek, BorderLayout.NORTH);
        tlo.add(east, BorderLayout.WEST);

        frame.add(tlo);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 400);
		frame.setVisible(true);
        
    }

    public String Sprawdz_pliki(String[] a) {
		File f;

		for (int i = 0; i < a.length; i++) {
			f = new File(a[i] + ".xlsx");

			if (!f.exists()) {
				return a[i];
			}

		}

		return "";
    }
    
    public ArrayList<Integer> waliduj_eany(String a) throws IOException {

		ArrayList<Integer> wiersze_waliduj_eany = new ArrayList<Integer>();

		FileInputStream file = new FileInputStream(new File(a + ".xlsx"));
		XSSFWorkbook wb = new XSSFWorkbook(file);
		Sheet sheet = wb.getSheetAt(0);
		int last_row = sheet.getLastRowNum();
		Row row;


		for(int i = 0; i<last_row; i++){
			row = sheet.getRow(i);
			if(row.getCell(2).toString().length() != 13){
				wiersze_waliduj_eany.add(i+1);
			}
		}
        wb.close();
		return wiersze_waliduj_eany;
    }
    
    public ArrayList<Integer> waliduj_puste_wiersze(String a) throws IOException{

		ArrayList<Integer> puste_wiersze = new ArrayList<Integer>();

		FileInputStream file = new FileInputStream(new File(a + ".xlsx"));
		XSSFWorkbook wb = new XSSFWorkbook(file);
		Sheet sheet = wb.getSheetAt(0);
		int last_row = sheet.getLastRowNum();
		Row row;

		for(int i = 0; i<last_row; i++){
			row = sheet.getRow(i);
			if(row.getCell(8) == null){
				puste_wiersze.add(i+1);
			}
		}

        wb.close();
		return puste_wiersze;
    }
    
    public ArrayList<Integer> czy_sa_kolumny(String c) throws IOException{
		FileInputStream file = new FileInputStream(new File(c + ".xlsx"));
		XSSFWorkbook wb = new XSSFWorkbook(file);
		Sheet sheet = wb.getSheetAt(0);
        Row row = sheet.getRow(0);
        
		ArrayList<Integer> checkKolumny = new ArrayList<Integer>();

		
        int ilosckolumn = row.getLastCellNum();


		for(int i = 0; i<ilosckolumn; i++){
          
        }

        wb.close();
        return checkKolumny;
	}

}