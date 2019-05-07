package korisnici;

import zdravstvenaKnjizica.ZdravstvenaKnjizica;

public class Pacijent extends Korisnici {
	
	private Lekar izabraniLekar;
	private ZdravstvenaKnjizica knjizica;
	
	public Pacijent() {
		this.izabraniLekar = new Lekar();
		this.knjizica = new ZdravstvenaKnjizica();
	}
	public Pacijent(String ime, String prezime, String jmbg, 
			String adresa, String telefon, String korisnickoime, String lozinka,
			Pol pol, Uloga uloga,
			Lekar izabraniLekar, ZdravstvenaKnjizica knjizica) {
		super(ime,prezime,jmbg,adresa,telefon,korisnickoime,lozinka,pol,uloga);
		this.izabraniLekar = izabraniLekar;
		this.knjizica = knjizica;
	}
	public Lekar getIzabraniLekar() {
		return izabraniLekar;
	}
	public void setIzabraniLekar(Lekar izabraniLekar) {
		this.izabraniLekar = izabraniLekar;
	}
	public ZdravstvenaKnjizica getKnjizica() {
		return knjizica;
	}
	public void setKnjizica(ZdravstvenaKnjizica knjizica) {
		this.knjizica = knjizica;
	}
	@Override
	public String toString() {
		return "Pacijent [izabraniLekar=" + izabraniLekar + ", knjizica=" + knjizica + ", ime=" + ime + ", prezime="
				+ prezime + ", jmbg=" + jmbg + ", adresa=" + adresa + ", telefon=" + telefon + ", korisnickoime="
				+ korisnickoime + ", lozinka=" + lozinka + "]";
	}
}
