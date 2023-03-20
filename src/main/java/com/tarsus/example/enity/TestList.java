package com.tarsus.example.enity;

import com.alibaba.fastjson.JSON;
import com.tarsus.example.decorator.TaroStruct;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@TaroStruct
public class TestList {
    public String name;
    public Job job;
    public List<Job> jobList;
    public TestList(List<Object> list) {

        this.name = (String) list.get(0);
        this.job = new Job((List<Object>) list.get(1));

        List<HashMap> maps = JSON.parseArray((String) list.get(2), HashMap.class);
        this.jobList = new ArrayList<>();
        for (HashMap map : maps) {
            Job job = new Job();
            job.JobName = (String) map.get("jobName");
            this.jobList.add(job);
        }

    }
}
