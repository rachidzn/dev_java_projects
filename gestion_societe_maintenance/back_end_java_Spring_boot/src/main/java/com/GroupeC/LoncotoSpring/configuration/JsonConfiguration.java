package com.GroupeC.LoncotoSpring.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

@Configuration
public class JsonConfiguration {

	// l'object Mapper est requis
		@Autowired(required=true)
		public void configJackson(ObjectMapper  jackson2ObjectMapper) {
			jackson2ObjectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		}
		
		// l'annotation Bean dénote une fonction a appeler pour récupérer un Bean spring
		// à injecter ailleur, c'est l'equivalent d'une balise <bean> dans le fichier
		// applicationContext
		// en l'occurence, ce bean permettra d'appliquer dans notre code/controleur une projection
		@Bean
		public SpelAwareProxyProjectionFactory projectionFactory() {
			return new SpelAwareProxyProjectionFactory();
		}
}
