package com.GroupeC.LoncotoSpring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.GroupeC.LoncotoSpring.metier.Intervenant;
import com.GroupeC.LoncotoSpring.metier.Intervention;

public interface IntervenantRepository extends PagingAndSortingRepository<Intervenant, Integer> {
	
/*
	@Query("select i from Intervenant i where i.materiel.id = :idgroupe")
	public List<Intervenant> findByGroupe_Id(@Param("idgroupe") int idgroupe);

*/
}
