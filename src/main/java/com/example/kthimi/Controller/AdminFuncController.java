package com.example.kthimi.Controller;

import com.example.kthimi.Model.AdministratorModel;
import com.example.kthimi.Model.LibrarianModel;
import com.example.kthimi.Model.ManagerModel;

import java.util.ArrayList;

public class AdminFuncController extends AdministratorModel {

    static ArrayList<ManagerModel> managers = AdministratorModel.getManagers();

    public static void deleteManager(ManagerModel mag) {
        for (int i=0;i<managers.size();i++) {
            if (managers.get(i).getUsername().equals(mag.getUsername()) ) {
                managers.remove(i);
                return;
            }
        }
    }

    public static void updateManagers(ManagerModel mag) {

        for (int i=0;i<managers.size();i++){
            if (managers.get(i).getUsername().equals(mag.getUsername())) {
                managers.get(i).setEmail( mag.getEmail() );
                managers.get(i).setPhone( mag.getPhone() );
                managers.get(i).setSalary( mag.getSalary() );
                managers.get(i).setPassword( mag.getPassword() );
                managers.get(i).setUsername( mag.getUsername() );
                return;
            }

        }

    }
}
