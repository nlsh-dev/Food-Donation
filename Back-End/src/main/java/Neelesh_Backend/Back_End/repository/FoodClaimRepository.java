package Neelesh_Backend.Back_End.repository;

import Neelesh_Backend.Back_End.Model.foodclaim.FoodClaim;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodClaimRepository
        extends JpaRepository<FoodClaim, Long> {
}