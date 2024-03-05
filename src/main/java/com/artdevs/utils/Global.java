package com.artdevs.utils;

public class Global { 
	public static final String path_api= "/api";
	public static final int size_page = 8;
	public static final String safeTrim(String input) {
	    if (input == null) {
	        return ""; 
	    } else {
	        return input.trim(); 
	    }
	}
}
