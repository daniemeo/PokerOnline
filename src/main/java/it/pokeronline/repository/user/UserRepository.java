package it.pokeronline.repository.user;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.pokeronline.model.user.StatoUser;
import it.pokeronline.model.user.User;



public interface UserRepository extends CrudRepository<User, Long>,QueryByExampleExecutor <User>{

	 User findByUsernameAndPasswordAndStato(String username,String password, StatoUser stato);
	 
	 @Query("select distinct u from User u left join fetch u.ruoli r")
	 List<User>ListUtentiRuoli();
	 

	 
}
