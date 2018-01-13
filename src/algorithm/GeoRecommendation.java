package algorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import db.DBConnection;
import db.DBConnectionFactory;
import entity.Item;

public class GeoRecommendation {
	public List<Item> recommendItems(String userId, double lat, double lon, String term){
		DBConnection conn = DBConnectionFactory.getDBConnection();
		
		// Get all favorite restaurants
		Set<String> favoriteItems  = conn.getFavoriteItemsIds(userId);
		
		// Get all favorite categories
		Set<String> allCategories = new HashSet<>();
		for(String item : favoriteItems) {
			allCategories.addAll(conn.getCategories(item));
			Set<String> set = conn.getCategories(item);
			for(String s : set) {
				System.out.println(s);
			}
		}
		
		if(allCategories.isEmpty()) {
			allCategories.add(term);
		}
		
		//Search recommended restaurant
		Set<Item> recommendedItems = new HashSet<>();
		for(String category : allCategories) {
			List<Item> items = conn.searchItems(userId, lat, lon, category);
			recommendedItems.addAll(items);
		}
		
		//Remove the restaurants already favored
		List<Item> filteredItems  = new ArrayList<>();
		for(Item item : recommendedItems) {
			if(!favoriteItems.contains(item.getItemId())) {
				filteredItems.add(item);
			}
		}
		
		//Rank the recommend restaurant based on distance
		Collections.sort(filteredItems, (a, b) -> (int)(a.getDistance() - b.getDistance()));
		
		return filteredItems;
	}
}
