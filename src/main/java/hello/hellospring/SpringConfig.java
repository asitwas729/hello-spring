package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//DI(필드 주입, setter주입, 생성자 주입): 의존관계가 실행중에 동적으로 변하는 경우는 없으므로 "생성자 주입"을 사용한다.
//상황에 따라 구현 클래스를 변경해야하면 설정을 통해 스프링 빈으로 등록한다.
//@autowired를 통한 DI는 스프링이 관리하는 객체에서만 동작한다. 스프링 빈으로 등록하지않고 직접 생성한 객체에서는 동작하지않는다.(빈 등록하면 됨)
//spring container에 올라가는 것만 autowired기능이 적용된다.(아무리 넣어도 적용되지않음)

//Java code로 직접 Spring Bean 등록하기
@Configuration
public class SpringConfig {

  @Bean
  public MemberService memberService(){
    return new MemberService(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository(){ //->인터페이스
    return new MemoryMemberRepository();  //->구현체(이 부분만 바꾸면 다른 코드를 아예 손대지않고 할수있다.
  }
}
