package com.fastcampus.javaallinone.project3.mycontact.Repository;

import com.fastcampus.javaallinone.project3.mycontact.Domain.Block;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlockRepositoryTest {
    @Autowired
    private BlockRepository blockRepository;
    @Test
    void crud(){
        Block block = new Block();
        block.setName("martin");
        block.setReason("맘에 안들어서");
        blockRepository.save(block);
    }
}