package it.pokeronline.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	private String cognome;
	private String username;
	private String password;

	@Temporal(TemporalType.DATE)
	private Date dataRegistrazione;

	@Enumerated(EnumType.STRING)
	private StatoUser stato = StatoUser.CREATO;

	private Long esperienzaAccumulato;
	private Double creditoAccumulato;

	@ManyToMany
	@JoinTable(name = "user_ruolo", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "ruolo_id", referencedColumnName = "ID"))
	private Set<Ruolo> ruoli = new HashSet<>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "user", orphanRemoval = true)
	private Set<Tavolo> tavoli = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tavolo_id", nullable = false)
	private Tavolo tavolo;

	public User() {
	}

	public User(String nome, String cognome, String username, String password, Date dataRegistrazione,
			Long esperienzaAccumulato, Double creditoAccumulato) {
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.password = password;
		this.dataRegistrazione = dataRegistrazione;
		this.esperienzaAccumulato = esperienzaAccumulato;
		this.creditoAccumulato = creditoAccumulato;
	}

	public Set<Tavolo> getTavoliCreati() {
		return tavoli;
	}

	public void setTavoliCreati(Set<Tavolo> tavoliCreati) {
		this.tavoli = tavoliCreati;
	}

	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDataRegistrazione() {
		return dataRegistrazione;
	}

	public void setDataRegistrazione(Date dataRegistrazione) {
		this.dataRegistrazione = dataRegistrazione;
	}

	public StatoUser getStato() {
		return stato;
	}

	public void setStato(StatoUser stato) {
		this.stato = stato;
	}

	public Long getEsperienzaAccumulato() {
		return esperienzaAccumulato;
	}

	public void setEsperienzaAccumulato(Long esperienzaAccumulato) {
		this.esperienzaAccumulato = esperienzaAccumulato;
	}

	public Double getCreditoAccumulato() {
		return creditoAccumulato;
	}

	public void setCreditoAccumulato(Double creditoAccumulato) {
		this.creditoAccumulato = creditoAccumulato;
	}

	public Set<Ruolo> getRuoli() {
		return ruoli;
	}

	public void setRuoli(Set<Ruolo> ruoli) {
		this.ruoli = ruoli;
	}

}
