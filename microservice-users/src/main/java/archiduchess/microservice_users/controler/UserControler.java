package archiduchess.microservice_users.controler;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import archiduchess.microservice_users.exceptions.UserNotFoundException;
import archiduchess.microservice_users.modele.User;
import archiduchess.microservice_users.repository.UserRepository;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path="/archiduchess")
public class UserControler {

		@Autowired
		private UserRepository userRepo;
		
		Logger log = LoggerFactory.getLogger(this.getClass());
		
		@ApiOperation(value = "Ajoute un utilisateur en base")
		@PostMapping(path="users/register")
		public ResponseEntity<Void> createUser(@Valid @RequestBody User user) {
			log.info(" =======> " + user.getUsername() + " :: " + user.getPassword() + " :: "+ user.getEmail() + " *******");
			
			User registeredUser = userRepo.save(user) ; 
			
			if (registeredUser==null) 
				return ResponseEntity.noContent().build();
			
			URI uri = ServletUriComponentsBuilder
					.fromCurrentRequest()
					.path("/{id}")
					.buildAndExpand(registeredUser.getId())
					.toUri();
			
			return ResponseEntity.created(uri).build();
		}
		
		@ApiOperation(value = "Liste tous les utilisateurs en base.")
		@GetMapping(path="/users")
		public @ResponseBody Iterable<User> getAllUsers() {
			return userRepo.findAll();
		}
		
		@ApiOperation(value = "Rechercher un utilisateur par son login")
		@GetMapping(path="/users/{username}")
		public @ResponseBody Optional<User> findUserByUsername(@PathVariable String username ) {
			Optional<User> optionalUser = userRepo.findByUsername(username);
			 
			if(!optionalUser.isPresent()) throw new UserNotFoundException("The user "+username+" has not registered yet");
			
			return optionalUser;
		}
		
		@ApiOperation(value = "Rechercher un utilisateur par son adresse e-mail")
		@GetMapping(path="/users/email/{email}")
		public @ResponseBody Optional<User> findUserByEmail(@PathVariable String email ) {
			Optional<User> optionalUser = userRepo.findByEmail(email);
			 
			if(!optionalUser.isPresent()) throw new UserNotFoundException("There is no user registered with the email address "+email);
			
			return optionalUser;
		}
		
		
}
