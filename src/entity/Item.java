package entity;

import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Item {
	private String itemId;
	private String name;
	private String imageUrl;
	private boolean is_closed;
	private String url;
	private double reviews_count;
	private Set<String> categories;
	private double rating;
	private double latitude;
	private double longitude;
	private Set<String> transactions;
	private String price;
	private String address;
	private String city;
	private String country;
	private String state;
	private String zipcode;
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		return true;
	}

	private String phone;
	private double distance;
	
	private Item(ItemBuilder builder) {
		this.itemId = builder.itemId;
		this.name = builder.name;
		this.rating = builder.rating;
		this.address = builder.address;
		this.city = builder.city;
		this.country = builder.country;
		this.state = builder.state;
		this.zipcode = builder.zipcode;
		this.latitude = builder.latitude;
		this.longitude = builder.longitude;
		this.is_closed = builder.is_closed;
		this.categories = builder.categories;
		this.imageUrl = builder.imageUrl;
		this.url = builder.url;
		this.reviews_count = builder.reviews_count;
		this.transactions = builder.transactions;
		this.price = builder.price;
		this.phone = builder.phone;
		this.distance = builder.distance;
	}
	
	public String getItemId() {
		return itemId;
	}
	public String getName() {
		return name;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public boolean isIs_closed() {
		return is_closed;
	}
	public String getUrl() {
		return url;
	}
	public double getReviews_count() {
		return reviews_count;
	}
	public Set<String> getCategories() {
		return categories;
	}
	public double getRating() {
		return rating;
	}
	public double getLatitude() {
		return latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public Set<String> getTransactions() {
		return transactions;
	}
	public String getPrice() {
		return price;
	}
	public String getAddress() {
		return address;
	}
	public String getCity() {
		return city;
	}
	public String getCountry() {
		return country;
	}
	public String getState() {
		return state;
	}
	public String getZipcode() {
		return zipcode;
	}
	public String getPhone() {
		return phone;
	}
	public double getDistance() {
		return distance;
	}
	
	public JSONObject toJSONObject() {
		JSONObject obj = new JSONObject();
		try {
			obj.put("item_id", itemId);
			obj.put("name", name);
			obj.put("image_url", imageUrl);
			obj.put("is_closed", is_closed);
			obj.put("url", url);
			obj.put("reviews_count", reviews_count);
			obj.put("categories", new JSONArray(categories));
			obj.put("rating", rating);
			obj.put("latitude", latitude);
			obj.put("longitude", longitude);
			obj.put("address", address);
			obj.put("city", city);
			obj.put("country", country);
			obj.put("state", state);
			obj.put("zipcode", zipcode);
			obj.put("phone", phone);
			obj.put("distance", distance);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	public static class ItemBuilder{
		private String itemId;
		private String name;
		private String imageUrl;
		private boolean is_closed;
		private String url;
		private double reviews_count;
		private Set<String> categories;
		private double rating;
		private double latitude;
		private double longitude;
		private Set<String> transactions;
		private String price;
		private String address;
		private String city;
		private String country;
		private String state;
		private String zipcode;
		private String phone;
		private double distance;
				
		public ItemBuilder setItemId(String itemId) {
			this.itemId = itemId;
			return this;
		}
		public ItemBuilder setName(String name) {
			this.name = name;
			return this;
		}
		public ItemBuilder setImageUrl(String imageUrl) {
			this.imageUrl = imageUrl;
			return this;
		}
		public ItemBuilder setIs_closed(boolean is_closed) {
			this.is_closed = is_closed;
			return this;
		}
		public ItemBuilder setUrl(String url) {
			this.url = url;
			return this;
		}
		public ItemBuilder setReviews_count(double reviews_count) {
			this.reviews_count = reviews_count;
			return this;
		}
		public ItemBuilder setCategories(Set<String> categories) {
			this.categories = categories;
			return this;
		}
		public ItemBuilder setRating(double rating) {
			this.rating = rating;
			return this;
		}
		public ItemBuilder setLatitude(double latitude) {
			this.latitude = latitude;
			return this;
		}
		public ItemBuilder setLongitude(double longitude) {
			this.longitude = longitude;
			return this;
		}
		public ItemBuilder setTransactions(Set<String> transactions) {
			this.transactions = transactions;
			return this;
		}
		public ItemBuilder setPrice(String price) {
			this.price = price;
			return this;
		}
		public ItemBuilder setAddress(String address) {
			this.address = address;
			return this;
		}
		public ItemBuilder setCity(String city) {
			this.city = city;
			return this;
		}
		public ItemBuilder setCountry(String country) {
			this.country = country;
			return this;
		}
		public ItemBuilder setState(String state) {
			this.state = state;
			return this;
		}
		public ItemBuilder setZipcode(String zipcode) {
			this.zipcode = zipcode;
			return this;
		}
		public ItemBuilder setPhone(String phone) {
			this.phone = phone;
			return this;
		}
		public ItemBuilder setDistance(double distance) {
			this.distance = distance;
			return this;
		}
		
		public Item build() {
			return new Item(this);
		}
		
	}
	
}
