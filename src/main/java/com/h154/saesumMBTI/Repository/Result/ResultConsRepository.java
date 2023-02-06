package com.h154.saesumMBTI.Repository.Result;

import com.h154.saesumMBTI.Domain.Result.ConsTipDomain;
import com.h154.saesumMBTI.Domain.Result.ResultConsDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class ResultConsRepository {

    @Autowired
    private final EntityManager em;

    public void save(ResultConsDomain resultConsDomain){ em.persist(resultConsDomain);}


    public ResultConsDomain findOne(Long id) { return em.find(ResultConsDomain.class, id); }

    public void remove(Long id){
        ResultConsDomain targetDomain = this.findOne(id);
        if (targetDomain == null){
            throw new RuntimeException("No Such Cons found, Wrong id. ");
        }
        else {
            //remove target user
            em.remove(targetDomain);
        }
    }
}
