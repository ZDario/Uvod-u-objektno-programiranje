package korisnici;

public abstract class Korisnici {
	
	protected String ime;
	protected String prezime;
	protected String jmbg;
	protected String adresa;
	protected String telefon;
	protected String korisnickoime;
	protected String lozinka;
	protected Pol pol;
	protected Uloga uloga;
	
	public Korisnici() {
		this.ime = "";
		this.prezime = "";
		this.jmbg = "";
		this.adresa = "";
		this.telefon = "";
		this.korisnickoime = "";
		this.lozinka = "";
		this.pol = null;
		this.uloga = null;
	}

	public Korisnici(String ime, String prezime, String jmbg, String adresa, String telefon, String korisnickoime,
			String lozinka, Pol pol, Uloga uloga) {
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

	public Pol getPol() {
		return pol;
	}

	public void setPol(Pol pol) {
		this.pol = pol;
	}

	public Uloga getUloga() {
		return uloga;
	}

	public void setUloga(Uloga uloga) {
		this.uloga = uloga;
	}
	@Override
	public String toString() {
		return  "Ime: " + this.ime 
				+ "\nPrezime: " + this.prezime 
				+ "\nJMBG: " + this.jmbg 
				+ "\nAdresa: " + this.adresa
				+"\nTelefon: " + this.telefon
				+"\nKorisnicko ime: " + this.korisnickoime
				+"\nLozinka: " + this.lozinka
				+"\nPol: " + this.pol
				+"\nUloga: " + this.uloga;
	}
}
