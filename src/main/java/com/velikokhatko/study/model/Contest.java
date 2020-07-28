package com.velikokhatko.study.model;

import com.velikokhatko.study.model.base.BaseEntityNamed;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = {"members", "winner"})
@Entity
public class Contest extends BaseEntityNamed {

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id")
    private Track track;

    private LocalDate date;

    @ManyToMany
    @JoinTable(name = "CONTEST_MEMBERS",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "CONTEST_ID")
    )
    private Set<UserProfile> members;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private UserProfile winner;

    //TODO добавить методы для ресолва bidirectional связи
}
