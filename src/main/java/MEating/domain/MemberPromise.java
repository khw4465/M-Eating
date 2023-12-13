package MEating.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class MemberPromise extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_promise_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "promise_id")
    private Promise promise;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //==연관관계 메서드==//
    public void addMemberPromise(Member member, Promise promise) {
        this.member = member;
        this.promise = promise;

        member.getMemberPromiseList().add(this);
        promise.getMemberPromiseList().add(this);
    }

}
