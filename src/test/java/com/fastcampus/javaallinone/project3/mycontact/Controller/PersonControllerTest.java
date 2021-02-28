package com.fastcampus.javaallinone.project3.mycontact.Controller;

import com.fastcampus.javaallinone.project3.mycontact.Repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
class PersonControllerTest {
    @Autowired
    private PersonController personController;

    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(personController).build();
    }
    @Test
    void getPerson() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void postPerson() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content("{\n"
                    +"\"name\": \"martin2\", \"age\": 20, \"bloodType\": \"A\"\n"
                    +"}"))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    void modifyPerson() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\n"
                       +"\"name\": \"martin\", \"age\": 20, \"bloodType\": \"A\"\n"
                       +"}"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void modifyName() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/person/1")
                        .param("name","martin2"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Disabled
    void deletePerson() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());

    }

}