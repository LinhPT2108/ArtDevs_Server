package com.artdevs.dto.user;

import java.util.List;

import com.artdevs.domain.entities.message.Message;
import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.Likes;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.Report;
import com.artdevs.domain.entities.post.Share;
import com.artdevs.domain.entities.user.MethodPay;
import com.artdevs.domain.entities.user.Role;
import com.artdevs.domain.entities.user.TransitionInfo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	private String userId;

	private String BackgroundImageUrl;

	private String city;

	private String district;

	private String ward;

	private boolean isDelete;

	private String email;


	private String firstName;

	private String lastName;

	private String middleName;

	private String isOnline;

	private String password;

	private String profilePicUrl;

	private String username;

	private Role role;

	private List<String> listDemandOfUser;

	private List<String> listSkillOfUser;

	private List<MethodPay> listMethod;

	private List<TransitionInfo> userTransition1;

	private List<TransitionInfo> userTransition2;

	private List<Post> listPostOfUser;

	private List<RelationShip> userRelation1;

	private List<RelationShip> userRelation2;

	private List<RelationShip> userAction;

	private List<Message> userForm;

	private List<Message> userTo;

	private List<Likes> listLike;

	private List<Share> listShare;

	private List<Report> listReport;

	private List<Comment> listComment;
}
