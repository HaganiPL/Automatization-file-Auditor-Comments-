import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Gui {

	Metody_gui mg = new Metody_gui();

	String[] nazwy_plikow = { "Plik_DA", "LAC_FOTO", "PROCESSING GROUP POLAND", "SG_PG_CONSO_POLAND", "SIEC_ADRES", "CS_LW" };
	String[] nazwy_plikow2 = { "Raport_ISS", "Raport_Epics" };
	JFrame frame;
	JPanel tlo;
	JPanel east;
	JLabel naglowek;
	JLabel rozkaz;
	JCheckBox checkbox1 = new JCheckBox("Plik DA");
	JCheckBox checkbox2 = new JCheckBox("LAC_FOTO");
	JCheckBox checkbox3 = new JCheckBox("PROCESSING GROUP POLAND");
	JCheckBox checkbox4 = new JCheckBox("SG_PG_CONSO_POLAND");
	JCheckBox checkbox5 = new JCheckBox("SIEC_ADRES");
	JCheckBox checkbox6 = new JCheckBox("CS_LW");
	JButton button = new JButton("NEXT");
	JButton button2;
	JButton waliduj_plik_od_DA;
	JButton waliduj_raport_ISS;
	JButton tworz_plik_RD;
	JTextArea text;
	JScrollPane przewijanie;

	public static void main(String[] args) {
		new Gui().Stworzgui1();
	}

	public void Stworzgui1() {
		mg.Tworz_gui();
	}

	class ActionListeners implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
		
			if (e.getSource() == button) { // Trzeba dodac jeszcze funkcje sprawdzajaca czy sa te pliki w folderze


				if (!checkbox1.isSelected() || !checkbox2.isSelected() || !checkbox3.isSelected() || !checkbox4.isSelected() || !checkbox5.isSelected() || !checkbox6.isSelected()) {
					JOptionPane.showMessageDialog(null, "Nie potwierdziles wgranie wszystkich plikow. \nZaznacz wszystko i nacisnij klawisz " + button.getText());
				}
				else if(mg.Sprawdz_pliki(nazwy_plikow) != ""){
					JOptionPane.showMessageDialog(null, "Nie moge znalezc pliku " + mg.Sprawdz_pliki(nazwy_plikow) + ". \nSprobuj ponownie!");
				}
				else {

					east.remove(checkbox3);
					east.remove(checkbox4);
					east.remove(checkbox5);
					east.remove(checkbox6);
					east.remove(button);

					rozkaz.setText("Wgraj nastepujace raporty:");
					checkbox1.setText("Raport_Epics");
					checkbox1.setSelected(false);
					checkbox2.setText("Raport_ISS");
					checkbox2.setSelected(false);

					button2 = new JButton("NEXT");

					button2.addActionListener(new ActionListeners());

					east.add(button2);

					frame.repaint();
				}
			}

			if (e.getSource() == button2) { 
				if (!checkbox1.isSelected() || !checkbox2.isSelected()) {
					JOptionPane.showMessageDialog(null,
							"Nie potwierdziles wgranie wszystkich plikow. \nZaznacz wszystko i nacisnij klawisz "
									+ button2.getText());
					
				}
				else if(mg.Sprawdz_pliki(nazwy_plikow2) != ""){
					JOptionPane.showMessageDialog(null, "Nie moge znalezc pliku " + mg.Sprawdz_pliki(nazwy_plikow) + ". \nSprobuj ponownie!");
				}
				
				else {
					rozkaz.setText("Wszystko gotowe! Wybierz opcje:");

					east.remove(button2);
					east.remove(checkbox1);
					east.remove(checkbox2);

					waliduj_plik_od_DA = new JButton("Waliduj plik DA");
					waliduj_raport_ISS = new JButton("Waliduj raport ISS");
					tworz_plik_RD = new JButton("Tworz plik RD i DA_BRAK_FOTO");

					waliduj_plik_od_DA.addActionListener(new ActionListeners());
					waliduj_raport_ISS.addActionListener(new ActionListeners());
					tworz_plik_RD.addActionListener(new ActionListeners());

					east.add(waliduj_plik_od_DA);
					east.add(waliduj_raport_ISS);
					east.add(tworz_plik_RD);

					frame.repaint();

				}
			}

			if(e.getSource() == waliduj_plik_od_DA){

				JLabel komunikat_waliduj_plik_od_DA = new JLabel("<html>Wiersze nie posiadajace 13 cyfr: <br>");

				try {
					for (int a : mg.waliduj_eany("Plik_DA")) {
						komunikat_waliduj_plik_od_DA.setText(komunikat_waliduj_plik_od_DA.getText() + a + "<br>");
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				komunikat_waliduj_plik_od_DA.setText(komunikat_waliduj_plik_od_DA.getText() + "<br> Brak komentarza ankietera:");

				try{
					for(int b : mg.waliduj_puste_wiersze("Plik_DA")){
						komunikat_waliduj_plik_od_DA.setText(komunikat_waliduj_plik_od_DA.getText() + b + "<br>");
					}
				}catch(IOException e2){
					e2.printStackTrace();
				}

				komunikat_waliduj_plik_od_DA.setText(komunikat_waliduj_plik_od_DA.getText() + "</html>");

				JOptionPane.showMessageDialog(null, komunikat_waliduj_plik_od_DA);
				waliduj_plik_od_DA.setEnabled(false);

			}

			if(e.getSource() == waliduj_raport_ISS){
				//Walidacja czy znajduje wszystkie kolumny
				//Walidacja czy wszystkie potrzebne kolumny są w pliku
				//Walidacja, czy externale maja 13 cyfr
			}
			
			if(e.getSource() == tworz_plik_RD) {
				
				text = new JTextArea(2,15);
				text.setLineWrap(true);
				
				przewijanie = new JScrollPane(text);
				przewijanie.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				przewijanie.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
				
				east.add(new JLabel("   "));
				east.add(przewijanie, BorderLayout.EAST);
				east.add(new JLabel("  "));
				frame.setSize(frame.getWidth() + 50, frame.getHeight());
				frame.repaint();
				
				tworz_plik_RD.setEnabled(false);
				
			}
		}
	}
}
