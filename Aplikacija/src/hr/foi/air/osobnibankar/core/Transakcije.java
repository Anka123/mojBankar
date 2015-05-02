package hr.foi.air.osobnibankar.core;

import hr.foi.air.osobnibankar.database.Dugovanje;
import hr.foi.air.osobnibankar.database.Partner;
import hr.foi.air.osobnibankar.database.Potrazivanje;
import hr.foi.air.osobnibankar.database.Prihod;
import hr.foi.air.osobnibankar.database.Rashod;
import hr.foi.air.osobnibankar.database.Transakcija;
import hr.foi.air.osobnibankar.interfaces.ITransakcija;

import java.util.ArrayList;
import java.util.List;

import com.activeandroid.query.Select;

public class Transakcije implements ITransakcija {

	@Override
	public List<Transakcija> dohvatiTransakcije(int grupa, int mjesec) {
		int grupaTransakcija = grupa;
		int trenutniMjesec = mjesec;
		
		List<Transakcija> transakcija = new ArrayList<Transakcija>();
		switch(grupaTransakcija){
		case 1:
			
			List<Prihod> prihodi = new Select().all().from(Prihod.class).where("mjesec = ?", trenutniMjesec)
						.execute();
	
				for (int i = 0; i < prihodi.size(); i++) {
					long remote_id = i;
					Prihod prihod = new Select().from(Prihod.class)
							.where("remote_id = ?", remote_id).executeSingle();
					String naziv = prihod.getNaziv();
					String iznos = String.valueOf(prihod.getIznos());
					String kategorija = prihod.getKategorija();
					boolean zatvoreno = false;
					Partner partner_id = null;
	
					Transakcija trans = new Transakcija(remote_id, naziv, iznos,
							zatvoreno, kategorija, partner_id);
					transakcija.add(trans);
				}
				List<Rashod> rashodi = new Select().all().from(Rashod.class).where("mjesec = ?", trenutniMjesec)
							.execute();
	
				for (int j = 0; j < rashodi.size(); j++) {
					long r_id = j;
					Rashod rashod = new Select().from(Rashod.class)
										.where("remote_id = ?", r_id).executeSingle();
					String n = rashod.getNaziv();
					String iz = String.valueOf(rashod.getIznos());
					String k = rashod.getKategorija();
					boolean z = false;
					Partner p_id = null;
						
					Transakcija trans2 = new Transakcija(r_id, n, iz,
										z, k, p_id);
					transakcija.add(trans2);
						
				}
				return transakcija;
		case 0:

			List<Potrazivanje> potrazivanja = new Select().all().from(Potrazivanje.class).where("mjesec = ?", trenutniMjesec)
						.execute();
	
				for (int i = 0; i < potrazivanja.size(); i++) {
					long remote_id = i;
					Potrazivanje potrazivanje = new Select().from(Potrazivanje.class)
							.where("remote_id = ?", remote_id).executeSingle();
					String naziv = potrazivanje.getNaziv();
					String iznos = String.valueOf(potrazivanje.getIznos());
					String kategorija = potrazivanje.getKategorija();
					boolean zatvoreno = false;
					Partner partner_id = null;
	
					Transakcija t = new Transakcija(remote_id, naziv, iznos,
							zatvoreno, kategorija, partner_id);
					transakcija.add(t);
				}
				List<Dugovanje> dugovanja = new Select().all().from(Dugovanje.class).where("mjesec = ?", trenutniMjesec)
							.execute();
	
				for (int j = 0; j < dugovanja.size(); j++) {
					long r_id = j;
					Dugovanje dugovanje = new Select().from(Dugovanje.class)
										.where("remote_id = ?", r_id).executeSingle();
					String n = dugovanje.getNaziv();
					String iz = String.valueOf(dugovanje.getIznos());
					String k = dugovanje.getKategorija();
					boolean z = false;
					Partner p_id = null;
						
					Transakcija t2 = new Transakcija(r_id, n, iz,
										z, k, p_id);
					transakcija.add(t2);
						
				}
				return transakcija;
		default:
			break;
		}
		return transakcija;
	}

	@Override
	public List<Transakcija> dohvatiTipTransakcije(int t, int mjesec) {
		int tip = t;
		int trenutniMjesec = mjesec;
		
		List<Transakcija> transakcija = new ArrayList<Transakcija>();

		switch (tip) {
		case 0:
			List<Prihod> prihodi = new Select().all().from(Prihod.class).where("mjesec = ?", trenutniMjesec)
					.execute();

			for (int i = 0; i < prihodi.size(); i++) {
				long remote_id = i;
				Prihod prihod = new Select().from(Prihod.class)
						.where("remote_id = ?", remote_id).executeSingle();
				String naziv = prihod.getNaziv();
				String iznos = String.valueOf(prihod.getIznos());
				String kategorija = "prihod";
				boolean zatvoreno = false;
				Partner partner_id = null;

				Transakcija trans = new Transakcija(remote_id, naziv, iznos,
						zatvoreno, kategorija, partner_id);
				transakcija.add(trans);

			}

			break;

		case 1:
			List<Rashod> rashodi = new Select().all().from(Rashod.class).where("mjesec = ?", trenutniMjesec)
			.execute();

			for (int i = 0; i < rashodi.size(); i++) {
				long remote_id = i;
				Rashod rashod = new Select().from(Rashod.class)
						.where("remote_id = ?", remote_id).executeSingle();
				String naziv = rashod.getNaziv();
				String iznos = String.valueOf(rashod.getIznos());
				String kategorija = "rashod";
				boolean zatvoreno = false;
				Partner partner_id = null;
		
				Transakcija trans = new Transakcija(remote_id, naziv, iznos,
						zatvoreno, kategorija, partner_id);
				transakcija.add(trans);
		
			}

			break;

		case 2:
			List<Potrazivanje> potrazivanja = new Select().all().from(Potrazivanje.class).where("mjesec = ?", trenutniMjesec)
			.execute();

			for (int i = 0; i < potrazivanja.size(); i++) {
				long remote_id = i;
				Potrazivanje potrazivanje = new Select().from(Potrazivanje.class)
						.where("remote_id = ?", remote_id).executeSingle();
				String naziv = potrazivanje.getNaziv();
				String iznos = String.valueOf(potrazivanje.getIznos());
				String kategorija = "potrazivanja";
				boolean zatvoreno = false;
				Partner partner_id = null;
		
				Transakcija trans = new Transakcija(remote_id, naziv, iznos,
						zatvoreno, kategorija, partner_id);
				transakcija.add(trans);
		
			}
			
			break;

		case 3:
			List<Dugovanje> dugovanja = new Select().all().from(Dugovanje.class).where("mjesec = ?", trenutniMjesec)
			.execute();

			for (int i = 0; i < dugovanja.size(); i++) {
				long remote_id = i;
				Dugovanje dugovanje = new Select().from(Dugovanje.class)
						.where("remote_id = ?", remote_id).executeSingle();
				String naziv = dugovanje.getNaziv();
				String iznos = String.valueOf(dugovanje.getIznos());
				String kategorija = "dugovanja";
				boolean zatvoreno = false;
				Partner partner_id = null;
		
				Transakcija trans = new Transakcija(remote_id, naziv, iznos,
						zatvoreno, kategorija, partner_id);
				transakcija.add(trans);
		
			}

			break;

		}
		return transakcija;

	}
}