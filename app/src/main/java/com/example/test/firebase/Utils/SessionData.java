package com.example.test.firebase.Utils;

public class SessionData {

    public static SessionData Instance = null;

    public static SessionData getInstance()
    {
        if (Instance == null)
        {
            Instance = new SessionData();
        }
        return Instance;
    }


}
