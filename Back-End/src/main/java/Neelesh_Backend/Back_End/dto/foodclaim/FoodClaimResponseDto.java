package Neelesh_Backend.Back_End.dto.foodclaim;

import Neelesh_Backend.Back_End.Model.foodclaim.FoodClaimStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FoodClaimResponseDto {

    private Long id;

    private Long donationId;

    private Long ngoId;

    private Integer requestedMeals;

    private FoodClaimStatus status;

    private LocalDateTime createdAt;
}