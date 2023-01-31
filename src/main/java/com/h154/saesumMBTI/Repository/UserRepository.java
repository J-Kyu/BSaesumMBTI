package com.h154.saesumMBTI.Repository;

import com.h154.saesumMBTI.Domain.UserDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    @Autowired
    private final EntityManager em;

    public void save(UserDomain userDomain){ em.persist(userDomain);}


    public UserDomain findOne(Long id) { return em.find(UserDomain.class, id); }

    public UserDomain findByNickname(String nickname){
        try {
            return em.createQuery("select distinct m from UserDomain m where m.nickname = :nickname", UserDomain.class)
                    .setParameter("nickname", nickname)
                    .getSingleResult();
        }
        catch (NoResultException e){
            return null;
        }
    }

    public void deleteUser(Long id){
        UserDomain targetUser = this.findOne(id);
        if (targetUser == null){
            throw new RuntimeException("No Such User found, Wrong id. ");
        }
        else {
            //remove target user
            em.remove(targetUser);
        }
    }


}
