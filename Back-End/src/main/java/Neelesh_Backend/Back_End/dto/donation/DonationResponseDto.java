package Neelesh_Backend.Back_End.dto.donation;




import Neelesh_Backend.Back_End.Model.donation.DonationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DonationResponseDto {

    private Long id;

    private String foodName;

    private String description;

    private String foodType;

    private Integer mealCount;

    private Integer remainingMeals;

    private String pickupAddress;

    private LocalDateTime pickupBefore;

    private DonationStatus status;

    private LocalDateTime createdAt;

    // Added during the relationship phase
    private String restaurantName;

    private String city;
}

