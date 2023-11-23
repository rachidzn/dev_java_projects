package com.GroupeC.LoncotoSpring.metier;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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

public class Catalogue {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
				@Id	private int id;

	@JsonIgnore @OneToMany(mappedBy="catalogue")      private Set <Article> articles;

public Catalogue(int id, int articleId) {
	super();
	this.id = id;

}

public Set<Article> getArticles() {
if ( articles == null) {
	articles = new HashSet<>();
}
return  articles;
}
}




