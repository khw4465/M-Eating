package MEating.service;

import MEating.domain.Member;
import MEating.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /** 중복체크 후 회원가입 **/
    @Transactional
    public Long join(Member member) {
        validateDuplicateLoginId(member);
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    /** 아이디 중복체크 **/
    private void validateDuplicateLoginId(Member member) {
        List<Member> findMembers = memberRepository.findByLoginId(member.getLoginId());
        if(findMembers.size() != 0) {
            throw new IllegalStateException("이미 존재하는 아이디입니다.");
        }
    }

    /** 회원 중복체크 **/
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByInfo(member.getName(), member.getBirthday(), member.getPhoneNumber());
        if(findMembers.size() != 0) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /** 회원 단건조회 **/
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    /** 회원 전체조회 **/
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    /** 이름, 생년월일, 전화번호로 회원조회 **/
    public List<Member> findByInfo(String name, LocalDate birth, String phoneNum) {
        return memberRepository.findByInfo(name, birth, phoneNum);
    }

    /** 로그인아이디로 회원조회 **/
    public List<Member> findByLoginId(String loginId) {
        return memberRepository.findByLoginId(loginId);
    }
}
