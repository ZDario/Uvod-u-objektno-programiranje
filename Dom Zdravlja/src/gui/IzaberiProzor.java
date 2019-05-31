package gui;


import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import domZdravlja.DomZdravlja;
import net.miginfocom.swing.MigLayout;

public class IzaberiProzor extends JFrame {
	
	private JLabel lblPoruka1 = new JLabel("Dobrodosli!");
	private JLabel lblPoruka2 = new JLabel("Molimo da izaberete opciju.");
	private JLabel lblPrazno = new JLabel("");
	private JLabel lblLekar = new JLabel("Lekar");
	private JLabel lblSestra = new JLabel("Medicinska Sestra");
	private JLabel lblPacijent = new JLabel("Pacijent");
	private ImageIcon lekarIcon = new ImageIcon(getClass().getResource("/slike/Lekar.jpg"));
	private JButton btnLekar = new JButton(lekarIcon);
	private ImageIcon sestraIcon = new ImageIcon(getClass().getResource("/slike/Medicinska sestra.jpg"));
	private JButton btnSestra = new JButton(sestraIcon);
	private ImageIcon pacijentIcon = new ImageIcon(getClass().getResource("/slike/Pacijent.jpg"));
	private JButton btnPacijent = new JButton(pacijentIcon);
	
	private DomZdravlja domZdravlja;
	
	public IzaberiProzor(DomZdravlja domZdravlja) {
		this.domZdravlja = domZdravlja;
		setTitle("Opcije");
		setSize(850,450);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		initGUI();
		initListeners();

	}
	private void initGUI() {
		MigLayout mig = new MigLayout();
		setLayout(mig);
		btnLekar.setPreferredSize(new Dimension(50,50));
		btnSestra.setPreferredSize(new Dimension(50,50));
		btnPacijent.setPreferredSize(new Dimension(50,50));
		lblPoruka1.setFont(new Font("American Typewriter",Font.PLAIN,25));
		lblPoruka2.setFont(new Font("Helvetica Neue",Font.PLAIN,25));
		lblLekar.setFont(new Font("Arial",Font.PLAIN,20));
		lblSestra.setFont(new Font("Arial",Font.PLAIN,20));
		lblPacijent.setFont(new Font("Arial",Font.PLAIN,20));

		add(lblPoruka1,"wrap 5");
		add(lblPoruka2,"wrap 50");
		add(lblLekar,"gapleft90");
		add(lblSestra,"gapleft25");
		add(lblPacijent,"gapleft150");
		add(lblPrazno,"wrap 25");
		add(btnLekar,"gapleft20");
		add(btnSestra,"gapleft15");
		add(btnPacijent,"gapleft100");
	}
	
	private void initListeners() {
		btnLekar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				LekarLoginProzor lekarLogin = new LekarLoginProzor(domZdravlja);
				lekarLogin.setVisible(true);
			}
		});
		btnSestra.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MedicinskaSestraLoginProzor sestraLogin = new MedicinskaSestraLoginProzor(domZdravlja);
				sestraLogin.setVisible(true);
			}
		});
		btnPacijent.addActionListener(new ActionListener() {
	
			@Override
			public void actionPerformed(ActionEvent e) {
				PacijentLoginProzor pacijentLogin = new PacijentLoginProzor(domZdravlja);
				pacijentLogin.setVisible(true);
			}
		});
	}
}
