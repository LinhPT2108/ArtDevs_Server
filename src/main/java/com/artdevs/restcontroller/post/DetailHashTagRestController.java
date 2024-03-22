package com.artdevs.restcontroller.post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.artdevs.domain.entities.post.DetailHashtag;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.dto.ReponseDTO;
import com.artdevs.dto.post.DetailHashtagDTO;
import com.artdevs.dto.post.PostToGetDTO;
import com.artdevs.dto.post.ShareDTO;
import com.artdevs.dto.post.DetailHashtagToGetDTO;
import com.artdevs.mapper.post.DetailHashTagMapper;
import com.artdevs.mapper.post.PostMapper;
import com.artdevs.mapper.post.ShareMapper;
import com.artdevs.repositories.post.DetailHashtagRepository;
import com.artdevs.services.DetailHashTagService;
import com.artdevs.services.HashTagService;
import com.artdevs.services.LikesService;
import com.artdevs.services.UserService;
import com.artdevs.utils.Global;

@RestController
@RequestMapping(Global.path_api)
public class DetailHashTagRestController {
	@Autowired
	DetailHashTagService detailHashTagService;

	@Autowired
	HashTagService hashtagSerivce;
	
	@Autowired
	LikesService likesService;
	
	@Autowired
	UserService userservice;

	@Autowired
	DetailHashtagRepository detailHashtagRepository;

	@PostMapping("/detailhashtag")
	public ResponseEntity<DetailHashtag> postDetailHashTag(@RequestBody DetailHashtagDTO detailHashtagDTO) {
		return ResponseEntity.ok(
				detailHashTagService.saveDetailHashtag(DetailHashTagMapper.convertTodDetailHashtag(detailHashtagDTO)));
	}

	@GetMapping("/detailhashtag/{detaiHashTagText}")
	public ResponseEntity<List<ShareDTO>> getDetailHashTagText(
			@PathVariable("detaiHashTagText") String detaiHashTagText, @RequestParam("page") Optional<Integer> p) {

		List<Post> listPostOfHashTag = detailHashTagService.findDetaiHashTagByName(detaiHashTagText)
				.getListHashtagOfDetail().stream().map(t -> t.getPostHashtag()).collect(Collectors.toList());

		int pageSize = Global.size_page;
		int currentPage = p.orElse(0);

		int start = currentPage * pageSize;
		int end = Math.min((start + pageSize), listPostOfHashTag.size());

		List<Post> sublist = listPostOfHashTag.subList(start, end);

		Pageable pageable = PageRequest.of(currentPage, pageSize, Sort.by("time").descending());
		Page<Post> postPage = new PageImpl<>(sublist, pageable, listPostOfHashTag.size());
		List<PostToGetDTO> listpost = postPage.get()
				.filter(t -> !t.isDel() && t.getPrivacyPostDetails().stream()
						.anyMatch(d -> d.isStatus() && d.getPrivacyPost().getId() == 1))
				.distinct().map(t -> PostMapper.convertoGetDTO(t, hashtagSerivce, userservice, likesService))
				.collect(Collectors.toList());
		return ResponseEntity.ok(listpost.stream()
				.map(t -> ShareMapper.convertToShareDTOByPost(t, hashtagSerivce, userservice, likesService))
				.collect(Collectors.toList()));
	}

    @GetMapping("/detailhashtag")
    public ResponseEntity<ReponseDTO> getDetailHashTag() {
        ReponseDTO reponseDTO = new ReponseDTO<>();

        List<DetailHashtagDTO> listDetailHashtagDTO = new ArrayList<>();
        List<DetailHashtag> listDetailHashtag = detailHashtagRepository.findAll();
        for (DetailHashtag detail : listDetailHashtag) {
            listDetailHashtagDTO.add(DetailHashTagMapper.convertToDetailHashTagDTO(detail));
            // System.out.println("check count hashtag: " +
            // listDetailHashtagDTO.get(0).getCountHashtagOfDetail());
        }
        // System.out.println(listDetailHashtagDTO.toString());
        reponseDTO.setMessage("Lấy dữ liệu HashTag thành công!");
        reponseDTO.setStatusCode(200);
        reponseDTO.setModel(listDetailHashtagDTO);
        return ResponseEntity.ok(reponseDTO);
    }
    
    @GetMapping("/search-detailhashtag")
    public ResponseEntity<List<DetailHashtagToGetDTO>> getDetailHashTagByKeyword(@RequestParam("keyword") String keyword) {
        List<DetailHashtagToGetDTO> listDetailHashtagDTO = new ArrayList<>();
        System.out.println(keyword);
        Optional<List<DetailHashtag>> listDetailHashtag = detailHashtagRepository.findByKeywordNonPage(keyword);
        if(listDetailHashtag.isPresent()) {
        	System.out.println(listDetailHashtag.get().size());
        	for (DetailHashtag detail : listDetailHashtag.get()) {
        		listDetailHashtagDTO.add(DetailHashTagMapper.convertToDetailHashTagToGetDTO(detail));
        	}
        	// System.out.println(listDetailHashtagDTO.toString());
        	return ResponseEntity.ok(listDetailHashtagDTO);        	
        }else {
        	return ResponseEntity.ok(null);
        }
    }
}
