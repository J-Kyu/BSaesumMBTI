package com.h154.saesumMBTI.Service;

import com.h154.saesumMBTI.DTO.Result.*;
import com.h154.saesumMBTI.Domain.Result.*;
import com.h154.saesumMBTI.Enum.MBTIType;
import com.h154.saesumMBTI.Repository.Result.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResultService {

    private final ConsTipRepository consTipRepository;

    private final HashTagRepository hashTagRepository;

    private final ProsSituationRepository prosSituationRepository;

    private final ResultConsRepository resultConsRepository;

    private final ResultProsRepository resultProsRepository;

    private final ResultRepository resultRepository;

    private final SelectedConsRepository selectedConsRepository;

    private final SelectedProsRepository selectedProsRepository;


    private final SelectedHashTagRepository selectedHashTagRepository;

    private final SelectedSituationRepository selectedSituationRepository;

    private final SelectedTipRepository selectedTipRepository;

    private final SelectedMBTIRepository selectedMBTIRepository;

    /*** PROS ***/
    // PROS DOMAIN
    @Transactional
    public Long joinResultPros(ResultProsDomain resultProsDomain) {
        resultProsRepository.save(resultProsDomain);
        return resultProsDomain.getId();
    }

    public ResultProsDTO findResultPros(Long id) {
        return new ResultProsDTO(resultProsRepository.findOne(id));
    }

    @Transactional
    public void removeResultPros(Long id) {
        resultProsRepository.remove(id);
    }

    @Transactional
    public ResultProsDTO updateResultProsContents(Long id, String contents) {
        ResultProsDomain resultProsDomain = resultProsRepository.findOne(id);
        resultProsDomain.setProsContents(contents);
        return new ResultProsDTO(resultProsDomain);
    }

    // SELECTED PROS
    @Transactional
    public Long joinSelectedPros(SelectedProsDomain selectedProsDomain) {
        selectedProsRepository.save(selectedProsDomain);
        return selectedProsDomain.getId();
    }

    @Transactional
    public void removeSelectedPros(Long id) {
        selectedProsRepository.remove(id);
    }


    /*** SITUATION ***/
    //SITUATION
    @Transactional
    public Long joinProsSituation(ProsSituationDomain prosSituationDomain) {
        prosSituationRepository.save(prosSituationDomain);
        return prosSituationDomain.getId();
    }

    @Transactional
    public ProsSituationDTO updateProsSituation(Long id, String contents) {
        ProsSituationDomain prosSituationDomain = prosSituationRepository.findOne(id);
        prosSituationDomain.setSituationContents(contents);
        return new ProsSituationDTO(prosSituationDomain);
    }

    public ProsSituationDTO findProsSituation(Long id) {
        return new ProsSituationDTO(prosSituationRepository.findOne(id));
    }

    @Transactional
    public void removeProsSituation(Long id) {
        prosSituationRepository.remove(id);
    }

    // SELECTED SITUATION
    @Transactional
    public Long joinSelectedSituation(SelectedSituationDomain selectedSituationDomain) {
        selectedSituationRepository.save(selectedSituationDomain);
        return selectedSituationDomain.getId();
    }

    @Transactional
    public void removeSelectedSituation(Long id) {
        selectedSituationRepository.remove(id);
    }


    /*** CONS ***/
    // CONS
    @Transactional
    public Long joinResultCons(ResultConsDomain resultConsDomain) {
        resultConsRepository.save(resultConsDomain);
        return resultConsDomain.getId();
    }

    @Transactional
    public void updateResultConsContents(Long id, String contents) {
        ResultConsDomain resultConsDomain = resultConsRepository.findOne(id);
        resultConsDomain.setConsContents(contents);
        return;
    }

    public ResultConsDTO findResultCons(Long id) {
        return new ResultConsDTO(resultConsRepository.findOne(id));
    }

    @Transactional
    public void removeResultCons(Long id) {
        resultConsRepository.remove(id);
    }

    // SELECTED CONS
    @Transactional
    public Long joinSelectedCons(SelectedConsDomain selectedConsDomain) {
        selectedConsRepository.save(selectedConsDomain);
        return selectedConsDomain.getId();
    }

    @Transactional
    public void removeSelectedCons(Long id) {
        selectedConsRepository.remove(id);
    }


    /*** TIP ***/
    // TIP
    @Transactional
    public Long joinConsTip(ConsTipDomain consTipDomain) {
        consTipRepository.save(consTipDomain);
        return consTipDomain.getId();
    }

    @Transactional
    public void updateConsTip(Long id, String contents) {

        ConsTipDomain consTipDomain = consTipRepository.findOne(id);
        consTipDomain.setTipContents(contents);
        return;
    }


    public ConsTipDTO findConsTip(Long id) {
        return new ConsTipDTO(consTipRepository.findOne(id));
    }

    @Transactional
    public void removeConsTip(Long id) {
        consTipRepository.remove(id);
    }


    // SELECTED TIP
    @Transactional
    public Long joinSelectedTip(SelectedTipDomain selectedConsDomain) {
        selectedTipRepository.save(selectedConsDomain);
        return selectedConsDomain.getId();
    }

    @Transactional
    public void removeSelectedTip(Long id) {
        selectedTipRepository.remove(id);
    }


    /*** HASH TAG ***/
    //HASH TAG
    @Transactional
    public Long joinHashTag(HashTagDomain hashTagDomain) {
        hashTagRepository.save(hashTagDomain);
        return hashTagDomain.getId();
    }

    public HashTagDTO findHashTag(Long id) {
        return new HashTagDTO(hashTagRepository.findOne(id));
    }

    @Transactional
    public void removeHashTag(Long id) {
        hashTagRepository.remove(id);
    }

    @Transactional
    public HashTagDTO updateHashTag(Long id, String contents) {
        HashTagDomain hashTagDomain = hashTagRepository.findOne(id);
        hashTagDomain.setHashTagContents(contents);
        return new HashTagDTO(hashTagDomain);
    }


    // SELECTED TIP
    @Transactional
    public Long joinSelectedHashTag(SelectedHashTagDomain selectedHashTagDomain) {
        selectedHashTagRepository.save(selectedHashTagDomain);
        return selectedHashTagDomain.getId();
    }

    @Transactional
    public void removeSelectedHashTag(Long id) {
        selectedHashTagRepository.remove(id);
    }


    /*** RESULT  ***/
    @Transactional
    public Long joinResult(ResultDomain resultDomain) {
        resultRepository.save(resultDomain);
        return resultDomain.getId();
    }

    public ResultDTO findResult(Long id) {

        ResultDomain resultDomain = resultRepository.findOne(id);
        ResultDTO resultDTO = new ResultDTO(resultDomain);

        //Find Pros
        for (SelectedProsDomain spd : resultDomain.getSelectedProsDomainList()) {
            resultDTO.getResultProsDTOList().add(new ResultProsDTO(spd.getResultProsDomain()));
        }

        //Find Cons
        for (SelectedConsDomain scd : resultDomain.getSelectedConsDomainList()) {
            resultDTO.getResultConsDTOList().add(new ResultConsDTO(scd.getResultConsDomain()));
        }

        //Find Hash Tag
        for (SelectedHashTagDomain sht : resultDomain.getSelectedHashTagDomainList()) {
            resultDTO.getHashTagDTOList().add(new HashTagDTO(sht.getHashTagDomain()));
        }


        return resultDTO;
    }

    @Transactional
    public void removeResult(Long id) {
        resultRepository.remove(id);
    }


    /*** selectedMBTIRepository ***/

    @Transactional
    public Long joinSelectedMBTI(SelectedMBTIDomain selectedMBTIDomain) {
        selectedMBTIRepository.save(selectedMBTIDomain);
        return selectedMBTIDomain.getId();
    }

    public SelectedMBTIDomain findSelectedMBTIDomain(Long id) {
        SelectedMBTIDomain selectedMBTIDomain = selectedMBTIRepository.findOne(id);
        return selectedMBTIDomain;
    }

    public ResultDomain findMBTIResult(MBTIType mbtiType){
        SelectedMBTIDomain selectedMBTIDomain = selectedMBTIRepository.findOneByType(mbtiType);
        ResultDomain resultDomain = selectedMBTIDomain.getResultDomain();
        return resultDomain;
    }

    @Transactional
    public void removeSelectedMBTI(Long id) {
        selectedMBTIRepository.remove(id);
    }



}
