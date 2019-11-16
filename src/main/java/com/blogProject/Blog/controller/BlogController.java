package com.blogProject.Blog.controller;

import com.blogProject.Blog.dao.Blog;
import com.blogProject.Blog.service.BlogService;
import com.blogProject.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class BlogController
{
    @Autowired
    BlogService blogS;

    @Autowired
    UserService userS;

    @PostMapping(value = "/addBlog",produces = "application/json")
    public Blog addBlog(@RequestBody Blog blog, Principal principal)
    {
        blogS.addBlog(blog,userS.getUserId(principal));
        return blog ;
    }

    @GetMapping(value = "/blogs")
    public List<Blog> getBlog()
    {
        return blogS.getBlog();

    }
    @GetMapping("/search/{searchedItem}")
    public Set<Blog> searchItem(@PathVariable("searchedItem") String searchedItem) {
        Set<Blog> blog = blogS.getSearchedData(searchedItem);
        return blog;
    }

    @GetMapping("/viewBlogs/{userId}")
    public List<Blog> viewBlogs(@PathVariable Long userId) { return blogS.toView(userId); }

    @GetMapping("/deleteBlog/{id}")
    public List<Blog> deleteBlog(@PathVariable Long id) { return blogS.deleteParticularBlog(id); }

    @GetMapping(value = "/getBlog/{id}")
    public Blog getOneBlog(@PathVariable Long id)
    {
        return blogS.getOneBlog(id);
    }
    @PostMapping(value = "/editBlog",produces = "application/json")
    public Blog editBlog(@RequestBody Blog blog)
    {
        return blogS.editBlog(blog);
    }
    @GetMapping("/makePrivate/{id}")
    public List<Blog> makePrivate(@PathVariable Long id) {
        blogS.makeThisPrivate(id);
        return blogS.getBlogList();
    }
    @GetMapping("/makePublic/{id}")
    public List<Blog> makePublic(@PathVariable Long id,Principal principal) {
        blogS.makeThisPublic(id);
        return blogS.getBlogList();
    }

}
