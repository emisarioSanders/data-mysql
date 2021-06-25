package com.luis.datamysql.repository;

import com.luis.datamysql.schema.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {

}
