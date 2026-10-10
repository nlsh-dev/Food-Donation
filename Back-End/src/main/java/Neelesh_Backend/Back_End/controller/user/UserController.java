
package Neelesh_Backend.Back_End.controller.user;

import Neelesh_Backend.Back_End.dto.User.UserRequestDto;
import Neelesh_Backend.Back_End.dto.User.UserResponseDto;
import Neelesh_Backend.Back_End.service.user.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Create User API
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponseDto createUser(
            @RequestBody UserRequestDto userRequestDto) {

        return userService.createUser(userRequestDto);
    }

    // Get All Users API
    @GetMapping
    public List<UserResponseDto> getAllUsers() {

        return userService.getAllUsers();
    }

    // Get User By ID API
    @GetMapping("/{id}")
    public UserResponseDto getUserById(@PathVariable Long id) {

        return userService.getUserById(id);
    }
}
