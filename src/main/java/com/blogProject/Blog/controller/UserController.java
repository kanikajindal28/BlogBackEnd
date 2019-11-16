package com.blogProject.Blog.controller;

import com.blogProject.Blog.dao.User;
import com.blogProject.Blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
public class UserController {

    @Autowired
    UserService userS;

    @GetMapping(path = "/validateLogin" , produces = "application/json")
    public String validateLogin()
    {
        return "\"valid\"";
    }

    @PostMapping(value = "/addUsers",produces = "application/json")
    public User addUser(@RequestBody User user)
    {
        user.setRole("user");
        userS.addUser(user);
        return user ;
    }

    @RequestMapping(value="/logout",method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth);

        if(auth!=null)
        {
            new SecurityContextLogoutHandler().logout(request,response,auth);
            request.getSession().invalidate();
        }
        return "/home";
    }

    @GetMapping(value = "/getuser")
    public User getCurrentUser(Principal principal)
    {
        return userS.getUsersProfile(principal);
    }
    @GetMapping(value = "/getUserById/{id}")
    public User getUserById(@PathVariable Long id)
    {
        return userS.getUserById(id);
    }

    @PostMapping(value = "/edituser",produces = "application/json")
    public User editCurrentUser(@RequestBody User user)
    {
        return userS.editUsersProfile(user);
    }


    @GetMapping("/viewUser/{id}")
    public User viewUser(@PathVariable Long id)
    {
        return userS.viewingUser(id);
    }

    @GetMapping("/searchUser/{searchedItem}")
    public Set<User> searchUser(@PathVariable("searchedItem") String searchedItem, Principal principal) {
        Set<User> users = userS.findUser(searchedItem, userS.getUserId(principal));
        return users;
    }


}
