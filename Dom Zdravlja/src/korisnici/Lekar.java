package korisnici;

import domZdravlja.SluzbeDomaZdravlja;

public class Lekar extends Zaposlen {
	
	private String specijalizacija;
	
	public Lekar() {
		this.specijalizacija = "";
	}
	public Lekar(String ime, String prezime, String jmbg, 
			String adresa, String telefon, String korisnickoime, String lozinka,
			Pol pol, Uloga uloga,
			double plata, SluzbeDomaZdravlja sluzba, String specijalizacija) {
		super(ime,prezime,jmbg,adresa,telefon,korisnickoime,lozinka,pol,uloga,
				plata,sluzba);
		this.specijalizacija = specijalizacija;
	}
	public String getSpecijalizacija() {
		return specijalizacija;
	}
	public void setSpecijalizacija(String specijalizacija) {
		this.specijalizacija = specijalizacija;
	}
	@Override
	public String toString() {
		return "LEKAR"
				+"\nIme: " + this.ime 
				+ "\nPrezime: " + this.prezime 
				+ "\nJMBG: " + this.jmbg 
				+ "\nAdresa: " + this.adresa
				+"\nTelefon: " + this.telefon
				+"\nKorisnicko ime: " + this.korisnickoime
				+"\nLozinka: " + this.lozinka
				+"\nPol: " + this.pol
				+"\nUloga: " + this.uloga
				+"\nPlata: " + this.plata
				+"\nSluzba: " + this.sluzba
				+"\nSpecijalizacija: " + this.specijalizacija;
	}
	
}
