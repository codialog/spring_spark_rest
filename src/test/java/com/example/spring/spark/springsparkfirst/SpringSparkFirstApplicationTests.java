package com.example.spring.spark.springsparkfirst;

import com.example.spring.spark.springsparkfirst.controller.DetailStatusController;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SpringSparkFirstApplicationTests {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private DetailStatusController statusController;

    @Test
    void contextLoads() throws Exception {
        for (int i = 0; i < 100000; i++) {
            try {
                this.mockMvc.perform(post("/api/detail")
                        .contentType(APPLICATION_JSON)
                        .content(String.valueOf(new JSONObject()
                                .put("id_sample", "76rtw" + i)
                                .put("num_id", "ffg#er111" + i)
                                .put("id_location", "3211.233" + i)
                                .put("id_signal_par", "0xcv11cs")
                                .put("id_detected", "None")
                                .put("id_class_det", "req11")))
                        .accept(APPLICATION_JSON))
                        .andExpect(status().is2xxSuccessful());
            } catch (Exception e) {
                System.out.println(e);
            }
        }

    }
}