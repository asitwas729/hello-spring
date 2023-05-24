package hello.hellospring.domain;

import javax.persistence.*;

@Entity
public class Member {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //identity: db에서 자동 생성
  private  Long id;
  //@Column(name="username")
  private String name;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
