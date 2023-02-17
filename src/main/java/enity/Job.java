package enity;

import base.ArcBaseParams;
import decorator.ArcSort;
import decorator.ArcParams;
import lombok.NoArgsConstructor;

import java.util.List;

@ArcParams
public class Job {
    public String JobName;

    public Job(List<String> list) {
        this.JobName = list.get(0);
    }
}
