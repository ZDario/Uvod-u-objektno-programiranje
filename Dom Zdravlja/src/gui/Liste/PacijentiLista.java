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
import gui.DodavanjeIzmena.PacijentUpdate;
import korisnici.Pacijent;

public class PacijentiLista extends JFrame {
	private ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
	private JButton btnAdd = new JButton(addIcon);
	private ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
	private JButton btnEdit = new JButton(editIcon);
	private ImageIcon removeIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
	private JButton btnRemove = new JButton(removeIcon);
	private JToolBar toolbar = new JToolBar();
	private JTable tabela;
	private DomZdravlja domZdravlja;
	
	public PacijentiLista(DomZdravlja domZdravlja) {
		this.domZdravlja = domZdravlja;
		setTitle("Pacijenti");
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
				"Lozinka","Pol","Uloga","Zdravstvena Knjizica","Izabrani Lekar",
		};
		Object[][] sadrzaj = new Object[domZdravlja.getPacijente().size()][zaglavlje.length];
		
		for(int i=0;i<domZdravlja.getPacijente().size();i++) {
			Pacijent pacijent = domZdravlja.getPacijente().get(i);
			sadrzaj[i][0] = pacijent.getIme();
			sadrzaj[i][1] = pacijent.getPrezime();
			sadrzaj[i][2] = pacijent.getJmbg();
			sadrzaj[i][3] = pacijent.getAdresa();
			sadrzaj[i][4] = pacijent.getTelefon();
			sadrzaj[i][5] = pacijent.getKorisnickoime();
			sadrzaj[i][6] = pacijent.getLozinka();
			sadrzaj[i][7] = pacijent.getPol();
			sadrzaj[i][8] = pacijent.getUloga();
			sadrzaj[i][9] = pacijent.getKnjizica().getIdent();
			sadrzaj[i][10] = pacijent.getIzabraniLekar().getKorisnickoime();
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
				PacijentUpdate pu = new PacijentUpdate(domZdravlja, null);
				pu.setVisible(true);
				
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
					Pacijent pacijent = domZdravlja.nadjiPacijenta(korisnickoime);
					if(pacijent != null){
						PacijentUpdate Pupdate = new PacijentUpdate(domZdravlja, pacijent);
						Pupdate.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog pacijenta!", "Greska", JOptionPane.ERROR_MESSAGE);
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
					Pacijent pacijent = domZdravlja.nadjiPacijenta(korisnickoime);
					if(pacijent != null) {
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete pacijenta "
							+ pacijent.getIme() + pacijent.getPrezime() + " ?",
							" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_NO_OPTION) {
							DefaultTableModel model = (DefaultTableModel) tabela.getModel();
							if(pacijent instanceof Pacijent) {
								domZdravlja.getPacijente().remove(pacijent);
							}
							model.removeRow(red);
							domZdravlja.snimiPacijente();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranog pacijenta!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}	
			}
		});
	}
}
