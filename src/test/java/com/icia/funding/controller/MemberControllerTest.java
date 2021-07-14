package com.icia.funding.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml", "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration
public class MemberControllerTest {

    @Autowired
    WebApplicationContext was;

    MockMvc mockMvc;

    @Before
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
                .param("m_email", "ccc123@naver.com")
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