package com.app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.repository.RetailManagerRepository;
import com.app.repository.Shop;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import com.google.maps.model.LatLng;

/**
 * Controller for processing the restful end points.
 * It contains various REST API calls. 
 * "/shops" - Http GET call to fetch shop details
 * "/shops" - Http POST call to push shop details. 
 * "/shopsNearBy" - Http GET call to get nearby shops. It will expect user's geo location details. 
 * 
 * @author Somenath
 *
 */
@RestController
public class RetailManagerController {
	
	@Autowired
	RetailManagerRepository shopclient;
	
	/**
	 * 
	 * @return List of Shops currently persisted.
	 */
    @RequestMapping(value = "/shops", method = RequestMethod.GET)
    public ResponseEntity<List<Shop>> listShops() {
    	
        List<Shop> shops = (List<Shop>) shopclient.findAll();
        if (shops.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Shop>>(shops, HttpStatus.OK);
    }
    
    
    /**
     * 
     * @param shop Shop object which needs to be peristed.
     * @return response which contains the shop object.
     * @throws ApiException throws API Exception
     * @throws InterruptedException throws INterrupted Exception
     * @throws IOException IO Exception
     */
    @RequestMapping(value = "/shops", method = RequestMethod.POST)
    public ResponseEntity<Shop> addShop(@RequestBody Shop shop) throws ApiException, InterruptedException, IOException {
      
    	Shop existingShop = null;
    	
    	Shop shopInDB = shopclient.findByShopName(shop.getShopName());
    	
    	if(null!=shopInDB)
    	{
    		existingShop = new Shop(shopInDB.getShopName(), shopInDB.getShopAddress(), shopInDB.getShopZipCode());
    		existingShop.setLatitude(shopInDB.getLatitude());
    		existingShop.setLongitude(shopInDB.getLongitude());
    		shopInDB.setShopAddress(shop.getShopAddress()); // Updating the shop address
    		shopInDB.setShopZipCode(shop.getShopZipCode());
    		shopclient.save(getAddress(shopInDB)); // Saving the saving address by setting current latitude and longitude
    		return new ResponseEntity<Shop>(existingShop, HttpStatus.CONFLICT);
    		
    	}else{
    		 shopclient.save(getAddress(shop));
    		 return new ResponseEntity<Shop>(shop, HttpStatus.OK);
    	}
    	
    }
    
    /**
     * Method which will be called to identify nearest shop
     * from a user's location.
     * @param latitude User's latitude
     * @param longitude User's longitude. 
     * @return Shop 
     */
    @RequestMapping(value = "/shopsNearBy",method = RequestMethod.GET)
    public ResponseEntity<Shop> getNearestShop(@RequestParam(value = "latitude", defaultValue = "0") Double latitude,
            @RequestParam(value = "longitude", defaultValue = "0") Double longitude) {
        
    	Shop shopTobeReturned = new Shop() ;
    	Double smallestDistance = null;
    	
    	List<Shop> shops = (List<Shop>) shopclient.findAll();
    	if (shops == null || shops.isEmpty()) {
             return new ResponseEntity<Shop>(HttpStatus.NO_CONTENT);
         }
    	
    	
    	for(Shop shop : shops)
    	{
    		Double dist = distance(shop.getLatitude(),shop.getLongitude(),latitude,longitude,'K');
    		if(null==smallestDistance || dist < smallestDistance)
    		{
    			smallestDistance = dist;
    			shopTobeReturned = shop;
    		}
    	}
        return new ResponseEntity<Shop>(shopTobeReturned, HttpStatus.OK);
    }
    
    /**
     * 
     * @param shop for which we need to find latitude and longitude.
     * @return Shop Shop
     * @throws ApiException API Exception
     * @throws InterruptedException Interrupted Exception
     * @throws IOException IO Exception
     */
    public Shop getAddress(Shop shop) throws ApiException, InterruptedException, IOException {
       
    	GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCtnijXZlRHR2THu7lCLSfCffrlQ2AxjiA");
        GeocodingResult[] results =  GeocodingApi.geocode(context,
            shop.getShopAddress()).await();
        Geometry s = results[0].geometry;
        LatLng a = s.location;
        shop.setLatitude(a.lat);
        shop.setLongitude(a.lng);
        return shop;
    }
     
    /**
     * 
     * @param lat1 Latitude of peristed object
     * @param lon1 Longitude of persisted object
     * @param lat2 Latitude of user's location
     * @param lon2 Longitude of user's location
     * @param unit Distance calculation based on unit
     * @return
     */
    private double distance(double lat1, double lon1, double lat2, double lon2, char unit) {
    	  double theta = lon1 - lon2;
    	  double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
    	  dist = Math.acos(dist);
    	  dist = rad2deg(dist);
    	  dist = dist * 60 * 1.1515;
    	  if (unit == 'K') {
    	    dist = dist * 1.609344;
    	  } else if (unit == 'N') {
    	  dist = dist * 0.8684;
    	    }
    	  return (dist);
    	}
    	
    	private double deg2rad(double deg) {
    	  return (deg * Math.PI / 180.0);
    	}
    	
    	private double rad2deg(double rad) {
    	  return (rad * 180.0 / Math.PI);
    	}

}
