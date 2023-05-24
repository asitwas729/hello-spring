package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional  
//테스트 시작 전에 시작하고 완료 후 항상 롤백함, 그러면 db에 test data가 남지 않음(기존처럼 지우는 코드를 따로 넣지 않아도 됨)
//테스트에 붙었을 때만 롤백하고, service...에 붙었을때는 정상작동함
public class MemberServiceIntegrationTest {
  @Autowired MemberService memberService;
  @Autowired MemberRepository memberRepository;


  @Test
  public void 회원가입() throws Exception{
    //Given
    Member member = new Member();
    member.setName("lili");

    //When
    Long saveId = memberService.join(member);

    //then
    Member findMember = memberService.findOne(saveId).get();  //memberservice로 가져옴
    assertThat(member.getName()).isEqualTo(findMember.getName());
  }

  @Test
  public void 중복_회원_예외(){
    //given
    Member member1 = new Member();
    member1.setName("lili1");
    Member member2 = new Member();
    member2.setName("lili2");

    //when
    memberService.join(member1);
    IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2)); //IllegalStateException터져야함
    assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
  }

}
