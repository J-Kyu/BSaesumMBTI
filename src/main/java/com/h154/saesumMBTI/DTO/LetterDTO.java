package com.h154.saesumMBTI.DTO;

import com.h154.saesumMBTI.Domain.LetterDomain;
import com.h154.saesumMBTI.Enum.LetterState;
import com.h154.saesumMBTI.Enum.LetterType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
public class LetterDTO {
    public LetterDTO(){}

    public LetterDTO(LetterDomain letterDomain){
        this.id = letterDomain.getId();
        this.letterContents = letterDomain.getLetterContents();
        this.letterState = letterDomain.getLetterState();
        this.letterType = letterDomain.getLetterType();
        this.releaseDate = letterDomain.getReleaseDate();
    }

    private Long id;

    private String letterContents;

    @Enumerated(EnumType.STRING)
    private LetterState letterState;

    @Enumerated(EnumType.STRING)
    private LetterType letterType;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;
}
