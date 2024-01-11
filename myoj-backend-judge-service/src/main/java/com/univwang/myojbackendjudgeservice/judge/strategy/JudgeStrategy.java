package com.univwang.myojbackendjudgeservice.judge.strategy;

import com.univwang.myojbackendmodel.model.codesandbox.JudgeInfo;

/**
 * 判题策略
 */
public interface JudgeStrategy {
    JudgeInfo doJudge(JudgeContext judgeContext);

}
