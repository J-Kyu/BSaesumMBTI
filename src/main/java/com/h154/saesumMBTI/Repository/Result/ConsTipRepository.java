package com.h154.saesumMBTI.Repository.Result;

import com.h154.saesumMBTI.Domain.Result.ConsTipDomain;
import com.h154.saesumMBTI.Domain.UserDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
@RequiredArgsConstructor
public class ConsTipRepository {

    @Autowired
    private final EntityManager em;

    public void save(ConsTipDomain consTipDomain){ em.persist(consTipDomain);}


    public ConsTipDomain findOne(Long id) { return em.find(ConsTipDomain.class, id); }

    public void remove(Long id){
        ConsTipDomain targetDomain = this.findOne(id);
        if (targetDomain == null){
            throw new RuntimeException("No Such Cons found, Wrong id. ");
        }
        else {
            //remove target user
            em.remove(targetDomain);
        }
    }




}
