package com.myblog.repository;

import com.myblog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long>
{
    //custom query
   Optional<User> findByUsername(String userName);//(select * from username where name=username)
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String userName,String email);
    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
// findBy    =      where clause (hibernate internally convert the findbY TO "WHERE" CLAUSE WHILE WE CREATE CUSTOM QUERY
// exists    =      CHECKING N RETURNING BOOLEAN VALUR
