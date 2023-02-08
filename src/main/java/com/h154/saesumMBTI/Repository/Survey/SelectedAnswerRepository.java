package com.h154.saesumMBTI.Repository.Survey;

import com.h154.saesumMBTI.Domain.Survey.SelectedAnswerDomain;
import com.h154.saesumMBTI.Domain.Survey.SelectedQuestionDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
@RequiredArgsConstructor
public class SelectedAnswerRepository {

    @Autowired
    private final EntityManager em;

    public void save(SelectedAnswerDomain selectedAnswerDomain){ em.persist(selectedAnswerDomain);}

    public SelectedAnswerDomain findOne(Long id) { return em.find(SelectedAnswerDomain.class, id); }

    public void remove(Long id){
        SelectedAnswerDomain targetDomain = this.findOne(id);
        if (targetDomain == null){
            throw new RuntimeException("No Such Selected Answer found, Wrong id. ");
        }
        else {
            //remove target user
            em.remove(targetDomain);
        }
    }


}
