
package Neelesh_Backend.Back_End.dto.User;

import Neelesh_Backend.Back_End.Model.user.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {

    private Long id;
    private String fullName;
    private String organisationName;
    private String email;
    private String phone;
    private String city;
    private Role role;
}
