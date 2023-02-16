package params;

import base.ArcBaseParams;
import decorator.ArcSort;
import decorator.ArcParams;

import java.util.List;

@ArcParams
public class Job extends ArcBaseParams {

    @ArcSort("0")
    public String JobName;

    public Job(List list) {
        super(list);
    }
}
