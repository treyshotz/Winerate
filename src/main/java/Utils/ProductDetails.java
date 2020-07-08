package Utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;


import java.io.IOException;
import java.net.URI;

public class ProductDetails {
	
	private static HttpEntity entity;
	
	
	
	/**
	 * Assembles product information to a specific product by getting data
	 * from the API and process the data into desired output
	 *
	 * @param productId of the product that will be searched for
	 * @return true/false
	 */
	public static boolean getProductDetails(int productId) {
		HttpEntity apiData = getDataFromApi(productId);
		if(apiData == null) {
			//Log something
			return false;
		}
		
		formatApiData(apiData);
		return false;
	}
	
	/**
	 * Retrieves the data from the API
	 *
	 * Uses the API key from the user
	 *
	 * @param productIdInt of the product that will be searched for
	 * @return HttpEntity result of the API call
	 */
	private static HttpEntity getDataFromApi(int productIdInt) {
		
		HttpClient httpClient = HttpClients.createDefault();
		
		try {
			//Creates a string of the productId to parse into uribuilder
			String productIdString = String.valueOf(productIdInt);
			
			URIBuilder uriBuilder = new URIBuilder("https://apis.vinmonopolet.no/products/v0/details-normal");
			
			//I see no point including other details than the productId
			uriBuilder.setParameter("productId", productIdString);
			
			URI uri = uriBuilder.build();
			//HttpPost request = new HttpPost(uri);
			HttpGet request = new HttpGet(uri);
			//TODO: Need to find out how to get API key
			// A solution is to bind every account to a personal key created by the user himself
			request.setHeader("Ocp-Apim-Subscription-Key", "API-KEY");
			
			//Sends GETRequest
			HttpResponse response = httpClient.execute(request);
			entity = response.getEntity();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	/**
	 * Formats the input entity to a desired outcome
	 *
	 * @param entity from the API call
	 * @return data in desired output
	 */
	private static boolean formatApiData(HttpEntity entity) {
		
		try {
			String resultString = EntityUtils.toString(entity);
			String resultString2 = resultString.substring(1, resultString.length() - 1);
			JSONObject resultObject = new JSONObject(resultString2);
			String priceString = resultObject.getJSONArray("prices").getJSONObject(0).get("salesPrice").toString();
			double priceInt = Double.parseDouble(priceString);
			
			JSONObject basic = resultObject.getJSONObject("basic");
			String name = basic.getString("productLongName");
			double alcohol = basic.getDouble("alcoholContent");
			double volume = basic.getDouble("volume");
			
			
		} catch (IOException | org.json.JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		getProductDetails(126801);
	}
	
}
