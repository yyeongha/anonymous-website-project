package com.example.askproject.Service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.askproject.Model.DAO.UserDAO;
import com.example.askproject.Model.DTO.UserDTO;
import com.example.askproject.Model.Entity.UserEntity;
import com.example.askproject.Service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDAO userDAO;

    @Override
    public void deleteUser(String userId) throws Exception{
        // TODO Auto-generated method stub
        userDAO.deleteUser(userId);
    }

    @Override
    public List<String> findAllUserId() throws Exception{
        // TODO Auto-generated method stub
        return userDAO.findAllUserId();
    }

    @Override
    public UserDTO findByUserId(String userId) throws Exception{
        // TODO Auto-generated method stub
        UserEntity entity = userDAO.findByUserId(userId);
        UserDTO dto = new UserDTO();
        dto.setUserId(entity.getUserId());
        dto.setUserEmail(entity.getUserEmail());
        dto.setUserNickname(entity.getUserNickname());
        dto.setUserRole(entity.getUserRole());
        dto.setUserPassword(entity.getUserPassword());
        return dto;
    }


    @Override
    public void updateUser(UserDTO userDTO) throws Exception{
        // TODO Auto-generated method stub
        UserEntity entity = userDAO.findByUserId(userDTO.getUserId());
        entity.setUserNickname(userDTO.getUserNickname());
        userDAO.updateUser(entity);
    }

    @Override
    public boolean existsByUserId(String userId) throws Exception{
        // TODO Auto-generated method stub
        return userDAO.existsByUserId(userId);
    }

    @Override
    public String findNicknameByUserId(String userId) throws Exception{
        return userDAO.findByUserId(userId).getUserNickname();
    }

}
