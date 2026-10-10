package Neelesh_Backend.Back_End.repository;

import Neelesh_Backend.Back_End.Model.delivery.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryRepository
        extends JpaRepository<Delivery, Long> {
}