package MEating.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Chatroom extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "chatroom_id")
    private Long id;
    //== 채팅방명 ==//
    private String name;

    //== 채팅방 <--> 회원 ==//
    @OneToMany(mappedBy = "chatroom")
    private List<MemberChatroom> memberChatroomList = new ArrayList<>();

    //== 채팅방 <--> 게시판 ==//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    //== 채팅방 <--> 채팅 ==//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chatroom")
    private List<Chatting> chattings = new ArrayList<>();

    public static Chatroom createChatroom(Member member, Board findBoard) {
        Chatroom chatroom = new Chatroom();
        List<MemberChatroom> memberList = new ArrayList<>();

        MemberChatroom memberChatroom1 = new MemberChatroom();
        memberChatroom1.setMember(member);
        memberList.add(memberChatroom1);

        MemberChatroom memberChatroom2 = new MemberChatroom();
        memberChatroom2.setMember(findBoard.getMember());
        memberList.add(memberChatroom2);

        chatroom.setName(findBoard.getMember().getName() + "님과의 채팅");

        return chatroom;
    }
}
