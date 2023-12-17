package MEating.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id")
    private Food food;

    //== 게시물 <--> 댓글 ==//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    //== 게시물 <--> 모임 ==//
    @OneToOne(mappedBy = "board", fetch = FetchType.LAZY)
    private Promise promise;

    //== 게시물 <--> 채팅방 ==//
    @OneToMany(mappedBy = "board")
    private List<Chatroom> chatrooms = new ArrayList<>();

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

