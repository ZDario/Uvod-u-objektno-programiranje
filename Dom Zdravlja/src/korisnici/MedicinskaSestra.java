package korisnici;

import domZdravlja.SluzbeDomaZdravlja;

public class MedicinskaSestra extends Zaposlen {
	public MedicinskaSestra(){
		
	}
	public MedicinskaSestra(String ime, String prezime, String jmbg, String adresa, String telefon,
			String korisnickoime, String lozinka, Pol pol, Uloga uloga, double plata, SluzbeDomaZdravlja sluzba) {
		super(ime, prezime, jmbg, adresa, telefon, korisnickoime, lozinka, pol, uloga, plata, sluzba);
	}
	@Override
	public String toString() {
		return "Ime: " + this.ime 
				+ "\nPrezime: " + this.prezime 
				+ "\nJMBG: " + this.jmbg 
				+ "\nAdresa: " + this.adresa
				+"\nTelefon: " + this.telefon
				+"\nKorisnicko ime: " + this.korisnickoime
				+"\nLozinka: " + this.lozinka
				+"\nPol: " + this.pol
				+"\nUloga: " + this.uloga
				+"\nPlata: " + this.plata
				+"\nSluzba: " + this.sluzba;
	}
	
}
