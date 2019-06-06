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
import korisnici.MedicinskaSestra;
import korisnici.Pol;
import korisnici.Uloga;
import net.miginfocom.swing.MigLayout;

public class MedicinskaSestraUpdate extends JFrame{
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
	
	
	private JButton btnOk = new JButton("OK");
	private JButton btnOtkazi = new JButton("Otkazi");
	private DomZdravlja domZdravlja;
	private MedicinskaSestra sestra;
	
	public MedicinskaSestraUpdate(DomZdravlja domZdravlja, MedicinskaSestra sestra){
		this.domZdravlja = domZdravlja;
		this.sestra = sestra;
		String korisnickoime = sestra == null ? "Dodavanje nove Medicinske Sestre " : "Izmena podataka o Medicinskoj Sestri " + sestra.getKorisnickoime();
		setTitle(korisnickoime);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		if(this.sestra != null) {popuniPolja();}
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
		add(lblUloga);				add(cbUloga); 	cbUloga.setSelectedItem(Uloga.Medicinska_Sestra);	cbUloga.setEnabled(false);
		add(lblPlata);				add(txtPlata);
		add(lblSluzba);				add(cbSluzba,"wrap 10");
		add(new JLabel());			add(btnOk,"split 2");		add(btnOtkazi);
	}
	private void popuniPolja() {
		txtIme.setText(sestra.getIme());
		txtIme.setEnabled(false);
		txtPrezime.setText(sestra.getPrezime());
		txtPrezime.setEnabled(false);
		txtJmbg.setText(sestra.getJmbg());
		txtJmbg.setEnabled(false);
		txtAdresa.setText(sestra.getAdresa());
		txtTelefon.setText(sestra.getTelefon());
		txtKorisnickoIme.setText(sestra.getKorisnickoime());
		txtKorisnickoIme.setEnabled(false);
		txtLozinka.setText(sestra.getLozinka());
		cbPol.setSelectedItem(sestra.getPol());
		cbPol.setEnabled(false);
		sestra.getUloga();
		cbUloga.setSelectedItem(Uloga.Medicinska_Sestra);
		cbUloga.setEnabled(false);	
		txtPlata.setText(String.valueOf(sestra.getPlata()));
		cbSluzba.setSelectedItem(sestra.getSluzba());
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
					if(sestra == null){ 	//DODAVANJE:
						sestra = new MedicinskaSestra(ime, prezime, jmbg, adresa, telefon, korisnickoime, 
								lozinka, pol, uloga, plata, sluzba);
						domZdravlja.dodajMedicinskaSestru(sestra);
					}else{		//IZMENA
						sestra.setIme(ime);
						sestra.setPrezime(prezime);
						sestra.setJmbg(jmbg);
						sestra.setAdresa(adresa);
						sestra.setTelefon(telefon);
						sestra.setKorisnickoime(korisnickoime);
						sestra.setLozinka(lozinka);
						sestra.setPol(pol);
						sestra.setUloga(uloga);
						sestra.setPlata(plata);
						sestra.setSluzba(sluzba);
					}
					domZdravlja.snimiMedicinskeSestre();
					MedicinskaSestraUpdate.this.dispose();
					MedicinskaSestraUpdate.this.setVisible(false);	
				}
			}
		});
		btnOtkazi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MedicinskaSestraUpdate.this.dispose();
				MedicinskaSestraUpdate.this.setVisible(false);
			}
		});
	}
}
