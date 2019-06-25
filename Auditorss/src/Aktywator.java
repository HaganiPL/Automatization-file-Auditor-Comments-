
import java.io.IOException;
import java.util.Scanner;

public class Aktywator {
    public void run() throws IOException, InterruptedException {
        
        System.out.println("\n\n\n\n\n======================================");
        System.out.println("=     WYJASNIENIA ANKIETEROW         =");
        System.out.println("======================================");
        System.out.println("\nWklej plik od DA z poprawnym formatem kolumn (szczegolnie Eany jako tekst, bez srednikow), zapisz jako Plik_DA.xlsx");
        System.out.println("Czy zaladowales plik? Nacisnij ENTER");
        Scanner reader = new Scanner(System.in);
        reader.nextLine();

        System.out.println("\nProsze zaladowac nastepujace pliki: \n\n- LAC_FOTO.xlsx, \n- PROCESSING GROUP POLAND.xlsx, \n- SG_PG_CONSO_POLAND.xlsx, \n- SIEC_ADRES.xlsx, \n- CS_LW.xlsx --> wszystkie barcody z raportu Client Supply i z pliku Lac Weekly polaczyc i wpisac w kolumnie A\n\nI nacisnac ENTER");
        reader.nextLine();
        
         Wczytanie_DA.wczytaj_surowy_plik();
         System.out.println("Tworzenie pliku dla RD");
         Tworzenie_plik_do_pracy.wczytaj_plik_roboczy();
         System.out.println("Tworzenie pliku dla DA");
         Tworzenie_plik_DA.Plik_dla_DA();
         System.out.println("Formatowanie ostatecznego pliku...");
    	 Format_wyjasnienia_ankieterow.Format();

    	System.out.println("Zosta³y stworzone nastepujace pliki: \n- Plik_DA_poprawiony --> Tutaj mozesz podejrzec co program zrobil z danym produktem\n- PLIK_DA_BRAK_FOTO --> Jest to plik z produktami dla ktorych nie przyszly zdjecia\n- Wyjasnienia_ankieterow --> Gotowy plik dla RD ");
        System.out.println("Nacisnij ENTER w celu zakonczenia programu");
        reader.nextLine();
        System.out.println("Koniec programu");
        reader.close();
        
    }
}
