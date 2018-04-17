package com.trivadis.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProductApi {

    @JsonProperty(value = "productId", required = true)
    public Integer productId;
    
    @JsonProperty(value = "name", required = true)
    public String productName; 
    
    @JsonProperty(value = "category", required = false)
    public String category; 
 
    
}
