package korisnici;

import domZdravlja.DomZdravlja;

public abstract class Zaposlen extends Korisnici {

	protected double plata;
	protected String sluzba;
	protected DomZdravlja domzdravlja;
	
	public Zaposlen() {
		this.plata = 0;
		this.sluzba = "";
		this.domzdravlja = new DomZdravlja();
	}

	public Zaposlen(String ime, String prezime, String jmbg, 
			String adresa, String telefon, String korisnickoime, String lozinka,
			String pol, String uloga,
			double plata, String sluzba, DomZdravlja domzdravlja) {
		super(ime,prezime,jmbg,adresa,telefon,korisnickoime,lozinka,pol,uloga);
		this.plata = plata;
		this.sluzba = sluzba;
		this.domzdravlja = domzdravlja;
	}
	public Zaposlen(Zaposlen original) {
		this.plata = original.plata;
		this.sluzba = original.sluzba;
		this.domzdravlja = original.domzdravlja;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public String getSluzba() {
		return sluzba;
	}

	public void setSluzba(String sluzba) {
		this.sluzba = sluzba;
	}

	public DomZdravlja getDomzdravlja() {
		return domzdravlja;
	}

	public void setDomzdravlja(DomZdravlja domzdravlja) {
		this.domzdravlja = domzdravlja;
	}

	@Override
	public String toString() {
		return "Zaposlen [plata=" + plata + ", sluzba=" + sluzba + ", domzdravlja=" + domzdravlja + ", ime=" + ime
				+ ", prezime=" + prezime + ", jmbg=" + jmbg + ", adresa=" + adresa + ", telefon=" + telefon
				+ ", korisnickoime=" + korisnickoime + ", lozinka=" + lozinka + ", pol=" + pol + ", uloga=" + uloga
				+ "]";
	}
}