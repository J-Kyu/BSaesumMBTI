package com.h154.saesumMBTI.Repository.Result;

import com.h154.saesumMBTI.Domain.Result.ConsTipDomain;
import com.h154.saesumMBTI.Domain.Result.SelectedConsDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class SelectedConsRepository {

    @Autowired
    private final EntityManager em;

    public void save(SelectedConsDomain selectedConsDomain){ em.persist(selectedConsDomain);}


    public SelectedConsDomain findOne(Long id) { return em.find(SelectedConsDomain.class, id); }

    public void remove(Long id){
        SelectedConsDomain targetDomain = this.findOne(id);
        if (targetDomain == null){
            throw new RuntimeException("No Such Cons found, Wrong id. ");
        }
        else {
            //remove target user
            em.remove(targetDomain);
        }
    }

}
