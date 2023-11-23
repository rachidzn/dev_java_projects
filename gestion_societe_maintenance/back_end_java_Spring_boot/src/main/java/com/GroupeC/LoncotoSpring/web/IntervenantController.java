package com.GroupeC.LoncotoSpring.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

import com.GroupeC.LoncotoSpring.metier.GroupeIntervenant;
import com.GroupeC.LoncotoSpring.metier.Intervenant;
import com.GroupeC.LoncotoSpring.repositories.IntervenantRepository;




@Controller
@RequestMapping("/loncogroup-c/intervenants")

public class IntervenantController {
	
	@Autowired
	
	private IntervenantRepository intervenantRepository;
	
	@RequestMapping(method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_UTF8_VALUE
			)
	@ResponseBody
	@CrossOrigin(origins= {"http://localhost:4200"}, methods= {RequestMethod.GET}) 
	public Page<Intervenant> findAll(@PageableDefault(page=0, size=5) Pageable pr) {
	
		return intervenantRepository.findAll(pr);
	}
	
	
	@RequestMapping(value="/{lid:[0-9]+}", method=RequestMethod.GET,
			produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins= {"http://localhost:4200"}, methods= {RequestMethod.GET, RequestMethod.GET})
	public Intervenant findclientById(@PathVariable("lid") int lid) {
			return intervenantRepository.findOne(lid) ;
	}
	
	
	
	@RequestMapping(value="/save",method=RequestMethod.PUT,produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.PUT})
	
	public Intervenant updateClient(@RequestBody Intervenant i) {
		System.out.println(i);
		return intervenantRepository.save(i);
	
	}
		
		@RequestMapping(value="/save",method=RequestMethod.POST,produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.POST})
		
		public Intervenant saveIntervention(@RequestBody Intervenant i) {
			return intervenantRepository.save(i);
		}
		
		
		@RequestMapping(value="/remove/{id:[0-9]+}",method=RequestMethod.DELETE,produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.DELETE})
		public Map<String, String> removeIntervention(@PathVariable("id") int id) {
			intervenantRepository.delete(id);
			HashMap<String, String> result = new HashMap<>();
			result.put("intervnant_deleted_id", "" + id);
			return result;
		}
		
		
		
		
		
		
	}


