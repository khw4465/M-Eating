package MEating.domain;

import MEating.exception.NoBoardException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Comment extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;
    //== 댓글 내용 ==//
    private String content;

    //== 댓글 <--> 게시물 ==//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    //== 댓글 <--> 회원 ==//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    //== 연관관계 메서드 ==//
    /**
     * 회원과 게시판 정보를 받아서 댓글 객체 생성
     */
    public static Comment createComment(Member member, Board board) {
        Comment comment = new Comment();
        comment.setMember(member);
        comment.setBoard(board);

        return comment;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
