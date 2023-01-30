package com.h154.saesumMBTI.Domain;

import com.h154.saesumMBTI.Enum.LetterState;
import com.h154.saesumMBTI.Enum.LetterType;
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

    @Enumerated(EnumType.STRING)
    private LetterState letterState;

    @Enumerated(EnumType.STRING)
    private LetterType letterType;

    @Temporal(TemporalType.DATE)
    private Date releaseDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserDomain userDomain;

}
