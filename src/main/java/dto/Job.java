package dto;

import decorator.ArcParams;

import java.util.List;
import java.util.Map;

@ArcParams("Job")
public class Job {

    public String JobName;

    public Job() {

    }

    public Job(String jobName){
        this.JobName = jobName;
    }

    public Job(List<String> list){
        final String jn = list.get(0);
        this.JobName = jn;
    }

}
