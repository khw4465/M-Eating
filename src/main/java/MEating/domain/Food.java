package MEating.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Food extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "food_id")
    private Long id;
    //== 메뉴명 ==//
    @Enumerated(EnumType.STRING)
    private FoodType name;

    //== 메뉴 <--> 게시물 ==//
    @OneToMany(mappedBy = "food")
    private List<Board> boards = new ArrayList<>();

    private Food(FoodType name) {
        this.name = name;
    }

    //== 음식별 공유 객체 생성 ==//
    private static final Map<FoodType, Food> foodMap = new EnumMap<>(FoodType.class);

    //== key값으로 들어온 음식명이 존재하지 않으면 객체 추가 ==//
    public static Food food(FoodType name) {
        Food food = foodMap.computeIfAbsent(name, Food::new);
        food.setRegDtm(LocalDateTime.now());
        return food;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
