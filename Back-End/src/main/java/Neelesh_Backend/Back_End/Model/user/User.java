package Neelesh_Backend.Back_End.Model.user;

import Neelesh_Backend.Back_End.Model.delivery.Delivery;
import Neelesh_Backend.Back_End.Model.donation.Donation;
import Neelesh_Backend.Back_End.Model.foodclaim.FoodClaim;
import Neelesh_Backend.Back_End.Model.foodrequest.FoodRequest;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String fullName;

    @Column(length = 150)
    private String organisationName;

    @Column(nullable = false, unique = true, length = 150)
    private String email;

    @Column(length = 255)
    private String passwordHash;

    @Column(length = 20)
    private String phone;

    @Column(length = 100)
    private String city;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Role role;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }


    ///jpa relationship

    @OneToMany(mappedBy = "restaurant")
    private List<Donation> donations = new ArrayList<>();

    @OneToMany(mappedBy = "ngo")
    private List<FoodClaim> foodClaims = new ArrayList<>();

    @OneToMany(mappedBy = "ngo")
    private List<FoodRequest> foodRequests = new ArrayList<>();

    @OneToMany(mappedBy = "driver")
    private List<Delivery> assignedDeliveries = new ArrayList<>();

    @OneToMany(mappedBy = "ngo")
    private List<Delivery> ngoDeliveries = new ArrayList<>();
}