package com.GroupeC.LoncotoSpring.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity

public class Intervenant {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id				private int id;
					private String nom;
					private String prenom;
					private String email;

					@JsonIgnore @OneToMany(mappedBy="intervenant")		private Set<Intervention> lesinterventions;


@ManyToMany 					private Set<GroupeIntervenant> groupeIntervenants;

public Intervenant(int id, String nom, String prenom, String email) {
	super();
	this.id = id;
	this.nom = nom;
	this.prenom = prenom;
	this.email = email;
}



	
public Set<Intervention> getLesinterventions() {
	if ( lesinterventions == null) {
	lesinterventions = new HashSet<>();
	}
	return  lesinterventions;
	}

}


