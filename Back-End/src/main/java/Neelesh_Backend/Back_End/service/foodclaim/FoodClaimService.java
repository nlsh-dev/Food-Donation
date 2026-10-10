package Neelesh_Backend.Back_End.service.foodclaim;

import Neelesh_Backend.Back_End.Model.foodclaim.FoodClaim;
import Neelesh_Backend.Back_End.Model.foodclaim.FoodClaimStatus;
import Neelesh_Backend.Back_End.dto.foodclaim.FoodClaimRequestDto;
import Neelesh_Backend.Back_End.dto.foodclaim.FoodClaimResponseDto;
import Neelesh_Backend.Back_End.exception.ResourceNotFoundException;
import Neelesh_Backend.Back_End.repository.FoodClaimRepository;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodClaimService {

    private final FoodClaimRepository foodClaimRepository;
    private final ModelMapper modelMapper;


    public FoodClaimResponseDto createClaim(
            FoodClaimRequestDto requestDto) {

        FoodClaim foodClaim =
                modelMapper.map(requestDto, FoodClaim.class);

        foodClaim.setStatus(FoodClaimStatus.PENDING);

        FoodClaim savedClaim =
                foodClaimRepository.save(foodClaim);

        return modelMapper.map(
                savedClaim, FoodClaimResponseDto.class);
    }




    public List<FoodClaimResponseDto> getAllClaims() {

        return foodClaimRepository.findAll()
                .stream()
                .map(claim -> modelMapper.map(
                        claim, FoodClaimResponseDto.class))
                .toList();
    }




    public FoodClaimResponseDto getClaimById(Long id) {

        FoodClaim foodClaim =
                foodClaimRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Food claim not found with id: " + id));

        return modelMapper.map(
                foodClaim, FoodClaimResponseDto.class);
    }
}