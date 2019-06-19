package gui.MeniKorisnika;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import domZdravlja.DomZdravlja;
import gui.Liste.LekariLista;
import gui.Liste.MedicinskeSestreLista;
import gui.Liste.PacijentiLista;
import gui.Liste.PreglediLista;
import gui.Liste.ZdravstveneKnjiziceLista;
import korisnici.MedicinskaSestra;
import net.miginfocom.swing.MigLayout;

public class MedicinskaSestraMeni extends JFrame {
	
	private JButton btnLekari = new JButton("Lista Lekara");
	private JButton btnSestre =  new JButton("Lista Medicinskih Sestri");
	private JButton btnPacijenti = new JButton("Lista Pacijenata");
	private JButton btnZdravstveneKnjizice = new JButton("Lista Zdravstvenih Knjizica");
	private JButton btnPregledi = new JButton("Lista Pregleda");
	private JButton btnCancel = new JButton("Zatvori");
	
	private JLabel lbl = new JLabel("Izaberite Opciju");
	private JLabel lbl1 = new JLabel("");
	private JLabel lbl2 = new JLabel("");
	private JLabel lbl3 = new JLabel("");
	private JLabel lbl4 = new JLabel("");
	private JLabel lbl5 = new JLabel("");
	private JLabel lbl6 = new JLabel("");
	
	private DomZdravlja domZdravlja;
	private MedicinskaSestra SestraLoginProzor;
	
	public MedicinskaSestraMeni(DomZdravlja domZdravlja,MedicinskaSestra SestraLoginProzor) {
		this.domZdravlja = domZdravlja;
		this.SestraLoginProzor = SestraLoginProzor;
		setTitle("Meni Medicinske Sestre: " + SestraLoginProzor.getKorisnickoime());
		setSize(420, 380);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initListeners();
	}
	private void initGUI() {
		MigLayout mig = new MigLayout();
		setLayout(mig);
		lbl.setFont(new Font("Arial",Font.PLAIN,20));
		btnLekari.setPreferredSize(new Dimension(150,130));
		btnSestre.setPreferredSize(new Dimension(180,130));
		btnPacijenti.setPreferredSize(new Dimension(150,130));
		btnZdravstveneKnjizice.setPreferredSize(new Dimension(230,130));
		btnPregledi.setPreferredSize(new Dimension(150,130));
		
		add(lbl,"gapleft120");
		add(lbl1,"wrap 40");
		add(btnLekari,"gapleft122");
		add(lbl2,"wrap 15");
		add(btnSestre,"gapleft110");
		add(lbl3,"wrap 15");
		add(btnPacijenti,"gapleft120");
		add(lbl4,"wrap 15");
		add(btnZdravstveneKnjizice,"gapleft80");
		add(lbl5,"wrap 15");
		add(btnPregledi,"gapleft120");
		add(lbl6,"wrap 20");
		add(btnCancel,"gapleft320");
	}
	private void initListeners() {
		btnLekari.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LekariLista lekarLista = new LekariLista(domZdravlja);
				lekarLista.setVisible(true);
			}
		});
		btnSestre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MedicinskeSestreLista sestraLista = new MedicinskeSestreLista(domZdravlja);
				sestraLista.setVisible(true);
			}
		});
		btnPacijenti.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				PacijentiLista pacijentLista = new PacijentiLista(domZdravlja);
				pacijentLista.setVisible(true);
			}
		});
		btnZdravstveneKnjizice.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ZdravstveneKnjiziceLista knjizicaLista = new ZdravstveneKnjiziceLista(domZdravlja);
				knjizicaLista.setVisible(true);
			}
		});
		btnPregledi.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				PreglediLista pregledLista = new PreglediLista(domZdravlja, null);
				pregledLista.setVisible(true);
			}
		});
		btnCancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				MedicinskaSestraMeni.this.setVisible(false);
				MedicinskaSestraMeni.this.dispose();
			}
		});
	}
}
