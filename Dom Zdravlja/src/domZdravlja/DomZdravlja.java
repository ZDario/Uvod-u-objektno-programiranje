package domZdravlja;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

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
	SimpleDateFormat formatKnjizice = new SimpleDateFormat("dd.MM.yyyy.");
	SimpleDateFormat formatTermina = new SimpleDateFormat("dd.MM.yyyy. HH:mm");
	
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
	public void dodajPacijenta(Pacijent pacijent) {
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
	
	
	
	//
	//FUNKCIJE PRONALAZENJA
	//
	
	
	//FUNKCIJE PRONALAZENJA PREMA KORISNICKOM IMENU, JMBG-U I ID-U
	
	public Lekar nadjiLekara(String korisnickoime) {
		for (Lekar lekar : lekari) {
			if (lekar.getKorisnickoime().equals(korisnickoime)) {
				return lekar;
			}
		}
		return null;
	}
	public Lekar nadjiLekara2(String jmbg) {
		for (Lekar lekar : lekari) {
			if (lekar.getJmbg().equals(jmbg)) {
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
	public MedicinskaSestra nadjiMedicinskuSestru2(String jmbg) {
		for (MedicinskaSestra sestra : sestre) {
			if (sestra.getJmbg().equals(jmbg)) {
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
	public Pacijent nadjiPacijenta2(String jmbg) {
		for (Pacijent pacijent : pacijenti) {
			if (pacijent.getJmbg().equals(jmbg)) {
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
	
	public ZdravstvenaKnjizica nadjiKnjizicuZaPregled(String korisnickoime) {
		for (Pacijent pacijent : pacijenti) {
			if (pacijent.getKorisnickoime().equals(korisnickoime)) {
				return pacijent.getKnjizica();
			}
		}
		return null;
	}
	
	public Pregledi nadjiPreglede(String ident) {
		for (Pregledi pregled : pregledi) {
			if (pregled.getIdent().equals(ident)) {
				return pregled;
			}
		}
		return null;
	}
	
	
	
	//FUNKCIJE PRONALAZENJA PREGLEDA ZA LEKARE I PACIJENTE
	
	public ArrayList<Pregledi> pronadjiPregledeLekar(String korisnickoime){
		ArrayList<Pregledi> pregledLekara=new ArrayList<Pregledi>();
		for(Pregledi pregled:pregledi){
			if((pregled.getLekar().getKorisnickoime().equals(korisnickoime))){
				pregledLekara.add(pregled);
			}
		}
		return pregledLekara;
	}
	
	public ArrayList<Pregledi> pronadjiPregledePacijent(String korisnickoime){
		ArrayList<Pregledi> pregledPacijenta=new ArrayList<Pregledi>();
		for(Pregledi pregled:pregledi){
			if(pregled.getPacijent().getKorisnickoime().equals(korisnickoime)){
				pregledPacijenta.add(pregled);
			}
		}
		return pregledPacijenta;
	}
	
	
	
	
	//FUNKCIJE PRONALAZENJA NAJVECEG ID ZA PREGLEDE I ZDRAVSTVENE KNJIZICE
	public int pronadjiNajveciBrojPregleda(){
		int najveci=0;
		for (Pregledi pregled : pregledi){
			if(najveci<Integer.parseInt(pregled.getIdent())){
				najveci=Integer.parseInt(pregled.getIdent());
			}
		}
		return najveci;
	}
	
	public int pronadjiNajveciBrojZdravstveneKnjizice(){
		int najveci=0;
		for (ZdravstvenaKnjizica knjizica : knjizice){
			if(najveci<Integer.parseInt(knjizica.getIdent())){
				najveci=Integer.parseInt(knjizica.getIdent());
			}
		}
		return najveci;
	}
	
	//FUNKCIJE ZA VREME
	
	public SimpleDateFormat getFormatKnjizice() {
		return formatKnjizice;
	}
	
	public void setFormatKnjizice(SimpleDateFormat formatKnjizice) {
		this.formatKnjizice = formatKnjizice;
	}
	
	public SimpleDateFormat getFormatTermina() {
		return formatTermina;
	}
	
	public void setFormatTermina(SimpleDateFormat formatTermina) {
		this.formatTermina = formatTermina;
	}
	
	
	
	public GregorianCalendar napraviDatumIVreme(String d) {
		String[] split = d.split("\\ ");
		String[] datum = split[0].split("\\.");
		String[] vreme = split[1].split("\\:");
		int dan = Integer.parseInt(datum[0]);
		int mesec = Integer.parseInt(datum[1]);
		int godina = Integer.parseInt(datum[2]);
		int sat = Integer.parseInt(vreme[0]);
		int minute = Integer.parseInt(vreme[1]);
		GregorianCalendar termin = new GregorianCalendar(godina,mesec-1,dan,sat,minute);
		return termin;
	}
	public GregorianCalendar napraviDatum(String d) {
		String[] datum1 = d.split("\\.");
		int dan = Integer.parseInt(datum1[0]);
		int mesec = Integer.parseInt(datum1[1]);
		int godina = Integer.parseInt(datum1[2]);
		GregorianCalendar datum = new GregorianCalendar(godina,mesec-1,dan);
		return datum;
	}
	public String VremeUString(GregorianCalendar termin,SimpleDateFormat f) {
		String d = f.format(termin.getTime());
		return d;
	}
	public GregorianCalendar FormatTermina() {
		SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
		GregorianCalendar Datum= new GregorianCalendar();
		format.format(Datum.getTime());
		return Datum;
	}
	
	
	
	
	//FUNKCIJE UCITAVANJA
	
	public void ucitajLekare() {
		try {
			File file = new File("src/fajlovi/Lekari.txt");
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
	
	
	public void ucitajMedicinskeSestre() {
		try {
			File file = new File("src/fajlovi/Medicinske sestre.txt");
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
		

	public void ucitajPacijente() {
		try {
			File file = new File("src/fajlovi/Pacijenti.txt");
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
				ZdravstvenaKnjizica knjizica = nadjiKnjizicu(split[9]);
				Lekar izabraniLekar = nadjiLekara(split[10]);
				Pacijent pacijent = new Pacijent(ime, prezime, jmbg, adresa, telefon, korisnickoime,lozinka, 
						pol, uloga, knjizica, izabraniLekar);
				pacijenti.add(pacijent);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public void ucitajZdravstveneKnjizice() {
		try {
			File file = new File("src/fajlovi/Zdravstvena Knjizica.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String ident = split[0];
				GregorianCalendar datumIsteka = napraviDatum(split[1]);
				KategorijaOsiguranja kategorijaosiguranja = KategorijaOsiguranja.valueOf(split[2]);
				ZdravstvenaKnjizica knjizica = new ZdravstvenaKnjizica(ident,datumIsteka,kategorijaosiguranja);
				knjizice.add(knjizica);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void ucitajPreglede() {
		try {
			File file = new File("src/fajlovi/Pregledi.txt");
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String ident = split[0];
				GregorianCalendar zatrazenDatum = napraviDatumIVreme(split[1]);
				String opis= split[2];
				Lekar izabraniLekar = nadjiLekara(split[3]);
				Pacijent pacijent = nadjiPacijenta(split[4]);
				String sobaString = split[5];
				int soba = Integer.parseInt(sobaString);
				StatusPregleda status = StatusPregleda.valueOf(split[6]);
				Pregledi pregled = new Pregledi(ident,zatrazenDatum,opis,izabraniLekar,pacijent,soba,status);
				pregledi.add(pregled);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	//FUNKCIJE SNIMANJA
	
	public void snimiLekare() {
		try {
			File file = new File("src/fajlovi/Lekari.txt");
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
	
	public void snimiMedicinskeSestre() {
		try {
			File file = new File("src/fajlovi/Medicinske sestre.txt");
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
	
	public void snimiPacijente() {
		try {
			File file = new File("src/fajlovi/Pacijenti.txt");
			String content = "";
			for (Pacijent pacijent : pacijenti) {
				content += pacijent.getIme() + "|" + pacijent.getPrezime() + "|"
						+ pacijent.getJmbg() + "|" + pacijent.getAdresa() + "|"
						+ pacijent.getTelefon() + "|" + pacijent.getKorisnickoime() + "|"
						+ pacijent.getLozinka() + "|" + pacijent.getPol() + "|" 
						+ pacijent.getUloga() + "|"
						+ pacijent.getKnjizica().getIdent() + "|"
						+ pacijent.getIzabraniLekar().getKorisnickoime() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja pacijenta.");
		}
	}
	
	public void snimiZdravstveneKnjizice() {
		try {
			File file = new File("src/fajlovi/Zdravstvena Knjizica.txt");
			String content = "";
			for (ZdravstvenaKnjizica knjizica : knjizice) {
				content += knjizica.getIdent() + "|" + VremeUString(knjizica.getDatumIsteka(),formatKnjizice) + "|"
						+ knjizica.getKategorijaosiguranja() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja zdravstvene knjizice");
		}
	}
	
	public void snimiPreglede() {
		try {
			File file = new File("src/fajlovi/Pregledi.txt");
			String content = "";
			for (Pregledi pregled : pregledi) {
				content += pregled.getIdent() + "|" + VremeUString(pregled.getZatrazenDatum(), formatTermina) + "|" + pregled.getOpis() + "|"
						+ pregled.getLekar().getKorisnickoime() + "|" 
						+ pregled.getPacijent().getKorisnickoime() + "|" 
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
	
	
	
	
	//FUNKCIJA RACUN
	
	public double napraviRacun(KategorijaOsiguranja kategorija){
		double racun=0;
		if (kategorija==KategorijaOsiguranja.Prva){
			racun=300;
		}
		if (kategorija==KategorijaOsiguranja.Druga){
			racun=50;
		}
		if (kategorija==KategorijaOsiguranja.Treca){
			racun=0;
		}
		return racun;
	}
}
