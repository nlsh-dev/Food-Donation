package Neelesh_Backend.Back_End.service.donation;

import Neelesh_Backend.Back_End.Model.donation.Donation;
import Neelesh_Backend.Back_End.Model.donation.DonationStatus;
import Neelesh_Backend.Back_End.Model.user.User;

import Neelesh_Backend.Back_End.dto.donation.DonationRequestDto;
import Neelesh_Backend.Back_End.dto.donation.DonationResponseDto;

import Neelesh_Backend.Back_End.exception.ResourceNotFoundException;

import Neelesh_Backend.Back_End.repository.DonationRepository;
import Neelesh_Backend.Back_End.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;

@Service
@RequiredArgsConstructor
@Transactional
public class DonationService {

    private final DonationRepository donationRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    // Create Donation
    public DonationResponseDto createDonation(
            DonationRequestDto requestDto) {

        // Find the restaurant using restaurantId
        User restaurant = userRepository.findById(
                requestDto.getRestaurantId()
        ).orElseThrow(() ->
                new ResourceNotFoundException(
                        "Restaurant not found with id: "
                                + requestDto.getRestaurantId()
                )
        );

        // Create a new donation.
        // Do not map the request DTO directly to the entity.
        Donation donation = new Donation();

        donation.setFoodName(requestDto.getFoodName());
        donation.setDescription(requestDto.getDescription());
        donation.setFoodType(requestDto.getFoodType());

        // Initialize meal quantities
        Integer mealCount = requestDto.getMealCount();

        donation.setTotalMeals(mealCount);
        donation.setRemainingMeals(mealCount);

        donation.setPickupAddress(requestDto.getPickupAddress());
        donation.setPickupBefore(requestDto.getPickupBefore());

        // Set initial status
        donation.setStatus(DonationStatus.AVAILABLE);

        // Set the required restaurant relationship
        donation.setRestaurant(restaurant);

        // Save the new donation.
        // The database generates its ID.
        Donation savedDonation = donationRepository.save(donation);

        return modelMapper.map(
                savedDonation,
                DonationResponseDto.class
        );
    }


    // Get All Donations
    @Transactional(readOnly = true)
    public List<DonationResponseDto> getAllDonations() {

        return donationRepository.findAll()
                .stream()
                .map(donation ->
                        modelMapper.map(
                                donation,
                                DonationResponseDto.class
                        )
                )
                .toList();
    }


    // Get Donation By ID
    @Transactional(readOnly = true)
    public DonationResponseDto getDonationById(Long id) {

        Donation donation = donationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Donation not found with id: " + id
                        )
                );

        return modelMapper.map(
                donation,
                DonationResponseDto.class
        );
    }


    // Search Donations By Food Name
    @Transactional(readOnly = true)
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
                                .contains(searchTerm)
                )
                .map(donation ->
                        modelMapper.map(
                                donation,
                                DonationResponseDto.class
                        )
                )
                .toList();
    }


    // Update Donation
    public DonationResponseDto updateDonation(
            Long id,
            DonationRequestDto requestDto) {

        // Find the existing donation
        Donation donation = donationRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Donation not found with id: " + id
                        )
                );

        // Update normal fields explicitly.
        // Do not map the request DTO into the existing entity.
        donation.setFoodName(requestDto.getFoodName());
        donation.setDescription(requestDto.getDescription());
        donation.setFoodType(requestDto.getFoodType());
        donation.setPickupAddress(requestDto.getPickupAddress());
        donation.setPickupBefore(requestDto.getPickupBefore());

        // Update meal quantities when a valid count is supplied
        Integer mealCount = requestDto.getMealCount();

        if (mealCount != null && mealCount > 0) {
            donation.setTotalMeals(mealCount);
            donation.setRemainingMeals(mealCount);
        }

        // Update the restaurant only if restaurantId is supplied
        if (requestDto.getRestaurantId() != null) {

            User restaurant = userRepository.findById(
                    requestDto.getRestaurantId()
            ).orElseThrow(() ->
                    new ResourceNotFoundException(
                            "Restaurant not found with id: "
                                    + requestDto.getRestaurantId()
                    )
            );

            donation.setRestaurant(restaurant);
        }

        // Save the existing donation
        Donation updatedDonation = donationRepository.save(donation);

        return modelMapper.map(
                updatedDonation,
                DonationResponseDto.class
        );
    }
}