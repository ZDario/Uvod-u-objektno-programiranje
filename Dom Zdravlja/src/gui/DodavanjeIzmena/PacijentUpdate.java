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
	
	private JLabel lblIzabraniLekar = new JLabel("Izabrani Lekar");
	private JTextField txtIzabraniLekar = new JTextField(20);
	
	private JLabel lblZdravstvenaKnjizica = new JLabel("Zdravstvena Knjizica");
	private JTextField txtZdravstvenaKnjizica = new JTextField(20);
	
	
	
	private JButton btnOk = new JButton("OK");
	private JButton btnOtkazi = new JButton("Otkazi");
	private DomZdravlja domZdravlja;
	private Pacijent pacijent;
	
	public PacijentUpdate(DomZdravlja domZdravlja, Pacijent pacijent){
		this.domZdravlja = domZdravlja;
		this.pacijent = pacijent;
		String korisnickoime = pacijent == null ? "Dodavanje novog Pacijenta" : "Izmena podataka o Pacijentu" + pacijent.getKorisnickoime();
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
		
		add(lblIme);					add(txtIme);
		add(lblPrezime);				add(txtPrezime);
		add(lblJmbg);					add(txtJmbg);
		add(lblAdresa);					add(txtAdresa);
		add(lblTelefon);				add(txtTelefon);
		add(lblKorisnickoIme);			add(txtKorisnickoIme);
		add(lblLozinka);				add(txtLozinka);
		add(lblPol);					add(cbPol);
		add(lblUloga);					add(cbUloga); 		cbUloga.setSelectedItem(Uloga.Pacijent);	cbUloga.setEnabled(false);
		add(lblIzabraniLekar);			add(txtIzabraniLekar);
		add(lblZdravstvenaKnjizica);	add(txtZdravstvenaKnjizica,"wrap 10");
		add(new JLabel());				add(btnOk,"split 2");		add(btnOtkazi);
	}
	private void popuniPolja() {
		txtIme.setText(pacijent.getIme());
		txtIme.setEnabled(false);
		txtPrezime.setText(pacijent.getPrezime());
		txtPrezime.setEnabled(false);
		txtJmbg.setText(pacijent.getJmbg());
		txtJmbg.setEnabled(false);
		txtAdresa.setText(pacijent.getAdresa());
		txtTelefon.setText(pacijent.getTelefon());
		txtKorisnickoIme.setText(pacijent.getKorisnickoime());
		txtLozinka.setText(pacijent.getLozinka());
		cbPol.setSelectedItem(pacijent.getPol());
		cbPol.setEnabled(false);
		pacijent.getUloga();
		cbUloga.setSelectedItem(Uloga.Pacijent);
		cbUloga.setEnabled(false);
		txtIzabraniLekar.setText(pacijent.getIzabraniLekar().getKorisnickoime());
		txtZdravstvenaKnjizica.setText(pacijent.getKnjizica().getIdent());
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
		if(txtIzabraniLekar.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Izabrani Lekar";
		}
		if(txtZdravstvenaKnjizica.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Zdravstvena Knjizica";
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
					String izlekar = txtIzabraniLekar.getSelectedText().toString();
					Lekar izabraniLekar = domZdravlja.nadjiLekara(izlekar);
					String zdrknjizica = txtZdravstvenaKnjizica.getSelectedText().toString();
					ZdravstvenaKnjizica knjizica = domZdravlja.nadjiKnjizicu(zdrknjizica);
					if(pacijent == null){ 	//DODAVANJE:
						pacijent = new Pacijent(ime, prezime, jmbg, adresa, telefon, korisnickoime, 
								lozinka, pol, uloga, izabraniLekar, knjizica);
						domZdravlja.dodajPacijenta(pacijent);
					}else{		//IZMENA
						pacijent.setIme(ime);
						pacijent.setPrezime(prezime);
						pacijent.setJmbg(jmbg);
						pacijent.setAdresa(adresa);
						pacijent.setTelefon(telefon);
						pacijent.setKorisnickoime(korisnickoime);
						pacijent.setLozinka(lozinka);
						pacijent.setPol(pol);
						pacijent.setUloga(uloga);
						pacijent.setIzabraniLekar(izabraniLekar);
						pacijent.setKnjizica(knjizica);
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
