package it.polito.tdp.libretto.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Memorizza e gestisce un insieme di voti superati.
 * 
 * @author gabri
 *
 */
public class Libretto {

	private List<Voto> voti = new ArrayList<>(); // voti è una variabile che punta ad un oggetto di tipo list; quando
													// faccio NEW inizializzo il mio puntatore voti, facendolo puntare
													// ad un arrayList vuota
	
	
	public Libretto() { //costruttore vero e proprio, da mettere se metto anche il costruttore dopo
		super();
	}
	
	
	/**
	 * Copy Constructor
	 * "Shallow"-->fa una copia dell'oggetto corrente(lib), ma non fa delle copie degli oggetti contenuti in esso
	 * Gli oggetti Voto vengono condivisi tra l'array precedente e questa nuovo--> fanno riferimento agli stessi oggetti fisici
	 * Non duplico gli oggetti Voto
	 * @param lib
	 */
	public Libretto(Libretto lib) { //costruttore 
		super();
		this.voti.addAll(lib.voti);
		
	}
	
	
	/**
	 * Aggiunge un nuovo voto al libre tto
	 * 
	 * @param v Voto da aggiungere
	 * @return {@code true} se il voto non è in conflitto e non è duplicato, {@code false} altrimenti
	 */
	public boolean add(Voto v) {
		
		if(this.isConflitto(v) || this.isDuplicato(v)) {
			return false;  //segnalo chiamante che non ha avuto successo
		}else {
			this.voti.add(v);
			return true;
		}
		
	}

	/**
	 * Dato un Libretto, restituisce una stringa nella quale vi sono solamente i
	 * voti pari al valore specificato
	 * 
	 * @param voto valore specificato
	 * @return stringa formattata per visualizzare il sotto-libretto
	 */
	public String stampaVotiUguali(int voto) {
		String s = "";
		for (Voto v : this.voti) {
			if (v.getVoto() == voto) {
				s += v.toString() + "\n";
			}
		}
		return s;
	}

	/**
	 * Genera un nuovo libretto, a partire da quello esistente, che conterrà
	 * esclusivamenti i voti con votazione pari a quella specificata.
	 * 
	 * @param voto votazione specificata
	 * @return nuovo Libretto "ridotto"
	 */
	public Libretto estraiVotiUguali(int voto) {
		Libretto nuovo = new Libretto();
		for (Voto v : this.voti) {
			if (v.getVoto() == voto) {
				nuovo.add(v);
			}
		}
		return nuovo;
	}

	public String toString() {
		String s = "";
		for (Voto v : this.voti) {
			s += v.toString() + "\n";
		}
		return s;
	}

	/**
	 * Dato il nome del corso ricera se il nome del corso esiste nel libretto, in
	 * caso affermativo ritorna il {@link Voto} corrispondente, in caso negativo
	 * ritorna {@code null}
	 * 
	 * @param nomeCorso nome esame da cercare
	 * @return il {@link Voto} corrispondente, oppure {@code null} se non esiste
	 */
	public Voto cercaNomeCorso(String nomeCorso) {
		/*
		 * for(Voto v: this.voti) { if(nomeCorso.equals(v.getCorso())) { return v; } }
		 * return null;
		 */

		int pos = this.voti.indexOf(new Voto(nomeCorso, 0, null));
		if (pos == -1) {

			return null;

		} else {
			return voti.get(pos);
		}
	}

	/**
	 * Ritorna {@code true} se il corso specificato da {@code v} esiste nel
	 * libretto, con stessa valutazione. Se non esiste o se la valutazione è diversa
	 * ritorna {@code false}
	 * 
	 * @param v il {@link Voto} da ricercare
	 * @return l'esistenza del duplicato
	 */
	public boolean isDuplicato(Voto v) {
		Voto esiste = this.cercaNomeCorso(v.getCorso());
		if (esiste == null) { // non l'ho trovato--> non è duplicato
			return false;
		}
		/*if (esiste.getVoto() == v.getVoto()) {
			return true;
		} else {
			return false;
		}*/
		return (esiste.getVoto() == v.getVoto());

	}

	/**
	 * Detremina se esiste un voto con lo stesso nome corso ma valutazione diverse
	 * 
	 * @param v
	 * @return
	 */
	public boolean isConflitto(Voto v) {
		Voto esiste = this.cercaNomeCorso(v.getCorso());
		if (esiste == null) { // non l'ho trovato--> non è duplicato
			return false;
		}
		return (esiste.getVoto() != v.getVoto());
	}
	
	public Libretto creaLibrettoMigliorato() {
		Libretto nuovo = new Libretto();
		for(Voto v: this.voti) {
			Voto v2 =  v.clone(); 
			//Voto v2 =new Voto(v);
			if(v2.getVoto()>=24) {
				v2.setVoto(v2.getVoto()+2);
				if(v2.getVoto()>30) {
					v2.setVoto(30);
				}
			}else {
					if(v2.getVoto()>=18) {
						v2.setVoto(v2.getVoto()+1);
					}
				}
			nuovo.add(v2);
			
		}
		
		return nuovo;
	}
	
	
	/**
	 * ordina voti presneti nel libretto corrente alfebiticamente per corso
	 */
	public void ordinaPerCorso() {
		Collections.sort(this.voti);  //ordina la lista in base all'ordine NATURALE degli elementi, implementata in compareTo in Voto
	}
	
	
	public void ordinaPerVoto() {
		Collections.sort(this.voti, new ConfrontaVotiPerValutazione());
	}
	
	public void cancellaVotiScarsi() {
		List<Voto> daRimuovere= new ArrayList<>();
		for(Voto v: this.voti) {  
			if(v.getVoto()<24) {
				daRimuovere.add(v);
			}
		}
		
		for(Voto v: daRimuovere) {
			this.voti.remove(v);
		}
	}

}
