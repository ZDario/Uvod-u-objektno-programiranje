package gui.Liste;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import domZdravlja.DomZdravlja;
import gui.DodavanjeIzmena.MedicinskaSestraUpdate;
import korisnici.MedicinskaSestra;

public class MedicinskeSestreLista extends JFrame {
	private ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
	private JButton btnAdd = new JButton(addIcon);
	private ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
	private JButton btnEdit = new JButton(editIcon);
	private ImageIcon removeIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
	private JButton btnRemove = new JButton(removeIcon);
	private JToolBar toolbar = new JToolBar();
	private JTable tabela;
	private DomZdravlja domZdravlja;
	
	public MedicinskeSestreLista(DomZdravlja domZdravlja) {
		this.domZdravlja = domZdravlja;
		setTitle("Medicinske Sestre");
		setSize(1200,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initListeners();
	}
	private void initGUI(){
		toolbar.add(btnAdd);
		toolbar.add(btnEdit);
		toolbar.add(btnRemove);
		add(toolbar, BorderLayout.NORTH);
		String[] zaglavlje = new String[] {
				"Ime","Prezime","JMBG","Adresa","Telefon","Korisnicko Ime",
				"Lozinka","Pol","Uloga","Plata","Sluzba"
		};
		Object[][] sadrzaj = new Object[domZdravlja.getMedicinskaSestre().size()][zaglavlje.length];
		
		for(int i=0;i<domZdravlja.getMedicinskaSestre().size();i++) {
			MedicinskaSestra sestra = domZdravlja.getMedicinskaSestre().get(i);
			sadrzaj[i][0] = sestra.getIme();
			sadrzaj[i][1] = sestra.getPrezime();
			sadrzaj[i][2] = sestra.getJmbg();
			sadrzaj[i][3] = sestra.getAdresa();
			sadrzaj[i][4] = sestra.getTelefon();
			sadrzaj[i][5] = sestra.getKorisnickoime();
			sadrzaj[i][6] = sestra.getLozinka();
			sadrzaj[i][7] = sestra.getPol();
			sadrzaj[i][8] = sestra.getUloga();
			sadrzaj[i][9] = sestra.getPlata();
			sadrzaj[i][10] = sestra.getSluzba();
		}
		DefaultTableModel model = new DefaultTableModel(sadrzaj, zaglavlje);
		tabela = new JTable(model);
		tabela.setRowSelectionAllowed(true);
		tabela.setColumnSelectionAllowed(false);
		tabela.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabela.setDefaultEditor(Object.class, null);
		JScrollPane scrollPane = new JScrollPane(tabela);
		add(scrollPane , BorderLayout.CENTER);		
	}

	private void initListeners(){
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MedicinskaSestraUpdate msu = new MedicinskaSestraUpdate(domZdravlja, null);
				msu.setVisible(true);
				
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tabela.getSelectedRow();
				if(red == -1){
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli!","Greska",JOptionPane.WARNING_MESSAGE);
				}else{
					String korisnickoime = tabela.getValueAt(red, 5).toString();
					MedicinskaSestra sestra = domZdravlja.nadjiMedicinskuSestru(korisnickoime);
					if(sestra != null){
						MedicinskaSestraUpdate MSupdate = new MedicinskaSestraUpdate(domZdravlja, sestra);
						MSupdate.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu medicinsku sestru!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tabela.getSelectedRow();
				if(red == -1){
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli!","Greska",JOptionPane.WARNING_MESSAGE);
				}else{
					String korisnickoime = tabela.getValueAt(red, 5).toString();
					MedicinskaSestra sestra = domZdravlja.nadjiMedicinskuSestru(korisnickoime);
					if(sestra != null) {
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete medicinsku sestru "
							+ sestra.getIme() + sestra.getPrezime() + " ?",
							" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_NO_OPTION) {
							DefaultTableModel model = (DefaultTableModel) tabela.getModel();
							if(sestra instanceof MedicinskaSestra) {
								domZdravlja.getMedicinskaSestre().remove(sestra);
							}
							model.removeRow(red);
							domZdravlja.snimiMedicinskeSestre();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu medicinsku sestru!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}	
			}
		});
	}
}
