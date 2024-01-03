package MEating.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
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
    //== 회원명 ==//
    private String name;
    //== 별명 ==//
    private String nickname;
    //== 성별 ==//
    @Enumerated(EnumType.STRING)
    private Gender gender;
    //== 생년월일 ==//
    private LocalDate birthday;
    //== 전화번호 ==//
    private String phoneNumber;
    //== 회원 유형 ==//
    @Enumerated(EnumType.STRING)
    private MemberType usertype;

    //== 회원 <--> 지역 ==//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<MemberRegion> memberRegionList = new ArrayList<>();

    //== 회원 <--> 게시물 ==//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member", orphanRemoval = true)
    private List<Board> boards = new ArrayList<>();

    //== 회원 <--> 댓글 ==//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    //== 회원 <--> 모임 ==//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<MemberPromise> memberPromiseList = new ArrayList<>();

    //== 회원 <--> 채팅방 ==//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "member")
    private List<MemberChatroom> memberChatroomList = new ArrayList<>();

    //== 회원 <--> 채팅 ==//
    @OneToMany(mappedBy = "member")
    private List<Chatting> chattings = new ArrayList<>();

    //== 연관관계 메서드 ==//

    /**
     * 회원과 지역명을 입력하면 회원과 지역에 각각 데이터를 채움
     */
    public List<MemberRegion> addRegionToMember(RegionName regionName) {
        Region region = Region.region(regionName);

        MemberRegion memberRegion = new MemberRegion();
        memberRegion.addMemberRegion(this, region);
        memberRegion.setRegDtm(LocalDateTime.now());

        return getMemberRegionList();
    }

    /**
     * 채팅 쓰기
     */
    public Chatting chatting(Chatroom chatroom, String content) {
        Chatting chatting = new Chatting();
        chatting.setMember(this);
        chatting.setChatroom(chatroom);
        chatting.setContent(content);
        chatting.setRegDtm(LocalDateTime.now());

        chatroom.getChattings().add(chatting);

        chatting.addChattingToChatroom(this, chatting);

        return chatting;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", loginId='" + loginId + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", gender=" + gender +
                ", birthday=" + birthday +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
