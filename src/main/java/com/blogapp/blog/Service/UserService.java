package com.blogapp.blog.Service;

import com.blogapp.blog.Payloads.UserDto;

import java.util.List;

public interface UserService {

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer id);
    void deleteUser(Integer id);
    List<UserDto> getAllUsers();
    UserDto getUserById(Integer id);

}
