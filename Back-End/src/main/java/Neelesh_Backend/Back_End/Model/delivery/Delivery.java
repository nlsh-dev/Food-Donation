package Neelesh_Backend.Back_End.Model.delivery;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "deliveries")
@Getter
@Setter
@NoArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long donationId;

    @Column(nullable = false)
    private Long ngoId;

    @Column(nullable = false)
    private Long driverId;

    @Column(nullable = false)
    private Integer mealCount;

    @Column(nullable = false, length = 255)
    private String pickupAddress;

    @Column(nullable = false, length = 255)
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DeliveryStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    private LocalDateTime deliveredAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();

        if (status == null) {
            status = DeliveryStatus.ASSIGNED;
        }
    }
}