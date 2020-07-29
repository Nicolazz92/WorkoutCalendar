package com.velikokhatko.study.model;

import com.velikokhatko.study.model.base.BaseEntityNamed;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
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

    public Set<UserProfile> getMembers() {
        return Collections.unmodifiableSet(members);
    }

    public void addMember(UserProfile member) {
        boolean added = members.add(member);
        if (added) {
            member.addContest(this);
        }
    }

    public void removeMember(UserProfile member) {
        boolean removed = members.remove(member);
        if (removed) {
            member.removeContest(this);
        }
    }
}
