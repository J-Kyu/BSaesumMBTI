package com.h154.saesumMBTI.Repository;

import com.h154.saesumMBTI.Domain.Survey.SelectedQuestionDomain;
import com.h154.saesumMBTI.Domain.Survey.SurveyDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class SelectedQuestionRepository {

    @Autowired
    private final EntityManager em;

    public void save(SelectedQuestionDomain selectedQuestionDomain){ em.persist(selectedQuestionDomain);}

    public SelectedQuestionDomain findOne(Long id) { return em.find(SelectedQuestionDomain.class, id); }

    public void remove(Long id){
        SelectedQuestionDomain targetDomain = this.findOne(id);
        if (targetDomain == null){
            throw new RuntimeException("No Such Survey found, Wrong id. ");
        }
        else {
            //remove target user
            em.remove(targetDomain);
        }
    }

}
