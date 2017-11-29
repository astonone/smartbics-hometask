package com.victorku.main.web;

import com.victorku.main.model.Hello;
import com.victorku.main.web.model.HelloDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @RequestMapping(value = "/say", method = RequestMethod.GET)
    public ResponseEntity<?> getAccount() {
        Hello hello = new Hello(1,"hello!");
        return new ResponseEntity<>(convert(hello), HttpStatus.OK);
    }

    private HelloDTO convert(Hello model) { return (model == null) ? null : new HelloDTO(model); }
}
