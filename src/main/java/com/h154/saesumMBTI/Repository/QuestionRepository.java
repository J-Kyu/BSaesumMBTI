package com.h154.saesumMBTI.Repository;

import com.h154.saesumMBTI.Domain.Survey.QuestionDomain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.patterns.TypePatternQuestions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Slf4j
@Repository
@RequiredArgsConstructor
public class QuestionRepository {


    @Autowired
    private final EntityManager em;


    public void save(QuestionDomain questionDomain){em.persist(questionDomain);}

    public QuestionDomain findOne(Long id){
        QuestionDomain questionDomain = em.find(QuestionDomain.class, id);
        if(questionDomain == null){
            log.info("Not Found");
            throw new RuntimeException("No Such Question found, Wrong id. ");
        }

        return questionDomain;
    }

    public List<QuestionDomain> findPage(int page, int count){
        TypedQuery<QuestionDomain> query = em.createQuery("SELECT q FROM QuestionDomain q", QuestionDomain.class);
        query.setFirstResult(page*count);
        query.setMaxResults(count);

        List<QuestionDomain> questionDomainList = query.getResultList();

        if(questionDomainList.size() <= 0){
            throw new RuntimeException("No Questions available. You have reached the end.");
        }

        return questionDomainList;
    }

    public void remove(Long id){
        QuestionDomain targetDomain = this.findOne(id);
        if(targetDomain == null){
            throw new RuntimeException("No Such Question found, Wrong id. ");

        }
        else {
            em.remove(targetDomain);
        }
    }
}
