package com.GroupeC.LoncotoSpring.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.GroupeC.LoncotoSpring.metier.Article;
import com.GroupeC.LoncotoSpring.metier.Client;
import com.GroupeC.LoncotoSpring.repositories.ArticleRepository;
import com.GroupeC.LoncotoSpring.repositories.ClientRepository;


@Controller
@RequestMapping("/loncogroup-c/articles")

public class ArticleController {




	@Autowired
	private ArticleRepository articleRepository;
	@Autowired
	private ClientRepository clientrepo; 
	@RequestMapping( method=RequestMethod.GET, 
			produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)

	@ResponseBody
	@CrossOrigin(origins= {"http://localhost:4200"}, methods= {RequestMethod.GET})
	public Page<Article> findAll(@PageableDefault(page=0, size=5) Pageable pr){
		return articleRepository.findAll(pr);
	}
	

	@RequestMapping(value="/{lid:[0-9]+}", method=RequestMethod.GET,
			produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins= {"http://localhost:4200"}, methods= {RequestMethod.GET, RequestMethod.GET})
	public Article findArticleById(@PathVariable("lid") int lid) {
			return articleRepository.findOne(lid) ;
	}
	
	
	
	@RequestMapping(value="/save",method=RequestMethod.PUT,produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.PUT})
	
	public Article updateClient(@RequestBody Article i) {
		System.out.println(i);
		return articleRepository.save(i);
	
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST,produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.POST})
	
	public Article saveArticle(@RequestBody Article i) {
		return articleRepository.save(i);
	}
	
	
	@RequestMapping(value="/remove/{id:[0-9]+}",method=RequestMethod.DELETE,produces=org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	@CrossOrigin(origins = {"http://localhost:4200"}, methods = {RequestMethod.DELETE})
	public Map<String, String> removeIntervention(@PathVariable("id") int id) {
		articleRepository.delete(id);
		HashMap<String, String> result = new HashMap<>();
		result.put("article_deleted_id", "" + id);
		return result;
	}
}
