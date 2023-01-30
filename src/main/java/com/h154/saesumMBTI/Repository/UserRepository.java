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


    public UserDomain findOne(Long id) {
        UserDomain userDomain = new UserDomain();

        userDomain = em.find(UserDomain.class, id);

        return userDomain;
    }

    public List<UserDomain> findByNickname(String nickname){
        return em. createQuery("select m from UserDomain m where m.nickname = :nickname", UserDomain.class )
                .setParameter("nickname", nickname)
                .getResultList();
    }

    public void deleteUser(Long id){
        UserDomain targetUser = this.findOne(id);
        em.remove(targetUser);
    }


}
