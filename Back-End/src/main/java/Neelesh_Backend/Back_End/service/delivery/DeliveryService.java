package Neelesh_Backend.Back_End.service.delivery;

import Neelesh_Backend.Back_End.Model.delivery.Delivery;
import Neelesh_Backend.Back_End.Model.delivery.DeliveryStatus;
import Neelesh_Backend.Back_End.dto.delivery.DeliveryRequestDto;
import Neelesh_Backend.Back_End.dto.delivery.DeliveryResponseDto;
import Neelesh_Backend.Back_End.exception.ResourceNotFoundException;
import Neelesh_Backend.Back_End.repository.DeliveryRepository;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final ModelMapper modelMapper;

    public DeliveryResponseDto createDelivery(
            DeliveryRequestDto requestDto) {

        Delivery delivery =
                modelMapper.map(requestDto, Delivery.class);

        delivery.setStatus(DeliveryStatus.ASSIGNED);

        Delivery savedDelivery =
                deliveryRepository.save(delivery);

        return modelMapper.map(
                savedDelivery, DeliveryResponseDto.class);
    }

    public List<DeliveryResponseDto> getAllDeliveries() {

        return deliveryRepository.findAll()
                .stream()
                .map(delivery ->
                        modelMapper.map(
                                delivery,
                                DeliveryResponseDto.class))
                .toList();
    }

    public DeliveryResponseDto getDeliveryById(Long id) {

        Delivery delivery =
                deliveryRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Delivery not found with id: " + id));

        return modelMapper.map(
                delivery, DeliveryResponseDto.class);
    }

    public DeliveryResponseDto updateDeliveryStatus(
            Long id, DeliveryStatus status) {

        Delivery delivery =
                deliveryRepository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Delivery not found with id: " + id));

        delivery.setStatus(status);

        if (status == DeliveryStatus.DELIVERED) {
            delivery.setDeliveredAt(LocalDateTime.now());
        }

        Delivery updatedDelivery =
                deliveryRepository.save(delivery);

        return modelMapper.map(
                updatedDelivery, DeliveryResponseDto.class);
    }
}