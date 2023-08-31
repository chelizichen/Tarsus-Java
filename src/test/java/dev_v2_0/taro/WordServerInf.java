 
    package dev_v2_0.taro;
    import dev_v2_0.struct.*;

    public interface  WordServerInf {
    public getWordListRes getWordList(getWordListReq req, getWordListRes res);
public getTranslateListRes getTranslateList(getTranslateListReq req, getTranslateListRes res);
public getTranslateListRes getTranslateListById(queryIdReq req, getTranslateListRes res);
public DelOrSaveRes delWordById(queryIdReq req, DelOrSaveRes res);
public DelOrSaveRes delTranslateByID(queryIdReq req, DelOrSaveRes res);
public DelOrSaveRes saveWord(Word req, DelOrSaveRes res);
public DelOrSaveRes saveTranslate(WordTranslate req, DelOrSaveRes res);

  }