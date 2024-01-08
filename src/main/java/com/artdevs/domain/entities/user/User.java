package com.artdevs.domain.entities.user;

import java.util.List;

import org.hibernate.annotations.Nationalized;

import com.artdevs.domain.entities.message.Message;
import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.Likes;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.Report;
import com.artdevs.domain.entities.post.Share;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "User")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
	@Id
	private String userId;

	@Column
	private boolean isAccountNonExpired;

	@Column
	private boolean isAccountNonLocked;

	@Nationalized
	@Column
	private String address;

	@Column
	private String BackgroundImageUrl;

	@Nationalized
	@Column
	private String city;

	@Nationalized
	@Column
	private String district;

	@Nationalized
	@Column
	private String ward;

	@Column
	private boolean isCreadentialsNonExprired;

	@Column
	private boolean isDelete;

	@Column
	private String email;

	@Column
	private boolean isEnabled;

	@Nationalized
	@Column
	private String firstName;

	@Nationalized
	@Column
	private String lastName;

	@Nationalized
	@Column
	private String middleName;

	@Column
	private String isOnline;

	@Column
	private String password;

	@Column
	private String profilePicUrl;

	@Nationalized
	@Column
	private String username;

	@ManyToOne
	@JoinColumn(name = "userRole")
	private Role role;

	@OneToMany(mappedBy = "user")
	private List<Picture> userPicture;

	@OneToMany(mappedBy = "user")
	private List<Log> userLog;

	// @OneToMany(mappedBy = "user")
	// private List<Wallet> userWallet;

	@OneToMany(mappedBy = "user")
	private List<Demand> userDemand;

	@OneToMany(mappedBy = "user")
	private List<Skill> userSkill;

	@OneToMany(mappedBy = "user")
	private List<MethodPay> userMethod;

	@OneToMany(mappedBy = "user1")
	private List<TransitionInfo> userTransition1;

	@OneToMany(mappedBy = "user2")
	private List<TransitionInfo> userTransition2;

	@OneToMany(mappedBy = "user")
	private List<Post> userPost;

	@OneToMany(mappedBy = "userOneId")
	private List<RelationShip> userRelation1;

	@OneToMany(mappedBy = "userTwoId")
	private List<RelationShip> userRelation2;

	@OneToMany(mappedBy = "actionUser")
	private List<RelationShip> userAction;

	@OneToMany(mappedBy = "formUserId")
	private List<Message> userForm;

	@OneToMany(mappedBy = "toUserId")
	private List<Message> userTo;

	@OneToMany(mappedBy = "userLikeId")
	private List<Likes> listLike;

	@OneToMany(mappedBy = "userShareId")
	private List<Share> listShare;

	@OneToMany(mappedBy = "userReportId")
	private List<Report> listReport;

	@OneToMany(mappedBy = "userReportId")
	private List<Comment> listComment;
}
