// (선택) 이건 참고용: 루트(/)에서 화이트라벨 안 보고 싶으면 쓰는 컨트롤러 예시
// src/main/java/com/example/simplereminder/HelloController.java
package com.example.simplereminder;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "백엔드 살아있음 ✅";
    }
}
