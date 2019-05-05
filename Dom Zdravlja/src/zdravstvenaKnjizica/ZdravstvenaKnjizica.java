package zdravstvenaKnjizica;

public class ZdravstvenaKnjizica {

	private String id;
	private double datumIsteka;
	private String kategorijaosiguranja;
	
	
	public ZdravstvenaKnjizica() {
		this.id = "";
		this.datumIsteka = 1900;
		this.kategorijaosiguranja = "";
	}
	public ZdravstvenaKnjizica(String id, double datumIsteka, 
			String kategorijaosiguranja) {
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
	public double getDatumIsteka() {
		return datumIsteka;
	}
	public void setDatumIsteka(double datumIsteka) {
		this.datumIsteka = datumIsteka;
	}
	public String getKategorijaosiguranja() {
		return kategorijaosiguranja;
	}
	public void setKategorijaosiguranja(String kategorijaosiguranja) {
		this.kategorijaosiguranja = kategorijaosiguranja;
	}
	@Override
	public String toString() {
		return "ZdravstvenaKnjizica [id=" + id + ", datumIsteka=" + datumIsteka + ", kategorijaosiguranja="
				+ kategorijaosiguranja + "]";
	}
	

}
