package com.tarsus.example.enity;

import com.tarsus.example.decorator.TaroStruct;
import lombok.NoArgsConstructor;

import java.util.List;

@TaroStruct
@NoArgsConstructor
public class Job {
    public String JobName;

    public Job(List<Object> list) {
        this.JobName = (String) list.get(0);
    }
}
