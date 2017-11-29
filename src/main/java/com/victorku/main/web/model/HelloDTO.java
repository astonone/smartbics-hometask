package com.victorku.main.web.model;

import com.victorku.main.model.Hello;

public class HelloDTO {
    private Long id;
    private String message;

    public HelloDTO() {
    }

    public HelloDTO(Hello model) {
        if (model == null) {
            return;
        }

        this.id = model.getId();
        this.message = model.getMessage();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
