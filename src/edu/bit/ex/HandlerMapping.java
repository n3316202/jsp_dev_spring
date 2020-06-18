package edu.bit.ex;

import java.util.HashMap;
import java.util.Map;

public class HandlerMapping {
	private Map<String,Controller> mappings;
	
	public HandlerMapping() {
		mappings = new HashMap<String,Controller>();
		mappings.put("/",new HelloController());
		mappings.put("/hello",new HelloController());
		
		//Board 관련 
		mappings.put("/list",new BListController());
	}
	
	public Controller getController(String path) {
		return mappings.get(path);
	}
}
