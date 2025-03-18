package com.tp.bookmydoctor.repository;

import com.tp.bookmydoctor.entity.User;
import com.tp.bookmydoctor.requestdto.UserRequestDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    public Optional<User> findByEmail(String email);

}
