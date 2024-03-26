package com.artdevs.utils;

import java.util.Date;
import java.util.Random;

import com.fasterxml.jackson.databind.util.ArrayBuilders.LongBuilder;

public class Global {
	public static final String path_api= "/api";
	public static final String path_admin= "/api/admin";
	public static final int size_page = 8;

	public static final String safeTrim(String input) {
		if (input == null) {
			return "";
		} else {
			return input.trim();
		}
	}

	public static  String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	public static  int MIN_LENGTH = 2;
	public static  int MAX_LENGTH =4;   

	public static final String generateRandomCode() {
		Random random = new Random();
		int length = random.nextInt(MAX_LENGTH - MIN_LENGTH + 1) + MIN_LENGTH;
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++) {
			int index = random.nextInt(CHAR_LIST.length());
			sb.append(CHAR_LIST.charAt(index));
		}
		sb.append(System.currentTimeMillis());
		return sb.toString();
	}
	
}
