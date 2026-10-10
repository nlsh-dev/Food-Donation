package Neelesh_Backend.Back_End.repository;

import Neelesh_Backend.Back_End.Model.TrackingLocation.TrackingLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrackingLocationRepository
        extends JpaRepository<TrackingLocation, Long> {

    List<TrackingLocation> findByDeliveryIdOrderByRecordedAtAsc(
            Long deliveryId);
}