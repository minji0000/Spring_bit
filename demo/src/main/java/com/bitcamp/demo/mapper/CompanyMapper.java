package com.bitcamp.demo.mapper;

import com.bitcamp.demo.model.Company;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CompanyMapper {

    @Insert("INSERT INTO company(company_name, company_address) VALUES(#{company.name}, #{company.address})")
    // 실제 디비에서 증가된 아이디를 받아올 수있음 (= 아이디가 생성된 결과)
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insert(@Param("company") Company company);

    @Select("SELECT * FROM company")
    @Results(id = "CompanyMap", value = {
            @Result(property = "name", column = "company_name"),
            @Result(property = "address", column = "company_address")
    })
    List<Company> getAll();

    @Select("SELECT * FROM company WHERE id = #{id}")
    @ResultMap("CompanyMap")
    Company getById(@Param("id") int id);

}
