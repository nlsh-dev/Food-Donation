package Neelesh_Backend.Back_End.controller.delivery;

import Neelesh_Backend.Back_End.Model.delivery.DeliveryStatus;
import Neelesh_Backend.Back_End.dto.delivery.DeliveryRequestDto;
import Neelesh_Backend.Back_End.dto.delivery.DeliveryResponseDto;
import Neelesh_Backend.Back_End.service.delivery.DeliveryService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deliveries")
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DeliveryResponseDto createDelivery(
            @RequestBody DeliveryRequestDto requestDto) {

        return deliveryService.createDelivery(requestDto);
    }

    @GetMapping
    public List<DeliveryResponseDto> getAllDeliveries() {
        return deliveryService.getAllDeliveries();
    }

    @GetMapping("/{id}")
    public DeliveryResponseDto getDeliveryById(
            @PathVariable Long id) {

        return deliveryService.getDeliveryById(id);
    }

    @PatchMapping("/{id}/status")
    public DeliveryResponseDto updateDeliveryStatus(
            @PathVariable Long id,
            @RequestParam DeliveryStatus status) {

        return deliveryService.updateDeliveryStatus(id, status);
    }
}