/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movietime.storage;

import java.io.Serializable;
import java.util.ArrayList;
import movietime.accounts.User;

public class UserData implements Serializable{
    static final long serialVersionUID = 1L;
    
    ArrayList<User> userList;

    public UserData(ArrayList<User> userList) {
        this.userList = userList;
    }

    public ArrayList<User> getUserList() {
        return userList;
    }
}
