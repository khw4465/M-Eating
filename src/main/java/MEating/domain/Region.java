package MEating.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Region extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "region_id")
    private Long id;
    //== 지역명 ==//
    @Enumerated(EnumType.STRING)
    private RegionName name;

    //== 지역 <--> 회원 ==//
    @OneToMany(mappedBy = "region")
    private List<MemberRegion> memberRegionList = new ArrayList<>();

    //== 지역 <--> 게시물 ==//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "region")
    private List<Board> boards = new ArrayList<>();
}
