package Neelesh_Backend.Back_End.repository;

import Neelesh_Backend.Back_End.Model.donation.Donation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonationRepository
        extends JpaRepository<Donation, Long> {
}