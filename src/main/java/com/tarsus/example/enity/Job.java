package com.tarsus.example.enity;

import com.tarsus.example.decorator.TarsusParam;

import java.util.List;

@TarsusParam
public class Job {
    public String JobName;

    public Job(List<String> list) {
        this.JobName = list.get(0);
    }
}
