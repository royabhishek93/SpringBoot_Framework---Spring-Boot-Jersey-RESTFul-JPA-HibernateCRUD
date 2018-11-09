package com.abhishek.app.config;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

import com.abhishek.app.endpoint_controller.ArticleEndpoint_Controller;
@Component
@ApplicationPath("/SpringWebservice")
public class JerseyConfig extends  ResourceConfig{
	
	public JerseyConfig() {
		register(ArticleEndpoint_Controller.class);
	}
}
