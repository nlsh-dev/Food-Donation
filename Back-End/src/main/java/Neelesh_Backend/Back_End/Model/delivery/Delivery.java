package Neelesh_Backend.Back_End.Model.delivery;

import Neelesh_Backend.Back_End.Model.donation.Donation;
import Neelesh_Backend.Back_End.Model.user.User;
import Neelesh_Backend.Back_End.Model.TrackingLocation.TrackingLocation;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donation_id", nullable = false)
    private Donation donation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ngo_id", nullable = false)
    private User ngo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    private User driver;

    private Integer mealCount;

    private String pickupAddress;

    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    private DeliveryStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime deliveredAt;

    @OneToMany(mappedBy = "delivery")
    private List<TrackingLocation> trackingLocations = new ArrayList<>();
}