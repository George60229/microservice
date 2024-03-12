package com.example.jymapplication.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AbstractEntity implements MyEntity {
    private Integer id;

    @Override
    public Integer getId() {
        return id;
    }
}
