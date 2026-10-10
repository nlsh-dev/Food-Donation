package Neelesh_Backend.Back_End.repository;

import Neelesh_Backend.Back_End.Model.foodrequest.FoodRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodRequestRepository
        extends JpaRepository<FoodRequest, Long> {
}