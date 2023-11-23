
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

 @NoArgsConstructor @AllArgsConstructor @ToString(exclude= {"famille"})
@Entity

public class Client {
	   @GeneratedValue(strategy=GenerationType.IDENTITY)
	    @Id private int id;
	    private String nom;
	    private String email;
	    private int nbsites;
	    
	    @ManyToMany							private Set<Site> sites;
	    @JsonIgnore  @OneToMany(mappedBy="client",
				 fetch=FetchType.EAGER) private Set<Materiel> lesmateriels;
	    
	    public Set<Materiel> getLesmateriels() {
			if ( lesmateriels == null) {
				 lesmateriels = new HashSet<>();
			}
			return  lesmateriels;
		}
	    
	    

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNom() {
			return nom;
		}

		public void setNom(String nom) {
			this.nom = nom;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public int getNbsites() {
			return nbsites;
		}

		public void setNbsites(int nbsites) {
			this.nbsites = nbsites;
		}
}
