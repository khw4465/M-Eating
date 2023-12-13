package MEating.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Food extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "food_id")
    private Long id;
    //== 메뉴명 ==//
    @Enumerated(EnumType.STRING)
    private FoodType name;

    //== 메뉴 <--> 게시물 ==//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "food")
    private List<Board> boards = new ArrayList<>();
}
