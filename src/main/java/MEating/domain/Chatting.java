package MEating.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Chatting extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "catting_id")
    private Long id;
    //== 채팅내용 ==//
    private String content;

    //== 채팅 <--> 채팅방 ==//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private Chatroom chatroom;

    //== 채팅 <--> 회원 ==//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Member member;

    //== 연관관계 메서드 ==//
    public void addChattingToChatroom(Member member, Chatting chatting) {
        this.member = member;
        member.getChattings().add(chatting);
    }

    @Override
    public String toString() {
        return "Chatting{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}

