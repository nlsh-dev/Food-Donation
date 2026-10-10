
package Neelesh_Backend.Back_End.Model.foodclaim;

import Neelesh_Backend.Back_End.Model.donation.Donation;
import Neelesh_Backend.Back_End.Model.user.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "food_claims")
@Getter
@Setter
@NoArgsConstructor
public class FoodClaim {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer requestedMeals;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodClaimStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();

        if (status == null) {
            status = FoodClaimStatus.PENDING;
        }
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "donation_id", nullable = false)
    private Donation donation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ngo_id", nullable = false)
    private User ngo;
}
