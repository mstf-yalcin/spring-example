package com.spring.jwt.demo;

import com.spring.jwt.demo.dto.UserRegisterDto;
import com.spring.jwt.demo.dto.request.CreateCategoryRequestDto;
import com.spring.jwt.demo.entity.Enum_Role;
import com.spring.jwt.demo.entity.Role;
import com.spring.jwt.demo.repository.RoleRepository;
import com.spring.jwt.demo.service.AuthService;
import com.spring.jwt.demo.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final CategoryService categoryService;

    private final AuthService authService;

    public DemoApplication(RoleRepository roleRepository, CategoryService categoryService, AuthService authService) {
        this.roleRepository = roleRepository;
        this.categoryService = categoryService;

        this.authService = authService;
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        roleRepository.save(Role.builder().role(Enum_Role.ROLE_USER).build());
        authService.register(new UserRegisterDto("string@gmail", "string"));
        categoryService.createCategory(new CreateCategoryRequestDto("test"));

    }
}
