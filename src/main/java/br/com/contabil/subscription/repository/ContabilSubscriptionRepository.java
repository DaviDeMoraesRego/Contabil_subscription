package br.com.contabil.subscription.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.contabil.subscription.entity.ContabilSubscriptionEntity;

@Repository
public interface ContabilSubscriptionRepository extends JpaRepository<ContabilSubscriptionEntity, Integer> {

	@Query(value = "SELECT * FROM tab_user_subscription WHERE clerk_id = :clerkId", nativeQuery = true)
	ContabilSubscriptionEntity findByClerkId (@Param("clerkId") String clerkId);
}
