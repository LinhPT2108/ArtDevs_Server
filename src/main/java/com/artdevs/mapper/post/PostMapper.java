package com.artdevs.mapper.post;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.artdevs.domain.entities.post.HashTag;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.Report;
import com.artdevs.domain.entities.user.User;
import com.artdevs.dto.post.HashTagDTO;
import com.artdevs.dto.post.PostDTO;
import com.artdevs.services.HashTagService;
import com.artdevs.services.UserService;

public class PostMapper {
	

	
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int LENGTH = 6;

    public static String generateRandomPostId() {
        SecureRandom random = new SecureRandom();
        StringBuilder postId = new StringBuilder();

        for (int i = 0; i < LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            postId.append(CHARACTERS.charAt(randomIndex));
        }

        return postId.toString();
    }
    private static String getTimePost() {
    	LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ss"); // Định dạng chỉ lấy giây
        String time = now.format(formatter);
        return time;
    }
	
    private static String encodeToHex(String input) {
        try {
            // Sử dụng SHA-256 là một trong những thuật toán mã hóa phổ biến
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = messageDigest.digest(input.getBytes());

            // Chuyển đổi dữ liệu byte thành chuỗi hex
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < Math.min(16, hashBytes.length); i++) {
                String hex = Integer.toHexString(0xff & hashBytes[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // Xử lý ngoại lệ nếu thuật toán không tồn tại
            return null;
        }
    }
    
	private static final ModelMapper modelMapper = new ModelMapper();
	


	public static PostDTO convertoDTO(Post post,HashTagService hashtagSerivce) {

		PostDTO postdto = modelMapper.map(post, PostDTO.class);
		// postdto.setListCommentPost(getComment(post));
		postdto.setUserId(post.getUser().getUserId());
		postdto.setListHashtag(getHashtag(post, hashtagSerivce));
		postdto.setListImageofPost(getImage(post));
		postdto.setTotalLike(gettotalLike(post));
		postdto.setTotalComment(gettotalComment(post));
		postdto.setListReportPost(null);
		postdto.setTotalShare(gettotalShare(post));
		postdto.setPostType(post.getPostType());
		return postdto;
	}

	public static Post convertToPost(PostDTO postdto,UserService userservice) {
		String postid = encodeToHex(generateRandomPostId() + getTimePost());
		Post post = modelMapper.map(postdto, Post.class);
		System.out.println( "post userid"+postdto.getUserId());
		post.setPostId(postid);
		post.setListLikePost(null);
		post.setListSharePost(null);
		post.setListCommentPost(null);
		post.setListReportPost(null);
		post.setUser(setUser(postdto, userservice));
		return post;
	}

	// private static List<Comment> getComment(Post post) {
	// return post
	// .getListCommentPost().stream()
	// .map(cmt -> new Comment(cmt.getId(), cmt.getContent(), cmt.getImageUrl(),
	// cmt.getCount(),
	// cmt.getTimeComment(), cmt.getTimeComment(), post))
	// .collect(Collectors.toList());
	// }

	private static List<HashTagDTO> getHashtag(Post post,HashTagService hashtagSerivce) {
		List<HashTagDTO> listHashtagdto = new ArrayList<>();
		List<HashTag> listHashTag = post.getListHashtag();
		for (HashTag hashTag : listHashTag) {
			listHashtagdto.add(HashTagMapper.convertToHashTagDTO(hashTag));
		}
		return listHashtagdto;
	}

	private static List<String> getImage(Post post) {
		return post
				.getListImage().stream().map(img -> (img.getImageOfPostUrl()))
				.collect(Collectors.toList());
	}

	private static Long gettotalLike(Post post ) {
		return (long) post.getListLikePost().size();
	}

	private static List<Report> getReportpost(Post post) {
		return post
				.getListReportPost().stream().map(rp -> new Report(rp.getId(),
						rp.getReportDetail(), rp.getCount(), rp.getTimeCreate(), rp.getUserReportId(), post))
				.collect(Collectors.toList());
	}

	private static Long gettotalShare(Post post ) {
		return (long) post.getListSharePost().size();
	}
	
	private static Long gettotalComment(Post post ) {
		return (long) post.getListCommentPost().size();
	}
	
	private static User setUser(PostDTO postdto,UserService userservice) {
			System.out.println(userservice.findUserById(postdto.getUserId()));
		return userservice.findUserById(postdto.getUserId());
	}
}
