package com.h154.saesumMBTI.Service;

import com.h154.saesumMBTI.DTO.AnswerOptionDTO;
import com.h154.saesumMBTI.DTO.QuestionDTO;
import com.h154.saesumMBTI.Domain.Survey.AnswerOptionDomain;
import com.h154.saesumMBTI.Domain.Survey.QuestionDomain;
import com.h154.saesumMBTI.Enum.AnswerType;
import com.h154.saesumMBTI.Repository.AnswerOptionRepository;
import com.h154.saesumMBTI.Repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SurveyService {

    private final QuestionRepository questionRepository;
    private final AnswerOptionRepository answerOptionRepository;


    //Question Service
    @Transactional
    public Long joinQuestion(QuestionDomain questionDomain){
        questionRepository.save(questionDomain);
        return questionDomain.getId();
    }

    @Transactional
    public void removeQuestion(Long id){
        questionRepository.remove(id);
    }


    public QuestionDomain findQuestion(Long id){
        return questionRepository.findOne(id);
    }

    public List<QuestionDTO> findQuestionsByPage(int page, int count){

        List<QuestionDomain> questionDomainList = questionRepository.findPage(page, count);
        List<QuestionDTO> questionDTOList = new ArrayList<QuestionDTO>();
        for ( QuestionDomain qd :   questionDomainList ) {
            questionDTOList.add(new QuestionDTO(qd));
        }
        return questionDTOList;
    }



    //Answer Option Service
    @Transactional
    public Long joinAnswer(AnswerOptionDomain answerOptionDomain){
        answerOptionRepository.save(answerOptionDomain);
        return answerOptionDomain.getId();
    }

    @Transactional
    public void removeAnswerOption(Long id){
        answerOptionRepository.remove(id);
    }


    public AnswerOptionDTO findAnswer(Long id){
        AnswerOptionDomain answerOptionDomain = answerOptionRepository.findOne(id);
        AnswerOptionDTO answerOptionDTO = new AnswerOptionDTO(answerOptionDomain);
        return answerOptionDTO;
    }

    public List<AnswerOptionDTO> findAnswersOptionByType(AnswerType answerType, int page, int count){

        List<AnswerOptionDomain> answerOptionDomainList = answerOptionRepository.findByType(answerType,page, count);
        List<AnswerOptionDTO> answerOptionDTOList = new ArrayList<AnswerOptionDTO>();
        for ( AnswerOptionDomain aod :   answerOptionDomainList ) {
            answerOptionDTOList.add(new AnswerOptionDTO(aod));
        }
        return answerOptionDTOList;
    }



}
