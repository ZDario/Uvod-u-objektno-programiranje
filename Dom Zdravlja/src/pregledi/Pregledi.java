package pregledi;

import korisnici.Pacijent;
import korisnici.MedicinskaSestra;
import korisnici.Lekar;
import domZdravlja.Soba;

public class Pregledi {
	
	private double zatrazenDatum;
	private double pocetakDatum;
	private double krajDatum;
	private String opis;
	private Pacijent pacijent;
	private MedicinskaSestra sestra;
	private Lekar lekar;
	private Soba soba;
	private String status;
	
	public Pregledi() {
		this.zatrazenDatum = 0;
		this.pocetakDatum = 0;
		this.krajDatum = 0;
		this.opis = "";
		this.pacijent = new Pacijent();
		this.sestra = new MedicinskaSestra();
		this.lekar = new Lekar();
		this.soba = new Soba();
		this.status = "";
		
	}
	
	public Pregledi(double zatrazenDatum, double pocetakDatum, double krajDatum, String opis, Pacijent pacijent,
			MedicinskaSestra sestra, Lekar lekar, Soba soba, String status) {
		super();
		this.zatrazenDatum = zatrazenDatum;
		this.pocetakDatum = pocetakDatum;
		this.krajDatum = krajDatum;
		this.opis = opis;
		this.pacijent = pacijent;
		this.sestra = sestra;
		this.lekar = lekar;
		this.soba = soba;
		this.status = status;
	}
	public Pregledi(Pregledi original) {
		this.zatrazenDatum = original.zatrazenDatum;
		this.pocetakDatum = original.pocetakDatum;
		this.krajDatum = original.krajDatum;
		this.opis = original.opis;
		this.pacijent = original.pacijent;
		this.sestra = original.sestra;
		this.lekar = original.lekar;
		this.soba = original.soba;
		this.status = original.status;
	}
	
	
	public double getZatrazenDatum() {
		return zatrazenDatum;
	}

	public void setZatrazenDatum(double zatrazenDatum) {
		this.zatrazenDatum = zatrazenDatum;
	}

	public double getPocetakDatum() {
		return pocetakDatum;
	}

	public void setPocetakDatum(double pocetakDatum) {
		this.pocetakDatum = pocetakDatum;
	}

	public double getKrajDatum() {
		return krajDatum;
	}

	public void setKrajDatum(double krajDatum) {
		this.krajDatum = krajDatum;
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

	public MedicinskaSestra getSestra() {
		return sestra;
	}

	public void setSestra(MedicinskaSestra sestra) {
		this.sestra = sestra;
	}

	public Lekar getLekar() {
		return lekar;
	}

	public void setLekar(Lekar lekar) {
		this.lekar = lekar;
	}

	public Soba getSoba() {
		return soba;
	}

	public void setSoba(Soba soba) {
		this.soba = soba;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Pregledi [zatrazenDatum=" + zatrazenDatum + ", pocetakDatum=" + pocetakDatum + ", krajDatum="
				+ krajDatum + ", opis=" + opis + ", pacijent=" + pacijent + ", sestra=" + sestra + ", lekar=" + lekar
				+ ", soba=" + soba + ", status=" + status + "]";
	}
	
}
