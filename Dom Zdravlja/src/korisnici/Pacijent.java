package korisnici;

import zdravstvenaKnjizica.ZdravstvenaKnjizica;

public class Pacijent extends Korisnici {
	
	private ZdravstvenaKnjizica knjizica;
	private Lekar izabraniLekar;
	
	public Pacijent() {
		this.izabraniLekar = new Lekar();
		this.knjizica = new ZdravstvenaKnjizica();
	}
	public Pacijent(String ime, String prezime, String jmbg, 
			String adresa, String telefon, String korisnickoime, String lozinka,
			Pol pol, Uloga uloga,
			ZdravstvenaKnjizica knjizica, Lekar izabraniLekar ) {
		super(ime,prezime,jmbg,adresa,telefon,korisnickoime,lozinka,pol,uloga);
		this.knjizica = knjizica;
		this.izabraniLekar = izabraniLekar;
		
	}
	public ZdravstvenaKnjizica getKnjizica() {
		return knjizica;
	}
	public void setKnjizica(ZdravstvenaKnjizica knjizica) {
		this.knjizica = knjizica;
	}
	public Lekar getIzabraniLekar() {
		return izabraniLekar;
	}
	public void setIzabraniLekar(Lekar izabraniLekar) {
		this.izabraniLekar = izabraniLekar;
	}
	@Override
	public String toString() {
		return "PACIJENT\n"
				+ super.toString() 
				+"\nID Knjizice: " + this.knjizica
				+"\nIzabrani Lekar: " + this.izabraniLekar;
	}
}
