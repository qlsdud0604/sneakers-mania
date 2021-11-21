package com.example.sneakersmania.test;

import com.example.sneakersmania.model.RoleType;
import com.example.sneakersmania.model.User;
import com.example.sneakersmania.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/dummy/join")
    public String join(User user) {
        System.out.println("username : " + user.getUsername());
        System.out.println("password : " + user.getPassword());

        user.setRole(RoleType.USER);

        userRepository.save(user);

        return "회원가입이 완료되었습니다.";
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id) {
        User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
            @Override
            public IllegalArgumentException get() {
                return new IllegalArgumentException("해당 유저는 존재하지 않습니다.");
            }
        });

        // 자바 오브젝트를 이턴하게 되면 MessageConverter가 Jackson 라이브러리를 호출해서,
        // 자바 오브젝트를 JSON으로 변환해서 브라우저에게 전달해줌
        return user;
    }

    @GetMapping("/dummy/users")
    public List<User> list() {
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<User> pagingUser = userRepository.findAll(pageable);


        List<User> users = pagingUser.getContent();
        return users;
    }

    @Transactional   // 함수 종료시 자동으로 commit
    @PutMapping("/dummy/user/{id}")
    public User update(@PathVariable int id, @RequestBody User user) {
        System.out.println("id : " + id);
        System.out.println("password : " + user.getPassword());

        User userEntity = userRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("해당 유저는 존재하지 않습니다.");
        });

        userEntity.setPassword(user.getPassword());

        return userEntity;
    }

    @DeleteMapping("/dummy/user/{id}")
    public String delete(@PathVariable int id) {
        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return "삭제에 실패하였습니다.";
        }

        return "삭제되었습니다.";
    }
}
