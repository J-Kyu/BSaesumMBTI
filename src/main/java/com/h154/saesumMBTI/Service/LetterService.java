package com.h154.saesumMBTI.Service;

import com.h154.saesumMBTI.DTO.LetterDTO;
import com.h154.saesumMBTI.Domain.LetterDomain;
import com.h154.saesumMBTI.Enum.LetterState;
import com.h154.saesumMBTI.Repository.Letter.LetterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LetterService {

    private final LetterRepository letterRepository;

    @Transactional
    public LetterDTO joinLetter(LetterDomain letterDomain){
        letterRepository.save(letterDomain);
        return new LetterDTO(letterDomain);
    }

    public LetterDTO findLetter(Long id){
       return new LetterDTO(letterRepository.findOne(id));
    }

    @Transactional
    public void removeLetter(Long id){
        letterRepository.remove(id);
    }

    @Transactional
    public LetterDTO updateLetterContents(Long id, String contents){
        LetterDomain letterDomain = letterRepository.findOne(id);
        letterDomain.setLetterContents(contents);
        return new LetterDTO(letterDomain);
    }

    @Transactional
    public LetterDTO finishLetterContents(Long id, String contents){
        LetterDomain letterDomain = letterRepository.findOne(id);
        letterDomain.setLetterContents(contents);

        letterDomain.setLetterState(LetterState.DONE);

        return new LetterDTO(letterDomain);
    }


}
