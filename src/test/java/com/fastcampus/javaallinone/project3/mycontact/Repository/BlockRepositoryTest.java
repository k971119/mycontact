package com.fastcampus.javaallinone.project3.mycontact.Repository;

import com.fastcampus.javaallinone.project3.mycontact.Domain.Block;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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
        block.setStartTime(LocalDate.now());
        block.setEndTime(LocalDate.now());

        blockRepository.save(block);

        List<Block> blocks = blockRepository.findAll();

        assertThat(blocks.size()).isEqualTo(3);
        assertThat(blocks.get(0).getName()).isEqualTo("dennis");
        assertThat(blocks.get(1).getName()).isEqualTo("sophia");
        assertThat(blocks.get(2).getName()).isEqualTo("martin");

    }
}