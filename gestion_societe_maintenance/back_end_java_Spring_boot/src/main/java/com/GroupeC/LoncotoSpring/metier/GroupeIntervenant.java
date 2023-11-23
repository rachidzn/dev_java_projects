package com.GroupeC.LoncotoSpring.metier;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

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
public class GroupeIntervenant {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id				private int id;
					private String nom;
					@JsonIgnore @ManyToMany(mappedBy="groupeIntervenants")			private Set<Intervenant> intervenants;

public GroupeIntervenant(int id, String nom) {
	super();
	this.id = id;
	this.nom = nom;
}

}
