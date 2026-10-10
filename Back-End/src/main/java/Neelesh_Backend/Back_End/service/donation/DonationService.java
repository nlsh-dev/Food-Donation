
package Neelesh_Backend.Back_End.service.donation;

import Neelesh_Backend.Back_End.Model.donation.Donation;
import Neelesh_Backend.Back_End.Model.donation.DonationStatus;
import Neelesh_Backend.Back_End.dto.donation.DonationRequestDto;
import Neelesh_Backend.Back_End.dto.donation.DonationResponseDto;
import Neelesh_Backend.Back_End.exception.ResourceNotFoundException;
import Neelesh_Backend.Back_End.repository.DonationRepository;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class DonationService {

    private final DonationRepository donationRepository;
    private final ModelMapper modelMapper;

    // Create Donation
    public DonationResponseDto createDonation(
            DonationRequestDto requestDto) {

        Donation donation = modelMapper.map(
                requestDto, Donation.class);

        Integer mealCount = requestDto.getMealCount();

        donation.setTotalMeals(mealCount);
        donation.setRemainingMeals(mealCount);
        donation.setStatus(DonationStatus.AVAILABLE);

        Donation savedDonation =
                donationRepository.save(donation);

        return modelMapper.map(
                savedDonation, DonationResponseDto.class);
    }

    // Get All Donations
    public List<DonationResponseDto> getAllDonations() {

        return donationRepository.findAll()
                .stream()
                .map(donation -> modelMapper.map(
                        donation, DonationResponseDto.class))
                .toList();
    }

    // Get Donation By ID
    public DonationResponseDto getDonationById(Long id) {

        Donation donation = donationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Donation not found with id: " + id));

        return modelMapper.map(
                donation, DonationResponseDto.class);
    }

    // Search Donations By Food Name
    public List<DonationResponseDto> searchDonations(
            String foodName) {

        if (foodName == null || foodName.isBlank()) {
            return getAllDonations();
        }

        String searchTerm = foodName.toLowerCase(Locale.ROOT);

        return donationRepository.findAll()
                .stream()
                .filter(donation ->
                        donation.getFoodName() != null
                                && donation.getFoodName()
                                .toLowerCase(Locale.ROOT)
                                .contains(searchTerm))
                .map(donation -> modelMapper.map(
                        donation, DonationResponseDto.class))
                .toList();
    }

    // Update Donation
    public DonationResponseDto updateDonation(
            Long id, DonationRequestDto requestDto) {

        Donation donation = donationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Donation not found with id: " + id));

        modelMapper.map(requestDto, donation);

        if (requestDto.getMealCount() != null
                && requestDto.getMealCount() > 0) {

            donation.setTotalMeals(requestDto.getMealCount());
            donation.setRemainingMeals(requestDto.getMealCount());
        }

        Donation updatedDonation =
                donationRepository.save(donation);

        return modelMapper.map(
                updatedDonation, DonationResponseDto.class);
    }
}
