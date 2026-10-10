package Neelesh_Backend.Back_End.config;

import Neelesh_Backend.Back_End.Model.donation.Donation;
import Neelesh_Backend.Back_End.Model.user.User;
import Neelesh_Backend.Back_End.dto.donation.DonationResponseDto;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {

        ModelMapper modelMapper = new ModelMapper();

        // Decide which user name becomes restaurantName
        Converter<User, String> restaurantNameConverter = context -> {
            User restaurant = context.getSource();

            if (restaurant == null) {
                return null;
            }

            String organisationName = restaurant.getOrganisationName();

            if (organisationName != null
                    && !organisationName.isBlank()) {
                return organisationName;
            }

            return restaurant.getFullName();
        };

        // Explicitly resolve the ambiguous restaurantName mapping
        modelMapper.typeMap(
                Donation.class,
                DonationResponseDto.class
        ).addMappings(mapper ->
                mapper.using(restaurantNameConverter)
                        .map(
                                Donation::getRestaurant,
                                DonationResponseDto::setRestaurantName
                        )
        );

        return modelMapper;
    }
}