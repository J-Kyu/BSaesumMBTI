package com.h154.saesumMBTI.Repository.Letter;

import com.h154.saesumMBTI.Domain.LetterDomain;
import com.h154.saesumMBTI.Domain.UserDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

@Repository
@RequiredArgsConstructor
public class LetterRepository {

    @Autowired
    private final EntityManager em;

    public void save(LetterDomain letterDomain){ em.persist(letterDomain);}


    public LetterDomain findOne(Long id) { return em.find(LetterDomain.class, id); }


    public void remove(Long id){
        LetterDomain tagetDomain = this.findOne(id);
        if (tagetDomain == null){
            throw new RuntimeException("No Such User found, Wrong id. ");
        }
        else {
            //remove target user
            em.remove(tagetDomain);
        }
    }



}
