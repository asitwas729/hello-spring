package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class MemberServiceTest {
  //MemberService에 있는 MemoryMemberRepository를 사용
  //MemberService memberService = new MemberService();
  MemberService memberService;
  //MemberService에 있는 MemoryMemberRepository가 다름, 다르기에 다른값이 될수도
  //MemoryMemberRepository memoryMemberRepository = new MemoryMemberRepository(); //aftereach()에 사용
  MemoryMemberRepository memoryMemberRepository;

  //동작하기전에 MemberService에 생성해놓은 memoryMemberRepository를 넣어줌
  @BeforeEach
  public void beforeEach(){ //각 test를 실행할때마다 각각 생성해줌
    //memoryMemberRepository생성 -> 걔를 memberService에 넣음(같은 레포지포리사용)
    memoryMemberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memoryMemberRepository);
  }

  //test끝날때마다 쌓이는 것 없애기(MemoryMemberRepository 필요)
  @AfterEach
  public void afterEach(){
    memoryMemberRepository.clearStore();
  }


  @Test
  void 회원가입() {   //test는 이름을 한글로 해도됨(영어권과 협업하는게 아니라면 실무에서도 사용함)
    //추천)given-when-then 문법
    //given(뭔가 주어짐)
    Member member = new Member();
    member.setName("spring");

    //when(이것을 실행했을때)
    Long saveId = memberService.join(member);

    //then(결과가 ~가 나와야함)
    //저장한게 repository에 있는게 맞아?를 찾고 싶음 -> repository를 꺼내야함
    Member findMember = memberService.findOne(saveId).get();  //memberservice로 가져옴
    assertThat(member.getName()).isEqualTo(findMember.getName());
    //이름을 검증하는 코드)member의 이름이 findmember의 이름과 같냐?
  }

  @Test
  public void 중복_회원_예외(){
    //given
    Member member1 = new Member();
    member1.setName("spring");
    Member member2 = new Member();
    member2.setName("spring");

    //when
    memberService.join(member1);
//    try {
//      memberService.join(member2);  //MemberService.join.validate걸림 예외처리문 출력
//      fail();
//    }catch (IllegalStateException e){ //join과 동일한 예외(IllegalSta~)사용해야함
//      assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//    }
    IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); //IllegalStateException터져야함
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    //then
  }

  @Test
  void findMembers() {
  }

  @Test
  void findOne() {
  }
}