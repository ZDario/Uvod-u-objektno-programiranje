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
import korisnici.Lekar;
import korisnici.MedicinskaSestra;
import korisnici.Pacijent;
import net.miginfocom.swing.MigLayout;

public class LoginProzor extends JFrame {
	
	private JLabel lblPoruka = new JLabel("Dobrodosli. Molimo da se prijavite");
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime");
	private JTextField txtKorisnickoIme = new JTextField(15);
	private JLabel lblSifra = new JLabel("Lozinka");
	private JPasswordField txtSifra = new JPasswordField(15);
	private JLabel lblPrazno = new JLabel();
	private JButton btnPrijava = new JButton("Prijava");
	private JButton btnCancel = new JButton("Otkazi");
	
	private DomZdravlja domZdravlja;
	
	public LoginProzor(DomZdravlja domZdravlja) {
		this.domZdravlja = domZdravlja;
		setTitle("Prijava");
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
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LoginProzor.this.dispose();
				LoginProzor.this.setVisible(false);
			}
		});
		btnPrijava.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String username = txtKorisnickoIme.getText().trim();
				String password = new String(txtSifra.getPassword()).trim();
				
				Lekar prijavljen = domZdravlja.login1(username, password);
				if(prijavljen == null) {
					JOptionPane.showMessageDialog(null, "Neispravni login podaci","Prijava",JOptionPane.WARNING_MESSAGE);
				}else {
					LoginProzor.this.dispose();
					LoginProzor.this.setVisible(false);
				}
				}
		});
	}
}
