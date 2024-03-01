package com.artdevs.utils;

import com.artdevs.domain.entities.user.User;

public class CustomContructor {
	public static String getFullname(User user) {
		String result = user.getFirstName() + " " + user.getMiddleName() + " " + user.getLastName();
		return result;
	}
}
