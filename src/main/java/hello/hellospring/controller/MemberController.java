package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller //Component가 들어있음(Repository,Service 등) ->컴포넌트 스캔과 자동 의존관계 설정
//Controller는 java code로 직접 Bean등록이 안됨
public class MemberController {
  //private final MemberService memberService = new MemberService(); //여러 컨트롤러에서 가져다 쓰는데, (특별한 기능이 있는게 아니니까) 공용으로 하나만 사용하면 됨
  //그래서 springContainer에 등록을 해서 쓰면 됨, container에는 딱 하나만 등록이됨
  //부가적인 효과
  private final MemberService memberService;
  //@Autowired private MemberService memberService; //필드 주입(바꿀수있는 방법이 없기때문에 사용ㄴㄴ)

  @Autowired  //autowired는 springContainer에서 memberService를 가져옴
  //Error:memberservice not found: memberservice는 순수한 java code, spring이 찾을수가 없음(어노테이션x) -> MemberService에 @Service 넣어줌
  //MemberService안 MemberRepository not found -> @Repository넣음
  //생성자 주입
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

//  @Autowired  //setter주입
//  public void setMemberService(MemberService memberService){
//    this.memberService = memberService;
//  }

}
