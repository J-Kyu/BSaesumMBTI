package com.h154.saesumMBTI.Service;

import com.h154.saesumMBTI.Controller.Body.SurveyBody;
import com.h154.saesumMBTI.DTO.Survey.AnswerOptionDTO;
import com.h154.saesumMBTI.DTO.Survey.QuestionDTO;
import com.h154.saesumMBTI.DTO.Survey.SurveyDTO;
import com.h154.saesumMBTI.Domain.Survey.*;
import com.h154.saesumMBTI.Enum.AnswerType;
import com.h154.saesumMBTI.Repository.Survey.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
    private final SelectedQuestionRepository selectedQuestionRepository;

    private final SelectedAnswerRepository selectedAnswerRepository;

    private final SurveyRepository surveyRepository;


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


    public QuestionDTO findQuestion(Long id){
        return new QuestionDTO(questionRepository.findOne(id));
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

    public AnswerOptionDomain findAnswerOptionDomain(Long id){
        return answerOptionRepository.findOne(id);
    }



    //Survey & Selected Question
    @Transactional
    public SurveyDTO joinSurvey(String body) throws ParseException {

        //parse body
        JSONParser jsonParse = new JSONParser();

        //create survey domain
        SurveyDomain surveyDomain = new SurveyDomain();
        JSONObject jsonObject = (JSONObject) jsonParse.parse(body);

        //check duplicated name


        // Set survey Title
        surveyDomain.setTitle(String.valueOf(jsonObject.get("surveyTitle")));

        //selected question id list
        JSONArray jsonArray = (JSONArray) jsonObject.get("selectedQuestions");
        for (Object o  : jsonArray) {
            Long id = (Long) o;

            //create Selected Question Domain
            SelectedQuestionDomain selectedQuestionDomain = new SelectedQuestionDomain();
            selectedQuestionDomain.setSurveyDomain(surveyDomain);



            //find given question entity
            QuestionDomain tempQuestion = questionRepository.findOne(id);
            selectedQuestionDomain.setQuestionDomain(tempQuestion);


            //add on survey Domain
            surveyDomain.addQuestion(selectedQuestionDomain);

            //save Selected Question Domain
            selectedQuestionRepository.save(selectedQuestionDomain);

        }

        //save survey
        surveyRepository.save(surveyDomain);

        return new SurveyDTO(surveyDomain);
    }

    @Transactional
    public SurveyDTO joinSurvey(SurveyBody surveyBody){


        //create survey domain
        SurveyDomain surveyDomain = new SurveyDomain();
        surveyDomain.setTitle(surveyBody.getSurveyTitle());

        for (Long id : surveyBody.getSelectedQuestionsId()){
            //create Selected Question Domain
            SelectedQuestionDomain selectedQuestionDomain = new SelectedQuestionDomain();
            selectedQuestionDomain.setSurveyDomain(surveyDomain);



            //find given question entity
            QuestionDomain tempQuestion = questionRepository.findOne(id);
            selectedQuestionDomain.setQuestionDomain(tempQuestion);


            //add on survey Domain
            surveyDomain.addQuestion(selectedQuestionDomain);

            //save Selected Question Domain
            selectedQuestionRepository.save(selectedQuestionDomain);
        }


        //save survey
        surveyRepository.save(surveyDomain);

        return new SurveyDTO(surveyDomain);
    }


    @Transactional
    public void removeSurvey(Long id){
        surveyRepository.remove(id);
    }

    public SurveyDTO findSurveyWithId(Long id){
        return new SurveyDTO(surveyRepository.findOne(id));
    }

    public SurveyDTO findSurveyWithTitle(String title){
        return new SurveyDTO(surveyRepository.findOneWithTitle(title));
    }
    public SurveyDomain findSurveyDomain(Long id){
        return surveyRepository.findOne(id);
    }

    //selected answer domain

    @Transactional
    public Long joinSelectedAnswer(SelectedAnswerDomain selectedAnswerDomain){
        selectedAnswerRepository.save(selectedAnswerDomain);
        return selectedAnswerDomain.getId();
    }

    public SelectedAnswerDomain findSelectedAnswerDomain(Long id){
        return selectedAnswerRepository.findOne(id);
    }
    @Transactional
    public void removeSelectedAnswer(Long id){
        selectedAnswerRepository.remove(id);
    }



}
