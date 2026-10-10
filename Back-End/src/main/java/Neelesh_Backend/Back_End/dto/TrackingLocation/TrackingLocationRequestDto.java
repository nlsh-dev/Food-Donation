package Neelesh_Backend.Back_End.dto.TrackingLocation;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackingLocationRequestDto {

    private Long deliveryId;

    private Double latitude;

    private Double longitude;

    private Double speed;
}
