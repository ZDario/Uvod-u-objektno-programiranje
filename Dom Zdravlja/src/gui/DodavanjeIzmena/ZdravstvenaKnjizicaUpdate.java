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
import korisnici.Pol;
import korisnici.Uloga;
import net.miginfocom.swing.MigLayout;
import pregledi.Pregledi;
import zdravstvenaKnjizica.KategorijaOsiguranja;
import zdravstvenaKnjizica.ZdravstvenaKnjizica;

public class ZdravstvenaKnjizicaUpdate extends JFrame {
	private JLabel lblIdent = new JLabel("Ident: ");
	private JTextField txtIdent = new JTextField(20);
	
	private JLabel lblDatum = new JLabel("Datum Isteka: ");
	private JTextField txtDatum = new JTextField(20);
	
	private JLabel lblKategorijaOsiguranja = new JLabel("Kategorija Osiguranja: ");
	private JComboBox<KategorijaOsiguranja> cbKategorijaOsiguranja = new JComboBox<KategorijaOsiguranja>(KategorijaOsiguranja.values());
	
	
	private JButton btnOk = new JButton("OK");
	private JButton btnOtkazi = new JButton("Otkazi");
	private DomZdravlja domZdravlja;
	private ZdravstvenaKnjizica knjizica;
	
	public ZdravstvenaKnjizicaUpdate(DomZdravlja domZdravlja, ZdravstvenaKnjizica knjizica){
		this.domZdravlja = domZdravlja;
		this.knjizica = knjizica;
		String ident = knjizica == null ? "Dodavanje nove Zdravstvene Knjizice"
				: "Izmena podataka o Zdravstvenoj Knjizici" + knjizica.getIdent();
		setTitle(ident);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		if(this.knjizica != null) {popuniPolja();}
		initListeners();
		pack();
	}	
	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		
		add(lblIdent);					add(txtIdent);
		add(lblDatum);					add(txtDatum);
		add(lblKategorijaOsiguranja);	add(cbKategorijaOsiguranja,"wrap 10");
		add(new JLabel());				add(btnOk,"split 2");		add(btnOtkazi);
	}
	private void popuniPolja() {
		txtIdent.setText(knjizica.getIdent());
		txtIdent.setEnabled(false);
		txtDatum.setText(domZdravlja.VremeUString(knjizica.getDatumIsteka(), domZdravlja.getFormatKnjizice()));
		cbKategorijaOsiguranja.setSelectedItem(knjizica.getKategorijaosiguranja());
		
	}
	private boolean validacija(){
		boolean ok = true;
		String poruka = "Proverite unos u sledecim poljima: ";
		
		if(txtIdent.getText().trim().equals("")){
			ok = false;
			poruka += "\n- Ident";
		}
		for(ZdravstvenaKnjizica knjizica : domZdravlja.getZdravstvenaKnjizice()) {
			if(knjizica.getIdent().equals(txtIdent.getText().trim())) {
				ok = false;
				poruka += "\n - Korisnicko ime ili jmbg vec postoji";
			}
			else if(knjizica.getIdent().equals(txtIdent.getText().trim())) {
				ok=true;
			}
		}
		try {
			domZdravlja.getFormatKnjizice().parse(txtDatum.getText().trim());
		}catch (ParseException e) {
			poruka += "zn- Datum mora biti formata dd.MM.yyyy";
			ok = false;
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
					GregorianCalendar datumIsteka = domZdravlja.napraviDatum(txtDatum.getText().trim());
					KategorijaOsiguranja kategorijaOsiguranja = 
							(KategorijaOsiguranja) cbKategorijaOsiguranja.getSelectedItem();
					
					if(knjizica == null){ 	//DODAVANJE:
						knjizica = new ZdravstvenaKnjizica(ident, datumIsteka, kategorijaOsiguranja);
						domZdravlja.dodajZdravstvenaKnjizicu(knjizica);
					}else{		//IZMENA
						knjizica.setId(ident);
						knjizica.setDatumIsteka(datumIsteka);
						knjizica.setKategorijaosiguranja(kategorijaOsiguranja);
					}
					domZdravlja.snimiZdravstveneKnjizice();
					ZdravstvenaKnjizicaUpdate.this.dispose();
					ZdravstvenaKnjizicaUpdate.this.setVisible(false);	
				}
			}
		});
		btnOtkazi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ZdravstvenaKnjizicaUpdate.this.dispose();
				ZdravstvenaKnjizicaUpdate.this.setVisible(false);
			}
		});
	}
}
