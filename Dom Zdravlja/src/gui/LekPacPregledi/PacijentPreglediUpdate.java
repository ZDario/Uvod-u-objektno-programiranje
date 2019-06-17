package gui.LekPacPregledi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
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

public class PacijentPreglediUpdate extends JFrame {
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
	
	public PacijentPreglediUpdate(DomZdravlja domZdravlja, Pregledi pregled,String korisnickoime){
		this.domZdravlja = domZdravlja;
		this.pregled = pregled;
		String ident = pregled == null ? "Dodavanje novog Pregleda" : "Izmena podataka o Pregledu" + pregled.getIdent();
		setTitle(ident);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		
		txtIdent.setEnabled(false);
		txtIdent.setText(String.valueOf(domZdravlja.pronadjiNajveciBrojPregleda()+1));
		Pacijent pacijent = domZdravlja.nadjiPacijenta(korisnickoime);
		cbLekar.setSelectedItem(pacijent.getIzabraniLekar().getKorisnickoime());
		cbPacijent.setSelectedItem(pacijent.getKorisnickoime());
		
		if(this.pregled != null) {popuniPolja();}
		initListeners();
		pack();
	}	
	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		
		for (Lekar lekar : this.domZdravlja.getLekare()) {
			cbLekar.addItem(lekar.getKorisnickoime());
		}
		
		for (Pacijent pacijent : this.domZdravlja.getPacijente()) {
			cbPacijent.addItem(pacijent.getKorisnickoime());
		}
		
		add(lblIdent);					add(txtIdent);
		add(lblZatrazenDatum);			add(txtZatrazenDatum);
		add(lblOpis);					add(txtOpis);
		
		add(lblLekar);					add(cbLekar);
		cbLekar.setEnabled(false);
		
		add(lblPacijent);				add(cbPacijent);
		cbPacijent.setEnabled(false);
		
		add(lblSoba);					add(txtSoba);
		add(lblStatusPregleda);			add(cbStatusPregleda,"wrap 10");
		cbStatusPregleda.addItem(StatusPregleda.Zatrazen);
		cbStatusPregleda.addItem(StatusPregleda.Otkazan);
		
		add(new JLabel());				add(btnOk,"split 2");		add(btnOtkazi);
	}
	private void popuniPolja() {
		txtIdent.setText(pregled.getIdent());
		txtIdent.setEnabled(false);
		txtZatrazenDatum.setText(String.valueOf(domZdravlja.VremeUString(this.pregled.getZatrazenDatum(),domZdravlja.getFormatTermina())));
		txtZatrazenDatum.setEnabled(false);
		txtOpis.setText(pregled.getOpis());
		cbLekar.setSelectedItem(this.pregled.getLekar().getKorisnickoime());
		cbLekar.setEnabled(false);
		cbPacijent.setSelectedItem(this.pregled.getPacijent().getKorisnickoime());
		cbPacijent.setEnabled(false);
		txtSoba.setText(String.valueOf(pregled.getSoba()));
		txtSoba.setEnabled(false);
		if (pregled.getStatus()!=StatusPregleda.Zatrazen){
			cbStatusPregleda.addItem(StatusPregleda.Zakazan);
			cbStatusPregleda.addItem(StatusPregleda.Zavrsen);
			cbStatusPregleda.setSelectedItem(this.pregled.getStatus());
			cbStatusPregleda.setEnabled(false);
		}  
		
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
					PacijentPreglediUpdate.this.dispose();
					PacijentPreglediUpdate.this.setVisible(false);	
				}
			}
		});
		btnOtkazi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PacijentPreglediUpdate.this.dispose();
				PacijentPreglediUpdate.this.setVisible(false);
			}
		});
	}
}
