package com.h154.saesumMBTI.Service;

import com.h154.saesumMBTI.DTO.UserDTO;
import com.h154.saesumMBTI.Domain.UserDomain;
import com.h154.saesumMBTI.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long join(UserDomain userDomain){

        validateDuplicateUser(userDomain);
        userRepository.save(userDomain);
        return userDomain.getId();
    }

    public UserDomain findUser(Long id){
        return userRepository.findOne(id);
    }



    private void validateDuplicateUser(UserDomain user){
        //EXCEPTION
        List<UserDomain> findMembers = userRepository.findByNickname(user.getNickname());
        if (!findMembers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public void deleteUserDomain(Long id){
        userRepository.deleteUser(id);
    }


}
