package gui.DodavanjeIzmena;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

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
import pregledi.Pregledi;
import pregledi.StatusPregleda;
import zdravstvenaKnjizica.ZdravstvenaKnjizica;

public class PregledUpdate extends JFrame {
	private JLabel lblIdent = new JLabel("Ident: ");
	private JTextField txtIdent = new JTextField(20);
	
	private JLabel lblZatrazenDatum = new JLabel("Zatrazen Datum: ");
	private JTextField txtZatrazenDatum = new JTextField(20);
	
	private JLabel lblOpis = new JLabel("Opis: ");
	private JTextField txtOpis = new JTextField(20);
	
	private JLabel lblPacijent = new JLabel("Pacijent: ");
	private JTextField txtPacijent = new JTextField(20);
	
	private JLabel lblLekar = new JLabel("Lekar: ");
	private JTextField txtLekar = new JTextField(20);
	
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
		
		add(lblIdent);					add(txtIdent);
		add(lblZatrazenDatum);			add(txtZatrazenDatum);
		add(lblOpis);					add(txtOpis);
		add(lblPacijent);				add(txtPacijent);
		add(lblLekar);					add(txtLekar);
		add(lblSoba);					add(txtSoba);
		add(lblStatusPregleda);			add(cbStatusPregleda,"wrap 10");
		add(new JLabel());				add(btnOk,"split 2");		add(btnOtkazi);
	}
	private void popuniPolja() {
		txtIdent.setText(pregled.getIdent());
		txtIdent.setEnabled(false);
		txtZatrazenDatum.setText(String.valueOf(pregled.getZatrazenDatum()));
		txtOpis.setText(pregled.getOpis());
		txtPacijent.setText(pregled.getPacijent().getKorisnickoime());
		txtPacijent.setEnabled(false);
		txtLekar.setText(pregled.getLekar().getKorisnickoime());
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
		if(txtZatrazenDatum.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Zatrazen Datum";
		}
		if(txtOpis.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Opis";
		}
		if(txtPacijent.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Pacijent";
		}
		if(txtLekar.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Lekar";
		}
		try{
			Double.parseDouble(txtSoba.getText().trim());
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
					Date zatrazenDatum = Calendar.getInstance().getTime();  
					DateFormat dateFormat = new SimpleDateFormat("dd-mm-yyyy");  
					String strDate = dateFormat.format(txtZatrazenDatum.getText().trim());
					String opis = txtOpis.getText().trim();
					String pac = txtPacijent.getSelectedText().toString();
					Pacijent pacijent = domZdravlja.nadjiPacijenta(pac);
					String lek = txtLekar.getSelectedText().toString();
					Lekar lekar = domZdravlja.nadjiLekara(lek);
					int soba = Integer.parseInt(txtSoba.getText().trim());
					StatusPregleda status = (StatusPregleda) cbStatusPregleda.getSelectedItem();
					if(pregled == null){ 	//DODAVANJE:
						pregled = new Pregledi(ident, zatrazenDatum, opis, pacijent, lekar, soba, status);
						domZdravlja.dodajPreglede(pregled);
					}else{		//IZMENA
						pregled.setIdent(ident);
						pregled.setZatrazenDatum(zatrazenDatum);
						pregled.setOpis(opis);
						pregled.setPacijent(pacijent);
						pregled.setLekar(lekar);
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
