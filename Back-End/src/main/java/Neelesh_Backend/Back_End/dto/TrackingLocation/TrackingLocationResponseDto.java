package Neelesh_Backend.Back_End.dto.TrackingLocation;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class TrackingLocationResponseDto {

    private Long id;
    private Long deliveryId;
    private Double latitude;
    private Double longitude;
    private Double speed;
    private LocalDateTime recordedAt;
}