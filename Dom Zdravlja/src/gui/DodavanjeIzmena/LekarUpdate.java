package gui.DodavanjeIzmena;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import domZdravlja.DomZdravlja;
import domZdravlja.SluzbeDomaZdravlja;
import korisnici.Lekar;
import korisnici.Pol;
import korisnici.Uloga;
import net.miginfocom.swing.MigLayout;

public class LekarUpdate extends JFrame {
	private JLabel lblIme = new JLabel("Ime: ");
	private JTextField txtIme = new JTextField(20);
	
	private JLabel lblPrezime = new JLabel("Prezime: ");
	private JTextField txtPrezime = new JTextField(20);
	
	private JLabel lblJmbg = new JLabel("JMBG: ");
	private JTextField txtJmbg = new JTextField(20);
	
	private JLabel lblAdresa = new JLabel("Adresa: ");
	private JTextField txtAdresa = new JTextField(20);
	
	private JLabel lblTelefon = new JLabel("Telefon: ");
	private JTextField txtTelefon = new JTextField(20);
	
	private JLabel lblKorisnickoIme = new JLabel("Korisnicko ime: ");
	private JTextField txtKorisnickoIme = new JTextField(20);
	
	private JLabel lblLozinka = new JLabel("Lozinka: ");
	private JTextField txtLozinka = new JTextField(20);
	
	private JLabel lblPol = new JLabel("Pol: ");
	private JComboBox<Pol> cbPol = new JComboBox<Pol>(Pol.values());
	
	private JLabel lblUloga = new JLabel("Uloga: ");
	private JComboBox<Uloga> cbUloga = new JComboBox<Uloga>(Uloga.values());
	
	private JLabel lblPlata = new JLabel("Plata: ");
	private JTextField txtPlata = new JTextField(20);
	
	private JLabel lblSluzba = new JLabel("Sluzba: ");
	private JComboBox<SluzbeDomaZdravlja> cbSluzba = new JComboBox<SluzbeDomaZdravlja>(SluzbeDomaZdravlja.values());
	
	private JLabel lblSpecijalizacija = new JLabel("Specijalizacija: ");
	private JTextField txtSpecijalizacija = new JTextField(20);
	
	
	private JButton btnOk = new JButton("OK");
	private JButton btnOtkazi = new JButton("Otkazi");
	private DomZdravlja domZdravlja;
	private Lekar lekar;
	
	public LekarUpdate(DomZdravlja domZdravlja, Lekar lekar){
		this.domZdravlja = domZdravlja;
		this.lekar = lekar;
		String korisnickoime = lekar == null ? "Dodavanje novog Lekara " : "Izmena podataka o lekaru " + lekar.getKorisnickoime();
		setTitle(korisnickoime);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		if(this.lekar != null) {popuniPolja();}
		initListeners();
		pack();
	}	
	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		
		add(lblIme);				add(txtIme);
		add(lblPrezime);			add(txtPrezime);
		add(lblJmbg);				add(txtJmbg);
		add(lblAdresa);				add(txtAdresa);
		add(lblTelefon);			add(txtTelefon);
		add(lblKorisnickoIme);		add(txtKorisnickoIme);
		add(lblLozinka);			add(txtLozinka);
		add(lblPol);				add(cbPol);
		add(lblUloga);				add(cbUloga);		cbUloga.setSelectedItem(Uloga.Lekar);	cbUloga.setEnabled(false);
		add(lblPlata);				add(txtPlata);
		add(lblSluzba);				add(cbSluzba);
		add(lblSpecijalizacija);	add(txtSpecijalizacija,"wrap 10");
		add(new JLabel());			add(btnOk,"split 2");		add(btnOtkazi);
	}
	private void popuniPolja() {
		txtIme.setText(lekar.getIme());
		txtIme.setEnabled(false);
		txtPrezime.setText(lekar.getPrezime());
		txtPrezime.setEnabled(false);
		txtJmbg.setText(lekar.getJmbg());
		txtJmbg.setEnabled(false);
		txtAdresa.setText(lekar.getAdresa());
		txtTelefon.setText(lekar.getTelefon());
		txtKorisnickoIme.setText(lekar.getKorisnickoime());
		txtKorisnickoIme.setEnabled(false);
		txtLozinka.setText(lekar.getLozinka());
		cbPol.setSelectedItem(lekar.getPol());
		cbPol.setEnabled(false);
		lekar.getUloga();
		cbUloga.setSelectedItem(Uloga.Lekar);
		cbUloga.setEnabled(false);
		txtPlata.setText(String.valueOf(lekar.getPlata()));
		cbSluzba.setSelectedItem(lekar.getSluzba());
		txtSpecijalizacija.setText(lekar.getSpecijalizacija());
	}
	private boolean validacija(){
		boolean ok = true;
		String poruka = "Proverite unos u sledecim poljima: ";
		
		if(txtIme.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Ime";
		}
		if(txtPrezime.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Prezime";
		}
		if(txtJmbg.getText().trim().equals("")){
			ok = false;
			poruka += "\n- JMBG";
		}
		if(txtAdresa.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Adresa";
		}
		if(txtTelefon.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Telefon";
		}
		if(txtKorisnickoIme.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Korisnicko Ime";
		}
		if(txtLozinka.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Lozinka";
		}
		try{
			Double.parseDouble(txtPlata.getText().trim());
		}catch(NumberFormatException e){
			ok = false;
			poruka += "\n- Plata";
		}
		if(txtSpecijalizacija.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Specijalizacija";
		}
		if(!ok){
			JOptionPane.showMessageDialog(null, poruka,"Validacija",JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
	private void initListeners(){
		btnOk.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(validacija()){
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					String jmbg = txtJmbg.getText().trim();
					String adresa = txtAdresa.getText().trim();
					String telefon = txtTelefon.getText().trim();
					String korisnickoime = txtKorisnickoIme.getText().trim();
					String lozinka = txtLozinka.getText().trim();
					Pol pol = (Pol) cbPol.getSelectedItem();
					Uloga uloga = (Uloga) cbUloga.getSelectedItem();
					double plata = Double.parseDouble(txtPlata.getText().trim());
					SluzbeDomaZdravlja sluzba = (SluzbeDomaZdravlja) cbSluzba.getSelectedItem();
					String specijalizacija = txtSpecijalizacija.getText().trim();
					if(lekar == null){ 	//DODAVANJE:
						lekar = new Lekar(ime, prezime, jmbg, adresa, telefon, korisnickoime, 
								lozinka, pol, uloga, plata, sluzba, specijalizacija);
						domZdravlja.dodajLekara(lekar);
					}else{		//IZMENA
						lekar.setIme(ime);
						lekar.setPrezime(prezime);
						lekar.setJmbg(jmbg);
						lekar.setAdresa(adresa);
						lekar.setTelefon(telefon);
						lekar.setKorisnickoime(korisnickoime);
						lekar.setLozinka(lozinka);
						lekar.setPol(pol);
						lekar.setUloga(uloga);
						lekar.setPlata(plata);
						lekar.setSluzba(sluzba);
						lekar.setSpecijalizacija(specijalizacija);
					}
					domZdravlja.snimiLekare();
					LekarUpdate.this.dispose();
					LekarUpdate.this.setVisible(false);	
				}
			}
		});
		btnOtkazi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LekarUpdate.this.dispose();
				LekarUpdate.this.setVisible(false);
			}
		});
	}
}
