package Utils;

import java.net.URI;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class ProductDetails {

	HttpClient httpClient = HttpClients.createDefault();
	
	public boolean getProductDetails(int productIdInt) {
	
		try {
			//Creates a string of the productId to parse into uribuilder
			String productIdString = String.valueOf(productIdInt);
			
			URIBuilder uriBuilder = new URIBuilder("https://api.vinmonopolet.no/docs/services/products/operations/GET_DETAILS_NORMAL");
			
			//I see no point including other details than the productId
			uriBuilder.setParameter("productId", productIdString);
			
			URI uri = uriBuilder.build();
			//HttpPost request = new HttpPost(uri);
			HttpGet request = new HttpGet(uri);
			//TODO: Need to find out how to get API key
			// A solution is to bind every account to a personal key created by the user himself
			request.setHeader("Ocp-Apim-Subscription-Key", "{subscription key}");
			
		/*	StringEntity stringEntity = new StringEntity("body");
			request*/
			
			HttpResponse response = httpClient.execute(request);
			HttpEntity entity = response.getEntity();
			
			if (entity != null) {
				System.out.println(EntityUtils.toString(entity));
			}
			
		} catch (Exception e) {
			System.out.println("stfu");
		}
		return false;
	}
	

}
