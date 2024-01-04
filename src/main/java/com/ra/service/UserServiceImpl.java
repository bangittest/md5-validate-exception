package com.ra.service;

import com.ra.exception.CustomException;
import com.ra.model.dao.request.UserRequest;
import com.ra.model.dao.response.UserResponse;
import com.ra.model.entity.User;
import com.ra.repository.UserReponsitory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    @Autowired
    private UserReponsitory userReponsitory;
    private PasswordEncoder passwordEncoder;
    @Override
    public Page<UserResponse> findAll(Pageable pageable) {
        Page<User>users=userReponsitory.findAll(pageable);
        return users.map(UserResponse::new);
    }

    @Override
    public UserResponse saveOrUpdate(UserRequest userRequest) throws CustomException {
        // check trung
        if(userReponsitory.existsByUserName(userRequest.getUserName())){
            throw new CustomException("UserName existed");
        }
        User user = User.builder()
                .userName(userRequest.getUserName()).
                fullName(userRequest.getFullName()).
                password(passwordEncoder.encode(userRequest.getPassword())).build();
        User userNew = userReponsitory.save(user);
        return UserResponse.builder().id(userNew.getId()).userName(userNew.getUserName()).fullName(user.getFullName()).build();
    }

    @Override
    public UserResponse findById(Long id) throws CustomException {
        Optional<User> userOptional = userReponsitory.findById(id);
        if (userOptional.isPresent()){
            User user = userOptional.get();

            return UserResponse.builder().
                    userName(user.getUserName()).
                    fullName(user.getFullName()).status(user.getStatus())
                    .build();
        }

        throw new CustomException("not fount");
    }
}
