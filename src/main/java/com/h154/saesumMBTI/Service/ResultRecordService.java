package com.h154.saesumMBTI.Service;

import com.h154.saesumMBTI.Controller.Body.FinishResultRecordBody;
import com.h154.saesumMBTI.DTO.Result.ResultRecordDTO;
import com.h154.saesumMBTI.Domain.Result.ResultDomain;
import com.h154.saesumMBTI.Domain.Result.ResultRecordDomain;
import com.h154.saesumMBTI.Domain.Result.SelectedMBTIDomain;
import com.h154.saesumMBTI.Domain.Survey.AnswerOptionDomain;
import com.h154.saesumMBTI.Domain.Survey.SelectedAnswerDomain;
import com.h154.saesumMBTI.Enum.AnswerType;
import com.h154.saesumMBTI.Enum.MBTIType;
import com.h154.saesumMBTI.Enum.ResultRecordState;
import com.h154.saesumMBTI.Repository.Result.ResultRecordRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResultRecordService {

    private final ResultRecordRepository resultRecordRepository;

    private final SurveyService surveyService;

    private final ResultService resultService;


    @Transactional
    public Long joinResultRecord(ResultRecordDomain resultRecordDomain){
        resultRecordRepository.save(resultRecordDomain);
        return resultRecordDomain.getId();
    }

    public ResultRecordDTO findResultRecord(Long id){
        return new ResultRecordDTO(resultRecordRepository.findOne(id));
    }

    @Transactional
    public void removeResultRecord(Long id){
        resultRecordRepository.remove(id);
    }

    @Transactional
    public void finishResultRecord(Long id, String body) throws ParseException  {

        ResultRecordDomain resultRecordDomain = resultRecordRepository.findOne(id);

        //parse body
        JSONParser jsonParse = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParse.parse(body);

        JSONArray jsonArray = (JSONArray) jsonObject.get("selectedAnswerOptions");

        //create mbti
        Map<AnswerType, Integer> mbti = new HashMap<>();
        for (AnswerType at: AnswerType.values()) {
            mbti.put(at,0);
        }

        for (Object o  : jsonArray) {
            Long answerOptionId = (Long) o;
            AnswerOptionDomain answerOptionDomain = surveyService.findAnswerOptionDomain(answerOptionId);

            //accumulate answer type
            AnswerType at = answerOptionDomain.getAnswerType();
            int weight = answerOptionDomain.getWeight();
            mbti.put(at, mbti.get(at)+weight);

            //create selected answer
            SelectedAnswerDomain selectedAnswerDomain = new SelectedAnswerDomain();
            selectedAnswerDomain.setAnswerOptionDomain(answerOptionDomain);
            selectedAnswerDomain.setResultRecordDomain(resultRecordDomain);
            surveyService.joinSelectedAnswer(selectedAnswerDomain);

            //add selected answer domain to result record domain
            resultRecordDomain.getSelectedAnswerDomainList().add(selectedAnswerDomain);
        }

        // calculate total mbti
        MBTIType mbtiType = this.CalculateMBTI(mbti);

        //create result
        ResultDomain resultDomain = resultService.findMBTIResult(mbtiType);
        resultRecordDomain.setResultDomain(resultDomain);

        resultRecordDomain.setResultRecordState(ResultRecordState.DONE);

    }

    @Transactional
    public ResultRecordDTO finishResultRecord(Long id, FinishResultRecordBody body) throws ParseException  {

        ResultRecordDomain resultRecordDomain = resultRecordRepository.findOne(id);


        //create mbti
        Map<AnswerType, Integer> mbti = new HashMap<>();
        for (AnswerType at: AnswerType.values()) {
            mbti.put(at,0);
        }

        for (Long answerOptionId  : body.getSelectedAnswerOptions()) {
            AnswerOptionDomain answerOptionDomain = surveyService.findAnswerOptionDomain(answerOptionId);

            //accumulate answer type
            AnswerType at = answerOptionDomain.getAnswerType();
            int weight = answerOptionDomain.getWeight();
            mbti.put(at, mbti.get(at)+weight);

            //create selected answer
            SelectedAnswerDomain selectedAnswerDomain = new SelectedAnswerDomain();
            selectedAnswerDomain.setAnswerOptionDomain(answerOptionDomain);
            selectedAnswerDomain.setResultRecordDomain(resultRecordDomain);
            surveyService.joinSelectedAnswer(selectedAnswerDomain);

            //add selected answer domain to result record domain
            resultRecordDomain.getSelectedAnswerDomainList().add(selectedAnswerDomain);
        }

        // calculate total mbti
        MBTIType mbtiType = this.CalculateMBTI(mbti);

        //create result
        ResultDomain resultDomain = resultService.findMBTIResult(mbtiType);
        resultRecordDomain.setResultDomain(resultDomain);

        resultRecordDomain.setResultRecordState(ResultRecordState.DONE);
        return new ResultRecordDTO(resultRecordDomain);
    }


    private MBTIType CalculateMBTI(Map<AnswerType,Integer> mbti){

        String result = "";

        // E vs I
        if (mbti.get(AnswerType.E) >= mbti.get(AnswerType.I)){
            result+="E";
        }
        else{
            result+="I";
        }

        // X vs H
        if (mbti.get(AnswerType.X) >= mbti.get(AnswerType.H)){
            result+="X";
        }
        else{
            result+="H";
        }

        // F vs T
        if (mbti.get(AnswerType.F) >= mbti.get(AnswerType.T)){
            result+="F";
        }
        else{
            result+="T";
        }

        // J vs P
        if (mbti.get(AnswerType.J) >= mbti.get(AnswerType.P)){
            result+="J";
        }
        else{
            result+="P";
        }

        return MBTIType.valueOf(result);
    }



}
