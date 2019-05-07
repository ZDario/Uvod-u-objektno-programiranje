package funkcije;

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
import domZdravlja.DomZdravlja;
import domZdravlja.SluzbeDomaZdravlja;
import domZdravlja.Soba;
import pregledi.Pregledi;
import zdravstvenaKnjizica.KategorijaOsiguranja;
import zdravstvenaKnjizica.ZdravstvenaKnjizica;


public class GUI {
	
	private ArrayList<Lekar> lekari;
	private ArrayList<MedicinskaSestra> sestre;
	private ArrayList<Pacijent> pacijenti;
	private ArrayList<ZdravstvenaKnjizica> knjizice;
	private ArrayList<Pregledi> pregledi;
	private ArrayList<Soba> sobe;
	
	public GUI() {
		this.lekari = new ArrayList<Lekar>();
		this.sestre = new ArrayList<MedicinskaSestra>();
		this.pacijenti = new ArrayList<Pacijent>();
		this.knjizice = new ArrayList<ZdravstvenaKnjizica>();
		this.pregledi = new ArrayList<Pregledi>();
		this.sobe = new ArrayList<Soba>();
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
	public void ucitajSestre(String imeFajla) {
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
				String pol = split[7];
				String uloga = split[8];
				Lekar izabraniLekar = nadjiLekara(split[9]);
				String knjizica = split[10];
				Pacijent pacijent = new Pacijent(ime, prezime, jmbg, adresa, telefon, korisnickoime,lozinka, 
						pol, uloga, izabraniLekar,knjizica);
				pacijenti.add(pacijent);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Lekar nadjiLekara(String korisnickoime) {
		for (Lekar lekar : lekari) {
			if (lekar.getKorisnickoime().equals(korisnickoime)) {
				return lekar;
			}
		}
		return null;
	}
	
	
	
	public void ucitajZdravstveneKnjizice(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String id = split[0];
				String string = split[1];
				DateFormat format = new SimpleDateFormat("dd-MM-yyyy");
				Date datumIsteka = format.parse(string);
				KategorijaOsiguranja kategorijaosiguranja = KategorijaOsiguranja.valueOf(split[2]);
				ZdravstvenaKnjizica knjizica = new ZdravstvenaKnjizica(id,datumIsteka,kategorijaosiguranja);
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
				String zatrazenDatumString = split[0];
				double zatrazenDatum  = Double.parseDouble(zatrazenDatumString);
				String pocetakDatumString = split[1];
				double pocetakDatum = Double.parseDouble(pocetakDatumString);
				String krajDatumString = split[2];
				double krajDatum = Double.parseDouble(krajDatumString);
				String opis= split[3];
				Pacijent pacijent = split[4];
				MedicinskaSestra sestra = split[5];
				Lekar lekar = split[6];
				Soba soba = split[7];
				String status = split[8];
				Pregledi pregled = new Pregledi(zatrazenDatum,pocetakDatum,
						krajDatum,opis,pacijent,sestra,lekar,soba,status);
				pregledi.add(pregled);
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void ucitajSobe(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line = null;
			while ((line = reader.readLine()) != null) {
				String[] split = line.split("\\|");
				String id = split[0];
				String naziv = split[1];
				String naziv1 = split[2];
				ArrayList<Soba> sobe = new ArrayList<Soba>();
				Soba soba = new Soba(id,naziv,naziv1,sobe);
				sobe.add(soba);
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
						+ lekar.getDomzdravlja() + "|" + lekar.getSpecijalizacija() + "\n";
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
						+ sestra.getPlata() + "|" + sestra.getSluzba() + "|" 
						+ sestra.getDomzdravlja() + "\n";
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
						+ pacijent.getLozinka() + "|" + pacijent.getPol() + "|" + pacijent.getUloga() + "|"
						+ pacijent.getIzabraniLekar() + "|" + pacijent.getKnjizica() + "\n";
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
				content += knjizica.getId() + "|" + knjizica.getDatumIsteka() + "|"
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
				content += pregled.getZatrazenDatum() + "|" + pregled.getPocetakDatum() + "|"
						+ pregled.getKrajDatum() + "|" + pregled.getOpis() + "|"
						+ pregled.getPacijent() + "|" + pregled.getSestra() + "|"
						+ pregled.getLekar() + "|" + pregled.getSoba() + "|" 
						+ pregled.getStatus() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja pregleda.");
		}
	}
	
	public void snimiSobe(String imeFajla) {
		try {
			File file = new File("src/fajlovi/" + imeFajla);
			String content = "";
			for (Soba soba : sobe) {
				content += soba.getId() + "|" + soba.getNaziv() + "|"
						+ soba.getDomzdravlja() + "\n";
			}
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(content);
			writer.close();
		} catch (IOException e) {
			System.out.println("Greska prilikom snimanja zdravstvene knjizice");
		}
	}	
}
