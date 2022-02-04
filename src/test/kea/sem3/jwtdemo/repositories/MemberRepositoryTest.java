package kea.sem3.jwtdemo.repositories;

import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    @BeforeEach
    void setUp() {
        memberRepository.save(new Member("xxx", "xxx@a.dk", "test123", "yyy"));

        memberRepository.save(new Member("aaa", "aaa@a.dk", "test123", "bbb"));
    }

    @Test
    public void testCount(){
        assertEquals(2, memberRepository.count());
    }

    @Test
    public void testAddMember(){
        Member memberNew = memberRepository.save(new Member("Amanda123", "mail@mailse.dk", "test123", "Amanda"));
        assertNotEquals(null, memberNew.getUsername());
        assertEquals(3,memberRepository.count());
    }

}