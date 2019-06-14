package gui.LekPacPregledi;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import domZdravlja.DomZdravlja;
import korisnici.Lekar;
import korisnici.Pacijent;
import net.miginfocom.swing.MigLayout;
import pregledi.Pregledi;
import pregledi.StatusPregleda;

public class LekarPreglediUpdate extends JFrame {
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
	
	private JButton btnOtkazi = new JButton("Zatvori");
	private DomZdravlja domZdravlja;
	private Pregledi pregled;
	
	public LekarPreglediUpdate(DomZdravlja domZdravlja, Pregledi pregled){
		this.domZdravlja = domZdravlja;
		this.pregled = pregled;
		setTitle("Podaci o Pregledu " + pregled.getIdent());
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
		
		add(new JLabel());	add(btnOtkazi);
	}
	private void popuniPolja() {
		txtIdent.setText(pregled.getIdent());
		txtIdent.setEnabled(false);
		GregorianCalendar datum=new GregorianCalendar(2018,12,01,00,00);
		txtZatrazenDatum.setText(String.valueOf(domZdravlja.VremeUString(datum,domZdravlja.getFormatTermina())));
		txtZatrazenDatum.setEnabled(false);
		txtOpis.setText(pregled.getOpis());
		txtOpis.setEnabled(false);
		txtSoba.setEnabled(false);
		cbLekar.setSelectedItem(this.pregled.getLekar().getKorisnickoime());
		cbLekar.setEnabled(false);
		cbPacijent.setSelectedItem(this.pregled.getPacijent().getKorisnickoime());
		cbPacijent.setEnabled(false);
		txtSoba.setText(String.valueOf(pregled.getSoba()));
		txtSoba.setEnabled(false);
		cbStatusPregleda.setSelectedItem(pregled.getStatus());
		cbStatusPregleda.setEnabled(false);
	}
	private void initListeners(){
		btnOtkazi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LekarPreglediUpdate.this.dispose();
				LekarPreglediUpdate.this.setVisible(false);
			}
		});
	}
}
