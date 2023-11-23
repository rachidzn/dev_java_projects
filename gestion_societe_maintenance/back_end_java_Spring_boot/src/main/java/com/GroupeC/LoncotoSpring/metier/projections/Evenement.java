package com.GroupeC.LoncotoSpring.metier.projections;

import java.util.Date;

import org.springframework.data.rest.core.config.Projection;

import com.GroupeC.LoncotoSpring.metier.Intervention;

@Projection(name="Evenement",types=Intervention.class)
public interface Evenement {
	int getId();
	String getTitle();
	Date getStart();
	Date getEnd();
	String getColor();

}
