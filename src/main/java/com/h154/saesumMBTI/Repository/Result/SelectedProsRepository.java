package com.h154.saesumMBTI.Repository.Result;

import com.h154.saesumMBTI.Domain.Result.ConsTipDomain;
import com.h154.saesumMBTI.Domain.Result.SelectedProsDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class SelectedProsRepository {

    @Autowired
    private final EntityManager em;

    public void save(SelectedProsDomain selectedProsDomain){ em.persist(selectedProsDomain);}


    public SelectedProsDomain findOne(Long id) { return em.find(SelectedProsDomain.class, id); }

    public void remove(Long id){
        SelectedProsDomain targetDomain = this.findOne(id);
        if (targetDomain == null){
            throw new RuntimeException("No Such Cons found, Wrong id. ");
        }
        else {
            //remove target user
            em.remove(targetDomain);
        }
    }
}
