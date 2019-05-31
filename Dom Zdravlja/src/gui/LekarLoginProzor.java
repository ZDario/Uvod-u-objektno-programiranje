package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import domZdravlja.DomZdravlja;
import gui.MeniKorisnika.LekarMeni;
import gui.MeniKorisnika.MedicinskaSestraMeni;
import korisnici.Lekar;
import korisnici.MedicinskaSestra;
import net.miginfocom.swing.MigLayout;

public class LekarLoginProzor extends JFrame {
	
	private JLabel lblPoruka = new JLabel("Dobrodosli. Molimo da se prijavite");
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme = new JTextField(15);
	private JLabel lblSifra = new JLabel("Lozinka");
	private JPasswordField txtSifra = new JPasswordField(15);
	private JLabel lblPrazno = new JLabel();
	private JButton btnPrijava = new JButton("Prijava");
	private JButton btnCancel = new JButton("Otkazi");
	
	private DomZdravlja domZdravlja;
	
	public LekarLoginProzor(DomZdravlja domZdravlja) {
		this.domZdravlja = domZdravlja;
		setTitle("Prijava Lekara");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initListeners();
		pack();
	}
	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		
		add(lblPoruka, "span2");
		add(lblKorisnickoIme);
		add(txtKorisnickoIme);
		add(lblSifra);
		add(txtSifra);
		add(lblPrazno);
		add(btnPrijava,"split2");
		add(btnCancel);
	}
	private void initListeners() {
		btnPrijava.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = txtKorisnickoIme.getText().trim();
				String password = new String(txtSifra.getPassword()).trim();
				
				MedicinskaSestra prijavljen = domZdravlja.login2(username, password);
				if(prijavljen == null) {
					JOptionPane.showMessageDialog(null, "Neispravni login podaci","Prijava",JOptionPane.WARNING_MESSAGE);
				}else {
					MedicinskaSestra lekar = domZdravlja.login2(username, password);
					if(lekar !=null) {
						LekarLoginProzor.this.setVisible(false);
						LekarLoginProzor.this.dispose();
						LekarMeni lekarMeni = new LekarMeni();
						lekarMeni.setVisible(true);
					}else {
						JOptionPane.showMessageDialog(null, "Pogrešni login podaci!");
					}
				}
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LekarLoginProzor.this.setVisible(false);
				LekarLoginProzor.this.dispose();
			}
		});
	}
}


