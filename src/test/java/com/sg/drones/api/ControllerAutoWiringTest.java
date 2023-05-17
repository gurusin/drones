package com.sg.drones.api;

import static org.assertj.core.api.Assertions.assertThat;

import com.sg.drones.api.v1.DroneController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ControllerAutoWiringTest {

    @Autowired
    private DroneController controller;

    @Test
    public void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }
}
