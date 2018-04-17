package com.trivadis.restapi;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController()
public class ApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApiController.class);
    private Integer startWith = 1;

    @RequestMapping(
            method = RequestMethod.GET,
            value= "/api/products"
    )
    //@CrossOrigin(origins = "http://localhost:4200")
    public List<ProductApi> getProducts(@RequestParam(value="category", required=false) String category
    							, @RequestParam(value="offset", required=false) Integer offset
    							, @RequestParam(value="nofRecords", required=false) Integer nofRecords
    							, @RequestParam(value="pageSize", required=false) Integer pageSize
    							, @RequestParam(value="offsetSemantic", required=false) String offsetSemantic
    							)  {
    	
    	System.out.println("call to /api/products: [" + category + "," + offset + "," + nofRecords + "," + pageSize + "," + offsetSemantic + "]");
    	
    	List<ProductApi> result = new ArrayList<>();
    	String semantic = (offsetSemantic!= null) ? offsetSemantic : "greater";
    	
    	Integer startWith = Integer.valueOf(offset);
    	if (semantic.equals("greater")) {
    		startWith += 1;
    	}
    	Integer numberOfRecords = (nofRecords == null) ? 50 : nofRecords;
    	
    	for (int i = startWith; i < numberOfRecords + startWith; i++) {
            ProductApi product = new ProductApi();
            
            product.productId = i;
            product.productName = "Name " + product.productId;
            product.category = (category != null && category.length() > 0) ? category : "electronics";
    		result.add(product);
    		
    	}
        
        return result;
    }
    
    
    @RequestMapping(
            method = RequestMethod.GET,
            value= "/api/customers"
    )
    //@CrossOrigin(origins = "http://localhost:4200")
    public List<CustomerApi> getCustomer(@RequestParam(value="country", required=false) String country
    							, @RequestParam(value="offset", required=false) Long offset
    							, @RequestParam(value="nofRecords", required=false) Integer nofRecords
    							, @RequestParam(value="pageSize", required=false) Integer pageSize
    							, @RequestParam(value="offsetSemantic", required=false) String offsetSemantic
    							)  {
    	System.out.println("call to /api/customers: [" + country + "," + offset + "," + nofRecords + "," + pageSize + "," + offsetSemantic + "]");

    	List<CustomerApi> result = new ArrayList<>();
    	String semantic = (offsetSemantic!= null) ? offsetSemantic : "greater";
    	
    	if (semantic.equals("greater")) {
//    		startWith += 1;
    	}
    	Integer numberOfRecords = (nofRecords == null) ? 50 : nofRecords;
    	
    	for (int i = startWith; i < numberOfRecords + startWith; i++) {
    		CustomerApi customer = new CustomerApi();
            
    		customer.customerId = i;
    		customer.name = "Name " + customer.customerId;
    		customer.country = (country != null && country.length() > 0) ? country : "sitzerland";
    		customer.modifiedAt = new Date();
    		result.add(customer);
    		
    	}
    	startWith += numberOfRecords;
        return result;
    }
    
}