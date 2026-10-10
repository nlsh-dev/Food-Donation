
package Neelesh_Backend.Back_End.Model.donation;

import Neelesh_Backend.Back_End.Model.donation.DonationStatus;
import Neelesh_Backend.Back_End.Model.foodclaim.FoodClaim;
import Neelesh_Backend.Back_End.Model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "donations")
@Getter
@Setter
@NoArgsConstructor
public class Donation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String foodName;

    @Column(length = 500)
    private String description;

    @Column(length = 100)
    private String foodType;

    @Column(nullable = false)
    private Integer totalMeals;

    @Column(nullable = false)
    private Integer remainingMeals;

    @Column(nullable = false, length = 255)
    private String pickupAddress;

    @Column(nullable = false)
    private LocalDateTime pickupBefore;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private DonationStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private User restaurant;


    @OneToMany(mappedBy = "donation")
    private List<FoodClaim> foodClaims = new ArrayList<>();
}
