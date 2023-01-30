package com.h154.saesumMBTI.Service;

import com.h154.saesumMBTI.DTO.UserDTO;
import com.h154.saesumMBTI.Domain.UserDomain;
import com.h154.saesumMBTI.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(UserDomain userDomain){

        validateDuplicateUser(userDomain.getNickname());
        userRepository.save(userDomain);
        return userDomain.getId();
    }

    public UserDomain findUser(Long id){
        return userRepository.findOne(id);
    }

    public UserDomain findUserByNickname(String nickname){
        return userRepository.findByNickname(nickname);
    }


    private void validateDuplicateUser(String nickname){
        //EXCEPTION
        UserDomain findMember = userRepository.findByNickname(nickname);

        if (findMember != null){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }

    }

    @Transactional
    public void deleteUserDomain(Long id){
        userRepository.deleteUser(id);
    }


}
