package com.nutritionix.client;

import com.nutritionix.dto.Brand;
import com.nutritionix.dto.Item;
import com.nutritionix.dto.SearchResults;

public interface NutritionixClient
{

	// Worker functions
	public abstract SearchResults search(String phrase);

	public abstract SearchResults search(String phrase, String brand_id,
			int calorie_max, int calorie_min, String resultsPage, String fields);

	public abstract Item item(String id, String upc, String fields);

	public abstract Brand brand(String brand_id);

	public abstract void shutdown();

}