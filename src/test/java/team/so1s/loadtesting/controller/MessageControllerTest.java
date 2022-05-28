package team.so1s.loadtesting.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import team.so1s.loadtesting.dto.MessageDto;
import team.so1s.loadtesting.entity.Message;
import team.so1s.loadtesting.utils.JsonMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class MessageControllerTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private JsonMapper jsonMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void apiTest() throws Exception {
        var requestDto = new MessageDto("Hello, World!");
        var requestBody = jsonMapper.asJsonString(requestDto);

        mockMvc.perform(post("/messages")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andDo(print())
                .andExpect(status().is(HttpStatus.CREATED.value()));


        var result = mockMvc.perform(get("/messages")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().is(HttpStatus.OK.value()))
                .andReturn();

        var messages = jsonMapper.fromMvcResult(result, new TypeReference<List<Message>> () {});

        assertThat(messages).isNotNull();
        assertThat(messages).hasSize(1);

        var message = messages.get(0);

        assertThat(message.getContent()).isEqualTo("Hello, World!");
    }
}
