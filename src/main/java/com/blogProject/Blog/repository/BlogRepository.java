package com.blogProject.Blog.repository;

import com.blogProject.Blog.dao.Blog;
import com.blogProject.Blog.dao.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepository extends JpaRepository<Blog,Long> {
    Blog getBlogByPostId(Long id);

    List<Blog> findAllByUserId(User viewUser);

    void deleteByPostId(Long id);
}
