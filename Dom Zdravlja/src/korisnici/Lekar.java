package korisnici;

import domZdravlja.DomZdravlja;

public class Lekar extends Zaposlen {
	
	private String specijalizacija;
	
	public Lekar() {
		this.specijalizacija = "";
	}
	public Lekar(String ime, String prezime, String jmbg, 
			String adresa, String telefon, String korisnickoime, String lozinka,
			String pol, String uloga,
			double plata, String sluzba, 
			DomZdravlja domzdravlja, String specijalizacija) {
		super(ime,prezime,jmbg,adresa,telefon,korisnickoime,lozinka,pol,uloga,
				plata,sluzba,domzdravlja);
		this.specijalizacija = specijalizacija;
	}
	public Lekar(Lekar original) {
		this.specijalizacija = original.specijalizacija;
	}
	
	public String getSpecijalizacija() {
		return specijalizacija;
	}
	public void setSpecijalizacija(String specijalizacija) {
		this.specijalizacija = specijalizacija;
	}
	@Override
	public String toString() {
		return "Lekar [specijalizacija=" + specijalizacija + ", plata=" + plata + ", sluzba=" + sluzba
				+ ", domzdravlja=" + domzdravlja + ", ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg
				+ ", adresa=" + adresa + ", telefon=" + telefon + ", korisnickoime=" + korisnickoime + ", lozinka="
				+ lozinka + ", pol=" + pol + ", uloga=" + uloga + "]";
	}


}
