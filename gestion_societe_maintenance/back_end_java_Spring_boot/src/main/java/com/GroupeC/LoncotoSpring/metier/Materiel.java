package com.GroupeC.LoncotoSpring.metier;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString(exclude= {"etudiants"})
@Entity
public class Materiel {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id private int id;
	private String SerialID;
	private String etat;
	@ManyToOne	private Article article;
	@ManyToOne	private Site lesite;
	@ManyToOne	private Client client;
	@JsonIgnore @OneToMany(mappedBy="materiel",
			 fetch=FetchType.EAGER) private Set<Intervention> lesinterventions;
	
	
	
	public Set<Intervention> getLesinterventions() {
		if ( lesinterventions == null) {
			lesinterventions = new HashSet<>();
		}
		return  lesinterventions;
	}



	
	
	
}
