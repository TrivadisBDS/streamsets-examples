package com.trivadis.restapi;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CustomerApi {

    @JsonProperty(value = "customerId", required = true)
    public Integer customerId;
    
    @JsonProperty(value = "name", required = true)
    public String name; 
    
    @JsonProperty(value = "country", required = false)
    public String country; 
 
    @JsonProperty(value = "modifiedAt", required = false)
    public Date modifiedAt; 
    
}
