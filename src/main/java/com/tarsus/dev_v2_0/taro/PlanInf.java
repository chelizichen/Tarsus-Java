 
    package com.tarsus.dev_v2_0.taro;
    import com.tarsus.dev_v2_0.struct.*;

  public interface  PlanInf {
    public getPlanDetailByIdRes getLatestPlanByUser(queryIdReq req, getPlanDetailByIdRes res);
public getUserPlansRes getPlansByUser(queryIdReq req, getUserPlansRes res);
public getPlanDetailByIdRes getPlanById(queryIdReq req, getPlanDetailByIdRes res);
public getPlanWordsByIdRes getPlanWordsById(queryIdReq req, getPlanWordsByIdRes res);
public baseRes markPlanWords(queryIdReq req, baseRes res);
public baseRes markPlan(queryIdReq req, baseRes res);
public baseRes delPlan(queryIdReq req, baseRes res);
public baseRes delPlanWords(queryIdReq req, baseRes res);
public baseRes savePlan(PlanDetail req, baseRes res);
public baseRes savePlanWords(PlanWords req, baseRes res);

  }