package com.artdevs.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.multipart.MultipartFile;

import com.artdevs.domain.entities.user.Picture;
import com.artdevs.services.ImageOfPostService;

public class uploadPictureToCloudinary {
	
	public static void uploadPictureToCloudinary(MultipartFile[] file, ImageOfPostService imageOfPostService) {
		
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserId = authenticate.getName();
		
		
		
	}
}
