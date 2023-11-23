package com.yaps.petstore.web;

import static org.junit.Assert.fail;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


/**
 * This class tests the HTML Pages and controllers
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class WebTestMockMvc {
    
    @Autowired
    private MockMvc mockMvc;

    /**
     * Checks that all pages are deployed
     */
    @Test
    public void testWebCheckPages() {
        try {
        	this.mockMvc.perform(get("/dummy.html")).andExpect(status().is3xxRedirection());
        } catch (Exception e) {
        }

        try {
        	this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
        		.andExpect(content().string(containsString("The YAPS Pet Store Demo for Spring Boot is a fictional sample application")));
        } catch (Exception e) {
            fail("Root / hasn't been found");
        }

        try {
        	this.mockMvc.perform(get("/createcustomer.html")).andExpect(status().is3xxRedirection());
        } catch (Exception e) {
        }

   }

    /**
     * Checks that all servlets are deployed
     */
    @Test
    public void testWebCheckServlets() {

        try {
        	this.mockMvc.perform(get("/new-account")).andDo(print()).andExpect(status().isOk())
        		.andExpect(content().string(containsString("Nouveau compte")));
        } catch (Exception e) {
            fail("The new-account Controller hasn't been found");
        }

        try {
        	this.mockMvc.perform(get("/findItem?itemId=EST25")).andDo(print()).andExpect(status().isOk())
        		.andExpect(content().string(containsString("Amazon Parrot sélectionné")));
        } catch (Exception e) {
            fail("The FindItem Controller hasn't been found");
        }

        try {
        	this.mockMvc.perform(get("/findItems?productId=AVCB01")).andDo(print()).andExpect(status().isOk())
        		.andExpect(content().string(containsString("Liste des articles appartenant au produit Amazon Parrot")));
        } catch (Exception e) {
            fail("The FindItems Controller hasn't been found");
        }

        try {
        	this.mockMvc.perform(get("/findProducts?categoryId=BIRDS")).andDo(print()).andExpect(status().isOk())
        		.andExpect(content().string(containsString("Liste de produits appartenant à la catégorie BIRDS")));
        } catch (Exception e) {
            fail("The FindProducts Controller hasn't been found");
        }
    }
}
