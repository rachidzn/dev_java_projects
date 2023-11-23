package com.GroupeC.LoncotoSpring.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.GroupeC.LoncotoSpring.metier.Intervention;
import com.GroupeC.LoncotoSpring.metier.Materiel;

public interface InterventionRepository extends PagingAndSortingRepository<Intervention, Integer> {

	
	public List<Intervention> findAll();
	
	@Query("select i from Intervention i where i.intervenant.id = :idintervenant")
	public List<Intervention> findByIntervenant_Id(@Param("idintervenant") int idintervenant);
	
	

	
	@Query("select i from Intervention i where i.materiel.id = :idmateriel")
	public List<Intervention> findByMateriel_Id(@Param("idmateriel") int idmateriel);
	
	@Query("select i from Intervention i where i.materiel.client.id = :idclient")
    public List<Intervention> findByClient_Id(@Param("idclient") int idclient);
	
}
