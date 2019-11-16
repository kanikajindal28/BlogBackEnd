package com.blogProject.Blog.service;
import com.blogProject.Blog.dao.User;
import com.blogProject.Blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService
{
    @Autowired
    private UserRepository urepo;

    public Long getUserId(Principal principal)
    {
        String email = principal.getName();
        Long id = urepo.findByEmail(email).getUserId();
        return id;
    }

    public User getUsersProfile(Principal principal)
    {
        return urepo.findByEmail(principal.getName());
    }

    public User getUserById(Long id)
    {
        return urepo.findByUserId(id);
    }




    public User getUserByEmailAndPassword(String email,String password)
    {
        return urepo.findByEmailAndPassword(email,password);
    }

    public String addUser(User user)
    {
        urepo.save(user);
        return "added";
    }

    public User findByEmail(String email)
    {
        return urepo.findByEmail(email);
    }

    public User editUsersProfile(User users)
    {
        User oldUser = urepo.findByUserId(users.getUserId());
        oldUser.setUserId(users.getUserId());
        oldUser.setName(users.getName());
        oldUser.setEmail(users.getEmail());
        oldUser.setMobileNo(users.getMobileNo());
        oldUser.setPassword(users.getPassword());
        urepo.saveAndFlush(oldUser);
        return oldUser;

    }

    public Set<User> findUser(String searchedItem, Long id) {
        List<User> usersList = urepo.findAll();
        Set<User> result = new HashSet<>();
        User currentUser = urepo.findByUserId(id);

        for(int i=0; i<usersList.size(); i++) {
            if(id != usersList.get(i).getUserId()) {
                if(usersList.get(i).getName().toLowerCase().contains(searchedItem.toLowerCase()) ||
                        usersList.get(i).getEmail().toLowerCase().contains(searchedItem.toLowerCase()) ) {
                    result.add(usersList.get(i));
                }
            }
        }
        return result;
    }

    public User viewingUser(Long id) {
        return urepo.findByUserId(id);
    }
}