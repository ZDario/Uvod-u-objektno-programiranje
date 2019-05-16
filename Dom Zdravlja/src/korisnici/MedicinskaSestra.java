package korisnici;

import domZdravlja.SluzbeDomaZdravlja;

public class MedicinskaSestra extends Zaposlen {
	public MedicinskaSestra(){
		
	}
	public MedicinskaSestra(String ime, String prezime, 
			String jmbg, String adresa, String telefon,
			String korisnickoime, String lozinka, Pol pol,
			Uloga uloga, double plata, SluzbeDomaZdravlja sluzba) {
		super(ime, prezime, jmbg, adresa, telefon, korisnickoime, lozinka, pol, uloga, plata, sluzba);
	}
	@Override
	public String toString() {
		return "MEDICINSKA SESTRA\n"  + super.toString();
	}
}
