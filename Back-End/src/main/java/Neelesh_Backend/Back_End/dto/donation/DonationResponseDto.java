package Neelesh_Backend.Back_End.dto.donation;

import Neelesh_Backend.Back_End.Model.donation.DonationStatus;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class DonationResponseDto {

    private Long id;
    private Long restaurantId;
    private String restaurantName;
    private String city;
    private String foodName;
    private String description;
    private String foodType;
    private Integer totalMeals;
    private Integer remainingMeals;
    private Integer mealCount;
    private String pickupAddress;
    private LocalDateTime pickupBefore;
    private DonationStatus status;
    private LocalDateTime createdAt;
}