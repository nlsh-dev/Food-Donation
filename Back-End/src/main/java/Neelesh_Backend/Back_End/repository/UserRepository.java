
package Neelesh_Backend.Back_End.repository;

import Neelesh_Backend.Back_End.Model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}



