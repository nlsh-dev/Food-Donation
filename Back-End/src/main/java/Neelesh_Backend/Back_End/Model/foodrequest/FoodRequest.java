package Neelesh_Backend.Back_End.Model.foodrequest;

import Neelesh_Backend.Back_End.Model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "food_requests")
@Getter
@Setter
@NoArgsConstructor
public class FoodRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(nullable = false, length = 150)
    private String foodName;

    @Column(nullable = false)
    private Integer requestedMeals;

    @Column(length = 500)
    private String description;

    @Column(nullable = false, length = 150)
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FoodRequestStatus status;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();

        if (status == null) {
            status = FoodRequestStatus.PENDING;
        }
    }




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ngo_id", nullable = false)
    private User ngo;


}