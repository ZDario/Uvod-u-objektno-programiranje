package zdravstvenaKnjizica;

import java.util.Date;

public class ZdravstvenaKnjizica {

	private String id;
	private Date datumIsteka;
	private KategorijaOsiguranja kategorijaosiguranja;
	
	
	public ZdravstvenaKnjizica() {
		this.id = "";
		this.datumIsteka = null;
		this.kategorijaosiguranja = null;
	}
	public ZdravstvenaKnjizica(String id, Date datumIsteka, 
			KategorijaOsiguranja kategorijaosiguranja) {
		super();
		this.id = id;
		this.datumIsteka = datumIsteka;
		this.kategorijaosiguranja = kategorijaosiguranja;
	}
	public ZdravstvenaKnjizica(ZdravstvenaKnjizica original) {
		this.id = original.id;
		this.datumIsteka = original.datumIsteka;
		this.kategorijaosiguranja = original.kategorijaosiguranja;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
		return "ZdravstvenaKnjizica [id=" + id + ", datumIsteka=" + datumIsteka + ", kategorijaosiguranja="
				+ kategorijaosiguranja + "]";
	}
	

}
