package com.GroupeC.LoncotoSpring.metier;

import java.time.LocalDate;
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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @ToString(exclude= {"famille"})
@Entity
public class Site {
	   @GeneratedValue(strategy=GenerationType.IDENTITY)
	    @Id private int id;
	    private String nom;
	    private String adresse;
	    @JsonIgnore  @ManyToMany(mappedBy="sites")			private Set<Client> clients;
	    
	    @JsonIgnore @OneToMany(mappedBy="lesite"
			) private Set<Materiel> lesmateriels;
	    
	    public Set<Materiel> getLesmateriels() {
			if ( lesmateriels == null) {
				 lesmateriels = new HashSet<>();
			}
			return  lesmateriels;
		}
}
