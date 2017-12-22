package db;

import java.util.List;
import java.util.Set;

import entity.Item;

public interface DBConnection {
	public void close();
	public void setFavoriteItems(String userId, List<Item> itemIds);
	public void unsetFavoriteItems(String userId, List<Item> itemIds);
	public Set<String> getFavoriteItemsIds(String userId);
	public Set<Item> getFavoriteItems(String userId);
	public Set<String> getCategories(String itemId);
	public List<Item> searchItems(String userId, double lat, double lon, String term);
	public void saveItem(Item item);
	public String getFullname(String userId);
	public boolean verifyLogin(String userId, String password);
}
