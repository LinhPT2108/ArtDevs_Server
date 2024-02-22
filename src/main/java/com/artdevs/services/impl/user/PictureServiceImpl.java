package com.artdevs.services.impl.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.artdevs.domain.entities.user.Picture;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.user.PictureDTO;
import com.artdevs.mapper.PictureMapper;
import com.artdevs.repositories.user.PictureRepository;
import com.artdevs.repositories.user.UserRepository;
import com.artdevs.services.CloudinaryService;
import com.artdevs.services.CloudinaryValidationService;
import com.artdevs.services.PictureService;

@Service
public class PictureServiceImpl implements PictureService {

	@Autowired
	PictureRepository pictureRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	CloudinaryService cloudinaryService;

	@Autowired
	CloudinaryValidationService cloudinaryValidation;

	@Override
	public Picture addPicture(String loggedInUserId, MultipartFile file, boolean posiotionOfPic) throws Exception {
		User user = this.userRepository.findByEmail(loggedInUserId).orElse(null);

		if (user == null) {
			return null;
		}

		String cloudinaryPublicId = UUID.randomUUID().toString();
		Map uploadMap = this.cloudinaryService.uploadImage(file, cloudinaryPublicId);

		if (!cloudinaryValidation.isValid(uploadMap)) {
			return null;
		}

		Picture picture = new Picture();
		picture.setImageUrl(uploadMap.get("url").toString());
		picture.setUser(user);
		picture.setTime(new Date());
		picture.setCloudinaryPublicId(uploadMap.get("public_id").toString());
		picture.setPositionOfPicture(posiotionOfPic);
		return this.pictureRepository.save(picture);
	}

	@Override
	public List<PictureDTO> getAllPicturesByUserId(String userId) {
		// TODO Auto-generated method stub
		return pictureRepository.findByUser_UserId(userId).stream().map(t -> PictureMapper.convertToPictureDTO(t))
				.collect(Collectors.toList());
	}

	@Override
	public boolean deletePicture(String loggedInUserId, String photoToRemoveId) throws Exception {
		User loggedInUser = this.userRepository.findByEmail(loggedInUserId).orElse(null);
		Picture photoToRemove = this.pictureRepository.findById(Integer.parseInt(photoToRemoveId)).orElse(null);

		if (loggedInUser == null || photoToRemove == null) {
			System.out.println("null");
			throw new Exception("sever error");
		}

		boolean pictureOwnershipCheck = photoToRemove.getUser().getEmail().equals(loggedInUserId);

		if (pictureOwnershipCheck) {
			this.pictureRepository.delete(photoToRemove);

			String cloudinaryPublicId = photoToRemove.getCloudinaryPublicId();

			return this.cloudinaryService.deleteImage(cloudinaryPublicId);
		}
		return false;
	}

	@Override
	public List<PictureDTO> getPictureOfUserByPosition(String userId, boolean position) {
		// TODO Auto-generated method stub
		User user = this.userRepository.findByEmail(userId).orElse(null);
		List<PictureDTO> pictureDTOs = new ArrayList<>();
		List<Picture> pictures = pictureRepository.findByUserAndPositionOfPicture(user, position);
		for (Picture p : pictures) {
			pictureDTOs.add(PictureMapper.convertToPictureDTO(p));
		}
		return pictureDTOs;
	}

}
