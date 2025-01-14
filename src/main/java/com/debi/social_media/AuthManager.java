package com.debi.social_media;

import com.debi.social_media.dao.UserDataAccessor;
import com.debi.social_media.exceptions.UserNotFoundException;
import com.debi.social_media.models.User;
import com.debi.social_media.utils.PasswordHasher;

import java.sql.SQLException;

public class AuthManager {
    private static AuthManager instance = null;
    private User currentUser;
    private AuthManager() {}
    public static AuthManager getInstance() {
        if (instance == null) {
            instance = new AuthManager();
        }
        return instance;
    }
    public User getCurrentUser() {
        return instance.currentUser;
    }
    public boolean isLoggedIn() {
        return currentUser != null;
    }
    public boolean login(String username, String password) throws UserNotFoundException, SQLException, ClassNotFoundException {
        UserDataAccessor userdao = new UserDataAccessor();
        User user = userdao.FindUserByUsername(username);
        userdao.Shutdown();
        if (PasswordHasher.verifyPassword(password, user.getPassword())) {
            currentUser = user;
            return true;
        }
        else {
            return false;
        }
    }
    public void logout() {
        currentUser = null;
    }
}
