package gui.MeniKorisnika;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import domZdravlja.DomZdravlja;
import gui.Liste.LekariLista;
import gui.Liste.MedicinskeSestreLista;
import gui.Liste.PacijentiLista;
import korisnici.Lekar;
import korisnici.MedicinskaSestra;
import net.miginfocom.swing.MigLayout;

public class MedicinskaSestraMeni extends JFrame {
	
	private JButton btnLekari = new JButton("Lista Lekara");
	private JButton btnSestre =  new JButton("Lista Medicinskih Sestri");
	private JButton btnPacijenti = new JButton("Lista Pacijenata");
	
	private DomZdravlja domZdravlja;
	private MedicinskaSestra SestraLoginProzor;
	
	public MedicinskaSestraMeni(DomZdravlja domZdravlja,MedicinskaSestra SestraLoginProzor) {
		this.domZdravlja = domZdravlja;
		this.SestraLoginProzor = SestraLoginProzor;
		setTitle("Meni Medicinske Sestre: " + SestraLoginProzor.getKorisnickoime());
		setSize(250, 250);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		initGUI();
		initListeners();
	}
	private void initGUI() {
		MigLayout mig = new MigLayout("wrap 2");
		setLayout(mig);
		
		add(btnLekari,"wrap");
		add(btnSestre,"wrap");
		add(btnPacijenti);
		
	}
	private void initListeners() {
		btnLekari.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LekariLista lekarMeni = new LekariLista(domZdravlja);
				lekarMeni.setVisible(true);
			}
		});
		btnSestre.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MedicinskeSestreLista sestraMeni = new MedicinskeSestreLista();
				sestraMeni.setVisible(true);
			}
		});
		btnPacijenti.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				PacijentiLista pacijentMeni = new PacijentiLista();
				pacijentMeni.setVisible(true);
			}
		});
	}
}
