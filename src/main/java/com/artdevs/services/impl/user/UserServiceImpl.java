package com.artdevs.services.impl.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.artdevs.domain.entities.user.Demand;
import com.artdevs.domain.entities.user.ProgramingLanguage;
import com.artdevs.domain.entities.user.Role;
import com.artdevs.domain.entities.user.Skill;
import com.artdevs.domain.entities.user.User;
import com.artdevs.repositories.user.RoleRepository;
import com.artdevs.repositories.user.UserRepository;
import com.artdevs.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	// private final UserRepository userRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository rolerepository;

	@Override
	public User findUserById(String userId) {
		Optional<User> userOptional = userRepository.findById(userId);
		System.out.println(userOptional);
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
		return userRepository.findByEmail(email).get();
	}

	@Override
	public List<User> findMentor() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User userlogin = findByEmail(auth.getName());
		List<User> result = new ArrayList<>();
		Role role = rolerepository.findById(3).get();
		List<User> listmentor = userRepository.findByRole(role);
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
}
