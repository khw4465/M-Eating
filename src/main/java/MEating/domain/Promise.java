package MEating.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "promise")
    private List<MemberPromise> memberPromiseList = new ArrayList<>();

    /** 모임 생성 */
    public static Promise createPromise(Board board, String name, String promiseDate, String meetingPlace, Member... members) {
        Promise promise = new Promise();
        promise.setBoard(board);
        promise.setName(name);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime date = LocalDateTime.parse(promiseDate, formatter);
        promise.setPromiseDate(date);
        promise.setMeetingPlace(meetingPlace);

        for (Member member : members) {
            MemberPromise memberPromise = new MemberPromise();
            memberPromise.setMember(member);
            memberPromise.setPromise(promise);
            memberPromise.setRegDtm(LocalDateTime.now());
            promise.getMemberPromiseList().add(memberPromise);
        }
        return promise;
    }

    /** 기존 모임에 참여 */
    public Promise joinPromise(Member member) {
        MemberPromise memberPromise = new MemberPromise();
        memberPromise.setMember(member);
        memberPromise.setPromise(this);
        memberPromise.setRegDtm(LocalDateTime.now());
        this.getMemberPromiseList().add(memberPromise);

        return this;
    }
}
