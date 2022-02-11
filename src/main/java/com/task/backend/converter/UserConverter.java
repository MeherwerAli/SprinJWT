package com.task.backend.converter;

import java.util.ArrayList;

import com.task.backend.model.User;
import com.task.backend.payload.response.UserDTO;

public class UserConverter {
	
    public static User toDAO(UserDTO userDTO){
        User userDAO = new User();
        
        userDAO.setId(userDTO.getId());
        userDAO.setUserName(userDTO.getUsername());
        userDAO.setEmail(userDTO.getEmail());
        userDAO.setFirstName(userDTO.getFirstName());
        userDAO.setLastName(userDTO.getLastName());
        
        return userDAO;
    }
    public static UserDTO toDTO(User userDAO){
    	UserDTO userDTO = new UserDTO();
        
        userDTO.setId(userDAO.getId());
        userDTO.setUsername(userDAO.getUserName());
        userDTO.setEmail(userDAO.getEmail());
        userDTO.setFirstName(userDAO.getFirstName());
        userDTO.setLastName(userDAO.getLastName());
        
        return userDTO;
    }

    public static ArrayList<User> toDAOList(ArrayList<UserDTO> userDTOs){
        ArrayList<User> userDAOs = new ArrayList<User>();
        userDTOs.forEach(
                dto -> userDAOs.add(toDAO(dto)
                )
        );
        return userDAOs;
    }

    public static ArrayList<UserDTO> toDTOList(ArrayList<User> userDAOs){
        ArrayList<UserDTO> userDTOs = new ArrayList<UserDTO>();
        userDAOs.forEach(
                dao -> userDTOs.add(toDTO(dao)
                )
        );
        return userDTOs;
    }
}
