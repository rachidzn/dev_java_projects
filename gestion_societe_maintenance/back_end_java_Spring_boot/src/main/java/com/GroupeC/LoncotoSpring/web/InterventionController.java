package com.GroupeC.LoncotoSpring.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.GroupeC.LoncotoSpring.metier.Intervenant;
import com.GroupeC.LoncotoSpring.metier.Intervention;
import com.GroupeC.LoncotoSpring.metier.Materiel;
import com.GroupeC.LoncotoSpring.metier.projections.Evenement;
import com.GroupeC.LoncotoSpring.repositories.InterventionRepository;


@Controller
@RequestMapping("/loncogroup-c/interventions")

public class InterventionController {

	@Autowired
	
	private InterventionRepository interventionRepository;
	
	private final ProjectionFactory projectionFactory;
	
	@Autowired
	public InterventionController(ProjectionFactory projectionFactory) {
		this.projectionFactory = projectionFactory;
	}
	
	

	@RequestMapping(value = "/client/{id:[0-9]+}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.GET})
	public List<Intervention> findInterventionByClient(@PathVariable("id") int id){
		return interventionRepository.findByClient_Id(id);
	}
	
	
	
	
	@RequestMapping(value = "/intervenant/{id:[0-9]+}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.GET})
	public List<Intervention> findInterventionByIntervenant(@PathVariable("id") int id){
		return interventionRepository.findByIntervenant_Id(id);
	}
	
	
	@RequestMapping(value = "/materiels/{id:[0-9]+}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.GET})
	public List<Intervention> findInterventionByMateriel(@PathVariable("id") int id){
		return interventionRepository.findByMateriel_Id(id);
	}
	
	
	
	@RequestMapping(method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE
			)
	@ResponseBody
	@CrossOrigin(origins= {"http://localhost:4200"}, methods= {RequestMethod.GET, RequestMethod.OPTIONS}) 
	public Page<Intervention> findAll(@PageableDefault(page=0, size=10) Pageable pr) {
	
		return interventionRepository.findAll(pr);
	}
	
	
	@RequestMapping(value="/list", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE
			)
	@ResponseBody
	@CrossOrigin(origins= {"http://localhost:4200"}, methods= {RequestMethod.GET, RequestMethod.OPTIONS}) 
	public List<Intervention> findAlljson() {
	
		return interventionRepository.findAll();
	}
	
	
	@RequestMapping(value="/{lid:[0-9]+}", method=RequestMethod.GET,
			produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins= {"http://localhost:4200"}, methods= {RequestMethod.GET, RequestMethod.GET})
	public Intervention findclientById(@PathVariable("lid") int lid) {
			return interventionRepository.findOne(lid) ;
	}
	
	
	
	@RequestMapping(value="/save",method=RequestMethod.PUT,produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.PUT})
	
	public Intervention updateClient(@RequestBody Intervention i) {
		System.out.println(i);
		return interventionRepository.save(i);
	
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST,produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.POST})
	
	public Intervention saveIntervention(@RequestBody Intervention i) {
		return interventionRepository.save(i);
	}
	
	
	@RequestMapping(value="/remove/{id:[0-9]+}",method=RequestMethod.DELETE,produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.DELETE})
	public Map<String, String> removeIntervention(@PathVariable("id") int id) {
		interventionRepository.delete(id);
		HashMap<String, String> result = new HashMap<>();
		result.put("intervention_deleted_id", "" + id);
		return result;
	}
	
	
	
	
	
	@RequestMapping(value="/evenement",method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE
			)
	@ResponseBody
	
	@CrossOrigin(origins= {"http://localhost:4200"}, methods= {RequestMethod.GET, RequestMethod.OPTIONS}) 
	public List<Evenement> findAllEvenement(@PageableDefault(page=0, size=1000) Pageable pr) {
	
		Page<Intervention> page =  interventionRepository.findAll(pr);
		List<Evenement> liste = page.getContent().stream().map(
				art -> projectionFactory.createProjection(Evenement.class, art))
				.collect(Collectors.toList());

				
				return liste;
	}
	
	

	@RequestMapping(value="/evenement/intervenant/{id:[0-9]+}",method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE
			)
	@ResponseBody
	
	@CrossOrigin(origins= {"http://localhost:4200"}, methods= {RequestMethod.GET, RequestMethod.OPTIONS}) 
	public List<Evenement> findAllEvenementBy_Intervenant(@PageableDefault(page=0, size=1000) Pageable pr,@PathVariable("id") int id) {
	
		
		List<Evenement> liste = interventionRepository.findByIntervenant_Id(id).stream().map(
				art -> projectionFactory.createProjection(Evenement.class, art))
				.collect(Collectors.toList());

				
				return liste;
	}
	
	
	
	
}

