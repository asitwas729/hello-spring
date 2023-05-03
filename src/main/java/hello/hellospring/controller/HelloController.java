package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

  @GetMapping("hello")
  public String hello(Model model){
    model.addAttribute("data", "hello!!");
    return "hello";
  }

  @GetMapping("hello-mvc")
  public String helloMvc(@RequestParam("name") String name, Model model){
    model.addAttribute("name", name);
    return "hello-template";
  };

  @GetMapping("hello-spring")
  @ResponseBody //body에 직접 넣어주겠다, view없이 문자가 그대로 내려감, html없이 무식하게
  public String helloString(@RequestParam("name") String name){
    return "hello " + name;   // "hello yang"
  }

  //API: 객체가 전달, 객체가 JSON으로 변환
  @GetMapping("hello-api")
  @ResponseBody   //JSON으로 자동으로 반환함
  public Hello helloApi(@RequestParam("name") String name){
    Hello hello = new Hello();
    hello.setName(name);  //출력: {"name" : "yang"}
    return hello;
    //JSON방식: 심플
    //XML방식: <html></html> 무겁, 태그두번써야함
  }

  static class Hello{
    private String name;
    //java bean 표준방식(프로퍼티 접근방식)
    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }
  }
}
