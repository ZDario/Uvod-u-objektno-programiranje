package gui.DodavanjeIzmena;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
import net.miginfocom.swing.MigLayout;
import pregledi.Pregledi;
import pregledi.StatusPregleda;

public class PregledUpdate extends JFrame {
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
	private JComboBox<StatusPregleda> cbStatusPregleda = new JComboBox<StatusPregleda>(StatusPregleda.values());
	

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
		add(new JLabel());				add(btnOk,"split 2");		add(btnOtkazi);
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
	}
	private boolean validacija(){
		boolean ok = true;
		String poruka = "Proverite unos u sledecim poljima: ";
		
		if(txtIdent.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Ident";
		}
		for(Pregledi pregled : domZdravlja.getPreglede()) {
			if(pregled.getIdent().equals(txtIdent.getText().trim())) {
				ok = false;
				poruka += "\n - Korisnicko ime ili jmbg vec postoji";
			}
			else if(pregled.getIdent().equals(txtIdent.getText().trim())) {
				ok=true;
			}
		}
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
			poruka += "\n- Soba";
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
}
