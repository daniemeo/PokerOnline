package it.pokeronline.repository.user;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import it.pokeronline.model.user.StatoUser;
import it.pokeronline.model.user.User;



public interface UserRepository extends CrudRepository<User, Long>,QueryByExampleExecutor <User>{

	 User findByUsernameAndPasswordAndStato(String username,String password, StatoUser stato);
}
