package com.h154.saesumMBTI.Repository.Result;

import com.h154.saesumMBTI.Domain.Result.ResultDomain;
import com.h154.saesumMBTI.Domain.Result.SelectedMBTIDomain;
import com.h154.saesumMBTI.Enum.MBTIType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class SelectedMBTIRepository {

    @Autowired
    private final EntityManager em;

    public void save(SelectedMBTIDomain selectedMBTIDomain){ em.persist(selectedMBTIDomain);}


    public SelectedMBTIDomain findOne(Long id) { return em.find(SelectedMBTIDomain.class, id); }

    public void remove(Long id){
        SelectedMBTIDomain targetDomain = this.findOne(id);
        if (targetDomain == null){
            throw new RuntimeException("No Such Cons found, Wrong id. ");
        }
        else {
            //remove target user
            em.remove(targetDomain);
        }
    }

    public SelectedMBTIDomain findOneByType(MBTIType mbtiType){
        return em.createQuery("SELECT sb FROM SelectedMBTIDomain sb WHERE mbtiType = :mbtiType", SelectedMBTIDomain.class)
                .setParameter("mbtiType", mbtiType.toString())
                .getSingleResult();

    }


}
