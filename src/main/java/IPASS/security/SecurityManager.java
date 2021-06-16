package IPASS.security;

import IPASS.DomeinModellen.Account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SecurityManager implements Serializable {

    private static SecurityManager securityManager;

    public static SecurityManager getInstance(){
        if(securityManager==null) securityManager = new SecurityManager();
        return securityManager;
    }

    private SecurityManager(){}

    public boolean deserialize(SecurityManager securityManager){
        if(securityManager==null) return false;
        this.securityManager = securityManager;
        return true;
    }

    //private List<MyUser> allUsers = new ArrayList<>();

//    public boolean registerUser(MyUser toAdd){
//        if(!this.allUsers.contains(toAdd))  return this.allUsers.add(toAdd);
//        return false;
//    }

    public Account getUserByName(String username) {
        return Account.getAlleAccounten().stream().filter(user -> user.getName().equals(username)).findFirst().orElse(null);
    }

    public String validateLogin(String username, String password){
        if(username==null || username.isBlank() || password == null || password.isBlank()) return null;
        var account = this.getUserByName(username);
        if(account == null) return null;
        return account.checkPassword(password) ? account.getRole() : null;
    }
}
