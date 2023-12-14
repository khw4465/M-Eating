package MEating.service;

import MEating.domain.Gender;
import MEating.domain.Member;
import MEating.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    EntityManager em;

    @Test
    public void 회원가입() throws Exception {
        Member member = new Member();
        member.setLoginId("asdf");
        member.setPassword("1234");
        member.setName("홍길동");
        member.setNickname("zl존길동");
        member.setGender(Gender.MALE);
        member.setPhoneNumber("01012345678");

        String st = "2000-11-11";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate birth = LocalDate.parse(st, formatter);
        member.setBirthday(birth);

        Long newMember = memberService.join(member);

        assertEquals(newMember, member.getId());

        System.out.println("Member = " + member.toString());

    }

    @Test(expected = IllegalStateException.class)
    public void 아이디중복체크() throws Exception {
        Member member1 = new Member();
        member1.setLoginId("asdf");
        member1.setPassword("1111");
        member1.setName("강호동");
        member1.setNickname("1박2일짱");
        member1.setGender(Gender.MALE);
        member1.setPhoneNumber("01012345678");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String st1 = "2000-11-11";
        LocalDate birth1 = LocalDate.parse(st1, formatter);
        member1.setBirthday(birth1);

        Member member2 = new Member();
        member2.setLoginId("asdf");
        member2.setPassword("2222");
        member2.setName("유재석");
        member2.setNickname("무한도전짱");
        member2.setGender(Gender.MALE);
        member2.setPhoneNumber("01087654321");

        String st2 = "2000-11-12";
        LocalDate birth2 = LocalDate.parse(st2, formatter);
        member2.setBirthday(birth2);

        memberService.join(member1);
        memberService.join(member2);

    }

    @Test(expected = IllegalStateException.class)
    public void 회원중복체크() throws Exception {
        Member member1 = new Member();
        member1.setLoginId("asdf");
        member1.setPassword("1111");
        member1.setName("뉴진스");
        member1.setNickname("암 슈퍼샤이~");
        member1.setGender(Gender.FEMALE);
        member1.setPhoneNumber("01012345678");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String st1 = "2000-11-11";
        LocalDate birth1 = LocalDate.parse(st1, formatter);
        member1.setBirthday(birth1);

        Member member2 = new Member();
        member2.setLoginId("qwer");
        member2.setPassword("2222");
        member2.setName("뉴진스");
        member2.setNickname("하 하 하입뽀이");
        member2.setGender(Gender.FEMALE);
        member2.setPhoneNumber("01012345678");

        String st2 = "2000-11-11";
        LocalDate birth2 = LocalDate.parse(st2, formatter);
        member2.setBirthday(birth2);

        memberService.join(member1);
        memberService.join(member2);
    }
}