package com.marketboro.savings.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marketboro.savings.dto.SavingsSaveDto;
import com.marketboro.savings.dto.SavingsUseDto;
import com.marketboro.savings.exceptions.LackSavingsException;
import com.marketboro.savings.exceptions.SavingsSaveMinusException;
import com.marketboro.savings.exceptions.SavingsUseMinusException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class SavingsControllerTest {
    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();
    @Test
    void 적립금_적립_정상() throws Exception {
        SavingsSaveDto.Request contentRequest = SavingsSaveDto.Request.builder()
                .saveSavings(BigDecimal.valueOf(10000L))
                .remarks("테스트코드")
                .build();

        String content = objectMapper.writeValueAsString(contentRequest);
        mockMvc.perform(post("/savings/save/{userNumber}", "1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                        .andExpect(status().isOk());
    }

    @Test
    void 적립금_적립_마이너스_금액() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        SavingsSaveDto.Request contentRequest = SavingsSaveDto.Request.builder()
                .saveSavings(BigDecimal.valueOf(-10000L))
                .remarks("테스트코드")
                .build();

        String content = objectMapper.writeValueAsString(contentRequest);

        mockMvc.perform(post("/savings/save/{userNumber}", "1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof SavingsSaveMinusException))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 적립금_사용_정상_금액() throws Exception {

        적립금_적립(BigDecimal.valueOf(10000L));

        ObjectMapper objectMapper = new ObjectMapper();

        SavingsUseDto.Request contentRequest = SavingsUseDto.Request.builder()
                .useSavings(BigDecimal.valueOf(1000L))
                .remarks("테스트코드")
                .build();

        String content = objectMapper.writeValueAsString(contentRequest);

        mockMvc.perform(post("/savings/use/{userNumber}", "1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 적립금_사용_부족_금액() throws Exception {
        적립금_적립(BigDecimal.valueOf(10000L));
        ObjectMapper objectMapper = new ObjectMapper();

        SavingsUseDto.Request contentRequest = SavingsUseDto.Request.builder()
                .useSavings(BigDecimal.valueOf(20000L))
                .remarks("테스트코드")
                .build();

        String content = objectMapper.writeValueAsString(contentRequest);

        mockMvc.perform(post("/savings/use/{userNumber}", "1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof LackSavingsException))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 적립금_사용_마이너스_금액() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();

        SavingsUseDto.Request contentRequest = SavingsUseDto.Request.builder()
                .useSavings(BigDecimal.valueOf(-10000L))
                .remarks("테스트코드")
                .build();

        String content = objectMapper.writeValueAsString(contentRequest);

        mockMvc.perform(post("/savings/use/{userNumber}", "1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(content))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof SavingsUseMinusException))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void 적립금_합계() throws Exception {
        적립금_적립(BigDecimal.valueOf(1000L));
        적립금_적립(BigDecimal.valueOf(1000L));
        적립금_적립(BigDecimal.valueOf(1000L));
        적립금_적립(BigDecimal.valueOf(1000L));
        적립금_적립(BigDecimal.valueOf(1000L));

        String jsonString = "{\"code\":\"S\",\"message\":\"정상\",\"data\":{\"totalSavings\":5000.00}}";

        mockMvc.perform(get("/savings/{userNumber}", "1")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(jsonString))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private void 적립금_적립(BigDecimal savings) throws Exception {
        SavingsSaveDto.Request contentRequest = SavingsSaveDto.Request.builder()
                .saveSavings(savings)
                .remarks("테스트코드")
                .build();

        String content = objectMapper.writeValueAsString(contentRequest);

        mockMvc.perform(post("/savings/save/{userNumber}", "1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content));
    }
}