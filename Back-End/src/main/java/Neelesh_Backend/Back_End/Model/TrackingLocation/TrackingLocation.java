package Neelesh_Backend.Back_End.Model.TrackingLocation;

import Neelesh_Backend.Back_End.Model.delivery.Delivery;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class TrackingLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_id", nullable = false)
    private Delivery delivery;

    private Double latitude;

    private Double longitude;

    private Double speed;

    private LocalDateTime recordedAt;
}