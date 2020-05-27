                                                            //Name: Kiran Gowda
                                                            //ID: 018761559
package com.example.android.simplelogin;

import java.util.HashMap;

public class HMap {
    static HashMap<String, String> hm = new HashMap<>();

    public void user(String uname, String password)
    {
        hm.put(uname,password);
    }
}
