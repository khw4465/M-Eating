package MEating.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Board extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    //== 게시물 제목 ==//
    private String title;
    
    //== 게시물 내용 ==//
    private String content;

    //== 게시물 타입 ==//
    @Enumerated(EnumType.STRING)
    private BoardType type;

    //== 게시물 <--> 회원 ==//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer_id")
    private Member member;

    //== 게시물 <--> 지역 ==//
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    //== 게시물 <--> 메뉴 ==//
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    //== 게시물 <--> 댓글 ==//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "board", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    //== 게시물 <--> 모임 ==//
    @OneToOne(mappedBy = "board", fetch = FetchType.LAZY)
    private Promise promise;

    //== 게시물 <--> 채팅방 ==//
    @OneToMany(mappedBy = "board")
    private List<Chatroom> chatrooms = new ArrayList<>();

    //== 연관관계 메서드 ==//

    /**
     * 회원, 메뉴, 지역을 파라미터로 받아 게시물 객체 생성
     */
    public static Board createBoard(Member member, FoodType name, int regionListNum) {
        Board board = new Board();
        board.setMember(member);
        Region region = member.getMemberRegionList().get(regionListNum).getRegion();
        board.setRegion(region);
        Food food = Food.food(name);
        board.setFood(food);

        return board;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Board board = (Board) o;

        if (getId() != null ? !getId().equals(board.getId()) : board.getId() != null) return false;
        if (getTitle() != null ? !getTitle().equals(board.getTitle()) : board.getTitle() != null) return false;
        if (getContent() != null ? !getContent().equals(board.getContent()) : board.getContent() != null) return false;
        if (getType() != board.getType()) return false;
        if (getMember() != null ? !getMember().equals(board.getMember()) : board.getMember() != null) return false;
        if (getRegion() != null ? !getRegion().equals(board.getRegion()) : board.getRegion() != null) return false;
        if (getFood() != null ? !getFood().equals(board.getFood()) : board.getFood() != null) return false;
        if (getComments() != null ? !getComments().equals(board.getComments()) : board.getComments() != null)
            return false;
        if (getPromise() != null ? !getPromise().equals(board.getPromise()) : board.getPromise() != null) return false;
        return getChatrooms() != null ? getChatrooms().equals(board.getChatrooms()) : board.getChatrooms() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getContent() != null ? getContent().hashCode() : 0);
        result = 31 * result + (getType() != null ? getType().hashCode() : 0);
        result = 31 * result + (getMember() != null ? getMember().hashCode() : 0);
        result = 31 * result + (getRegion() != null ? getRegion().hashCode() : 0);
        result = 31 * result + (getFood() != null ? getFood().hashCode() : 0);
        result = 31 * result + (getComments() != null ? getComments().hashCode() : 0);
        result = 31 * result + (getPromise() != null ? getPromise().hashCode() : 0);
        result = 31 * result + (getChatrooms() != null ? getChatrooms().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Board{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", type=" + type +
                ", member=" + member +
                ", region=" + region +
                ", food=" + food +
                '}';
    }
}

