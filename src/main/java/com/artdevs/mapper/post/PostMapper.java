package com.artdevs.mapper.post;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;

import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.HashTag;
import com.artdevs.domain.entities.post.ImageOfPost;
import com.artdevs.domain.entities.post.Likes;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.Report;
import com.artdevs.domain.entities.post.Share;
import com.artdevs.dto.post.PostDTO;

public class PostMapper {
	private static final ModelMapper modelMapper = new ModelMapper();
	private static PostDTO convertoDTO(Post post) {
		PostDTO postdto = modelMapper.map(post, PostDTO.class);
		postdto.setListCommentPost(getComment(post));
		postdto.setListHashtag(getHashtag(post));
		postdto.setListImage(getImage(post));
		postdto.setListLikePost(getLikepost(post));
		postdto.setListReportPost(getReportpost(post));
		postdto.setListSharePost(getSharepost(post));
		postdto.setPostType(post.getPostType());
		return postdto;
	}
	
	public static Post convertToPost(PostDTO postdto) {
		Post post = modelMapper.map(postdto, Post.class);
		return post;
	}
	private static List<Comment> getComment(Post post){
		return post
				.getListCommentPost().stream().map(cmt -> new Comment(cmt.getId(),cmt.getContent(),cmt.getImageUrl(),cmt.getCount()
				,cmt.getTimeComment(),cmt.getTimeComment(),cmt.getUserReportId(),post))
				.collect(Collectors.toList());
	}
	
	private static List<HashTag> getHashtag(Post post){
		return post
				.getListHashtag().stream().map(hashtag -> new HashTag(hashtag.getId(),
						hashtag.getCount(),post,hashtag.getHashtagDetail()))
				.collect(Collectors.toList());
	}
	private static List<ImageOfPost> getImage(Post post){
		return post
				.getListImage().stream().map(img -> new ImageOfPost(img.getId(),
						img.getImageOfPostUrl(),post))
				.collect(Collectors.toList());
	}
	private static List<Likes> getLikepost(Post post){
		return post
				.getListLikePost().stream().map(like -> new Likes(like.getId(),
						like.getCount(),like.getUserLikeId(),post))
				.collect(Collectors.toList());
	}
	private static List<Report> getReportpost(Post post){
		return post
				.getListReportPost().stream().map(rp -> new Report(rp.getId(),
						rp.getReportDetail(),rp.getCount(),rp.getUserReportId(),post))
				.collect(Collectors.toList());
	}
	private static List<Share> getSharepost(Post post){
		return post
				.getListSharePost().stream().map(share -> new Share(share.getId(),
						share.getCount(),share.getUserShareId(),post))
				.collect(Collectors.toList());
	}
}
