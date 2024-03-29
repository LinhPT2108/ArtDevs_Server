package com.artdevs.domain.entities.user;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.Nationalized;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.artdevs.domain.entities.message.Message;
import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.domain.entities.post.Comment;
import com.artdevs.domain.entities.post.DetailHashtag;
import com.artdevs.domain.entities.post.Likes;
import com.artdevs.domain.entities.post.Post;
import com.artdevs.domain.entities.post.Report;
import com.artdevs.domain.entities.post.Share;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "User")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonIgnoreProperties({ "hibernateLazyInitializer" })
public class User implements UserDetails {

	@Id
	private String userId;

	@Nationalized
	@Column
	private String address;

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
	private boolean isDelete;

	@Column
	private String email;

	@Nationalized
	@Column
	private String firstName;
	
	@Column
	private String provider;
	
	@Nationalized
	@Column
	private String lastName;

	@Nationalized
	@Column
	private String middleName;

	@Column
	private Boolean isOnline;

	@Column
	private String password;

	@Column
	private Integer MatchPrice;
	
	@Column 
	private Boolean isReady;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date birthday;

	@Column
	private Integer Gender;
	
	@Nationalized
	@Column
	private String username;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userRole")
	private Role role;

	@JsonIgnore
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private List<Picture> userPicture;

	@JsonIgnore
	@OneToMany(mappedBy = "userCreate")
	private List<DetailHashtag> userDetailHashtag;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Log> userLog;

//	 @OneToMany(mappedBy = "user")
//	 private List<Wallet> userWallet;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Demand> userDemand;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Skill> userSkill;

	@JsonIgnore
	@OneToMany(mappedBy = "userSend")
	private List<Star> userStarSend;

	@JsonIgnore
	@OneToMany(mappedBy = "userReceive")
	private List<Star> userStarReceive;
	
	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<SearchHistory> userSearchHistory;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<MethodPay> userMethod;

	@JsonIgnore
	@OneToMany(mappedBy = "Userlogin")
	private List<TransitionInfo> userTransition1;

	@JsonIgnore
	@OneToMany(mappedBy = "Mentor")
	private List<TransitionInfo> userTransition2;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	private List<Post> userPost;

	@JsonIgnore
	@OneToMany(mappedBy = "userOneId",cascade = CascadeType.ALL)
	private List<RelationShip> userRelationShipOne;

	@JsonIgnore
	@OneToMany(mappedBy = "userTwoId",cascade = CascadeType.ALL)
	private List<RelationShip> userRelationShipTwo;

	@JsonIgnore
	@OneToMany(mappedBy = "actionUser")
	private List<RelationShip> userAction;

	@JsonIgnore
	@OneToMany(mappedBy = "formUserId")
	private List<Message> userForm;

	@JsonIgnore
	@OneToMany(mappedBy = "toUserId")
	private List<Message> userTo;

	@JsonIgnore
	@OneToMany(mappedBy = "userLikeId")
	private List<Likes> listLike;

	@JsonIgnore
	@OneToMany(mappedBy = "userShareId")
	private List<Share> listShare;

	@JsonIgnore
	@OneToMany(mappedBy = "userReportId")
	private List<Report> listReport;

	@JsonIgnore
	@OneToMany(mappedBy = "userReportId")
	private List<Comment> listComment;

	@Override
	public String getUsername() {
		return this.email;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(this.role.getRoleName()));
		return List.of(new SimpleGrantedAuthority(authorities.toString()));
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	public User(String userId, String email, String firstName, String provider, String username,
			Role role) {
		super();
		this.userId = userId;
		this.email = email;
		this.firstName = firstName;
		this.provider = provider;
		this.username = username;
		this.role = role;
	}


}
