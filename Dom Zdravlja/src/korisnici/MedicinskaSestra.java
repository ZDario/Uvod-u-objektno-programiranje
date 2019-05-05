package korisnici;

import domZdravlja.DomZdravlja;

public class MedicinskaSestra extends Zaposlen {
	public MedicinskaSestra(){
		
	}
	
	public MedicinskaSestra(String ime, String prezime, String jmbg, 
			String adresa, String telefon, String korisnickoime, String lozinka,
			String pol, String uloga,
			double plata, String sluzba, DomZdravlja domzdravlja) {
		super(ime,prezime,jmbg,adresa,telefon,korisnickoime,lozinka,pol,uloga,
				plata,sluzba,domzdravlja);
	}
	@Override
	public String toString() {
		return "MedicinskaSestra [plata=" + plata + ", sluzba=" + sluzba + ", domzdravlja=" + domzdravlja + ", ime="
				+ ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", adresa=" + adresa + ", telefon=" + telefon
				+ ", korisnickoime=" + korisnickoime + ", lozinka=" + lozinka + ", pol=" + pol + ", uloga=" + uloga
				+ "]";
	}	
}
