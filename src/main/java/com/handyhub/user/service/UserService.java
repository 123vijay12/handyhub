package com.handyhub.user.service;

import com.handyhub.user.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    UserDTO createUser(UserDTO user);

   UserDTO getUserById(Long id);

    List<UserDTO> getAllUsers();

    UserDTO updateUser(Long id, UserDTO user);

    void deleteUser(Long id);
}
