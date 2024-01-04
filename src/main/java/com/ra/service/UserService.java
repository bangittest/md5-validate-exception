package com.ra.service;

import com.ra.exception.CustomException;
import com.ra.model.dao.request.UserRequest;
import com.ra.model.dao.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    Page<UserResponse>findAll(Pageable pageable);
    UserResponse saveOrUpdate(UserRequest userRequest) throws CustomException;
    UserResponse findById(Long id) throws CustomException;

}
