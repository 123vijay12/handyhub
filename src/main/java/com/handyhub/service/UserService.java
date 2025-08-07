package com.handyhub.service;

import com.handyhub.dto.UserDTO;
import com.handyhub.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    UserDTO createUser(UserDTO user);

   UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(Long id, UserDTO user);

    void deleteUser(Long id);
}
