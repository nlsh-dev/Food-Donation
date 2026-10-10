package Neelesh_Backend.Back_End.controller.foodclaim;

import Neelesh_Backend.Back_End.dto.foodclaim.FoodClaimRequestDto;
import Neelesh_Backend.Back_End.dto.foodclaim.FoodClaimResponseDto;
import Neelesh_Backend.Back_End.service.foodclaim.FoodClaimService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
@RequiredArgsConstructor
public class FoodClaimController {

    private final FoodClaimService foodClaimService;

    // Create a new food claim
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FoodClaimResponseDto createClaim(
            @RequestBody FoodClaimRequestDto requestDto) {

        return foodClaimService.createClaim(requestDto);
    }

    // Get all food claims
    @GetMapping
    public List<FoodClaimResponseDto> getAllClaims() {

        return foodClaimService.getAllClaims();
    }

    // Get a food claim by ID
    @GetMapping("/{id}")
    public FoodClaimResponseDto getClaimById(
            @PathVariable Long id) {

        return foodClaimService.getClaimById(id);
    }
}