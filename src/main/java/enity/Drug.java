package enity;

import decorator.orm.ArcEntity;
import decorator.orm.ArcField;

@ArcEntity
public class Drug {

    @ArcField
    String id;

    @ArcField
    String dr_name;

    @ArcField
    String dr_price;

    @ArcField
    String dr_remark;

    @ArcField
    String dr_code;


}
