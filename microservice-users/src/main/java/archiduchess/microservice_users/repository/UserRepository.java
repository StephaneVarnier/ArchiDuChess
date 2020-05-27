package archiduchess.microservice_users.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import archiduchess.microservice_users.modele.User;

public interface UserRepository extends CrudRepository<User, Integer> {
	
	Optional<User> findByUsername (String username) ;
	
	Optional<User> findByEmail(String email);

}
