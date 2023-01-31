package com.h154.saesumMBTI.Repository;


import com.h154.saesumMBTI.Domain.Survey.AnswerOptionDomain;
import com.h154.saesumMBTI.Domain.Survey.QuestionDomain;
import com.h154.saesumMBTI.Enum.AnswerType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class AnswerOptionRepository {

    @Autowired
    private final EntityManager em;

    public void save(AnswerOptionDomain answerOptionDomain){em.persist(answerOptionDomain);}

    public AnswerOptionDomain findOne(Long id){
        AnswerOptionDomain answerOptionDomain = em.find(AnswerOptionDomain.class, id);
        if(answerOptionDomain == null){
            throw new RuntimeException("No Such Question found, Wrong id. ");
        }

        return answerOptionDomain;
    }

    public List<AnswerOptionDomain> findByType(AnswerType answerType, int page, int count){
        TypedQuery<AnswerOptionDomain> query = em.createQuery("SELECT a FROM AnswerOptionDomain a WHERE a.answerType = :answerType", AnswerOptionDomain.class);
        query.setParameter("answerType",answerType);
        query.setFirstResult(page*count);
        query.setMaxResults(count);

        List<AnswerOptionDomain> answerOptionDomainList = query.getResultList();

        if(answerOptionDomainList.size() <= 0){
            throw new RuntimeException("No Answer Option available. You have reached the end.");
        }

        return answerOptionDomainList;
    }

    public void remove(Long id){
        AnswerOptionDomain targetDomain = this.findOne(id);
        if(targetDomain == null){
            throw new RuntimeException("No Such Answer Option found, Wrong id. ");

        }
        else {
            em.remove(targetDomain);
        }
    }


}
