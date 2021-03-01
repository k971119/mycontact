package com.fastcampus.javaallinone.project3.mycontact.Controller;

import com.fastcampus.javaallinone.project3.mycontact.Controller.dto.PersonDto;
import com.fastcampus.javaallinone.project3.mycontact.Domain.Person;
import com.fastcampus.javaallinone.project3.mycontact.Repository.PersonRepository;
import com.fastcampus.javaallinone.project3.mycontact.configuration.serializer.BirthdaySerializer;
import com.fastcampus.javaallinone.project3.mycontact.dto.Birthday;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@Slf4j
class PersonControllerTest {
    @Autowired
    private PersonController personController;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MappingJackson2HttpMessageConverter messageConverter;

    private MockMvc mockMvc;

    @BeforeEach         //모든 함수가 실행전 기본적으로 실행시키는 함수 지정 어노테이션
    void beforeEach(){
        mockMvc = MockMvcBuilders.standaloneSetup(personController).setMessageConverters(messageConverter).build();
    }

    @Test               //테스트 어노테이션
    void getPerson() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name")/*리턴되는 name 어트리뷰트*/.value("martin"))         //get api에서 리턴되는 값 키와 값을 통한 검증
                .andExpect(jsonPath("$.hobby").isEmpty())
                .andExpect(jsonPath("$.address").isEmpty())
                .andExpect(jsonPath("$.birthday").value("1991-08-15"))
                .andExpect(jsonPath("$.job").isEmpty())
                .andExpect(jsonPath("$.phoneNumber").isEmpty())
                .andExpect(jsonPath("$.deleted").value(false))
                .andExpect(jsonPath("$.age").isNumber())              //키값 존재 여부 확인
                .andExpect(jsonPath("$.birthdayToday").isBoolean());

        //jsonPath("$.name").value("martin");             //json 객제에 대한 검증
        //assertThat(result.getName().isEqualTo("martin")                   //자바 객체에 대한 검증
    }

    @Test
    void postPerson() throws Exception{
        PersonDto dto = PersonDto.of("martin","programming","판교",LocalDate.now(),"programmer","010-1111-2222");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/person")
                    .contentType(MediaType.APPLICATION_JSON_UTF8)
                    .content(toJsonString(dto)))
                    .andDo(print())
                    .andExpect(status().isCreated());

        Person result = personRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).get(0);

        assertAll(
                ()-> assertThat(result.getName()).isEqualTo("martin"),
                ()->assertThat(result.getHobby()).isEqualTo("programming"),
                ()->assertThat(result.getAddress()).isEqualTo("판교"),
                ()->assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.now())),
                ()->assertThat(result.getJob()).isEqualTo("programmer"),
                ()->assertThat(result.getPhoneNumber()).isEqualTo("010-1111-2222"));
    }

    @Test
    void modifyPerson() throws Exception{
        PersonDto dto = PersonDto.of("martin","programming","판교",LocalDate.now(),"programmer","010-1111-2222");

        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(toJsonString(dto)))
                .andDo(print())
                .andExpect(status().isOk());

        Person result = personRepository.findById(1L).get();

        assertAll(
            ()->assertThat(result.getName()).isEqualTo("martin"),
            ()->assertThat(result.getHobby()).isEqualTo("programming"),
            ()->assertThat(result.getAddress()).isEqualTo("판교"),
            ()->assertThat(result.getBirthday()).isEqualTo(Birthday.of(LocalDate.now())),
            ()->assertThat(result.getJob()).isEqualTo("programmer"),
            ()->assertThat(result.getPhoneNumber()).isEqualTo("010-1111-2222"));
        }

    @Test
    void modifyPersonIfNameisDifferent() throws Exception{
        PersonDto dto = PersonDto.of("jennis","programming","판교",LocalDate.now(),"programmer","010-1111-2222");

        assertThrows(NestedServletException.class, ()->
        mockMvc.perform(
                MockMvcRequestBuilders.put("/api/person/1")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(toJsonString(dto)))
                .andDo(print())
                .andExpect(status().isOk()));
    }

    @Test
    void modifyName() throws Exception{
        mockMvc.perform(
                MockMvcRequestBuilders.patch("/api/person/1")
                        .param("name","martinModifind"))
                .andDo(print())
                .andExpect(status().isOk());

        assertThat(personRepository.findById(1l).get().getName()).isEqualTo("martinModifind");
    }

    @Test
    void deletePerson() throws Exception{

        mockMvc.perform(
                MockMvcRequestBuilders.delete("/api/person/1"))
                .andDo(print())
                .andExpect(status().isOk());

        assertTrue(personRepository.findPeopleDeleted().stream().anyMatch(person -> person.getId() == (1L)));
    }

    private String toJsonString(PersonDto personDto) throws JsonProcessingException {
        return objectMapper.writeValueAsString(personDto);
    }
}