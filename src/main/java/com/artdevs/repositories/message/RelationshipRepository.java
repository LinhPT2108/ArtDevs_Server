package com.artdevs.repositories.message;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;

import com.artdevs.domain.entities.message.RelationShip;
import com.artdevs.domain.entities.user.User;

@EnableJpaRepositories
public interface RelationshipRepository extends JpaRepository<RelationShip, Integer> {

	List<RelationShip> findByUserOneIdAndUserTwoIdAndStatus(User userOneId, User userTwoId, int status);

//	 List<RelationShip> findAllByUserOneIdAndStatus(String id, int status);
//
//	    List<RelationShip> findAllByUserOneIdAndStatusOrUserTwoIdAndStatus(String id1, int status1, String id2, int status2);
//
//	    List<RelationShip> findAllByUserOneIdOrUserTwoIdAndStatusNot(String id1, String id2, int status);
//
//	    RelationShip findByUserOneIdAndUserTwoId(String userOneId, String userTwoId);
//
	RelationShip findAllByUserOneIdOrUserTwoId(User userOneId, User userTwoId);

	
	
	@Query(value = "" + "SELECT r FROM RelationShip AS r "
			+ "WHERE ((r.userOneId.userId = :id1 AND r.userTwoId.userId = :id2) "
			+ "OR ( r.userTwoId.userId = :id1 AND r.userOneId.userId = :id2)) " + "AND r.status = :status")
	RelationShip findRelationshipWithFriendWithStatus(@Param(value = "id1") String userOneId,
			@Param(value = "id2") String userTwoId, @Param(value = "status") int status);

//	    @Query(value = "" +
//	            "SELECT r FROM RelationShip AS r " +

//
//	    @Query(value = "" +
//	            "SELECT r FROM Relationship AS r " +
//	            "WHERE (r.userOneId.userId = :id OR r.userTwoId.userId = :id ) " +
//	            "AND r.status = :status")
//	    List<RelationShip> findRelationshipByUserIdAndStatus(@Param(value = "id") String userId,
//	                                                         @Param(value = "status") int status);
//
//
//	    @Query(value = "" +
//	            "SELECT r FROM Relationship AS r " +
//	            "WHERE ((r.userOneId.userId = :id1 AND r.userTwoId.userId = :id2) " +
//	            "OR ( r.userTwoId.id = :id1 AND r.userOneId.id = :id2)) " +
//	            "AND r.status = :status")
//	    RelationShip findRelationshipWithFriendWithStatus(@Param(value = "id1") String userOneId,
//	                                                      @Param(value = "id2") String userTwoId,
//	                                                      @Param(value = "status") int status);
//
//	    @Query(value = "" +
//	            "SELECT r FROM Relationship AS r " +

//	            "WHERE ((r.userOneId.userId = :id1 AND r.userTwoId.userId = :id2) " +
//	            "OR ( r.userTwoId.userId = :id1 AND r.userOneId.userId = :id2)) ")
//	    RelationShip findRelationshipByUserOneIdAndUserTwoId(@Param(value = "id1") String userOneId,
//	                                                         @Param(value = "id2") String userTwoId);
//
//
//	    @Query(value = "" +

//	            "SELECT r FROM RelationShip AS r " +
//	            "WHERE (r.userOneId.userId = :id OR r.userTwoId.userId = :id) " +
//	            "AND r.status  NOT IN (0 , 2)")
//	    List<RelationShip> findAllNotCandidatesForFriends(@Param(value = "id") String id);

	    @Query(value = "" +
	            "SELECT r FROM RelationShip AS r " +
	            "WHERE (r.userOneId.userId = :id OR r.userTwoId.userId = :id) " )
	    List<RelationShip> findAllRequestedForFriendUsers(@Param(value = "id") String id);



	@Query(value = "" + "SELECT r FROM RelationShip AS r "
			+ "WHERE (r.userOneId.userId = :id OR r.userTwoId.userId = :id ) " + "AND r.status = :status")
	List<RelationShip> findRelationshipByUserIdAndStatus(@Param(value = "id") String userId,
			@Param(value = "status") int status);

	@Query(value = "" + "SELECT r FROM RelationShip AS r "
			+ "WHERE (r.userOneId.userId = :id OR r.userTwoId.userId = :id ) " + "AND r.status = :status " + // Add a
																												// space
																												// after
																												// :status
			"AND (r.userOneId.isOnline = :online OR r.userTwoId.isOnline = :online) ")
	List<RelationShip> findRelationshipByUserIdAndStatusAndOnline(@Param(value = "id") String userId,
			@Param(value = "status") int status, @Param(value = "online") Boolean online);

//	            "SELECT r FROM Relationship AS r " +
//	            "WHERE (r.userOneId.userId = :id OR r.userTwoId.userId = :id) " +
//	            "AND r.status  NOT IN (0 , 2)")
//	    List<RelationShip> findAllNotCandidatesForFriends(@Param(value = "id") String id);
//
//	    @Query(value = "" +
//	            "SELECT r FROM Relationship AS r " +
//	            "WHERE (r.userOneId.userId = :id OR r.userTwoId.userId = :id) " +
//	            "AND r.status = 0")
//	    List<RelationShip> findAllRequestedForFriendUsers(@Param(value = "id") String id);

}
