package Neelesh_Backend.Back_End.service.foodrequest;

import Neelesh_Backend.Back_End.Model.foodrequest.FoodRequest;
import Neelesh_Backend.Back_End.Model.foodrequest.FoodRequestStatus;
import Neelesh_Backend.Back_End.dto.foodrequest.FoodRequestRequestDto;
import Neelesh_Backend.Back_End.dto.foodrequest.FoodRequestResponseDto;
import Neelesh_Backend.Back_End.exception.ResourceNotFoundException;
import Neelesh_Backend.Back_End.repository.FoodRequestRepository;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FoodRequestService {

    private final FoodRequestRepository foodRequestRepository;
    private final ModelMapper modelMapper;

    public FoodRequestResponseDto createRequest(
            FoodRequestRequestDto requestDto) {

        FoodRequest foodRequest =
                modelMapper.map(requestDto, FoodRequest.class);

        foodRequest.setStatus(FoodRequestStatus.PENDING);

        FoodRequest savedRequest =
                foodRequestRepository.save(foodRequest);

        return modelMapper.map(
                savedRequest, FoodRequestResponseDto.class);
    }

    public List<FoodRequestResponseDto> getAllRequests() {

        return foodRequestRepository.findAll()
                .stream()
                .map(request -> modelMapper.map(
                        request, FoodRequestResponseDto.class))
                .toList();
    }

    public FoodRequestResponseDto getRequestById(Long id) {

        FoodRequest foodRequest =
                foodRequestRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Food request not found with id: " + id));

        return modelMapper.map(
                foodRequest, FoodRequestResponseDto.class);
    }
}