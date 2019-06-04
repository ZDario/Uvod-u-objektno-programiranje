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
import gui.DodavanjeIzmena.PregledUpdate;
import korisnici.Pacijent;
import pregledi.Pregledi;

public class PreglediLista extends JFrame {
	private ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
	private JButton btnAdd = new JButton(addIcon);
	private ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
	private JButton btnEdit = new JButton(editIcon);
	private ImageIcon removeIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
	private JButton btnRemove = new JButton(removeIcon);
	private JToolBar toolbar = new JToolBar();
	private JTable tabela;
	private DomZdravlja domZdravlja;
	
	public PreglediLista(DomZdravlja domZdravlja) {
		this.domZdravlja = domZdravlja;
		setTitle("Pregledi");
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
				"Ident","Zatrazen Datum","Opis","Pacijent","Lekar","Soba","Status"
		};
		Object[][] sadrzaj = new Object[domZdravlja.getPreglede().size()][zaglavlje.length];
		
		for(int i=0;i<domZdravlja.getPreglede().size();i++) {
			Pregledi pregled = domZdravlja.getPreglede().get(i);
			sadrzaj[i][0] = pregled.getIdent();
			sadrzaj[i][1] = pregled.getZatrazenDatum();
			sadrzaj[i][2] = pregled.getOpis();
			sadrzaj[i][3] = pregled.getPacijent().getKorisnickoime();
			sadrzaj[i][4] = pregled.getLekar().getKorisnickoime();
			sadrzaj[i][5] = pregled.getSoba();
			sadrzaj[i][6] = pregled.getStatus();
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
				PregledUpdate pu = new PregledUpdate(domZdravlja, null);
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
					String ident = tabela.getValueAt(red, 0).toString();
					Pregledi pregled = domZdravlja.nadjiPreglede(ident);
					if(pregled != null){
						PregledUpdate Pupdate = new PregledUpdate(domZdravlja, pregled);
						Pupdate.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
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
					String ident = tabela.getValueAt(red, 0).toString();
					Pregledi pregled = domZdravlja.nadjiPreglede(ident);
					if(pregled != null) {
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete pregled broj "
							+ pregled.getIdent() + " ?",
							" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_NO_OPTION) {
							DefaultTableModel model = (DefaultTableModel) tabela.getModel();
							if(pregled instanceof Pregledi) {
								domZdravlja.getPreglede().remove(pregled);
							}
							model.removeRow(red);
							domZdravlja.snimiPreglede();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}	
			}
		});
	}
}