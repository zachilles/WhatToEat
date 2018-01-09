package rpc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;

import algorithm.GeoRecommendation;
import db.DBConnection;
import db.DBConnectionFactory;
import entity.Item;

/**
 * Servlet implementation class RandomList
 */
@WebServlet("/randomList")
public class RandomList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RandomList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
//		if (session.getAttribute("user") == null) {
//			response.setStatus(403);
//			return;
//		}

		String userId = request.getParameter("user_id");
		double lat = Double.parseDouble(request.getParameter("lat"));
		double lon = Double.parseDouble(request.getParameter("lon"));
		
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
			allCategories.add("");
		}
		
		//Search recommended restaurant
		Set<Item> recommendedItems = new HashSet<>();
		for(String category : allCategories) {
			List<Item> items = conn.searchItems(userId, lat, lon, category);
			recommendedItems.addAll(items);
		}
		
		JSONArray result = new JSONArray();
		try {
			for(Item item : recommendedItems) {
				result.put(item.toJSONObject());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RpcHelper.writeJsonArray(response, result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
