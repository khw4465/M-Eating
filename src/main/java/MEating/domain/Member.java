package MEating.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;
    //== 로그인 아이디 ==//
    private String loginId;
    //== 비밀번호 ==//
    private String password;
    //== 회원명 ==//용
    private String name;
    //== 별명 ==//
    private String nickname;
    //== 성별 ==//
    @Enumerated(EnumType.STRING)
    private Gender gender;
    //== 생년월일 ==//
    private LocalDateTime birthday;
    //== 전화번호 ==//
    private String phoneNumber;
    //== 회원 유형 ==//
    @Enumerated(EnumType.STRING)
    private MemberType usertype;

    //== 회원 <--> 지역 ==//
    @OneToMany(mappedBy = "member")
    private List<MemberRegion> memberRegionList = new ArrayList<>();

    //== 회원 <--> 게시물 ==//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<Board> boards = new ArrayList<>();

    //== 회원 <--> 댓글 ==//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    //== 회원 <--> 모임 ==//
    @OneToMany(mappedBy = "member")
    private List<MemberPromise> memberPromiseList = new ArrayList<>();

    //== 회원 <--> 채팅방 ==//
    @OneToMany(mappedBy = "member")
    private List<MemberChatroom> memberChatroomList = new ArrayList<>();

    //== 회원 <--> 채팅 ==//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<Chatting> chattings = new ArrayList<>();
}
