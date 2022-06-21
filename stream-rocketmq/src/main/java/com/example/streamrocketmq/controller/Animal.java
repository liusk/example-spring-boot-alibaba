package com.example.streamrocketmq.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liusk
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Animal implements Serializable {
    private String name;
    private String sex;
}
