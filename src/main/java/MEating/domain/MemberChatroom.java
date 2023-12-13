package MEating.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class MemberChatroom extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "member_chatroom_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private Chatroom chatroom;

    //== 연관관계 메서드 ==//
    public void addMemberChatroom(Member member, Chatroom chatroom) {
        this.member = member;
        this.chatroom = chatroom;

        member.getMemberChatroomList().add(this);
        chatroom.getMemberChatroomList().add(this);
    }
}
