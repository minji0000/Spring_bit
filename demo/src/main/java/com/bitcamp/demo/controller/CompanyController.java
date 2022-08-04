package com.bitcamp.demo.controller;

import com.bitcamp.demo.mapper.CompanyMapper;
import com.bitcamp.demo.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


// ORM ?

// Controller에 @ResponseBody가 추가된 것
// 따라서 컨트롤러 클래스의 각 메서드마다 @ResponseBody를 추가할 필요가 없다.
@CrossOrigin
@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyMapper companyMapper;

    @PostMapping("")
    public Company post(@RequestBody Company company) {
        // 왜 1이 들어가면 잘 들어간거야?
        companyMapper.insert(company);
        return company;
    }

    @GetMapping("")
    public List<Company> getAll() {
        return companyMapper.getAll();
    }

    @GetMapping("/{id}")
    // 패쓰랑 리퀘스트 파람 차이 알아두기
    // 패쓰배리어블은 밸류 값(객체)으로 들어오는 것 ?
    public Company getById(@PathVariable("id") int id) {
        return companyMapper.getById(id);
    }
}
