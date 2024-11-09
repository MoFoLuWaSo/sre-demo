package com.cardinal.cidemo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class PartDaoTest {

    @Autowired
    PartDao partDao;

    @Test
    public void test_getParts() {
        List<Part> parts = partDao.getParts();
        assertThat(parts)
            .hasSize(2)
            .extracting(Part::getPartNumber)
            .containsExactlyInAnyOrder("PART1234", "BUMPER");
    }

}