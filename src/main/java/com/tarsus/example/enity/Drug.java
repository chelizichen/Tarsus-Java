package com.tarsus.example.enity;

import com.tarsus.example.base.TarsusBaseOrm;
import com.tarsus.example.decorator.TarsusParam;
import com.tarsus.example.decorator.orm.Entity;
import com.tarsus.example.decorator.orm.Column;
import com.tarsus.example.decorator.orm.Key;
import com.tarsus.example.decorator.orm.Keyword;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity
@NoArgsConstructor
@TarsusParam
public class Drug extends TarsusBaseOrm {

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

    @Keyword
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



