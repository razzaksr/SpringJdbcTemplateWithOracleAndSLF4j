package com.example.springoraclejdbctemplateslf4j;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringOracleJdbcTemplateSlf4jApplicationTests {

    @Mock
    JdbcTemplate jdbcTemplate;

    @InjectMocks
    HaiServices services;

    @Test
    public void testListAll(){
        Hai h2=new Hai(55,"Product2",90);
        Hai h3=new Hai(120,"Product3",101);
        Hai h4=new Hai(55,"Product4",120);
        List<Hai> tempList= Stream.of(h2,h3,h4).collect(Collectors.toList());

        //when(jdbcTemplate.query(any(String.class),any(HaiServices.HaiMapper.class))).thenReturn(tempList);
        when(jdbcTemplate.query(eq("select * from hai"),any(RowMapper.class))).thenReturn(tempList);

        //assertEquals(h2,services.listAll().get(1));
        assertEquals(h2,services.listAll().get(0));
    }

    @Test
    public void testListOne(){
        Hai h1=new Hai(12,"Product1",1200);
        int userId=12;
//        when(jdbcTemplate.queryForObject(any(String.class), eq(new Object[]{userId}), any(RowMapper.class)))
//                .thenReturn(h1);
        when(jdbcTemplate.queryForObject(eq("select * from hai where id=?"), eq(new Object[]{userId}), any(RowMapper.class)))
                .thenReturn(h1);

        Hai h=services.listOne(12).get();
        assertEquals(h1,h);
    }

    @Test
    public void testInsertOne(){
        Hai h3=new Hai(120,"Product3",101);
        Hai h4=new Hai(55,"Product4",120);
        when(jdbcTemplate.update(eq("insert into hai values(?,?,?)"), eq(new Object[]{h3.getId(),h3.getName(),h3.getPrice()})))
                .thenReturn(1);
        String dat=services.insertOne(h3);
        //assertEquals("Product3 has inserted",dat);
        assertEquals("Product4 has inserted",dat);
    }
}
