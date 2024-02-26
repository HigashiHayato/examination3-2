package com.example.demo.steps;

import com.example.demo.Configuration;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.springframework.beans.factory.annotation.Autowired;

public class Hook {

  @Autowired
  Configuration configuration;

  @Before
  public void setRestAssured() {
    RestAssured.baseURI = configuration.getBaseUri();
  }

}
