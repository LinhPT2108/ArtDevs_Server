package com.artdevs.restcontroller.picture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.artdevs.domain.entities.user.Picture;
import com.artdevs.mapper.PictureMapper;
import com.artdevs.services.PictureService;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class picOfUserRestController {

	@Autowired
	PictureService pictureService;

	@PostMapping("/add-picture")
	public ResponseEntity<?> postMethodName(@RequestParam(name = "imageUrl") MultipartFile imageUrl,
			@RequestParam(name = "positionOfPic") String positionOfPic) {
		// TODO: process POST request
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserId = authenticate.getName();

		try {
			Picture result = this.pictureService.addPicture(loggedInUserId, imageUrl, Boolean.parseBoolean(positionOfPic));
			return ResponseEntity.ok(PictureMapper.convertToPictureDTO(result));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e);
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("/delete-picture/{id}")
	public ResponseEntity<Boolean> DeletePicture(@PathVariable("id") String photoToRemoveId) {
		Authentication authenticate = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUserId = authenticate.getName();
		try {
			boolean result = this.pictureService.deletePicture(loggedInUserId, photoToRemoveId);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return ResponseEntity.badRequest().build();
		}
	}

}
