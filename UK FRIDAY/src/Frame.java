import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.border.EtchedBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.AbstractListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class Frame extends JFrame {

	private JPanel contentPane;
	JFileChooser fc;
	File[] files;
	JList list;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame frame = new Frame();
					frame.setTitle("UK FRIDAY STORES");
					
					
					frame.Process();
					
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Frame() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 479, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblChoosedFiles = new JLabel("Choosed files:");
		lblChoosedFiles.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblChoosedFiles.setBounds(26, 11, 145, 29);
		panel.add(lblChoosedFiles);
		
		JLabel lblColumnsToDelete = new JLabel("Columns to delete");
		lblColumnsToDelete.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblColumnsToDelete.setBounds(283, 11, 148, 29);
		panel.add(lblColumnsToDelete);
		
		list = new JList();
		list.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list.setBounds(26, 63, 145, 88);
		panel.add(list);
		
		JLabel lblChooseDelimiter = new JLabel("Choose delimiter");
		lblChooseDelimiter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblChooseDelimiter.setBounds(26, 162, 122, 37);
		panel.add(lblChooseDelimiter);
		
		JButton btnStart = new JButton("START");
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 27));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				System.out.println("In progressss");
			}
		});
		btnStart.setBounds(181, 150, 259, 106);
		panel.add(btnStart);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 199, 93, 37);
		panel.add(scrollPane);
		
		JList list_2 = new JList();
		scrollPane.setViewportView(list_2);
		list_2.setValueIsAdjusting(true);
		list_2.setModel(new AbstractListModel() {
			String[] values = new String[] {";", ",", "TAB", "SPACE"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_2.setSelectedIndex(0);
		list_2.setVisibleRowCount(2);
		list_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		list_2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(283, 51, 58, 88);
		panel.add(scrollPane_1);
		
		JList list_1 = new JList();
		scrollPane_1.setViewportView(list_1);
		list_1.setVisibleRowCount(10);
		list_1.setModel(new AbstractListModel() {
			String[] values = new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
			public int getSize() {
				return values.length;
			}
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
	}

	//Create function
	public void Process() {
		FileNameExtensionFilter fnef = new FileNameExtensionFilter("Pick just .csv", "csv");
		fc = new JFileChooser();
		fc.setMultiSelectionEnabled(true);
        fc.setFileFilter(fnef);
        fc.getCurrentDirectory();
        
		
		int choosedParametr = fc.showOpenDialog(null);
		if(choosedParametr == fc.APPROVE_OPTION) {
			files = fc.getSelectedFiles();
			
			
		}
		
			list.setModel(new AbstractListModel() {
				String[] values = new String[] {";", ",", "TAB", "SPACE"};
				public int getSize() {
					return values.length;
				}
				public Object getElementAt(int index) {
					return values[index];
				}
			});
			
	}
	
}
