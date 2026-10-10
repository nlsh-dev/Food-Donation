package Neelesh_Backend.Back_End.service.TrackingLocation;

import Neelesh_Backend.Back_End.Model.TrackingLocation.TrackingLocation;
import Neelesh_Backend.Back_End.dto.TrackingLocation.TrackingLocationRequestDto;
import Neelesh_Backend.Back_End.dto.TrackingLocation.TrackingLocationResponseDto;
import Neelesh_Backend.Back_End.exception.ResourceNotFoundException;
import Neelesh_Backend.Back_End.repository.TrackingLocationRepository;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrackingLocationService {

    private final TrackingLocationRepository trackingLocationRepository;
    private final ModelMapper modelMapper;

    public TrackingLocationResponseDto saveLocation(
            TrackingLocationRequestDto requestDto) {

        TrackingLocation location =
                modelMapper.map(
                        requestDto, TrackingLocation.class);

        TrackingLocation savedLocation =
                trackingLocationRepository.save(location);

        return modelMapper.map(
                savedLocation,
                TrackingLocationResponseDto.class);
    }

    public List<TrackingLocationResponseDto> getLocationsByDeliveryId(
            Long deliveryId) {

        List<TrackingLocation> locations =
                trackingLocationRepository
                        .findByDeliveryIdOrderByRecordedAtAsc(
                                deliveryId);

        return locations.stream()
                .map(location -> modelMapper.map(
                        location,
                        TrackingLocationResponseDto.class))
                .toList();
    }

    public TrackingLocationResponseDto getLocationById(Long id) {

        TrackingLocation location =
                trackingLocationRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Tracking location not found with id: "
                                                + id));

        return modelMapper.map(
                location,
                TrackingLocationResponseDto.class);
    }
}