package Neelesh_Backend.Back_End.dto.foodrequest;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodRequestRequestDto {

    private Long ngoId;
    private String foodName;
    private Integer requestedMeals;
    private String description;
    private String deliveryAddress;
}