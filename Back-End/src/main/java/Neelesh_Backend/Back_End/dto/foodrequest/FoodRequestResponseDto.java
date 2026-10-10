package Neelesh_Backend.Back_End.dto.foodrequest;

import Neelesh_Backend.Back_End.Model.foodrequest.FoodRequestStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FoodRequestResponseDto {

    private Long id;

    private Long ngoId;

    private String foodName;

    private Integer requestedMeals;

    private String description;

    private String deliveryAddress;

    private FoodRequestStatus status;

    private LocalDateTime createdAt;
}