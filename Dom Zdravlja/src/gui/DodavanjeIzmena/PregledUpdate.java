package gui.DodavanjeIzmena;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import domZdravlja.DomZdravlja;
import korisnici.Lekar;
import korisnici.Pacijent;
import pregledi.Pregledi;
import pregledi.StatusPregleda;
import net.miginfocom.swing.MigLayout;


public class PregledUpdate extends JFrame {
	
	public static final int EXAM_DISTANCE = 15;
	public static final int CONVERSION_FACTOR_MINUTES = (60 * 1000);
	
	private JLabel lblIdent = new JLabel("Ident: ");
	private JTextField txtIdent = new JTextField(20);
	
	private JLabel lblZatrazenDatum = new JLabel("Zatrazen Datum: ");
	private JTextField txtZatrazenDatum = new JTextField(20);
	
	private JLabel lblOpis = new JLabel("Opis: ");
	private JTextField txtOpis = new JTextField(20);
	
	private JLabel lblLekar = new JLabel("Lekar: ");
	private JComboBox<String> cbLekar = new JComboBox<String>();
	
	private JLabel lblPacijent = new JLabel("Pacijent: ");
	private JComboBox<String> cbPacijent = new JComboBox<String>();
	
	private JLabel lblSoba = new JLabel("Soba: ");
	private JTextField txtSoba = new JTextField(20);
	
	private JLabel lblStatusPregleda = new JLabel("Status Pregleda: ");
	private JComboBox<StatusPregleda> cbStatusPregleda = new JComboBox<StatusPregleda>();
	
	private JButton btnOk = new JButton("OK");
	private JButton btnOtkazi = new JButton("Otkazi");
	private DomZdravlja domZdravlja;
	private Pregledi pregled;
	
	public PregledUpdate(DomZdravlja domZdravlja, Pregledi pregled){
		this.domZdravlja = domZdravlja;
		this.pregled = pregled;
		String ident = pregled == null ? "Dodavanje novog Pregleda" : "Izmena podataka o Pregledu" + pregled.getIdent();
		setTitle(ident);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		txtIdent.setEnabled(false);
		txtIdent.setText(String.valueOf(domZdravlja.pronadjiNajveciBrojPregleda()+1));
		initGUI();
		if(this.pregled != null) {popuniPolja();}
		initListeners();
		pack();
	}	
	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		
		for(Lekar lekar : this.domZdravlja.getLekare()) {
			cbLekar.addItem(lekar.getKorisnickoime());
		}
		
		for(Pacijent pacijent : this.domZdravlja.getPacijente()) {
			cbPacijent.addItem(pacijent.getKorisnickoime());
		}
		
		add(lblIdent);					add(txtIdent);
		add(lblZatrazenDatum);			add(txtZatrazenDatum);
		add(lblOpis);					add(txtOpis);
		add(lblLekar);					add(cbLekar);
		add(lblPacijent);				add(cbPacijent);
		add(lblSoba);					add(txtSoba);
		add(lblStatusPregleda);			add(cbStatusPregleda,"wrap 10");
		cbStatusPregleda.addItem(StatusPregleda.Zakazan);
		add(new JLabel());			add(btnOk,"split 2"); 
		add(btnOtkazi);
	}
	private void popuniPolja() {
		txtIdent.setText(pregled.getIdent());
		txtIdent.setEnabled(false);
		txtZatrazenDatum.setText(String.valueOf(domZdravlja.VremeUString(this.pregled.getZatrazenDatum(),domZdravlja.getFormatTermina())));
		txtOpis.setText(pregled.getOpis());
		cbLekar.setSelectedItem(this.pregled.getLekar().getKorisnickoime());
		cbPacijent.setSelectedItem(this.pregled.getPacijent().getKorisnickoime());
		txtSoba.setText(String.valueOf(pregled.getSoba()));
		cbStatusPregleda.setSelectedItem(pregled.getStatus());
		if(pregled.getStatus()==StatusPregleda.Zatrazen){
			cbStatusPregleda.addItem(StatusPregleda.Zatrazen);
			cbStatusPregleda.addItem(StatusPregleda.Zavrsen);
			cbStatusPregleda.addItem(StatusPregleda.Otkazan);
		}
		if(pregled.getStatus()==StatusPregleda.Zakazan) {
			cbStatusPregleda.removeItem(StatusPregleda.Zatrazen);
			cbStatusPregleda.addItem(StatusPregleda.Zavrsen);
			cbStatusPregleda.addItem(StatusPregleda.Otkazan);
		}
		if(pregled.getStatus()==StatusPregleda.Zavrsen){
			cbStatusPregleda.removeItem(StatusPregleda.Zakazan);
			cbStatusPregleda.addItem(StatusPregleda.Zavrsen);
			txtZatrazenDatum.setEnabled(false);
			txtOpis.setEnabled(false);
			cbLekar.setEnabled(false);
			cbPacijent.setEnabled(false);
			txtSoba.setEnabled(false);
			cbStatusPregleda.setEnabled(false);
		}
		if(pregled.getStatus()==StatusPregleda.Otkazan){
			cbStatusPregleda.removeItem(StatusPregleda.Zakazan);
			cbStatusPregleda.addItem(StatusPregleda.Otkazan);
			txtZatrazenDatum.setEnabled(false);
			txtOpis.setEnabled(false);
			cbLekar.setEnabled(false);
			cbPacijent.setEnabled(false);
			txtSoba.setEnabled(false);
			cbStatusPregleda.setEnabled(false);
		}
	}
	private boolean validacija(){
		boolean ok = true;
		String poruka = "Proverite unos u sledecim poljima: ";
		
		try {
			domZdravlja.getFormatTermina().parse(txtZatrazenDatum.getText().trim());
		}catch (ParseException e) {
			poruka += "\n- Datum mora biti formata dd.MM.yyyy. HH:mm";
			ok = false;
		}
		
		if(txtOpis.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Opis";
		}
		try{
			Integer.parseInt(txtSoba.getText().trim());
		}catch(NumberFormatException e){
			ok = false;
			poruka += "\n- Soba mora biti jednocifren broj";
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
					String ident = txtIdent.getText().trim();
					
					GregorianCalendar termin = domZdravlja.napraviDatumIVreme(txtZatrazenDatum.getText().trim());
					
					String opis = txtOpis.getText().trim();
					
					String korisnickoime = (String) cbLekar.getSelectedItem();
					Lekar lekar = domZdravlja.nadjiLekara(korisnickoime);
					
					String korisnickoime1 = (String) cbPacijent.getSelectedItem();
					Pacijent pacijent = domZdravlja.nadjiPacijenta(korisnickoime1);
					
					int soba = Integer.parseInt(txtSoba.getText().trim());
					
					StatusPregleda status = (StatusPregleda) cbStatusPregleda.getSelectedItem();
					
					
						if (lekarIsNotBusy(termin, lekar)) {
							if(pregled == null){ 	//DODAVANJE:
								pregled = new Pregledi(ident, termin, opis, lekar, pacijent, soba, status);
								domZdravlja.dodajPreglede(pregled);
							}else{		//IZMENA
								pregled.setIdent(ident);
								pregled.setZatrazenDatum(termin);
								pregled.setOpis(opis);
								pregled.setLekar(lekar);
								pregled.setPacijent(pacijent);
								pregled.setSoba(soba);
								pregled.setStatus(status);
							}
							domZdravlja.snimiPreglede();
							PregledUpdate.this.dispose();
							PregledUpdate.this.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "Izabrani lekar je zauzet za unet pocetak pregleda","Obaveštenje",JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});
		btnOtkazi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PregledUpdate.this.dispose();
				PregledUpdate.this.setVisible(false);
			}
		});
	}
	
	private boolean lekarIsNotBusy(GregorianCalendar pocetakPregleda, Lekar izabraniLekar) {
				Date pocetak = pocetakPregleda.getTime();
	
				long longPocetak = pocetak.getTime();
				
				boolean uListiPregleda = false;	
				
				for (Pregledi pregled : domZdravlja.getPreglede()) {
					if (pregled.getStatus() == StatusPregleda.Zakazan) { 
						if (pregled.getLekar().getKorisnickoime().equals(izabraniLekar.getKorisnickoime())) {
							uListiPregleda = true; 
							
							if (pocetak.before(pregled.getZatrazenDate())){
								long pregledPocetak = pregled.getZatrazenDate().getTime();
								long difference = (pregledPocetak - longPocetak) / CONVERSION_FACTOR_MINUTES;
								if (difference <= EXAM_DISTANCE ) return false;	
							}
							if(pocetak.after(pregled.getZatrazenDate())) {
								long pregledPocetak = pregled.getZatrazenDate().getTime();
								long difference = (longPocetak - pregledPocetak) / CONVERSION_FACTOR_MINUTES;
								if(difference <= EXAM_DISTANCE) return false;
							}
							
						}
					}	
				}
				if (!uListiPregleda) return true;
				return true;
	}
}