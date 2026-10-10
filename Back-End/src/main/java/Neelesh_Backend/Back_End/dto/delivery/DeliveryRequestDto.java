package Neelesh_Backend.Back_End.dto.delivery;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeliveryRequestDto {

    private Long donationId;

    private Long ngoId;

    private Long driverId;

    private Integer mealCount;

    private String pickupAddress;

    private String deliveryAddress;
}