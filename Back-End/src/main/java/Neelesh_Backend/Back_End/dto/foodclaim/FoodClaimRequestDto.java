package Neelesh_Backend.Back_End.dto.foodclaim;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodClaimRequestDto {

    private Long donationId;

    private Long ngoId;

    private Integer requestedMeals;
}