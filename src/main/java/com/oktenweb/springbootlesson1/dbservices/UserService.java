package com.oktenweb.springbootlesson1.dbservices;

import com.oktenweb.springbootlesson1.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends JpaRepository<User, Integer> {
}
