package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

  private static Map<Long, Member> store = new HashMap<>();
  //실무에서는 동시성문제때문에 공유되는 변수일 경우, 다른것을 사용해야함(예제니깐 단순한 hashmap을 사용)
  private static long sequence = 0L;  //sequence는 0,1,2같은 키값을 생성
  //실무에서는 동시성문제때문에, 어텀롱 사용

  @Override
  public Member save(Member member) {
    member.setId(++sequence); //id setting
    store.put(member.getId(), member);  //store(map)에 저장
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    //만약 null일수있기때문에 ofnullable로 감싸주지않으면 오류남
    return Optional.ofNullable(store.get(id));  //Optional.ofNullable(): null이라도 감싸줌
  }

  @Override
  public Optional<Member> findByName(String name) {
    return store.values().stream() //store에서 (Java의 Lambda사용) java에서 루프로 돌림
        .filter(member -> member.getName().equals(name))  //member에서 파라미터로 넘어오는 값과 같은지 확인, 같은 경우에만 필터가 됨
        .findAny(); //찾으면 하나라도 반환됨
  }

  @Override
  public List<Member> findAll() {
    //store는 map인데 이것은 List임, 실무에서는 List가 많이 사용됨(루프 돌리기가 쉬움)
    return new ArrayList<>(store.values()); //store에 있는 values가 반환됨, values(=Member)
  }

  public void clearStore(){
    store.clear();
  }

}
