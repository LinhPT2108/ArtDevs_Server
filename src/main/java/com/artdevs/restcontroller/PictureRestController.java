package com.artdevs.restcontroller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.user.Picture;
import com.artdevs.dto.user.PictureDTO;
import com.artdevs.mapper.PictureMapper;
import com.artdevs.repositories.user.PictureRepository;
import com.artdevs.services.impl.user.PictureServiceImpl;
import com.artdevs.utils.Path;

@RestController
@RequestMapping(Path.path_api)
public class PictureRestController {
    @Autowired
    PictureServiceImpl pictureServiceImpl;
    @Autowired
    PictureRepository pictureRepository;

    @PostMapping("/picture")
    public ResponseEntity<Picture> postPicture(@RequestBody PictureDTO pictureDTO) {
        return ResponseEntity.ok(pictureServiceImpl.savePicture(PictureMapper.convertToPicture(pictureDTO)));
    }

    @GetMapping("/picture")
    public ResponseEntity<List<PictureDTO>> getPicture() {
        List<PictureDTO> listPictureDTO = new ArrayList<>();
        List<Picture> listPictures = pictureRepository.findAll();
        for (Picture picture : listPictures) {
            listPictureDTO.add(PictureMapper.convertToPictureDTO(picture));
        }
        return ResponseEntity.ok(listPictureDTO);
    }
}
