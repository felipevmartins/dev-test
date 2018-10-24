package br.com.deveria.devtest.userapi.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.deveria.devtest.userapi.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {

}
