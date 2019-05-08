package main;

import korisnici.Lekar;
import korisnici.MedicinskaSestra;
import korisnici.Pacijent;
import domZdravlja.DomZdravlja;
import pregledi.Pregledi;
import zdravstvenaKnjizica.ZdravstvenaKnjizica;

public class DomZdravljaMain {
	private static String LEKARI_FAJL = "Lekari";
	private static String MEDICINSKESESTRE_FAJL = "Medicinske sestre";
	private static String PACIJENTI_FAJL = "Pacijenti";
	private static String PREGLEDI_FAJL = "Pregledi";
	private static String ZDRAVSTVENAKNJIZICA_FAJL = "Zdravstvena Knjizica";
	
	public static void main(String[] args) {
		DomZdravlja domzdravlja = new DomZdravlja();
		domzdravlja.ucitajLekare(LEKARI_FAJL);
		domzdravlja.ucitajMedicinskeSestre(MEDICINSKESESTRE_FAJL);
		domzdravlja.ucitajPacijente(PACIJENTI_FAJL);
		domzdravlja.ucitajPreglede(PREGLEDI_FAJL);
		domzdravlja.ucitajZdravstveneKnjizice(ZDRAVSTVENAKNJIZICA_FAJL);
		
		
		System.out.println("PODACI UCITANI IZ DATOTEKA:");
		System.out.println("----------------------------------------------");
		ispisiSvePodatke(domzdravlja);
		System.out.println("----------------------------------------------");
		
		
		
		
		System.out.println("Snimanje dodanih podataka...");
		domzdravlja.snimiLekare(LEKARI_FAJL);
		domzdravlja.snimiMedicinskeSestre(MEDICINSKESESTRE_FAJL);
		domzdravlja.snimiPacijente(PACIJENTI_FAJL);
		domzdravlja.snimiPreglede(PREGLEDI_FAJL);
		domzdravlja.snimiZdravstveneKnjizice(ZDRAVSTVENAKNJIZICA_FAJL);
	}

	public static void ispisiSvePodatke(DomZdravlja domzdravlja) {
		for(Lekar lekar : domzdravlja.getLekare()) {
			System.out.println(lekar + "\n");
		}
		
		for(MedicinskaSestra sestra : domzdravlja.getMedicinskaSestre()) {
			System.out.println(sestra + "\n");
		}
		
		for(Pacijent pacijent : domzdravlja.getPacijente()) {
			System.out.println(pacijent + "\n");
		}
		
		for(ZdravstvenaKnjizica knjizica : domzdravlja.getZdravstvenaKnjizice()) {
			System.out.println(knjizica + "\n");
		}
		
		for(Pregledi pregled : domzdravlja.getPreglede()) {
			System.out.println(pregled + "\n");
		}
	}
}
