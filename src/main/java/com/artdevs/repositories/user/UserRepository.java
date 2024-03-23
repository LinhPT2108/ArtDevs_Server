package com.artdevs.repositories.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.artdevs.domain.entities.user.Role;
import com.artdevs.domain.entities.user.User;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

	Optional<User> findByEmail(String email);

	@Query("SELECT p FROM User p " + "WHERE (p.firstName LIKE %:keyword%" + " OR  p.middleName LIKE %:keyword%"
			+ " OR  p.lastName LIKE %:keyword% )" + " AND p.role.id = 2")
	Optional<Page<User>> searchByKeyword(@Param("keyword") String keyword, Pageable pageable);

	@Query("SELECT p FROM User p " + "WHERE (p.firstName LIKE %:keyword%" + " OR  p.middleName LIKE %:keyword%"
			+ " OR  p.lastName LIKE %:keyword% )" + " AND p.role.id = 3")
	Optional<Page<User>> searchMentorByKeyword(@Param("keyword") String keyword, Pageable pageable);

	List<User> findByRole(Role role);

	List<User> findByRoleAndIsReadyAndIsOnline(Role role, Boolean Ready, Boolean online);

	List<User> findByRoleAndIsReady(Role role, Boolean Ready);

	Optional<User> findByEmailAndProvider(String email, String provider);

	// Câu query dùng để truy vấn cho gợi ý bạn bè cùng demand

	// @Query("SELECT DISTINCT u FROM User u " +
	// "JOIN u.userDemand d " +
	// "WHERE u.email != :currentUserEmail AND d.language.languageName IN
	// :currentUserDemands")
	// List<User> findUsersWithSimilarDemands(@Param("currentUserEmail") String
	// currentUserEmail,
	// @Param("currentUserDemands") List<String> currentUserDemands);

	// @Query("SELECT DISTINCT u FROM User u " +
	// "JOIN u.userDemand d " +
	// "WHERE u.email != :currentUserEmail AND d.language.languageName IN
	// :currentUserDemands")
	// List<User> findUsersWithSimilarDemands(@Param("currentUserEmail") String
	// currentUserEmail,
	// @Param("currentUserDemands") List<String> currentUserDemands);

}
