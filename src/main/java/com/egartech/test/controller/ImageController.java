package com.egartech.test.controller;

import com.egartech.test.service.GraphDesigner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ImageController {
    @Autowired
    private GraphDesigner designer;

    @RequestMapping(
            value = "/getchart",
            method = RequestMethod.GET,
            produces = "image/png"
    )
    @ResponseBody
    public byte[] getChart() {
        var image = designer.getAllUniq();
        return image;
    }

}
