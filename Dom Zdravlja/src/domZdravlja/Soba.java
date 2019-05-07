package domZdravlja;

public class Soba {
	
	private String id;
	private String naziv;
	private DomZdravlja domzdravlja;
	
	public Soba() {
		super();
		this.id = "";
		this.naziv = "";
		this.domzdravlja = new DomZdravlja();
	}
	
	public Soba(String id, String naziv, DomZdravlja domzdravlja) {
		super();
		this.id = id;
		this.naziv = naziv;
		this.domzdravlja = domzdravlja;
	}
	public Soba(Soba original) {
		super();
		this.id = original.id;
		this.naziv = original.naziv;
		this.domzdravlja = original.domzdravlja;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNaziv() {
		return naziv;
	}
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	public DomZdravlja getDomzdravlja() {
		return domzdravlja;
	}
	public void setDomzdravlja(DomZdravlja domzdravlja) {
		this.domzdravlja = domzdravlja;
	}
	
	@Override
	public String toString() {
		return "Soba [id=" + id + ", naziv=" + naziv + ", domzdravlja=" + domzdravlja + "]";
	}
	
}
