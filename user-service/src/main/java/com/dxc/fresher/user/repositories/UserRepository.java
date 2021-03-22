package com.dxc.fresher.user.repositories;

import com.dxc.fresher.user.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    public User findByUserName(String userName);
}
