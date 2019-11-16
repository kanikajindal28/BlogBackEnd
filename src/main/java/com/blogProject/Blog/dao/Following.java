package com.blogProject.Blog.dao;

import javax.persistence.*;

@Entity
public class Following
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long following_id;
    @ManyToOne
    private User currentUser;
    @ManyToOne
    private User following;

    public Following()
    {

    }

    public Following(User currentUser, User following) {
        this.currentUser = currentUser;
        this.following = following;
    }

    public Long getFollowing_id() {
        return following_id;
    }

    public void setFollowing_id(Long following_id) {
        this.following_id = following_id;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getFollowing() {
        return following;
    }

    public void setFollowing(User following) {
        this.following = following;
    }
}
