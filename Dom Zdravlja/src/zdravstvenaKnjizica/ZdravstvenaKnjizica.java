package zdravstvenaKnjizica;

import java.util.Date;

public class ZdravstvenaKnjizica {

	private String ident;
	private Date datumIsteka;
	private KategorijaOsiguranja kategorijaosiguranja;
	
	
	public ZdravstvenaKnjizica() {
		this.ident = "";
		this.datumIsteka = null;
		this.kategorijaosiguranja = null;
	}
	public ZdravstvenaKnjizica(String ident, Date datumIsteka, 
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
	public Date getDatumIsteka() {
		return datumIsteka;
	}
	public void setDatumIsteka(Date datumIsteka) {
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
