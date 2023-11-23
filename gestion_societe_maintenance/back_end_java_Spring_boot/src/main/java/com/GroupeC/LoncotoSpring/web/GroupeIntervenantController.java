package com.GroupeC.LoncotoSpring.web;

import java.util.HashMap;
import java.util.List;
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

import com.GroupeC.LoncotoSpring.metier.Article;
import com.GroupeC.LoncotoSpring.metier.GroupeIntervenant;
import com.GroupeC.LoncotoSpring.metier.Intervention;
import com.GroupeC.LoncotoSpring.repositories.GroupeIntervenantRepository;


@Controller
@RequestMapping("/loncogroup-c/groupeintervenants")

public class GroupeIntervenantController {
	
		@Autowired
		
		private GroupeIntervenantRepository groupeIntervenantRepository;
		
		
		
		
		/*
		@RequestMapping(value = "/intervenant/{id:[0-9]+}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.GET})
		public List<GroupeIntervenant> findMaterielByClient(@PathVariable("id") int id){
			return groupeIntervenantRepository.findByIntervenant_Id(id);
		}
		
		*/
		
		
		
		@RequestMapping(method=RequestMethod.GET,
				produces=MediaType.APPLICATION_JSON_UTF8_VALUE
				)
		@ResponseBody
		@CrossOrigin(origins= {"http://localhost:4200"}, methods= {RequestMethod.GET, RequestMethod.OPTIONS}) 
		public Page<GroupeIntervenant> findAll(@PageableDefault(page=0, size=5) Pageable pr) {
		
			return groupeIntervenantRepository.findAll(pr);
		}
		
		
		
		@RequestMapping(value="/{lid:[0-9]+}", method=RequestMethod.GET,
				produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		@CrossOrigin(origins= {"http://localhost:4200"}, methods= {RequestMethod.GET, RequestMethod.GET})
		public GroupeIntervenant findclientById(@PathVariable("lid") int lid) {
				return groupeIntervenantRepository.findOne(lid) ;
		}
		
		
		
		@RequestMapping(value="/save",method=RequestMethod.PUT,produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.PUT})
		
		public GroupeIntervenant updateClient(@RequestBody GroupeIntervenant i) {
			System.out.println(i);
			return groupeIntervenantRepository.save(i);
		
		}
		
		@RequestMapping(value="/save",method=RequestMethod.POST,produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.POST})
		
		public GroupeIntervenant saveIntervention(@RequestBody GroupeIntervenant i) {
			return groupeIntervenantRepository.save(i);
		}
		
		
		@RequestMapping(value="/remove/{id:[0-9]+}",method=RequestMethod.DELETE,produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
		@ResponseBody
		@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.DELETE})
		public Map<String, String> removeIntervention(@PathVariable("id") int id) {
			groupeIntervenantRepository.delete(id);
			HashMap<String, String> result = new HashMap<>();
			result.put("grpintervnant_deleted_id", "" + id);
			return result;
		}
		
		
	
}
