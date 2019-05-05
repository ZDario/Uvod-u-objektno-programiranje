package domZdravlja;

import java.util.ArrayList;

public class DomZdravlja {
	
	private String naziv;
	private ArrayList<Soba> sobe;
	
	public DomZdravlja() {
		this.naziv = "";
		this.sobe = new ArrayList<Soba>();
	}

	public DomZdravlja(String naziv, ArrayList<Soba> sobe) {
		super();
		this.naziv = naziv;
		this.sobe = sobe;
	}
	public DomZdravlja(DomZdravlja original) {
		this.naziv = original.naziv;
		this.sobe = original.sobe;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public ArrayList<Soba> getSobe() {
		return sobe;
	}

	public void setSobe(ArrayList<Soba> sobe) {
		this.sobe = sobe;
	}

	@Override
	public String toString() {
		return "DomZdravlja [naziv=" + naziv + ", sobe=" + sobe + "]";
	}
}
