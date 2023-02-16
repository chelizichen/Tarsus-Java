package dto;

import base.ArcBaseParams;
import decorator.ArcSort;
import decorator.ArcParams;

import java.util.List;

@ArcParams("Job")
public class Job extends ArcBaseParams {

    @ArcSort("0")
    public String JobName;

    public Job(List list) {
        super(list);
    }
}
