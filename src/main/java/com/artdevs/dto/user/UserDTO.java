package com.artdevs.dto.user;

import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Nationalized;

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
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
	
	private String userId;

	private String BackgroundImageUrl;
	
	private String profileImageUrl;

	private String city;

	private String district;

	private String ward;

	private String email;
	
	private Date birthday;

	private String firstName;

	private String lastName;

	private String middleName;

	private String isOnline;

	private String password;
	
	private Integer Gender;

	private String provider;

	private String username;

	private Role role;

	private List<String> ListDemandOfUser;

}
