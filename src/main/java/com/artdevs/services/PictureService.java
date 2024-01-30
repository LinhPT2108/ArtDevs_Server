package com.artdevs.services;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.artdevs.domain.entities.user.Picture;
import com.artdevs.dto.user.PictureDTO;

public interface PictureService {
	  	Picture addPicture(String loggedInUserId, MultipartFile file, boolean posiotionOfPic) throws Exception;

	    List<PictureDTO> getAllPicturesByUserId(String userId);

	    List<PictureDTO> getPictureOfUserByPosition(String userId, boolean position);
	    
	    boolean deletePicture(String loggedInUserId, String photoToRemoveId) throws Exception;

}
