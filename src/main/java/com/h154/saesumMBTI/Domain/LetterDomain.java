package com.h154.saesumMBTI.Domain;

import com.h154.saesumMBTI.Enum.LetterState;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
public class LetterDomain {

    @Id
    @GeneratedValue
    @Column(name="letter_id")
    private Long id;

    @Column(length = 200)
    private String letterContents;

    @Enumerated(EnumType.ORDINAL)
    private LetterState letterState;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

}
