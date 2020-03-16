package com.example.demo.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface GroupRepositry extends JpaRepository<Group, Long>{
    Group findByName(String name);
}