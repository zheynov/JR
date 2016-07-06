package com.javarush.test.level36.lesson04.big01.model;

import com.javarush.test.level36.lesson04.big01.bean.User;
import com.javarush.test.level36.lesson04.big01.model.service.UserService;
import com.javarush.test.level36.lesson04.big01.model.service.UserServiceImpl;

import java.util.List;

public class MainModel implements Model {
    private ModelData modelData = new ModelData();
    private UserService userService = new UserServiceImpl();

    @Override
    public ModelData getModelData() {
        return modelData;
    }

    @Override
    public void loadUsers() {
        modelData.setDisplayDeletedUserList(false);
        List<User> result = activeUsersFilter();
        modelData.setUsers(result);
    }

    public void loadDeletedUsers() {
        modelData.setDisplayDeletedUserList(true);
        List<User> users = userService.getAllDeletedUsers();
        modelData.setUsers(users);
    }

    public void loadUserById(long userId) {
        User user = userService.getUsersById(userId);
        modelData.setActiveUser(user);
    }

    public void deleteUserById(long id) {

        userService.deleteUser(id);
        modelData.setUsers(activeUsersFilter());
        modelData.setDisplayDeletedUserList(false);
    }

    private List<User> activeUsersFilter() {

        List<User> result = userService.getUsersBetweenLevels(1, 100);
        List<User> deletedUsers = userService.getAllDeletedUsers();

        result.removeAll(deletedUsers);

        return userService.filterOnlyActiveUsers(result);
    }

    public void changeUserData(String name, long id, int level) {
        userService.createOrUpdateUser(name, id, level);
        modelData.setUsers(activeUsersFilter());
        modelData.setDisplayDeletedUserList(false);
    }

}

