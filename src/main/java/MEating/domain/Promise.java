package MEating.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Promise extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "promise_id")
    private Long id;
    //== 모임명 ==//
    private String name;
    //== 모임일시 ==//
    private LocalDateTime promiseDate;
    //== 모임장소 ==//
    private String meetingPlace;
    //== 메모 ==//
    private String memo;

    //== 모임 <--> 게시물 ==//
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    //== 모임 <--> 회원 ==//
    @OneToMany(mappedBy = "promise")
    private List<MemberPromise> memberPromiseList = new ArrayList<>();
}
