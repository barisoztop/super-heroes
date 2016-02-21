package com.baris.domain;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Hero {
	
	@Id
	@GeneratedValue
	@Column(name="HERO_ID")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="PSEUDONYM")
	private String pseudonym;
	
	@Column(name="PUBLISHER")
	private String publisher;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="HERO_ID")
	private Set<Power> powers;
	
	@Column(name="ALLY")
	private String ally;
	
	@Column(name="FIRST_APPEARANCE")
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date firstAppearance;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPseudonym() {
		return pseudonym;
	}

	public void setPseudonym(String pseudonym) {
		this.pseudonym = pseudonym;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public Set<Power> getPowers() {
		return powers;
	}

	public void setPowers(Set<Power> powers) {
		this.powers = powers;
	}
	
	public String getAlly() {
		return ally;
	}

	public void setAlly(String ally) {
		this.ally = ally;
	}

	public Date getFirstAppearance() {
		return firstAppearance;
	}

	public void setFirstAppearance(Date firstAppearance) {
		this.firstAppearance = firstAppearance;
	}

	@Override
	public String toString() {
		return "Hero [id=" + id + ", name=" + name + ", pseudonym=" + pseudonym + ", publisher=" + publisher
				+ ", powers=" + powers + ", ally=" + ally + ", firstAppearance=" + firstAppearance + "]";
	}

}
