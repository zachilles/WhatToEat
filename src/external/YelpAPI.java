package external;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.Item;
import entity.Item.ItemBuilder;

public class YelpAPI {
	private static final String API_HOST = "api.yelp.com/v3";
	private static final String SEARCH_PATH = "/businesses/search";
	private static final String DEFAULT_TERM = "";
	private static final String API_KEY = "YPgaUOpQ3IFhUByRwFO1irlJu2gg77WIJqTrsprH_xlpC2j8iF1vEFG0lio33S4kQJd1KkBjhlBlYERs5c-yV7x2GMlR0lPn-mwjBau5SUMAAnxwl02DgIxvQVQ3WnYx";
	
	public List<Item> search(double lat, double lon, String term) {
		String url = "https://" + API_HOST + SEARCH_PATH;
		if(term == null) {
			term = DEFAULT_TERM;
		}
		term = urlEncodeHelper(term);
		int radius = 200;//200 inches field
		String query = String.format("term=%s&latitude=%s&longitude=%s&radius=%s", term, lat, lon, radius);
		
		try {
			//Open a HTTP connection
			HttpURLConnection connection = (HttpURLConnection) new URL(url + "?" + query).openConnection();
			connection.setRequestMethod("GET");
			//This is Yelp API required
			connection.setRequestProperty("Authorization", "Bearer " + API_KEY);
			
			//Save the response body in InputStream of connection
			int responseCode = connection.getResponseCode();
			System.out.println("\nSending 'GET' request to URL : " + url + "?" + query);
			System.out.println("Response Code : " + responseCode);
			
			// Read response body to get businesses data
			BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuilder response = new StringBuilder();
			while((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			//Extract useful information
			JSONObject responseJson = new JSONObject(response.toString());
			JSONArray businesses = (JSONArray) responseJson.get("businesses");
			return getItemList(businesses);
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String urlEncodeHelper(String term) {
		try {
			term = java.net.URLEncoder.encode(term, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return term;
	}
	
	//show the returned result for debugging
	private void queryAPI(double lat, double lon) {
		List<Item> itemList = search(lat, lon, null);
		try {
			for (Item item : itemList) {
				JSONObject jsonObject = item.toJSONObject();
				System.out.println(jsonObject);
			}
		} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	
	/*
	 * Helper methods
	 */
	
	//Convert JSONArray to a list of objects
	private List<Item> getItemList(JSONArray businesses) throws JSONException{
		List<Item> itemList = new ArrayList<>();
		for(int i = 0; i < businesses.length(); i++) {
			JSONObject business = businesses.getJSONObject(i);
			ItemBuilder builder = new ItemBuilder();
			builder.setItemId(getStringFieldOrNull(business, "id"));
			builder.setName(getStringFieldOrNull(business, "name"));
			builder.setCategories(getCategories(business));
			builder.setImageUrl(getImageUrl(business));
			builder.setUrl(getUrl(business));
			builder.setRating(getNumericFieldOrNull(business, "rating"));
			JSONObject coordinates = getCoordinates(business);
			if(coordinates != null) {
				builder.setLatitude(getNumericFieldOrNull(coordinates, "latitude"));
				builder.setLongitude(getNumericFieldOrNull(coordinates, "longitude"));
			}
			JSONObject location = getLocation(business);
			if (location != null) {
				if (!location.isNull("address1")) {
					StringBuilder sb = new StringBuilder();
					sb.append(location.getString("address1"));
					if (!location.isNull("address2")) {
						sb.append(location.getString("address2"));
					}
					if (!location.isNull("address3")) {
						sb.append(location.getString("address3"));
					}
					builder.setAddress(sb.toString());
				}
				if (!location.isNull("city")) {
					builder.setCity(getStringFieldOrNull(location, "city"));
				}
				if (!location.isNull("country")) {
					builder.setCountry(getStringFieldOrNull(location, "country"));
				}
				if (!location.isNull("state")) {
					builder.setState(getStringFieldOrNull(location, "state"));
				}
				builder.setZipcode(getStringFieldOrNull(location, "zip_code"));
			}
			
			//Build a item
			Item item = builder.build();
			itemList.add(item);
		}
		
		return itemList;
	}
	
	private JSONObject getCoordinates(JSONObject business) throws JSONException {
		return business.isNull("coordinates") ? null : business.getJSONObject("coordinates");
	}

	private JSONObject getLocation(JSONObject business) throws JSONException {
		return business.isNull("location") ? null : business.getJSONObject("location");
	}

	private String getUrl(JSONObject business) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getImageUrl(JSONObject business) {
		// TODO Auto-generated method stub
		return null;
	}

	private Set<String> getCategories(JSONObject business) {
		// TODO Auto-generated method stub
		return null;
	}

	private String getStringFieldOrNull(JSONObject business, String field) throws JSONException {
		return business.isNull(field) ? null : business.getString(field);
	}
	
	private double getNumericFieldOrNull(JSONObject location, String field) throws JSONException {
		return location.isNull(field) ? null : location.getDouble(field);
	}

	/*
	 * main method for debug
	 */
	public static void main(String[] args) {
		YelpAPI yApi = new YelpAPI();
		yApi.queryAPI(40.453184, -79.948549);
	}
	
}
