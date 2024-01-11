package com.univwang.myojbackendjudgeservice.judge;


import com.univwang.myojbackendmodel.model.entity.QuestionSubmit;

public interface JudgeService {
    /**
     * 判题
     * @param questionSubmitId
     * @return
     */
    QuestionSubmit doJudge(long questionSubmitId);
}
