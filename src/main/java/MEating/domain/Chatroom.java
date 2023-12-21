package MEating.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chatroom")
    private List<MemberChatroom> memberChatroomList = new ArrayList<>();

    //== 채팅방 <--> 게시판 ==//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    //== 채팅방 <--> 채팅 ==//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "chatroom")
    private List<Chatting> chattings = new ArrayList<>();

    //== 연관관계 메서드 ==//
    public static Chatroom createChatroom(Member member, Board findBoard) {
        Chatroom chatroom = new Chatroom();
        chatroom.setBoard(findBoard);
        chatroom.setName(member.getName() + ", " +findBoard.getMember().getName());
        chatroom.setRegDtm(LocalDateTime.now());

        MemberChatroom memberChatroom1 = new MemberChatroom();
        memberChatroom1.addMemberChatroom(member, chatroom);
        memberChatroom1.setRegDtm(LocalDateTime.now());

        MemberChatroom memberChatroom2 = new MemberChatroom();
        memberChatroom2.addMemberChatroom(findBoard.getMember(), chatroom);
        memberChatroom2.setRegDtm(LocalDateTime.now());

        findBoard.getChatrooms().add(chatroom);
        return chatroom;
    }

    @Override
    public String toString() {
        return "Chatroom{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
