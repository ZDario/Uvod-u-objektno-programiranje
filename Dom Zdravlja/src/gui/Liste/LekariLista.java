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
import gui.DodavanjeIzmena.LekarUpdate;
import korisnici.Lekar;

public class LekariLista extends JFrame {
	private ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
	private JButton btnAdd = new JButton(addIcon);
	private ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
	private JButton btnEdit = new JButton(editIcon);
	private ImageIcon removeIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
	private JButton btnRemove = new JButton(removeIcon);
	private JToolBar toolbar = new JToolBar();
	private JTable tabela;
	private DomZdravlja domZdravlja;
	
	public LekariLista(DomZdravlja domZdravlja) {
		this.domZdravlja = domZdravlja;
		setTitle("Lekari");
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
				"Lozinka","Pol","Uloga","Plata","Sluzba","Specijalizacija"
		};
		Object[][] sadrzaj = new Object[domZdravlja.getLekare().size()][zaglavlje.length];
		
		for(int i=0;i<domZdravlja.getLekare().size();i++) {
			Lekar lekar = domZdravlja.getLekare().get(i);
			sadrzaj[i][0] = lekar.getIme();
			sadrzaj[i][1] = lekar.getPrezime();
			sadrzaj[i][2] = lekar.getJmbg();
			sadrzaj[i][3] = lekar.getAdresa();
			sadrzaj[i][4] = lekar.getTelefon();
			sadrzaj[i][5] = lekar.getKorisnickoime();
			sadrzaj[i][6] = lekar.getLozinka();
			sadrzaj[i][7] = lekar.getPol();
			sadrzaj[i][8] = lekar.getUloga();
			sadrzaj[i][9] = lekar.getPlata();
			sadrzaj[i][10] = lekar.getSluzba();
			sadrzaj[i][11] = lekar.getSpecijalizacija();
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
				LekarUpdate lu = new LekarUpdate(domZdravlja, null);
				lu.setVisible(true);
				
			}
		});
		btnEdit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tabela.getSelectedRow();
				if(red == -1){
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli","Greska",JOptionPane.WARNING_MESSAGE);
				}else{
					String korisnickoime = tabela.getValueAt(red, 5).toString();
					Lekar lekar = domZdravlja.nadjiLekara(korisnickoime);
					if(lekar != null){
						LekarUpdate Lupdate = new LekarUpdate(domZdravlja, lekar);
						Lupdate.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog lekara!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnRemove.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tabela.getSelectedRow();
				if(red == -1){
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli","Greska",JOptionPane.WARNING_MESSAGE);
				}else{
					String korisnickoime = tabela.getValueAt(red, 5).toString();
					Lekar lekar = domZdravlja.nadjiLekara(korisnickoime);
					if(lekar != null) {
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete lekara?",
							" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_NO_OPTION) {
							DefaultTableModel model = (DefaultTableModel) tabela.getModel();
							if(lekar instanceof Lekar) {
								domZdravlja.getLekare().remove(lekar);
							}
							model.removeRow(red);
							domZdravlja.snimiLekare();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog lekara!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}	
			}
		});
	}
}
