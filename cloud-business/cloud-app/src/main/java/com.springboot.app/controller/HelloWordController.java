package com.springboot.app.controller;

import com.springboot.app.sevice.WordService;
import com.springboot.app.sevice.impl.WordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description
 * @Date 2022/9/8 5:34 PM
 */
@RestController
@RequestMapping("/app")
public class HelloWordController {
    @Autowired
    WordServiceImpl wordServiceImpl;
    @GetMapping("helloWord")
    public WordService.WordServiceResponse helloWord() {
        return wordServiceImpl.init(new WordService.WordServiceRequest(123129L));
    }
}
