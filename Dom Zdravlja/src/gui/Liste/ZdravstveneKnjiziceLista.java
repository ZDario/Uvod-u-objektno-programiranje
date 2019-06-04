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
import gui.DodavanjeIzmena.ZdravstvenaKnjizicaUpdate;
import korisnici.Pacijent;
import zdravstvenaKnjizica.ZdravstvenaKnjizica;

public class ZdravstveneKnjiziceLista extends JFrame {
	private ImageIcon addIcon = new ImageIcon(getClass().getResource("/slike/add.gif"));
	private JButton btnAdd = new JButton(addIcon);
	private ImageIcon editIcon = new ImageIcon(getClass().getResource("/slike/edit.gif"));
	private JButton btnEdit = new JButton(editIcon);
	private ImageIcon removeIcon = new ImageIcon(getClass().getResource("/slike/remove.gif"));
	private JButton btnRemove = new JButton(removeIcon);
	private JToolBar toolbar = new JToolBar();
	private JTable tabela;
	private DomZdravlja domZdravlja;
	
	public ZdravstveneKnjiziceLista(DomZdravlja domZdravlja) {
		this.domZdravlja = domZdravlja;
		setTitle("Zdravstvene Knjizice");
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
				"Ident","Datum Isteka","Kategorija Osiguranja"
		};
		Object[][] sadrzaj = new Object[domZdravlja.getZdravstvenaKnjizice().size()][zaglavlje.length];
		
		for(int i=0;i<domZdravlja.getZdravstvenaKnjizice().size();i++) {
			ZdravstvenaKnjizica knjizica = domZdravlja.getZdravstvenaKnjizice().get(i);
			sadrzaj[i][0] = knjizica.getIdent();
			sadrzaj[i][1] = knjizica.getDatumIsteka();
			sadrzaj[i][2] = knjizica.getKategorijaosiguranja();
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
				ZdravstvenaKnjizicaUpdate zku = new ZdravstvenaKnjizicaUpdate(domZdravlja, null);
				zku.setVisible(true);
				
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
					ZdravstvenaKnjizica knjizica = domZdravlja.nadjiKnjizicu(ident);
					if(knjizica != null){
						ZdravstvenaKnjizicaUpdate ZKupdate = new ZdravstvenaKnjizicaUpdate(domZdravlja, knjizica);
						ZKupdate.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu zdravstvenu knjizicu!", "Greska", JOptionPane.ERROR_MESSAGE);
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
					ZdravstvenaKnjizica knjizica = domZdravlja.nadjiKnjizicu(ident);
					if(knjizica != null) {
					int izbor = JOptionPane.showConfirmDialog(null, "Da li ste sigurni da zelite da obrisete zdravstvenu knjizicu broja "
							+ knjizica.getIdent() + " ?",
							" - Potvrda brisanja", JOptionPane.YES_NO_OPTION);
						if(izbor == JOptionPane.YES_NO_OPTION) {
							DefaultTableModel model = (DefaultTableModel) tabela.getModel();
							if(knjizica instanceof ZdravstvenaKnjizica) {
								domZdravlja.getZdravstvenaKnjizice().remove(knjizica);
							}
							model.removeRow(red);
							domZdravlja.snimiZdravstveneKnjizice();
						}
					}else {
						JOptionPane.showMessageDialog(null, "Nije moguce pronaci odabranu zdravstvenu knjizicu!", "Greska", JOptionPane.ERROR_MESSAGE);
					}
				}	
			}
		});
	}
}
