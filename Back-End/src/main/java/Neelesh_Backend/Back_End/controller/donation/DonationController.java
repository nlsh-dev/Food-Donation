
package Neelesh_Backend.Back_End.controller.donation;

import Neelesh_Backend.Back_End.dto.donation.DonationRequestDto;
import Neelesh_Backend.Back_End.dto.donation.DonationResponseDto;
import Neelesh_Backend.Back_End.service.donation.DonationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donations")
@RequiredArgsConstructor
public class DonationController {

    private final DonationService donationService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DonationResponseDto createDonation(
            @RequestBody DonationRequestDto requestDto) {

        return donationService.createDonation(requestDto);
    }

    @GetMapping
    public List<DonationResponseDto> getAllDonations(
            @RequestParam(required = false) String search) {

        if (search != null && !search.isBlank()) {
            return donationService.searchDonations(search);
        }

        return donationService.getAllDonations();
    }


    @GetMapping("/{id}")
    public DonationResponseDto getDonationById(
            @PathVariable Long id) {

        return donationService.getDonationById(id);
    }


    @PutMapping("/{id}")
    public DonationResponseDto updateDonation(
            @PathVariable Long id,
            @RequestBody DonationRequestDto requestDto) {

        return donationService.updateDonation(id, requestDto);
    }
}
