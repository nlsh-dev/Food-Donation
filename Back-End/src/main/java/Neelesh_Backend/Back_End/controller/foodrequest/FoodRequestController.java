package Neelesh_Backend.Back_End.controller.foodrequest;

import Neelesh_Backend.Back_End.dto.foodrequest.FoodRequestRequestDto;
import Neelesh_Backend.Back_End.dto.foodrequest.FoodRequestResponseDto;
import Neelesh_Backend.Back_End.service.foodrequest.FoodRequestService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/food-requests")
@RequiredArgsConstructor
public class FoodRequestController {

    private final FoodRequestService foodRequestService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public FoodRequestResponseDto createRequest(
            @RequestBody FoodRequestRequestDto requestDto) {

        return foodRequestService.createRequest(requestDto);
    }

    @GetMapping
    public List<FoodRequestResponseDto> getAllRequests() {

        return foodRequestService.getAllRequests();
    }

    @GetMapping("/{id}")
    public FoodRequestResponseDto getRequestById(
            @PathVariable Long id) {

        return foodRequestService.getRequestById(id);
    }
}