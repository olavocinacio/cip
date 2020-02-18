package com.holaf.easypay.data;

import android.app.Activity;

import com.holaf.easypay.data.model.LoggedInUser;
import com.holaf.easypay.souce.SaveClass;
import com.holaf.easypay.souce.SaveLogin;
import com.holaf.easypay.souce.User;

import java.io.File;
import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {
    public static  interface Logable {
        String getPath();
        void d(Object o);
    }

    public Result<LoggedInUser> login(String email, String password,Logable data) {

        try {
            // TODO: handle loggedInUser authentication
            String s = new String(data.getPath()+"/"+SaveLogin.crip(email) + SaveLogin.crip(password) + ".ho");
            if (new File(s).exists())
                throw new User.FileAlreadyExistSException("usuario ja cadastrado");
         User u = (User) SaveClass.load(s);
         data.d(u);
         LoggedInUser fakeUser = new LoggedInUser(java.util.UUID.randomUUID().toString(), u.getName());
         return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}
