package pregledi;

import korisnici.Pacijent;
import korisnici.Lekar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Pregledi {
	
	private String ident;
	private GregorianCalendar zatrazenDatum;
	private String opis;
	private Lekar lekar;
	private Pacijent pacijent;
	private int soba;
	private StatusPregleda status;
	
	public Pregledi() {
		this.ident = "";
		this.zatrazenDatum = new GregorianCalendar();
		this.opis = "";
		this.pacijent = new Pacijent();
		this.lekar = new Lekar();
		this.soba = 0;
		this.status = StatusPregleda.Zatrazen;
		
	}
	
	public Pregledi(String ident, GregorianCalendar zatrazenDatum,  String opis, Lekar lekar, 
			Pacijent pacijent, int soba, StatusPregleda status) {
		super();
		this.ident = ident;
		this.zatrazenDatum = zatrazenDatum;
		this.opis = opis;
		this.lekar = lekar;
		this.pacijent = pacijent;
		this.soba = soba;
		this.status = status;
	}

	public GregorianCalendar getZatrazenDatum() {
		return zatrazenDatum;
	}

	public void setZatrazenDatum(GregorianCalendar zatrazenDatum) {
		this.zatrazenDatum = zatrazenDatum;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Lekar getLekar() {
		return lekar;
	}

	public void setLekar(Lekar lekar) {
		this.lekar = lekar;
	}
	
	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public int getSoba() {
		return soba;
	}

	public void setSoba(int soba) {
		this.soba = soba;
	}

	public StatusPregleda getStatus() {
		return status;
	}

	public void setStatus(StatusPregleda status) {
		this.status = status;
	}
	
	public String getIdent() {
		return ident;
	}

	public void setIdent(String ident) {
		this.ident = ident;
	}

	@Override
	public String toString() {
		return "PREGLED"
				+"\nIdent: " + this.ident
				+"\nDatum: " + this.zatrazenDatum 
				+ "\nKratak opis pregleda: " + this.opis
				+ "\nLekar: " + this.lekar
				+ "\nPacijent: " + this.pacijent
				+"\nSoba: " + this.soba
				+"\nStatus pregleda: " + this.status;
	}
	
}
