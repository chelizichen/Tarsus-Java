package com.tarsus.dev_v1_0.enity;

import com.tarsus.dev_v1_0.base.TarsusBaseOrm;
import com.tarsus.dev_v1_0.decorator.orm.Column;
import com.tarsus.dev_v1_0.decorator.orm.Entity;
import com.tarsus.dev_v1_0.decorator.orm.Key;
import com.tarsus.dev_v1_0.decorator.orm.Keyword;

@Entity
public class Drug extends TarsusBaseOrm {

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



