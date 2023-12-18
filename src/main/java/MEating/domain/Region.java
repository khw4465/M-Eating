package MEating.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.*;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
    
    private Region(RegionName name) {
        this.name = name;
    }

    //== 지역별 공유 객체 생성 ==//
    private static final Map<RegionName, Region> regionMap = new EnumMap<>(RegionName.class);

    //== key값으로 들어온 지역명이 존재하지 않으면 객체 추가 ==//
    public static Region region(RegionName name) {
        Region region = regionMap.computeIfAbsent(name, Region::new);
        region.setRegDtm(LocalDateTime.now()); // 등록일시
        return region;
    }

    @Override
    public String toString() {
        return "Region{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
