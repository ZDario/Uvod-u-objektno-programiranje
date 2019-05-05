package korisnici;

public abstract class Korisnici {
	
	protected String ime;
	protected String prezime;
	protected String jmbg;
	protected String adresa;
	protected String telefon;
	protected String korisnickoime;
	protected String lozinka;
	protected String pol;
	protected String uloga;
	
	public Korisnici() {
		this.ime = "";
		this.prezime = "";
		this.jmbg = "";
		this.adresa = "";
		this.telefon = "";
		this.korisnickoime = "";
		this.lozinka = "";
		this.pol = "";
		this.uloga = "";
	}

	public Korisnici(String ime, String prezime, String jmbg, String adresa, String telefon, String korisnickoime,
			String lozinka, String pol, String uloga) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.jmbg = jmbg;
		this.adresa = adresa;
		this.telefon = telefon;
		this.korisnickoime = korisnickoime;
		this.lozinka = lozinka;
		this.pol = pol;
		this.uloga = uloga;
	}
	public Korisnici(Korisnici original) {
		this.ime = original.ime;
		this.prezime = original.prezime;
		this.jmbg = original.jmbg;
		this.adresa = original.adresa;
		this.telefon = original.telefon;
		this.korisnickoime = original.korisnickoime;
		this.lozinka = original.lozinka;
		this.pol = original.pol;
		this.uloga = original.uloga;
	}
	
	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getJmbg() {
		return jmbg;
	}

	public void setJmbg(String jmbg) {
		this.jmbg = jmbg;
	}

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getKorisnickoime() {
		return korisnickoime;
	}

	public void setKorisnickoime(String korisnickoime) {
		this.korisnickoime = korisnickoime;
	}

	public String getLozinka() {
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getPol() {
		return pol;
	}

	public void setPol(String pol) {
		this.pol = pol;
	}

	public String getUloga() {
		return uloga;
	}

	public void setUloga(String uloga) {
		this.uloga = uloga;
	}

	@Override
	public String toString() {
		return "Korisnici [ime=" + ime + ", prezime=" + prezime + ", jmbg=" + jmbg + ", adresa=" + adresa + ", telefon="
				+ telefon + ", korisnickoime=" + korisnickoime + ", lozinka=" + lozinka + ", pol=" + pol + ", uloga="
				+ uloga + "]";
	}

	
}
