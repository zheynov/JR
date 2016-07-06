package com.javarush.test.level36.lesson04.big01;

import com.javarush.test.level36.lesson04.big01.controller.Controller;

import com.javarush.test.level36.lesson04.big01.model.MainModel;
import com.javarush.test.level36.lesson04.big01.model.Model;

import com.javarush.test.level36.lesson04.big01.view.EditUserView;
import com.javarush.test.level36.lesson04.big01.view.UsersView;

public class Solution {
    public static void main(String[] args) {
        Model model = new MainModel();


        Controller controller = new Controller();
        controller.setModel(model);

        UsersView usersView = new UsersView();
        usersView.setController(controller);

        EditUserView editUserView = new EditUserView();
        editUserView.setController(controller);

        controller.setUsersView(usersView);
        controller.setEditUserView(editUserView);


        usersView.fireEventShowAllUsers();
        usersView.fireEventShowDeletedUsers();

        usersView.fireEventOpenUserEditForm(126);

        editUserView.fireEventUserDeleted(123);
        editUserView.fireEventUserChanged("Petrosyan", 124, 3);

    }
}
