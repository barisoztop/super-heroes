package com.baris;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.models.dto.builder.ApiInfoBuilder;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Configuration
@EnableSwagger
public class SwaggerConfig {

	@Autowired
	private SpringSwaggerConfig springSwaggerConfig;
	
	@Bean
	public SwaggerSpringMvcPlugin configureSwaggerV1() {
		SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new SwaggerSpringMvcPlugin(this.springSwaggerConfig);
		
		swaggerSpringMvcPlugin
					.apiInfo(getAppInfo())
					.apiVersion("1.0")
					.includePatterns("/v1/*.*")
					.swaggerGroup("v1");
		
		swaggerSpringMvcPlugin.useDefaultResponseMessages(false);
		
	    return swaggerSpringMvcPlugin;
	}
	
	@Bean
	public SwaggerSpringMvcPlugin configureSwaggerV2() {
		SwaggerSpringMvcPlugin swaggerSpringMvcPlugin = new SwaggerSpringMvcPlugin(this.springSwaggerConfig);
		
		swaggerSpringMvcPlugin
					.apiInfo(getAppInfo())
					.apiVersion("2.0")
					.includePatterns("/v2/*.*")
					.swaggerGroup("v2");
		
		swaggerSpringMvcPlugin.useDefaultResponseMessages(false);
		
	    return swaggerSpringMvcPlugin;
	}

	private ApiInfo getAppInfo() {
		ApiInfo apiInfo = new ApiInfoBuilder()
							        .title("Super Heroes REST API")
							        .description("Super Heroes Api for creating and managing heroes")
							        .build();
		return apiInfo;
	}

}
