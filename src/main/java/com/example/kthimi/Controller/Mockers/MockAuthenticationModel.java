package com.example.kthimi.Controller.Mockers;

import com.example.kthimi.Model.AuthenticationModel;

public class MockAuthenticationModel extends AuthenticationModel {
    @Override
    public boolean authenticateLibrarian(String username, String password) {
        // Return true for a specific set of credentials you want to test
        return "1".equals(username) && "11".equals(password);
    }

    // Implement other methods if required for testing different scenarios
}
