package com.artdevs.services.impl.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.domain.entities.user.Demand;
import com.artdevs.domain.entities.user.ProgramingLanguage;
import com.artdevs.domain.entities.user.Role;
import com.artdevs.domain.entities.user.Skill;
import com.artdevs.domain.entities.user.TransitionInfo;
import com.artdevs.domain.entities.user.User;
import com.artdevs.domain.entities.user.Wallet;
import com.artdevs.repositories.message.RelationshipRepository;
import com.artdevs.repositories.user.RoleRepository;
import com.artdevs.repositories.user.TransitioninfoRepository;
import com.artdevs.repositories.user.UserRepository;
import com.artdevs.services.UserService;
import com.artdevs.services.WalletService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired UserDetailsService userdetailsevice;
	// private final UserRepository userRepository;
	@Autowired WalletService wallectservice;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository rolerepository;
	
	@Autowired RelationshipRepository relationrepository;

	@Autowired TransitioninfoRepository traninfoservice;
	
	
	
	@Override
	public User findUserById(String userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		System.out.println("check User"+userOptional);
		return userOptional.get();
	}

	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(User user) {
		userRepository.delete(user);
	}

	@Override
	public User findByEmail(String email) {
		// TODO Auto-generated method stub
		Optional<User> result = userRepository.findByEmail(email);
		if(!result.isEmpty()) {
			return result.get();
		}else {
			return null;
		}
		
	}

	@Override
	public List<User> findMentor() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userlogin = findByEmail(auth.getName());
		List<User> result = new ArrayList<>();
		Role role = rolerepository.findById(3).get();
		List<User> listmentor = userRepository.findByRoleAndIsReady(role, false);
		List<ProgramingLanguage> demandOfUserLogin = new ArrayList<>();

		for (Demand demand : userlogin.getUserDemand()) {
			demandOfUserLogin.add(demand.getLanguage());
		}

		for (User mentor : listmentor) {
			List<ProgramingLanguage> skillofmentor = new ArrayList<>();
			// Tao list chua skill mentor
			for (Skill skill : mentor.getUserSkill()) {
				skillofmentor.add(skill.getLanguage());
			}
			// neu skill co trong list demand add metor vao result
			for (ProgramingLanguage programingLanguage : skillofmentor) {
				if (demandOfUserLogin.contains(programingLanguage)) {
					result.add(mentor);
				}
			}
		}
		List<User> ListMentorAccept = new ArrayList<>();
		List<User> ListMentorSendmatch = new ArrayList<>();
		
		List<RelationShip> Accept = relationrepository.findRelationshipByUserIdAndStatus(userlogin.getUserId(), 3);
		List<RelationShip> Matching = relationrepository.findRelationshipByUserIdAndStatus(userlogin.getUserId(), 2);
		for (RelationShip relationShip : Accept) {
			if(relationShip.getUserOneId() != userlogin) {
				ListMentorAccept.add(relationShip.getUserOneId());
			}else {
				ListMentorAccept.add(relationShip.getUserTwoId());
			}
		}
		for (RelationShip relationShip : Matching) {
			if(relationShip.getUserOneId() != userlogin) {
				ListMentorAccept.add(relationShip.getUserOneId());
			}else {
				ListMentorAccept.add(relationShip.getUserTwoId());
			}
		}
		result.removeAll(ListMentorAccept);
		result.removeAll(ListMentorSendmatch);
		return result;
	}
	
	@Override
	public List<User> findSuitableFriend(){
		//Tạo List Result kiểu User
		List<User> result = new ArrayList<>();
		
		//Lấy UserLogin từ Token
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userlogin = findByEmail(auth.getName());
		
		//Tạo List Tất cả user
		List<User> allUser = userRepository.findAll();
		
		//Tạo list Chứa User Có Relationship với UserLogin
		List<User> isUserRelationShipOfUserLogin = new ArrayList<>();
		
		

		//Query Các Relation của userLogin & chạy for nếu userOneId == userLogin => isUserRelationShipOfUserLogin thêm vào userOneId và ngược lại
		List<RelationShip> isRelationshipWithUserLogin = relationrepository.findAllRequestedForFriendUsers(userlogin.getUserId());
		
		//List Mentor 
		List<User> allMentor = userRepository.findByRole(rolerepository.findById(3).get());
		for (RelationShip relationShip : isRelationshipWithUserLogin) {
			if(relationShip.getUserOneId() == userlogin) {
				isUserRelationShipOfUserLogin.add(relationShip.getUserTwoId());
			}else {
				isUserRelationShipOfUserLogin.add(relationShip.getUserOneId());
			}
		}
		
		// Tạo danh sách mới chứa tất cả người dùng trừ đi những người có mối quan hệ
		List<User> usersWithoutRelationship = new ArrayList<>(allUser);
		usersWithoutRelationship.removeAll(isUserRelationShipOfUserLogin);
		usersWithoutRelationship.removeAll(allMentor);
		
		//Lấy ra List ProgamingLangue Demand của user login
		List<ProgramingLanguage> demandOfUserLogin = new ArrayList<>();
		for (Demand demand : userlogin.getUserDemand()) {
			demandOfUserLogin.add(demand.getLanguage());
		}
		
		
		for (User userSuitable : usersWithoutRelationship) {
			List<ProgramingLanguage> DemandOfuserSuitable = new ArrayList<>();
			// Tao list chua demand của userSuitable
			for (Demand demand : userSuitable.getUserDemand()) {
				DemandOfuserSuitable.add(demand.getLanguage());
			}
			// neu Demand co trong list demand add UserSuitable vao result
				for (ProgramingLanguage programingLanguage : DemandOfuserSuitable) {
					if (demandOfUserLogin.contains(programingLanguage)) {
								result.add(userSuitable);
					}
				}
		}
		

		
		return result;
	}

	@Override
	public List<User> FindMentorIsReady() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userlogin = findByEmail(auth.getName());
		List<User> result = new ArrayList<>();
		Role role = rolerepository.findById(3).get();
		List<User> listmentor = userRepository.findByRoleAndIsReadyAndIsOnline(role,true,true);
		List<ProgramingLanguage> demandOfUserLogin = new ArrayList<>();

		for (Demand demand : userlogin.getUserDemand()) {
			demandOfUserLogin.add(demand.getLanguage());
		}

		for (User mentor : listmentor) {
			List<ProgramingLanguage> skillofmentor = new ArrayList<>();
			// Tao list chua skill mentor
			for (Skill skill : mentor.getUserSkill()) {
				skillofmentor.add(skill.getLanguage());
			}
			// neu skill co trong list demand add metor vao result
			for (ProgramingLanguage programingLanguage : skillofmentor) {
				if (demandOfUserLogin.contains(programingLanguage)) {
					result.add(mentor);
				}
			}
		}
		return result;
	}

	
	@Override
	public Boolean SendMatchMentor(String mentorID) {
		TransitionInfo result = new TransitionInfo();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("test Atuh" + auth.getName());
		User userlogin = findByEmail(auth.getName());
		User mentor = findUserById(mentorID);
		Wallet walletUserLogin = wallectservice.FindByUser(userlogin);

		System.out.println("userlogin"+ walletUserLogin.getSurplus());
		System.out.println("mentor"+  mentor.getMatchPrice());
		
		//Nếu Ví tiến User đang đăng nhập >= giá match của mentor
		if(walletUserLogin.getSurplus()  >=  mentor.getMatchPrice() && mentor.getRole().getId() == 3) {
			result.setMentor(mentor);
			result.setUserlogin(userlogin);
			//Save MQH với mentor
			if(relationrepository.findByUserOneIdAndUserTwoIdAndStatus(userlogin, mentor,2).isEmpty()
				&& 	relationrepository.findByUserOneIdAndUserTwoIdAndStatus(userlogin, mentor,3).isEmpty()
					) {
				RelationShip relation = new RelationShip();
				relation.setTimeRelation(new Date());
				relation.setActionUser(userlogin);
				relation.setUserOneId(userlogin);
				relation.setUserTwoId(mentor);
				relation.setStatus(2);
				relationrepository.save(relation);
				
			}else if(!relationrepository.findByUserOneIdAndUserTwoIdAndStatus(userlogin, mentor,3).isEmpty()) {
				RelationShip relation = relationrepository.findRelationshipWithFriendWithStatus(userlogin.getUserId(), mentor.getUserId(),3);
				System.out.println("check Relation :"+relationrepository.findByUserOneIdAndUserTwoIdAndStatus(userlogin, mentor,3));
				relation.setStatus(2);
				relationrepository.save(relation);
			}
			return true;
		}else {
			System.out.println("Lỗi match");
			return false;
		}
		
	}
	
	@Override
	public Boolean AcceptMatchMentor(String userid) {
		TransitionInfo result = new TransitionInfo();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user= findUserById(userid);
		User mentorlogin  = findByEmail(auth.getName());
		Wallet walletUserLogin = wallectservice.FindByUser(user);
		Wallet walletMentor = wallectservice.FindByUser(mentorlogin );
		
		//Nếu Ví tiến User đang đăng nhập >= giá match của mentor
		System.out.println("check Mentor ID:"+ auth.getAuthorities());
		if( mentorlogin.getRole().getId() == 3) {
			//set và Save Mentor
			result.setPrice_match(mentorlogin.getMatchPrice());
			result.setTimeTransiton(new Date());
			result.setUserlogin(user);
			result.setMentor(mentorlogin );
			traninfoservice.save(result);
			
			//Save MQH với mentor
			RelationShip matchbyuser = relationrepository.findRelationshipWithFriendWithStatus(user.getUserId(), mentorlogin.getUserId(),2 );
			if(matchbyuser == null) {
				RelationShip relation = new RelationShip();
				relation.setTimeRelation(new Date());
				relation.setActionUser(user);
				relation.setUserOneId(user);
				relation.setUserTwoId(mentorlogin);
				relation.setStatus(3);
				relationrepository.save(relation);
			}else {
				matchbyuser.setStatus(3);
				relationrepository.save(matchbyuser);
			}
			
			// Cập nhật ví tiền user và mentor
			walletUserLogin.setSurplus(walletUserLogin.getSurplus() - mentorlogin.getMatchPrice());
			wallectservice.saveWallet(walletUserLogin);
			walletMentor.setSurplus(walletMentor.getSurplus() + mentorlogin.getMatchPrice());
			wallectservice.saveWallet(walletMentor);
			return true;
		}else {
			System.out.println("Lỗi match");
			return false;
		}
		
	}
	
	@Override
	public List<RelationShip> getListMatchbyUser(){

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User mentor = findByEmail(auth.getName());
//		List<RelationShip> listrelation = relationrepository.findRelationshipByUserIdAndStatusAndOnline(mentor.getUserId(), 2, true);
		List<RelationShip> result = new ArrayList<>();
		List<RelationShip> temp  = relationrepository.findRelationshipByUserIdAndStatus(mentor.getUserId(), 2);
		for (RelationShip relationShip : temp) {
			if(relationShip.getActionUser().getUserId() != mentor.getUserId()) {
				result.add(relationShip);
			}
		}
		System.out.println( "check result" + result);
		return result;
	}
	
	@Override
	public Boolean CancelSendMatchMentor(String userid){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User mentor = findByEmail(auth.getName());
		System.out.println("check Mentor" + mentor.getUserId());
		System.out.println("check userid"+ userid );
		RelationShip result = relationrepository.findRelationshipWithFriendWithStatus(mentor.getUserId(), userid, 2);
		if(result != null) {
			relationrepository.delete(result);
			return true;
		}else {
			return false;
		}
	}
	
	@Override
	public User setIsReady() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User mentor = findByEmail(auth.getName());
		if(mentor.getIsReady()==true || mentor.getIsReady()== null) {
			mentor.setIsReady(false);
		}else {
			mentor.setIsReady(true);
		}
		return userRepository.save(mentor);
	}
	
	


	@Override
	public Optional<Page<User>> findUserByKeyword(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return userRepository.searchByKeyword(keyword, pageable);
	}

	@Override
	public Optional<Page<User>> findMentorByKeyword(String keyword, Pageable pageable) {
		// TODO Auto-generated method stub
		return userRepository.searchMentorByKeyword(keyword, pageable);
	}

	@Override
	public long coutUser() {
		// TODO Auto-generated method stub
		
		return userRepository.countUsers();
	}

	@Override
	public long coutMentor() {
		// TODO Auto-generated method stub
		return userRepository.countMentors();
	}
	
	@Override
	public long coutUserNew() {
		// TODO Auto-generated method stub
		
		return userRepository.countUsers();
	}
	
	@Override
	public long coutMentorNew() {
		// TODO Auto-generated method stub
		return userRepository.countMentors();
	}

	@Override
	public List<User> getNewMentor(Date Starttime) {
		// TODO Auto-generated method stub
		return userRepository.getMentorWithinRange(Starttime, new Date());
	}

	@Override
	public List<User> getNewUser(Date Starttime) {
		// TODO Auto-generated method stub
		return userRepository.getUserWithinRange(Starttime, new Date());
	}
	
	@Override
	public List<User> getUserReport1() {
		// TODO Auto-generated method stub
		return userRepository.userFristReport();
	}
	
	@Override
	public List<User> getUserReport2() {
		// TODO Auto-generated method stub
		return userRepository.userSecondReport();
	}

	@Override
	public List<User> getUserBand() {
		// TODO Auto-generated method stub
		return userRepository.findByAccountNonLocked(false);
	}




	
	
}
