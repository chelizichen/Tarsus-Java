package com.tarsus.dev_v2_0.interfaces;

import com.tarsus.dev_v2_0.struct.*;
import com.tarsus.dev_v2_0.taro.PlanInf;
import com.tarsus.lib.lib_decorator.ms.TarsusMethod;
import com.tarsus.lib.lib_decorator.ms.TarsusReflect;

@TarsusReflect(proxy = "WordNodeServer",interFace = "plan")
public class PlanImpl implements PlanInf {
    @Override
    public getPlanDetailByIdRes getLatestPlanByUser(queryIdReq req, getPlanDetailByIdRes res) {
        return null;
    }

    @Override
    public getUserPlansRes getPlansByUser(queryIdReq req, getUserPlansRes res) {
        return null;
    }

    @Override
    public getPlanDetailByIdRes getPlanById(queryIdReq req, getPlanDetailByIdRes res) {
        return null;
    }

    @Override
    public getPlanWordsByIdRes getPlanWordsById(queryIdReq req, getPlanWordsByIdRes res) {
        return null;
    }

    @Override
    public baseRes markPlanWords(queryIdReq req, baseRes res) {
        return null;
    }

    @Override
    public baseRes markPlan(queryIdReq req, baseRes res) {
        return null;
    }

    @Override
    public baseRes delPlan(queryIdReq req, baseRes res) {
        return null;
    }

    @Override
    public baseRes delPlanWords(queryIdReq req, baseRes res) {
        return null;
    }

    @Override
    public baseRes savePlan(PlanDetail req, baseRes res) {
        return null;
    }

    @Override
    public baseRes savePlanWords(PlanWords req, baseRes res) {
        return null;
    }
}
