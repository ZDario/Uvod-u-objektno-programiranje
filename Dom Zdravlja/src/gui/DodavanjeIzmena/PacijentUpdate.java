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
import korisnici.Lekar;
import korisnici.MedicinskaSestra;
import korisnici.Pacijent;
import korisnici.Pol;
import korisnici.Uloga;
import net.miginfocom.swing.MigLayout;
import zdravstvenaKnjizica.ZdravstvenaKnjizica;

public class PacijentUpdate extends JFrame {
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
	
	private JLabel lblZdravstvenaKnjizica = new JLabel("Zdravstvena Knjizica");
	private JComboBox<String> cbZdravstvenaKnjizica = new JComboBox<String>();
	
	private JLabel lblIzabraniLekar = new JLabel("Izabrani Lekar");
	private JComboBox<String> cbIzabraniLekar = new JComboBox<String>();
	
	
	
	private JButton btnOk1 = new JButton("OK");
	private JButton btnOk2 = new JButton("OK");
	private JButton btnOtkazi = new JButton("Otkazi");
	private DomZdravlja domZdravlja;
	private Pacijent pacijent;
	
	public PacijentUpdate(DomZdravlja domZdravlja, Pacijent pacijent){
		this.domZdravlja = domZdravlja;
		this.pacijent = pacijent;
		String korisnickoime = pacijent == null ? "Dodavanje novog Pacijenta " : "Izmena podataka o Pacijentu " + pacijent.getKorisnickoime();
		setTitle(korisnickoime);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		if(this.pacijent != null) {popuniPolja();}
		initListeners();
		pack();
	}	
	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		
		for(ZdravstvenaKnjizica knjizica : this.domZdravlja.getZdravstvenaKnjizice()) {
			cbZdravstvenaKnjizica.addItem(knjizica.getIdent());
		}
		
		for(Lekar lekar : this.domZdravlja.getLekare()) {
			cbIzabraniLekar.addItem(lekar.getKorisnickoime());
		}
		
		add(lblIme);					add(txtIme);
		add(lblPrezime);				add(txtPrezime);
		add(lblJmbg);					add(txtJmbg);
		add(lblAdresa);					add(txtAdresa);
		add(lblTelefon);				add(txtTelefon);
		add(lblKorisnickoIme);			add(txtKorisnickoIme);
		add(lblLozinka);				add(txtLozinka);
		add(lblPol);					add(cbPol);
		add(lblUloga);					add(cbUloga); 		cbUloga.setSelectedItem(Uloga.Pacijent);	cbUloga.setEnabled(false);
		add(lblZdravstvenaKnjizica);	add(cbZdravstvenaKnjizica);
		add(lblIzabraniLekar);			add(cbIzabraniLekar,"wrap 10");
		add(new JLabel());			add(btnOk1,"split 3"); 
		add(btnOk2);	btnOk2.setVisible(false);	btnOk2.setEnabled(false);	
		add(btnOtkazi);
	}
	private void popuniPolja() {
		txtIme.setText(pacijent.getIme());
		txtPrezime.setText(pacijent.getPrezime());
		txtJmbg.setText(pacijent.getJmbg());
		txtJmbg.setEnabled(false);
		txtAdresa.setText(pacijent.getAdresa());
		txtTelefon.setText(pacijent.getTelefon());
		txtKorisnickoIme.setText(pacijent.getKorisnickoime());
		txtKorisnickoIme.setEnabled(false);
		txtLozinka.setText(pacijent.getLozinka());
		cbPol.setSelectedItem(pacijent.getPol());
		cbPol.setEnabled(false);
		pacijent.getUloga();
		cbUloga.setSelectedItem(Uloga.Pacijent);
		cbUloga.setEnabled(false);
		cbZdravstvenaKnjizica.setSelectedItem(this.pacijent.getKnjizica().getIdent());
		cbZdravstvenaKnjizica.setEnabled(false);
		cbIzabraniLekar.setSelectedItem(this.pacijent.getIzabraniLekar().getKorisnickoime());
		btnOk1.setVisible(false);
		btnOk1.setEnabled(false);
		btnOk2.setVisible(true);
		btnOk2.setEnabled(true);
	}
	private boolean validacija1(){
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
		if(domZdravlja.nadjiPacijenta(txtJmbg.getText().trim()) != null) {
			poruka += "\n- Ovaj JMBG vec postoji";
			ok=false;
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
		if(domZdravlja.nadjiPacijenta(txtKorisnickoIme.getText().trim()) != null) {
			poruka += "\n- Ovo korisnicko ime vec postoji";
			ok=false;
		}
		if(txtLozinka.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Lozinka";
		}
		if(!ok){
			JOptionPane.showMessageDialog(null, poruka,"Validacija",JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
	private boolean validacija2(){
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
		if(txtAdresa.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Adresa";
		}
		if(txtTelefon.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Telefon";
		}
		if(txtLozinka.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Lozinka";
		}
		if(!ok){
			JOptionPane.showMessageDialog(null, poruka,"Validacija",JOptionPane.WARNING_MESSAGE);
		}
		
		return ok;
	}
	private void initListeners(){
		btnOk1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(validacija1()){
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					String jmbg = txtJmbg.getText().trim();
					String adresa = txtAdresa.getText().trim();
					String telefon = txtTelefon.getText().trim();
					String korisnickoime1 = txtKorisnickoIme.getText().trim();
					String lozinka = txtLozinka.getText().trim();
					Pol pol = (Pol) cbPol.getSelectedItem();
					Uloga uloga = (Uloga) cbUloga.getSelectedItem();
					String ident = (String) cbZdravstvenaKnjizica.getSelectedItem();
					ZdravstvenaKnjizica knjizica = domZdravlja.nadjiKnjizicu(ident);
					String korisnickoime2 = (String) cbIzabraniLekar.getSelectedItem();
					Lekar izabraniLekar = domZdravlja.nadjiLekara(korisnickoime2);
					if(pacijent == null){ 	//DODAVANJE:
						pacijent = new Pacijent(ime, prezime, jmbg, adresa, telefon, korisnickoime1, 
								lozinka, pol, uloga, knjizica, izabraniLekar);
						domZdravlja.dodajPacijenta(pacijent);
					}
					domZdravlja.snimiPacijente();
					PacijentUpdate.this.dispose();
					PacijentUpdate.this.setVisible(false);	
				}
			}
		});
		btnOk2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(validacija2()){
					String ime = txtIme.getText().trim();
					String prezime = txtPrezime.getText().trim();
					String jmbg = txtJmbg.getText().trim();
					String adresa = txtAdresa.getText().trim();
					String telefon = txtTelefon.getText().trim();
					String korisnickoime1 = txtKorisnickoIme.getText().trim();
					String lozinka = txtLozinka.getText().trim();
					Pol pol = (Pol) cbPol.getSelectedItem();
					Uloga uloga = (Uloga) cbUloga.getSelectedItem();
					String ident = (String) cbZdravstvenaKnjizica.getSelectedItem();
					ZdravstvenaKnjizica knjizica = domZdravlja.nadjiKnjizicu(ident);
					String korisnickoime2 = (String) cbIzabraniLekar.getSelectedItem();
					Lekar izabraniLekar = domZdravlja.nadjiLekara(korisnickoime2);
					if(pacijent!=null) {	//IZMENI
						pacijent.setIme(ime);
						pacijent.setPrezime(prezime);
						pacijent.setJmbg(jmbg);
						pacijent.setAdresa(adresa);
						pacijent.setTelefon(telefon);
						pacijent.setKorisnickoime(korisnickoime1);
						pacijent.setLozinka(lozinka);
						pacijent.setPol(pol);
						pacijent.setUloga(uloga);
						pacijent.setKnjizica(knjizica);
						pacijent.setIzabraniLekar(izabraniLekar);
					}
					domZdravlja.snimiPacijente();
					PacijentUpdate.this.dispose();
					PacijentUpdate.this.setVisible(false);	
				}
				
			}
		});
		btnOtkazi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PacijentUpdate.this.dispose();
				PacijentUpdate.this.setVisible(false);
			}
		});
	}
}
