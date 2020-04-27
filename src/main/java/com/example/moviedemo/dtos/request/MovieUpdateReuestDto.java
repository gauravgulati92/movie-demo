package com.example.moviedemo.dtos.request;

import javax.validation.constraints.NotNull;

public class MovieUpdateReuestDto extends MovieCreateRequestDto {

    @NotNull
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}