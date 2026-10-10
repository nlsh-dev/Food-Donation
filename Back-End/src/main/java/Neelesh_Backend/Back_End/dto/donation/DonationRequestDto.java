package Neelesh_Backend.Back_End.dto.donation;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class DonationRequestDto {

    private Long restaurantId;
    private String foodName;
    private String description;
    private String foodType;
    private Integer mealCount;
    private String pickupAddress;
    private LocalDateTime pickupBefore;
}