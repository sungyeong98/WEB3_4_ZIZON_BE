package com.ll.dopdang.domain.review.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.ll.dopdang.domain.project.entity.Contract;
import com.ll.dopdang.domain.review.entity.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	@Query(
    "SELECT COUNT(r) > 0 FROM Review r "
    + "WHERE r.contract = :contract "
    + "AND r.deleted = false "
	)
	boolean existsByContract(@Param("contract") Contract contract);

	Optional<Review> findByContract(Contract contract);

	@Query(
		"SELECT r FROM Review r "
			+ "JOIN FETCH r.reviewer "
			+ "JOIN r.contract c "
			+ "WHERE r.deleted = false "
			+ "AND c.expert.id = :expertId "
			+ "AND c.status = 'COMPLETED' "
			+ "ORDER BY r.createdAt DESC"
	)
	Page<Review> findByExpertIdWithReviewer(
		@Param("expertId") Long expertId,
		Pageable pageable
	);

	@Query(
		"SELECT r FROM Review r "
			+ "JOIN FETCH r.contract c "
			+ "JOIN FETCH c.expert e "
			+ "JOIN FETCH e.member m "
			+ "WHERE r.deleted = false "
			+ "AND r.reviewer.id = :clientId "
			+ "AND c.status = 'COMPLETED' "
			+ "ORDER BY r.createdAt DESC"
	)
	Page<Review> findByReviewerIdAndCompletedContract(
		@Param("clientId") Long clientId,
		Pageable pageable
	);
}
