package pregledi;

import korisnici.Pacijent;
import korisnici.Lekar;
import java.util.Date;

public class Pregledi {
	
	private Date zatrazenDatum;
	private String opis;
	private Pacijent pacijent;
	private Lekar lekar;
	private int soba;
	private StatusPregleda status;
	
	public Pregledi() {
		this.zatrazenDatum = null;
		this.opis = "";
		this.pacijent = new Pacijent();
		this.lekar = new Lekar();
		this.soba = 0;
		this.status = null;
		
	}
	
	public Pregledi(Date zatrazenDatum,  String opis, Pacijent pacijent,
			 Lekar lekar, int soba, StatusPregleda status) {
		super();
		this.zatrazenDatum = zatrazenDatum;
		this.opis = opis;
		this.pacijent = pacijent;
		this.lekar = lekar;
		this.soba = soba;
		this.status = status;
	}

	public Date getZatrazenDatum() {
		return zatrazenDatum;
	}

	public void setZatrazenDatum(Date zatrazenDatum) {
		this.zatrazenDatum = zatrazenDatum;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public Pacijent getPacijent() {
		return pacijent;
	}

	public void setPacijent(Pacijent pacijent) {
		this.pacijent = pacijent;
	}

	public Lekar getLekar() {
		return lekar;
	}

	public void setLekar(Lekar lekar) {
		this.lekar = lekar;
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
	@Override
	public String toString() {
		return "Datum: " + this.zatrazenDatum 
				+ "\nKratak opis pregleda: " + this.opis
				+ "\nPacijent: " + this.pacijent
				+ "\nLekar: " + this.lekar
				+"\nSoba: " + this.soba
				+"\nStatus pregleda: " + this.status;
	}
	
}
