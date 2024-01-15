package com.artdevs.dto;

import java.util.List;

import com.artdevs.domain.entities.message.Message;
import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.Likes;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.Report;
import com.artdevs.domain.entities.post.Share;
import com.artdevs.domain.entities.user.Demand;
import com.artdevs.domain.entities.user.MethodPay;
import com.artdevs.domain.entities.user.Role;
import com.artdevs.domain.entities.user.Skill;
import com.artdevs.domain.entities.user.TransitionInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class UserRegisterDTO {

	private String userId;

	private String address;

	private String BackgroundImageUrl;

	private String city;

	private String district;

	private String ward;

	private String email;

	private String firstName;

	private String lastName;

	private String middleName;

	private String password;

	private String profilePicUrl;

	private String username;

	private Role role;

	private List<String> listDemandOfUser;

	private List<String> listSkillOfUser;

}
