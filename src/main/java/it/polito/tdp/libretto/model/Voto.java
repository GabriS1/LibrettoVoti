package it.polito.tdp.libretto.model;

import java.time.LocalDate;

/**
 * ClasseVoto, contiene le informazioni su un esame superato
 * 
 * @author gabri
 *
 */
public class Voto implements Comparable<Voto> {
	
	private String corso ; // "Tecniche di Programmazione"
	private int voto ; // 28
	private LocalDate data ; // 15/06/2020
	
	/**
	 * Costruisce un nuovo Voto.
	 * 
	 * @param corso nome del corso superato
	 * @param voto valore del voto acquisito
	 * @param data data di superamento dell'esame
	 */
	public Voto(String corso, int voto, LocalDate data) {  //COSTRUTTORE, riceve tre parametri e inizializza
		super();
		this.corso = corso;
		this.voto = voto;
		this.data = data;
	}

	public String getCorso() {
		return corso;
	}

	public void setCorso(String corso) {
		this.corso = corso;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return corso + ": " + voto + " (" + data + ")";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corso == null) ? 0 : corso.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Voto other = (Voto) obj;
		if (corso == null) {
			if (other.corso != null)
				return false;
		} else if (!corso.equals(other.corso))
			return false;
		return true;
	}
	
	/**
	 * Copy constructor di {@link Voto}: crea un nuovo {@link Voto}, copiando il 
	 * contenuto del parametro {@code v}
	 * @param v
	 */
	public Voto(Voto v) {
		this.corso=v.corso;  //v.getCorso;
		this.data=v.data;
		this.voto=v.voto;
	}
	
	public Voto clone() {
		Voto v = new Voto(this.corso, this.voto, this.data);
		return v;
	}

	@Override
	public int compareTo(Voto other) {
		/*<0 se this<other
		 * =0 se this=other
		 * >0 se this>other
		 */
		return this.corso.compareTo(other.corso);  //siccome corso è un tipo String, possiamo utilizzare direttamente il metodo compareTo implementato da queat classe
	}
	

}
