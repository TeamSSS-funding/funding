package io.github.mygoodsupporter.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;


public class MemberControllerTest {

    @Autowired
    WebApplicationContext was;

    MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(was).build();
    }

    @Test
    public void joinTest() throws Exception {
        MockHttpServletRequestBuilder request = post("/memberJoin").contentType(MediaType.APPLICATION_FORM_URLENCODED)
                                                    .param("m_id", "ccc123")
                .param("m_password", "password1!")
                .param("m_name", "ccc123")
                .param("m_phone", "010-5555-5455")
                .param("emailId", "ccc123")
                .param("domain", "naver.com")
                .param("m_type", "ROLE_ADMIN");

        mockMvc.perform(request)
                .andDo(print());
    }

    @Test
    public void HomeTest() throws Exception {
        MockHttpServletRequestBuilder request = get("/");

        mockMvc.perform(request).andDo(print());
    }
}