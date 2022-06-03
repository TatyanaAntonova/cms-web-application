package com.example.cmswebapp.controllers;

import com.example.cmswebapp.config.TestConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(value = {"/sql/create_table_before.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/sql/delete_data_after.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
public class MainControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private MainController controller;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void welcomePageTest() throws Exception {
        mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//a[@id='published-pages-list']").exists())
                .andExpect(xpath("//a[@id='published-pages-list']").nodeCount(2));
    }

    @Test
    public void viewUnpublishedPageList() throws Exception {
        mockMvc.perform(get("/unpublished"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(xpath("//a[@id='unpublished-pages-list']").exists())
                .andExpect(xpath("//a[@id='unpublished-pages-list']").nodeCount(1));
    }

    @Test
    public void viewPageTest() throws Exception {
        mockMvc.perform(get("/1-New-title"))
                .andDo(print())
                .andReturn();
    }

    @Test
    public void newPageTest() throws Exception {
        mockMvc.perform(get("/new"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void createTest() throws Exception {
        MockHttpServletRequestBuilder createMessage = post("/new-page")
                .param("title", "Spring Rocks")
                .param("description", "In case you didn't know, Spring Rocks!")
                .param("htmlContent", "<p>What is Lorem Ipsum?</p>")
                .param("priority", "10")
                .param("publishedFl", "true");

        mockMvc.perform(createMessage)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(""));
    }

    @Test
    public void editTest() throws Exception {
        mockMvc.perform(get("/{slug}/edit", "1-New-title"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void updateTest() throws Exception {
        MockHttpServletRequestBuilder createMessage = patch("/{slug}","1-New-title")
                .param("title", "Spring Rocks!!!!")
                .param("description", "In case you didn't know, Spring Rocks!!!!")
                .param("htmlContent", "<p>What is Lorem Ipsum?</p>")
                .param("priority", "5")
                .param("publishedFl", "false");

        mockMvc.perform(createMessage)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(""));
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/{slug}", "1-New-title"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl(""));
    }
}


