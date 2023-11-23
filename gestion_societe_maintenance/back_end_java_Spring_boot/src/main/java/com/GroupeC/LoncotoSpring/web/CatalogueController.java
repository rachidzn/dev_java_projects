package com.GroupeC.LoncotoSpring.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.GroupeC.LoncotoSpring.metier.Catalogue;
import com.GroupeC.LoncotoSpring.repositories.CatalogueRepository;

public class CatalogueController {

	@Controller
	@RequestMapping("/loncogroup-c/catalogues")
	
	public class catalogueController {
		
		@Autowired
		
		private CatalogueRepository catalogueRepository;
		
		@RequestMapping(method=RequestMethod.GET,
				produces=MediaType.APPLICATION_JSON_UTF8_VALUE
				)
		@ResponseBody
		@CrossOrigin(origins= {"http://localhost:4200"}, methods= {RequestMethod.GET, RequestMethod.OPTIONS}) 
		public Page<Catalogue> findAll(@PageableDefault(page=0, size=5) Pageable pr) {
		
			return catalogueRepository.findAll(pr);
		}
	}
}
