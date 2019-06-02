package main;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import korisnici.Lekar;
import zdravstvenaKnjizica.KategorijaOsiguranja;
import korisnici.MedicinskaSestra;
import korisnici.Pacijent;
import domZdravlja.DomZdravlja;
import pregledi.Pregledi;
import zdravstvenaKnjizica.ZdravstvenaKnjizica;
import korisnici.Pol;
import korisnici.Uloga;
import domZdravlja.SluzbeDomaZdravlja;
import gui.IzaberiProzor;



public class DomZdravljaMain {
//	private static String LEKARI_FAJL = "Lekari.txt";
//	private static String MEDICINSKESESTRE_FAJL = "Medicinske sestre.txt";
//	private static String PACIJENTI_FAJL = "Pacijenti.txt";
//	private static String PREGLEDI_FAJL = "Pregledi.txt";
//	private static String ZDRAVSTVENAKNJIZICA_FAJL = "Zdravstvena Knjizica.txt";
	
	public static void main(String[] args) {
		DomZdravlja domzdravlja = new DomZdravlja();
		domzdravlja.ucitajLekare();
		domzdravlja.ucitajMedicinskeSestre();
		domzdravlja.ucitajPacijente();
		domzdravlja.ucitajPreglede();
		domzdravlja.ucitajZdravstveneKnjizice();
		
		IzaberiProzor iz = new IzaberiProzor(domzdravlja);
		iz.setVisible(true);
		
//		
//		System.out.println("PODACI UCITANI IZ DATOTEKA:");
//		System.out.println("----------------------------------------------");
//		ispisiSvePodatke(domzdravlja);
//		System.out.println("----------------------------------------------");
//		
//		
//		System.out.println("Datum dodavanja nove knjizice");
//		Date date2 = Calendar.getInstance().getTime();  
//		DateFormat dateFormat = new SimpleDateFormat("15-06-2019");  
//		String strDate = dateFormat.format(date2); 
//		System.out.println(strDate);
//		KategorijaOsiguranja kat = KategorijaOsiguranja.Prva;
//		ZdravstvenaKnjizica testKnjizica = new ZdravstvenaKnjizica ("234", date2, kat);
//		domzdravlja.dodajZdravstvenaKnjizicu(testKnjizica);
//		
//		
//		System.out.println("Dodavanje novog Lekara");
//		Pol pol1 = Pol.Zenski;
//		Uloga uloga1 = Uloga.Lekar;
//		SluzbeDomaZdravlja sluzba1 = SluzbeDomaZdravlja.Sluzba_Zdravstvene_Zastite_Dece;
//		Lekar testLekar = new Lekar ("Marina","Marinkovic","897234","Cara Dusana 102","0602497592","M.Marinkovic","325908m",pol1,uloga1,57000.00,sluzba1,"Doktor medicine");
//		domzdravlja.dodajLekara(testLekar);
//		
//		Pol pol2 = Pol.Zenski;
//		Uloga uloga2 = Uloga.Medicinska_Sestra;
//		SluzbeDomaZdravlja sluzba2 = SluzbeDomaZdravlja.Sluzba_Pravno_Ekonomski_Poslova;
//		System.out.println("Dodavanje nove Medicinske sestre");
//		MedicinskaSestra testSestra = new MedicinskaSestra("Jovana","Jovanovic","328957","Janka Cmelika 52","0643497201","J.Jovanovic","672316j",pol2,uloga2,45000.00,sluzba2);
//		domzdravlja.dodajMedicinskaSestru(testSestra);
//		
//		
//		
//		System.out.println("Snimanje dodanih podataka...");
//		domzdravlja.snimiLekare();
//		domzdravlja.snimiMedicinskeSestre();
//		domzdravlja.snimiPacijente();
//		domzdravlja.snimiPreglede();
//		domzdravlja.snimiZdravstveneKnjizice();
//	}
//
//	public static void ispisiSvePodatke(DomZdravlja domzdravlja) {
//		for(Lekar lekar : domzdravlja.getLekare()) {
//			System.out.println(lekar + "\n");
//		}
//		
//		for(MedicinskaSestra sestra : domzdravlja.getMedicinskaSestre()) {
//			System.out.println(sestra + "\n");
//		}
//		
//		for(Pacijent pacijent : domzdravlja.getPacijente()) {
//			System.out.println(pacijent + "\n");
//		}
//		
//		for(ZdravstvenaKnjizica knjizica : domzdravlja.getZdravstvenaKnjizice()) {
//			System.out.println(knjizica + "\n");
//		}
//		
//		for(Pregledi pregled : domzdravlja.getPreglede()) {
//			System.out.println(pregled + "\n");
//		}
	}
}
