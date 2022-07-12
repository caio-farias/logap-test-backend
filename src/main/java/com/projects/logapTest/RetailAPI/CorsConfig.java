package com.projects.logapTest.RetailAPI;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {

    registry.addMapping("/**").allowedMethods(
      "POST",
      "PATCH",
      "PUT",
      "GET",
      "DELETE",
      "OPTIONS"
    );

      /*.exposedHeaders("X-Total-Count",
                      "Location",
                      "Access-Control-Allow-Origin"
      )
      .allowCredentials(false)
      .maxAge(6000);*/
  }

}