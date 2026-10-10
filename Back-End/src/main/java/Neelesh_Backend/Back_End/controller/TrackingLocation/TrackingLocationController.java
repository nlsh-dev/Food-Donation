package Neelesh_Backend.Back_End.controller.TrackingLocation;

import Neelesh_Backend.Back_End.dto.TrackingLocation.TrackingLocationRequestDto;
import Neelesh_Backend.Back_End.dto.TrackingLocation.TrackingLocationResponseDto;
import Neelesh_Backend.Back_End.service.TrackingLocation.TrackingLocationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tracking")
@RequiredArgsConstructor
public class TrackingLocationController {

    private final TrackingLocationService trackingLocationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TrackingLocationResponseDto saveLocation(
            @RequestBody TrackingLocationRequestDto requestDto) {

        return trackingLocationService.saveLocation(requestDto);
    }

    @GetMapping("/delivery/{deliveryId}")
    public List<TrackingLocationResponseDto> getLocationsByDeliveryId(
            @PathVariable Long deliveryId) {

        return trackingLocationService
                .getLocationsByDeliveryId(deliveryId);
    }

    @GetMapping("/{id}")
    public TrackingLocationResponseDto getLocationById(
            @PathVariable Long id) {

        return trackingLocationService.getLocationById(id);
    }
}