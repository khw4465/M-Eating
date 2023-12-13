package MEating.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class MemberRegion extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_region_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    //==연관관계 메서드==//
    public void addMemberRegion(Member member, Region region) {
        this.member = member;
        this.region = region;
        member.getMemberRegionList().add(this);
        region.getMemberRegionList().add(this);
    }
}
