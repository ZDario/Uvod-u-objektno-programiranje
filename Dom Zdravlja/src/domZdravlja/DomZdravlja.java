package domZdravlja;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import korisnici.Lekar;
import korisnici.MedicinskaSestra;
import korisnici.Pacijent;
import korisnici.Pol;
import korisnici.Uloga;
import domZdravlja.SluzbeDomaZdravlja;
import pregledi.Pregledi;
import pregledi.StatusPregleda;
import zdravstvenaKnjizica.KategorijaOsiguranja;
import zdravstvenaKnjizica.ZdravstvenaKnjizica;


public class DomZdravlja {
	
	private ArrayList<Lekar> lekari;
	private ArrayList<MedicinskaSestra> sestre;
	private ArrayList<Pacijent> pacijenti;
	private ArrayList<ZdravstvenaKnjizica> knjizice;
	private ArrayList<Pregledi> pregledi;
	
	public DomZdravlja() {
		this.lekari = new ArrayList<Lekar>();
		this.sestre = new ArrayList<MedicinskaSestra>();
		this.pacijenti = new ArrayList<Pacijent>();
		this.knjizice = new ArrayList<ZdravstvenaKnjizica>();
		this.pregledi = new ArrayList<Pregledi>();
	}
	
	
	
	//FUNKCIJE DODAVANJA
	
	public ArrayList<Lekar> getLekare() {
		return lekari;
	}
	public void dodajLekara(Lekar lekar) {
		this.lekari.add(lekar);
	}
	
	
	public ArrayList<MedicinskaSestra> getMedicinskaSestre() {
		return sestre;
	}
	public void dodajMedicinskaSestru(MedicinskaSestra sestra) {
		this.sestre.add(sestra);
	}
	
	
	public ArrayList<Pacijent> getPacijente() {
		return pacijenti;
	}
	public void dodajMedicinskaSestru(Pacijent pacijent) {
		this.pacijenti.add(pacijent);
	}
	

	public ArrayList<ZdravstvenaKnjizica> getZdravstvenaKnjizice() {
		return knjizice;
	}
	public void dodajZdravstvenaKnjizicu(ZdravstvenaKnjizica knjizica) {
		this.knjizice.add(knjizica);
	}

	
	public ArrayList<Pregledi> getPreglede() {
		return pregledi;
	}
	public void dodajPreglede(Pregledi pregled) {
		this.pregledi.add(pregled);
	}
	
	
	
	
	
	//FUNKCIJE BRISANJA
	
	public void obrisiLekara(Lekar lekar) {
		this.lekari.remove(lekar);
	}
	
	public void obrisiMedicinskuSestru(MedicinskaSestra sestra) {
		this.sestre.remove(sestra);
	}
	
	public void obrisiPacijenta(Pacijent pacijent) {
		this.pacijenti.remove(pacijent);
	}
	
	public void obrisiZdravstvenuKnjizicu(ZdravstvenaKnjizica knjizica) {
		this.knjizice.remove(knjizica);
	}
	
	public void obrisiPregled(Pregledi pregled) {
		this.pregledi.remove(pregled);
	}
	
	
	//FUNKCIJE PRONALAZENJA
	
	public Lekar nadjiLekara(String korisnickoime) {
		for (Lekar lekar : lekari) {
			if (lekar.getKorisnickoime().equals(korisnickoime)) {
				return lekar;
			}
		}
		return null;
	}
	
	public MedicinskaSestra nadjiMedicinskuSestru(String korisnickoime) {
		for (MedicinskaSestra sestra : sestre) {
			if (sestra.getKorisnickoime().equals(korisnickoime)) {
				return sestra;
			}
		}
		return null;
	}
	
	public Pacijent nadjiPacijenta(String korisnickoime) {
		for (Pacijent pacijent : pacijenti) {
			if (pacijent.getKorisnickoime().equals(korisnickoime)) {
				return pacijent;
			}
		}
		return null;
	}
	
	public ZdravstvenaKnjizica nadjiKnjizicu(String ident) {
		for (ZdravstvenaKnjizica knjizica : knjizice) {
			if (knjizica.getIdent().equals(ident)) {
				return knjizica;
			}
		}
		return null;
	}		
	
	public Pregledi nadjiPreglede(Date zatrazenDatum) {
		for (Pregledi pregled : pregledi) {
			if (pregled.getZatrazenDatum().equals(zatrazenDatum)) {
				return pregled;
			}
		}
		return null;
	}
	
	
	
	//FUNKCIJE UCITAVANJA
	
	public void ucitajLekare(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String ime = split[0];
				String prezime = split[1];
				String jmbg = split[2];
				String adresa= split[3];
				String telefon = split[4];
				String korisnickoime = split[5];
				String lozinka = split[6];
				Pol pol = Pol.valueOf(split[7]);
				Uloga uloga = Uloga.valueOf(split[8]);
				String plataString = split[9];
				double plata = Double.parseDouble(plataString);
				SluzbeDomaZdravlja sluzba = SluzbeDomaZdravlja.valueOf(split[10]);
				String specijalizacija = split[11];
				Lekar lekar = new Lekar(ime, prezime, jmbg, adresa, telefon, korisnickoime,lozinka, 
						pol, uloga, plata, sluzba ,specijalizacija);
				lekari.add(lekar);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void ucitajMedicinskeSestre(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String ime = split[0];
				String prezime = split[1];
				String jmbg = split[2];
				String adresa= split[3];
				String telefon = split[4];
				String korisnickoime = split[5];
				String lozinka = split[6];
				Pol pol = Pol.valueOf(split[7]);
				Uloga uloga = Uloga.valueOf(split[8]);
				String plataString = split[9];
				double plata = Double.parseDouble(plataString);
				SluzbeDomaZdravlja sluzba = SluzbeDomaZdravlja.valueOf(split[10]);
				MedicinskaSestra sestra = new MedicinskaSestra(ime, prezime, jmbg, adresa, telefon, korisnickoime,lozinka, 
						pol, uloga, plata, sluzba);
				sestre.add(sestra);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		

	public void ucitajPacijente(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String ime = split[0];
				String prezime = split[1];
				String jmbg = split[2];
				String adresa= split[3];
				String telefon = split[4];
				String korisnickoime = split[5];
				String lozinka = split[6];
				Pol pol = Pol.valueOf(split[7]);
				Uloga uloga = Uloga.valueOf(split[8]);
				Lekar izabraniLekar = nadjiLekara(split[9]);
				ZdravstvenaKnjizica knjizica = nadjiKnjizicu(split[10]);
				Pacijent pacijent = new Pacijent(ime, prezime, jmbg, adresa, telefon, korisnickoime,lozinka, 
						pol, uloga, izabraniLekar,knjizica);
				pacijenti.add(pacijent);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void ucitajZdravstveneKnjizice(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String ident = split[0];
				String string = split[1];
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				Date datumIsteka = format.parse(string);
				KategorijaOsiguranja kategorijaosiguranja = KategorijaOsiguranja.valueOf(split[2]);
				ZdravstvenaKnjizica knjizica = new ZdravstvenaKnjizica(ident,datumIsteka,kategorijaosiguranja);
				knjizice.add(knjizica);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void ucitajPreglede(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String string = split[0];
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				Date zatrazenDatum = format.parse(string);
				String opis= split[1];
				Pacijent pacijent = nadjiPacijenta(split[2]);
				Lekar izabraniLekar = nadjiLekara(split[3]);
				String sobaString = split[4];
				int soba = Integer.parseInt(sobaString);
				StatusPregleda status = StatusPregleda.valueOf(split[5]);
				Pregledi pregled = new Pregledi(zatrazenDatum,opis,pacijent,izabraniLekar,soba,status);
				pregledi.add(pregled);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//FUNKCIJE SNIMANJA
	
	public void snimiLekare(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			String content = "";
			for (Lekar lekar : lekari) {
				content += lekar.getIme() + "|" + lekar.getPrezime() + "|"
						+ lekar.getJmbg() + "|" + lekar.getAdresa() + "|"
						+ lekar.getTelefon() + "|" + lekar.getKorisnickoime() + "|"
						+ lekar.getLozinka() + "|" + lekar.getPol() + "|" + lekar.getUloga() + "|"
						+ lekar.getPlata() + "|" + lekar.getSluzba() + "|" 
						+ lekar.getSpecijalizacija() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja lekara.");
		}
	}
	
	public void snimiMedicinskeSestre(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			String content = "";
			for (MedicinskaSestra sestra : sestre) {
				content += sestra.getIme() + "|" + sestra.getPrezime() + "|"
						+ sestra.getJmbg() + "|" + sestra.getAdresa() + "|"
						+ sestra.getTelefon() + "|" + sestra.getKorisnickoime() + "|"
						+ sestra.getLozinka() + "|" + sestra.getPol() + "|" + sestra.getUloga() + "|"
						+ sestra.getPlata() + "|" + sestra.getSluzba() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja medicinske sestre.");
		}
	}
	
	public void snimiPacijente(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			String content = "";
			for (Pacijent pacijent : pacijenti) {
				content += pacijent.getIme() + "|" + pacijent.getPrezime() + "|"
						+ pacijent.getJmbg() + "|" + pacijent.getAdresa() + "|"
						+ pacijent.getTelefon() + "|" + pacijent.getKorisnickoime() + "|"
						+ pacijent.getLozinka() + "|" + pacijent.getPol() + "|" 
						+ pacijent.getUloga() + "|"
						+ pacijent.getIzabraniLekar().getKorisnickoime() + "|" 
						+ pacijent.getKnjizica().getIdent() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja pacijenta.");
		}
	}
	
	public void snimiZdravstveneKnjizice(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			String content = "";
			for (ZdravstvenaKnjizica knjizica : knjizice) {
				
				Date datumisteka = knjizica.getDatumIsteka(); 
			    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
			    String strDate = formatter.format(datumisteka);
			    
				content += knjizica.getIdent() + "|" + strDate + "|"
						+ knjizica.getKategorijaosiguranja() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja zdravstvene knjizice");
		}
	}
	
	public void snimiPreglede(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			String content = "";
			for (Pregledi pregled : pregledi) {
				
				Date zatrazendatum = pregled.getZatrazenDatum(); 
			    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
			    String strDate = formatter.format(zatrazendatum);
				
				content += strDate + "|" + pregled.getOpis() + "|"
						+ pregled.getPacijent().getKorisnickoime() + "|" 
						+ pregled.getLekar().getKorisnickoime() + "|" 
						+ pregled.getSoba() + "|" 
						+ pregled.getStatus() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja pregleda.");
		}
	}
	
	
	
	//FUNKCIJE LOGIN
	
	public Lekar login1(String korisnickoime,String sifra) {
		for(Lekar lekar : lekari) {
			if(lekar.getKorisnickoime().equalsIgnoreCase(korisnickoime)&& lekar.getLozinka().equals(sifra)) {
				return lekar;
			}
		}
		return null;
	}
	public MedicinskaSestra login2(String korisnickoime,String sifra) {
		for(MedicinskaSestra sestra : sestre) {
			if(sestra.getKorisnickoime().equalsIgnoreCase(korisnickoime)&& sestra.getLozinka().equals(sifra)) {
				return sestra;
			}
		}
		return null;
	}
	public Pacijent login3(String korisnickoime,String sifra) {
		for(Pacijent pacijent : pacijenti) {
			if(pacijent.getKorisnickoime().equalsIgnoreCase(korisnickoime)&& pacijent.getLozinka().equals(sifra)) {
				return pacijent;
			}
		}
		return null;
	}
}