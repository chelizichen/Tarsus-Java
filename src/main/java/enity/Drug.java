package enity;

import base.AdoBaseOrm;
import decorator.ArcParams;
import decorator.orm.Entity;
import decorator.orm.Column;
import decorator.orm.Key;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@ArcParams
@NoArgsConstructor
public class Drug extends AdoBaseOrm {

    public Drug(List<String> list) {
        this.id = list.get(0);
        this.DrName = list.get(1);
        this.DrPrice = list.get(2);
        this.DrRemark = list.get(3);
        this.DrCode = list.get(4);
    }

    @Column
    @Key
    public String id;

    @Column("dr_name")
    public String DrName;

    @Column("dr_price")
    public String DrPrice;

    @Column("dr_remark")
    public String DrRemark;

    @Column("dr_code")
    public String DrCode;

    @Override
    public String toString() {
        return "Drug{" +
                "id='" + id + '\'' +
                ", dr_name='" + DrName + '\'' +
                ", dr_price='" + DrPrice + '\'' +
                ", dr_remark='" + DrRemark + '\'' +
                ", dr_code='" + DrCode + '\'' +
                '}';
    }
}



