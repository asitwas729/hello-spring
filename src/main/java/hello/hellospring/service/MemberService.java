package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public class MemberService {

  //회원서비스를 하려면 회원레포지포리가 필요함
  //Test시 MemberService에서 사용하는 객체를 동일하게 사용하게끔 바꿔야함
  //private  final MemberRepository memberRepository = new MemoryMemberRepository();
  private final MemberRepository memberRepository;

  //MemberRepository not found: @Repository 넣기
  //spring이 MemberService를 생성할때, SpringContainer에 넣으면서 memberRepository도 넣어줌(너 이거 필요하구나)
  //MemoryMemberRepository가 구현체로 있는데, service에 주입해줌
//  @Autowired  //예전방법, 단점)memberService.setMemberRepository()이 떠서 아무 개발자가 호출할수 있음
//  public MemberRepository setMemberRepository() {
//    this.memberRepository = memberRepository;
//  }
  public MemberService(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
  }






  //회원가입
  public Long join(Member member){
    //비즈니스로직: 같은 이름인 중복회원X(가정)
    //Optional<Member> result = memberRepository.findByName(member.getName());
    //자주 사용) result.orElseGet()
//    result.ifPresent(member1 -> { //ifPresent: 값이 존재하면(=동일한 이름이 존재)
//      throw new IllegalStateException("이미 존재하는 회원입니다.");
//    });
    validateDuplicateMember(member);
    memberRepository.save(member);
    return member.getId();
  }

  private void validateDuplicateMember(Member member) {
    memberRepository.findByName(member.getName())
        .ifPresent(member1 -> {
          throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
  }

  //전체 회원 조회
  public List<Member> findMembers(){
    return memberRepository.findAll();
  }

  //
  public Optional<Member> findOne(Long memberId){
    return memberRepository.findById(memberId);
  }

}
