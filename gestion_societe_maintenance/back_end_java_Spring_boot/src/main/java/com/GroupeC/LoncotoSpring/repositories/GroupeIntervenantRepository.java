package com.GroupeC.LoncotoSpring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.GroupeC.LoncotoSpring.metier.GroupeIntervenant;
import com.GroupeC.LoncotoSpring.metier.Intervention;

public interface GroupeIntervenantRepository extends PagingAndSortingRepository<GroupeIntervenant, Integer> {

	/*
	@Query("select i from GroupeIntervenant,Intervenant i where i.groupe_intervenant.intervenant.id = :idintervenant")
	public List<GroupeIntervenant> findByIntervenant_Id(@Param("idintervenant") int idintervenant);*/
}
