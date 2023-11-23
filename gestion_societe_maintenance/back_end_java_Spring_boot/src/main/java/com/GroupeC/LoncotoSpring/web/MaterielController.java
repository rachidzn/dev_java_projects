package com.GroupeC.LoncotoSpring.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.GroupeC.LoncotoSpring.metier.Intervention;
import com.GroupeC.LoncotoSpring.metier.Materiel;
import com.GroupeC.LoncotoSpring.repositories.MaterielRepository;;

@Controller
@RequestMapping("/loncogroup-c/materiels")

public class MaterielController {






	@Autowired
	private MaterielRepository materielRepository;

	@RequestMapping(value = "/clients/{id:[0-9]+}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.GET})
	public List<Materiel> findMaterielByClient(@PathVariable("id") int id){
		return materielRepository.findByClient_Id(id);
	}
	
	
	
	@RequestMapping(value = "/sites/{id:[0-9]+}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.GET})
	public List<Materiel> findMaterielBySite(@PathVariable("id") int id){
		return materielRepository.findBySite_Id(id);
	}
	
	
	@RequestMapping( method=RequestMethod.GET, 
			produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)

	@ResponseBody
	@CrossOrigin(origins= {"http://localhost:4200"}, methods= {RequestMethod.GET})
	public Page<Materiel> findAll(@PageableDefault(page=0, size=5) Pageable pr){
		return materielRepository.findAll(pr);
	}
	
	
	@RequestMapping(value="/{lid:[0-9]+}", method=RequestMethod.GET,
			produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins= {"http://localhost:4200"}, methods= {RequestMethod.GET, RequestMethod.GET})
	public Materiel findclientById(@PathVariable("lid") int lid) {
			return materielRepository.findOne(lid) ;
	}
	
	
	
	@RequestMapping(value="/save",method=RequestMethod.PUT,produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.PUT})
	
	public Materiel updateClient(@RequestBody Materiel i) {
		System.out.println(i);
		return materielRepository.save(i);
	
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST,produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.POST})
	
	public Materiel saveIntervention(@RequestBody Materiel i) {
		return materielRepository.save(i);
	}
	
	
	@RequestMapping(value="/remove/{id:[0-9]+}",method=RequestMethod.DELETE,produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.DELETE})
	public Map<String, String> removeIntervention(@PathVariable("id") int id) {
		materielRepository.delete(id);
		HashMap<String, String> result = new HashMap<>();
		result.put("materiel_deleted_id", "" + id);
		return result;
	}
	
	
}
