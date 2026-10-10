
package Neelesh_Backend.Back_End.service.user;

import Neelesh_Backend.Back_End.Model.user.User;
import Neelesh_Backend.Back_End.dto.User.UserRequestDto;
import Neelesh_Backend.Back_End.dto.User.UserResponseDto;
import Neelesh_Backend.Back_End.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    // Create User API
    public UserResponseDto createUser(UserRequestDto userRequestDto) {

        User user = modelMapper.map(userRequestDto, User.class);

        User savedUser = userRepository.save(user);

        return modelMapper.map(savedUser, UserResponseDto.class);
    }


    public List<UserResponseDto> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserResponseDto.class))
                .toList();
    }


    public UserResponseDto getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found with id: " + id));

        return modelMapper.map(user, UserResponseDto.class);
    }
}
