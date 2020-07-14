package Utils;

import models.Drink;
import models.Whisky;
import models.Wine;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

public class ProductDetails {
	
	private static HttpEntity entity;
	
	
	
	/**
	 * Assembles product information to a specific product by getting data
	 * from the API and process the data into desired output
	 *
	 * @param drink that will be getting information about
	 * @return true/false
	 */
	public static boolean getProductDetails(Drink drink) {
		//int productId = drink.getProductId();
		int productId = 126801;
		HttpEntity apiData = getDataFromApi(productId);
		if(apiData == null) {
			//Log something
			return false;
		}
		
		if (formatApiData(apiData, drink)) {
			return true;
		}
		
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
	private static boolean formatApiData(HttpEntity entity, Drink drink) {
		
		try {
			String resultString = EntityUtils.toString(entity);
			String resultString2 = resultString.substring(1, resultString.length() - 1);
			JSONObject resultObject = new JSONObject(resultString2);
			String priceString = resultObject.getJSONArray("prices").getJSONObject(0).get("salesPrice").toString();
			double priceDouble = Double.parseDouble(priceString);

			JSONObject basic = resultObject.getJSONObject("basic");
			String name = basic.getString("productLongName");
			double alcohol = basic.getDouble("alcoholContent");
			double volume = basic.getDouble("volume");
			
			drink.setPrice(priceDouble);
			drink.setName(name);
			drink.setAlcohol(alcohol);
			drink.setVolume(volume);
			
			if(drink instanceof Wine) {
				formatWineData(resultObject);
			} else if(drink instanceof Whisky) {
				//Format whisky data
			}
		} catch (IOException | org.json.JSONException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	private static boolean formatWineData(JSONObject resultObject) {
		ArrayList<String> grapesString = new ArrayList<>();
		
		
		try {
			JSONObject ingredients = resultObject.getJSONObject("ingredients");
			JSONArray grapes = ingredients.getJSONArray("grapes");
			if (grapes != null) {
				for (int i = 1; i < grapes.length(); i++) {
					grapesString.add(grapes.getJSONObject(i).toString());
				}
			}
			
			JSONObject origins = resultObject.getJSONObject("origins").getJSONObject("origin");
			String country = origins.getString("country");
			String region = origins.getString("region");
			
			JSONObject basic = resultObject.getJSONObject("basic");
			int vintage = basic.getInt("vintage");
			
			JSONObject classification = resultObject.getJSONObject("classification");
			String type = classification.getString("productTypeName");
			
		} catch (org.json.JSONException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static void main(String[] args) {
		getProductDetails(null);
	}
	
}
