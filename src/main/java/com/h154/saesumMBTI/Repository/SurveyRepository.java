package com.h154.saesumMBTI.Repository;

import com.h154.saesumMBTI.Domain.Survey.SelectedQuestionDomain;
import com.h154.saesumMBTI.Domain.Survey.SurveyDomain;
import com.h154.saesumMBTI.Domain.UserDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class SurveyRepository {

    @Autowired
    private final EntityManager em;


    public void save(SurveyDomain surveyDomain){ em.persist(surveyDomain);}

    public SurveyDomain findOne(Long id) { return em.find(SurveyDomain.class, id); }

    public void remove(Long id){
        SurveyDomain targetDomain = this.findOne(id);
        if (targetDomain == null){
            throw new RuntimeException("No Such Survey found, Wrong id. ");
        }
        else {
            //remove target user
            em.remove(targetDomain);
        }
    }

    public SurveyDomain findOneWithTitle(String title){

        SurveyDomain targetDomain = null;

        targetDomain = em.createQuery("SELECT s FROM SurveyDomain s WHERE s.title = :title", SurveyDomain.class)
                .setParameter("title", title)
                .getSingleResult();

        return targetDomain;
    }

}
