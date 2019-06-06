package zdravstvenaKnjizica;

import java.util.Date;
import java.util.GregorianCalendar;

public class ZdravstvenaKnjizica {

	private String ident;
	private GregorianCalendar datumIsteka;
	private KategorijaOsiguranja kategorijaosiguranja;
	
	
	public ZdravstvenaKnjizica() {
		this.ident = "";
		this.datumIsteka = new GregorianCalendar();
		this.kategorijaosiguranja = KategorijaOsiguranja.Prva;
	}
	public ZdravstvenaKnjizica(String ident, GregorianCalendar datumIsteka, 
			KategorijaOsiguranja kategorijaosiguranja) {
		super();
		this.ident = ident;
		this.datumIsteka = datumIsteka;
		this.kategorijaosiguranja = kategorijaosiguranja;
	}
	
	public String getIdent() {
		return ident;
	}
	public void setId(String ident) {
		this.ident = ident;
	}
	public GregorianCalendar getDatumIsteka() {
		return datumIsteka;
	}
	public void setDatumIsteka(GregorianCalendar datumIsteka) {
		this.datumIsteka = datumIsteka;
	}
	public KategorijaOsiguranja getKategorijaosiguranja() {
		return kategorijaosiguranja;
	}
	public void setKategorijaosiguranja(KategorijaOsiguranja kategorijaosiguranja) {
		this.kategorijaosiguranja = kategorijaosiguranja;
	}
	@Override
	public String toString() {
		return "ZDRAVSTVENA KNJIZICA"
				+"\nID: " + this.ident 
				+ "\nDatum isteka: " + this.datumIsteka
				+ "\nKategorija osiguranja: " + this.kategorijaosiguranja;
	}
}
