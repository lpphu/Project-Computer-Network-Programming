package com.project.Controllers;


import java.util.prefs.Preferences;

public class LoginPreferences {
    private static final String USERNAME_KEY = "username";
    private Preferences preferences;

    public LoginPreferences() {
        preferences = Preferences.userRoot().node(getClass().getName());
    }

    public void saveUsername(String username) {
        preferences.put(USERNAME_KEY, username);
    }

    public String getUsername() {
        String defaultValue = null;
        return preferences.get(USERNAME_KEY, defaultValue);
    }

    public void clearPreferences() {
        preferences.remove(USERNAME_KEY);
    }
}

