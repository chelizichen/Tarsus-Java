package com.tarsus.example.enity;

import com.tarsus.example.decorator.TarsusParam;
import com.tarsus.example.decorator.orm.Entity;
import lombok.NoArgsConstructor;

import java.util.List;

@TarsusParam
public class Job {
    public String JobName;

    public Job(List<Object> list) {
        this.JobName = (String) list.get(0);
    }
}
