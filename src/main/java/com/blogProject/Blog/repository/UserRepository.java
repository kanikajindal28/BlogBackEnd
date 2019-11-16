package com.blogProject.Blog.repository;

import com.blogProject.Blog.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findByEmailAndPassword(String email,String password);
    User findByUserId(Object principal);
    User findByEmail(String email);
    User getByEmail(String name);
}




