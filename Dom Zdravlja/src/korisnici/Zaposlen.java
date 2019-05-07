package korisnici;

import domZdravlja.SluzbeDomaZdravlja;

public abstract class Zaposlen extends Korisnici {

	protected double plata;
	protected SluzbeDomaZdravlja sluzba;
	
	public Zaposlen() {
		this.plata = 0;
		this.sluzba = null;
	}

	public Zaposlen(String ime, String prezime, String jmbg, 
			String adresa, String telefon, String korisnickoime, String lozinka,
			Pol pol, Uloga uloga,
			double plata, SluzbeDomaZdravlja sluzba) {
		super(ime,prezime,jmbg,adresa,telefon,korisnickoime,lozinka,pol,uloga);
		this.plata = plata;
		this.sluzba = sluzba;
	}

	public double getPlata() {
		return plata;
	}

	public void setPlata(double plata) {
		this.plata = plata;
	}

	public SluzbeDomaZdravlja getSluzba() {
		return sluzba;
	}

	public void setSluzba(SluzbeDomaZdravlja sluzba) {
		this.sluzba = sluzba;
	}
}