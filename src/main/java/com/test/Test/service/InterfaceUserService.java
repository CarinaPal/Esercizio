package com.test.Test.service;

import com.test.Test.model.User;
import com.test.Test.model.UserDTO;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface InterfaceUserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserById(String id);
    List<User> searchUsers(Optional<String> firstName, Optional<String> lastName);
    UserDTO updateUser(String id, UserDTO userDTO);
    void deleteUser(String id);
    void loadUsersFromCSV(String filePath) throws IOException;

}
