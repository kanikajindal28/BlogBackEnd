package com.blogProject.Blog.service;

import com.blogProject.Blog.dao.Blog;
import com.blogProject.Blog.dao.Followers;
import com.blogProject.Blog.dao.Following;
import com.blogProject.Blog.dao.User;
import com.blogProject.Blog.repository.BlogRepository;
import com.blogProject.Blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.security.Principal;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class BlogService {
    @Autowired
    BlogRepository brepo;

    @Autowired
    UserRepository urepo;

    public Blog addBlog(Blog blog, Long userId) {
        User current = urepo.findByUserId(userId);
        blog.setUserId(current);
        return brepo.save(blog);
    }

    public Blog getOneBlog(Long id) {
        return brepo.getBlogByPostId(id);
    }

    public List<Blog> getBlog() {
        return brepo.findAll();
    }

    public Blog editBlog(Blog blog) {
        Blog oldBlog = brepo.getBlogByPostId(blog.getPostId());
        oldBlog.setPostId(blog.getPostId());
        oldBlog.setContent(blog.getContent());
        oldBlog.setDate(new Date());
        oldBlog.setTitle(blog.getTitle());
        brepo.saveAndFlush(oldBlog);
        return oldBlog;
    }

    public Set<Blog> getSearchedData(String searchedItem) {
        List<Blog> blogsList = brepo.findAll();
        Set<Blog> result = new HashSet<>();

        for (int i = 0; i < blogsList.size(); i++) {
            if (blogsList.get(i).getTitle().toLowerCase().contains(searchedItem.toLowerCase()) ||
                    blogsList.get(i).getContent().toLowerCase().contains(searchedItem.toLowerCase()) ||
                    blogsList.get(i).getUserId().getName().toLowerCase().contains(searchedItem.toLowerCase()) ||
                    blogsList.get(i).getDate().toString().toLowerCase().split(" ")[0].contains(searchedItem.toLowerCase())) {
                result.add(blogsList.get(i));
            }
        }
        return result;
    }

    public List<Blog> toView(Long userId) {
        User viewUser = urepo.findByUserId(userId);
        return brepo.findAllByUserId(viewUser);
    }

    public List<Blog> deleteParticularBlog(Long id) {
        brepo.deleteByPostId(id);
        return brepo.findAll();
    }

    public void makeThisPrivate(Long id) {
        Blog oldBlog = brepo.getBlogByPostId(id);
        oldBlog.setPostId(id);
        oldBlog.setPrivate(true);
        brepo.saveAndFlush(oldBlog);
    }

    public void makeThisPublic(Long id) {
        Blog oldBlog = brepo.getBlogByPostId(id);
        oldBlog.setPostId(id);
        oldBlog.setPrivate(false);
        brepo.saveAndFlush(oldBlog);
    }

    public List<Blog> getBlogList() {
        return brepo.findAll();
    }
}

