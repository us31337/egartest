package com.egartech.test.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GraphDesignerTest {
    @Autowired
    private GraphDesigner designer;

    @Test
    void getAllUniqAndDraw() {
        designer.getAllUniq();
/*
        try {
            designer.draw();
        } catch (IOException e) {
            e.printStackTrace();
        }
*/
    }
}