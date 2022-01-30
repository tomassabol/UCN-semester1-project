package models;

import java.util.HashMap;

public class PrimaryKey {
	
	public enum Keys {
		ORDER,
		LOAN,
		PRODUCT,
		PRODUCT_GROUP,
		ITEM,
		CUSTOMER,
		CONTRACTOR,
		SUPPLY_OFFER,
		SHELF,
		STORAGE_LOCATION,
		EMPLOYEE,
		QUOTE,
		SUPPLY_ORDER,
		CUSTOMER_TYPE
	}
	
	private static HashMap<Keys, Integer> ids = new HashMap<>();
	
	public static int peekID(Keys key) {
		if (!ids.containsKey(key)) {
			ids.put(key, 0);
		}
		
		return ids.get(key);
	}
	
	public static int getID(Keys key) {
		if (!ids.containsKey(key)) {
			ids.put(key, 0);
		}
		
		// Increment
		int id = ids.get(key);
		ids.put(key, id + 1);
		
		return id;
	}

}
