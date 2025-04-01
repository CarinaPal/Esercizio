package com.test.Test.service;

import com.test.Test.mapper.UserMapper;
import com.test.Test.model.User;
import com.test.Test.model.UserDTO;
import com.test.Test.repository.UserRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements InterfaceUserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    public UserDTO createUser(UserDTO userDTO) {
        User user = userMapper.userDTOToUser(userDTO);
        User savedUser = userRepository.save(user);
        return userMapper.userToUserDTO(savedUser);
    }


    public UserDTO getUserById(String id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::userToUserDTO).orElse(null);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    public List<User> searchUsers(Optional<String> firstName, Optional<String> lastName) {

        if (firstName.isPresent() && lastName.isPresent()) {
            return userRepository.findByFirstNameContainingIgnoreCaseAndLastNameContainingIgnoreCase(firstName.get(), lastName.get());
        } else if (firstName.isPresent()) {
            return userRepository.findByFirstNameContainingIgnoreCase(firstName.get());
        } else if (lastName.isPresent()) {
            return userRepository.findByLastNameContainingIgnoreCase(lastName.get());
        } else {
            return userRepository.findAll();
        }
    }


    public UserDTO updateUser(String id, UserDTO userDTO) {
        Optional<User> existingUser = userRepository.findById(id);
        if (existingUser.isPresent()) {
            User user = existingUser.get();
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setAddress(userDTO.getAddress());
            User updatedUser = userRepository.save(user);
            return userMapper.userToUserDTO(updatedUser);
        }
        return null;
    }

    public void deleteUser(String id) {
        userRepository.deleteById(id);
    }


    public void loadUsersFromCSV(String filePath) throws IOException {
        FileReader reader = new FileReader(filePath);
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

        List<User> users = new ArrayList<>();

        for (CSVRecord record : records) {
            User user = new User();
            user.setId(record.get("id"));
            user.setFirstName(record.get("firstName"));
            user.setLastName(record.get("lastName"));
            user.setAddress(record.get("address"));
            user.setEmail(record.get("email"));
            users.add(user);
            logger.info("Users: " + users);
        }
        userRepository.saveAll(users);
    }
}
