package gui.MeniKorisnika;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import domZdravlja.DomZdravlja;
import gui.LekPacPregledi.PacijentPreglediUpdate;
import korisnici.Pacijent;
import pregledi.Pregledi;
import zdravstvenaKnjizica.ZdravstvenaKnjizica;

public class PacijentMeni extends JFrame {
	private ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
	private JButton btnAdd = new JButton(addIcon);
	private ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
	private JButton btnEdit = new JButton(editIcon);
	private JToolBar toolbar = new JToolBar();
	private JTable tabela;
	private DomZdravlja domZdravlja;
	private JButton btnRacun = new JButton("Racun");
	private JTextField txtRacun = new JTextField(20);
	private JLabel lblCena= new JLabel("dinara.");
	private ArrayList<Pregledi> pregledi = new ArrayList<Pregledi>();
	
	public PacijentMeni(DomZdravlja domZdravlja,String korisnickoime) {
		this.domZdravlja = domZdravlja;
		setTitle("Lista Pregleda:	" + domZdravlja.nadjiPacijenta(korisnickoime).getKorisnickoime());
		setSize(1200,500);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI(korisnickoime);
		initListeners(korisnickoime);
	}
	private void initGUI(String korisnickoime){
		toolbar.add(btnAdd);
		toolbar.add(btnEdit);
		toolbar.add(btnRacun);
		toolbar.add(txtRacun);
		toolbar.add(lblCena);
		add(toolbar, BorderLayout.NORTH);
		String[] zaglavlje = new String[] {
				"Ident","Zatrazen Datum","Opis","Lekar","Pacijent","Soba","Status"
		};
		pregledi = domZdravlja.pronadjiPregledePacijent(korisnickoime);
		Object[][] sadrzaj = new Object[pregledi.size()][zaglavlje.length];
		
		for(int i=0;i<pregledi.size();i++) {
			Pregledi pregled = pregledi.get(i);
			sadrzaj[i][0] = pregled.getIdent();
			sadrzaj[i][1] = domZdravlja.VremeUString(pregled.getZatrazenDatum(),domZdravlja.getFormatTermina());
			sadrzaj[i][2] = pregled.getOpis();
			sadrzaj[i][3] = pregled.getLekar().getKorisnickoime();
			sadrzaj[i][4] = pregled.getPacijent().getKorisnickoime();
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

	private void initListeners(String korisnickoime){
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PacijentPreglediUpdate pu = new PacijentPreglediUpdate(domZdravlja, null, korisnickoime);
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
						PacijentPreglediUpdate Pupdate = new PacijentPreglediUpdate(domZdravlja, pregled, korisnickoime);
						Pupdate.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabrani pregled!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnRacun.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int red = tabela.getSelectedRow();
				if (red == -1) {
					JOptionPane.showMessageDialog(null, "Morate odabrati red u tabeli.", "Greska",
							JOptionPane.WARNING_MESSAGE);
				}else{
					String korisnickoime = tabela.getValueAt(red, 4).toString();
					Pacijent pacijent = domZdravlja.nadjiPacijenta(korisnickoime);
					ZdravstvenaKnjizica knjizica = domZdravlja.nadjiKnjizicuZaPregled(pacijent.getKorisnickoime());
					double racun = domZdravlja.napraviRacun(knjizica.getKategorijaosiguranja());
					txtRacun.setText(String.valueOf(racun));
				}
			}
		});
	}
}
