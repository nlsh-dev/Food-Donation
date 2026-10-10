package Neelesh_Backend.Back_End.dto.delivery;

import Neelesh_Backend.Back_End.Model.delivery.DeliveryStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DeliveryResponseDto {

    private Long id;

    private Long donationId;

    private Long ngoId;

    private Long driverId;

    private Integer mealCount;

    private String pickupAddress;

    private String deliveryAddress;

    private DeliveryStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime deliveredAt;
}