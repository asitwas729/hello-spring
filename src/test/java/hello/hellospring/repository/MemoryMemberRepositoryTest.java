package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {
  MemoryMemberRepository repository = new MemoryMemberRepository();

  //전체 실행시 ERROR:
  //모든 test는 순서와 상관없이 동시에 실행됨, findall이 먼저 실행되었는데, 이때 2개가 저장 되어버림
  //findByName은 findAll에 저장된 다른 객체가 나와버린거임
  //해결방안: test하나가 끝나면 데이터를 clear해야함 -> afterEach()
  @AfterEach  //test가 끝날때마다 실행(callback method)
  public void afterEach(){  //clear
    repository.clearStore();  //repository를 test가 끝날때마다 비워줌
  }

  @Test
  public void save(){
    Member member = new Member();
    member.setName("spring");

    repository.save(member);

    Member result = repository.findById(member.getId()).get();
    System.out.println("result = " + (result == member));
    //Assertions.assertEquals(member, result);  //(==)오류가 안남, JUNIT version(assertEquals)
    //Assertions.assertEquals(member, null);  //(!=)오류남   필요:hello.hellospring.domain.Member@6f4a47c7  실제:null
    //Assertions.assertThat(member).isEqualTo(result);  //더 쉬운버전, ASSERTJ version(assertThat), import static org.assertj.core.api.Assertions.*;
    assertThat(member).isEqualTo(result);  //import하면 Assertions을 쓰지 않고 바로 assertThat만 사용하면 됨
    //실무)BuildTool에서 Build할때, testcase통과하지않으면 다음단계로 못넘어가게 막음
  }

  @Test
  public void findByName() {
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    Member result = repository.findByName("spring2").get();
    assertThat(result).isEqualTo(member2);

  }

  @Test
  public void findAll(){
    Member member1 = new Member();
    member1.setName("spring1");
    repository.save(member1);

    Member member2 = new Member();
    member2.setName("spring2");
    repository.save(member2);

    List<Member> result = repository.findAll();
    assertThat(result.size()).isEqualTo(2); //member 2개만들었으니까 List인 result는 2개

  }

}