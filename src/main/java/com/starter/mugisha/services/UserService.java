package com.starter.mugisha.services;

import com.starter.mugisha.dtos.UserUpdateDto;
import com.starter.mugisha.exceptions.ApiRequestException;
import com.starter.mugisha.exceptions.AppException;
import com.starter.mugisha.exceptions.ResourceNotFoundException;
import com.starter.mugisha.models.User;
import com.starter.mugisha.repository.RoleRepository;
import com.starter.mugisha.repository.UserRepository;
import com.starter.mugisha.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    public UserService(UserRepository userRepository,RoleRepository roleRepository){
        this.userRepository=userRepository;
        this.roleRepository=roleRepository;
    }
    public User getUser(UUID userId){
        return this.userRepository.findById(userId).orElseThrow(()-> new ApiRequestException("user with id "+userId+" not found"));
    }
    public User deleteUser(UUID userId){
        User user = userRepository.findById(userId).orElseThrow(()-> new ApiRequestException("user with id "+userId+" not found"));
   this.userRepository.delete(user);

   return user;
    }
    public Page<User> getAllUsers(int page,int size,String column){
        Constants.validatePageNumberAndPageSize(page,size);
        Pageable pageable = (Pageable) PageRequest.of(page,size, Sort.Direction.ASC,column);
        Page<User> users = userRepository.findAll(pageable);
        return users;
    }
    public User getLoggedInUser(){
        String email;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetails){
            email = ((UserDetails) principal).getUsername();
        }else{
            email=principal.toString();
        }
        User findByEmail = userRepository.findByEmail(email).orElseThrow(()-> new ApiRequestException("user with email "+email+" not found"));
    return findByEmail;
    }
    public User updateUser(UUID userId, UserUpdateDto userUpdateRequest){
        User user  = userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("get user by id",""+userId,new User()));
        if(getLoggedInUser().getId() != user.getId()){
            throw new AppException("you are not authorized to update");
        }
        if(userRepository.existsByMobile(userUpdateRequest.getMobile()) && !(userUpdateRequest.getMobile().equalsIgnoreCase(user.getMobile()))){
            throw new AppException("Phone number already in use");
        }else{
            user.setMobile(userUpdateRequest.getMobile());
        }
        user.setFirstName(userUpdateRequest.getFirstName());
        user.setLastName(userUpdateRequest.getLastName());
        user.setGender(userUpdateRequest.getGender());
        user.setEmail(userUpdateRequest.getEmail());
        userRepository.save(user);
        return user;
    }
}
