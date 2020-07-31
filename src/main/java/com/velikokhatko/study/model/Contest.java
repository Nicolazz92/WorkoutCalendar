package com.velikokhatko.study.model;

import com.velikokhatko.study.model.base.BaseEntityNamed;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true, exclude = {"members", "winner"})
@Entity
public class Contest extends BaseEntityNamed {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "track_id")
    private Track track;

    @Column(name = "date")
    private LocalDate date;

    @ManyToMany
    @JoinTable(name = "CONTEST_MEMBERS",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "CONTEST_ID")
    )
    private Set<UserProfile> members = new HashSet<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "winner_id")
    private UserProfile winner;

    public Set<UserProfile> getMembers() {
        return Collections.unmodifiableSet(members);
    }

    public void setMembers(Set<UserProfile> set) {
        throw new UnsupportedOperationException("for modify 'members' collection " +
                "use addMember(UserProfile member) and removeMember(UserProfile member)");
    }

    public void addMember(UserProfile member) {
        boolean added = members.add(member);
        if (added) {
            member.addContest(this);
        }
    }

    public void removeMember(UserProfile member) {
        members.remove(member);
        if (member.getContests().contains(this)) {
            member.removeContest(this);
        }
    }
}
