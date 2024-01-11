package com.univwang.myojbackendjudgeservice.judge.strategy;

import com.univwang.myojbackendmodel.model.codesandbox.JudgeInfo;
import com.univwang.myojbackendmodel.model.dto.question.JudgeCase;
import com.univwang.myojbackendmodel.model.entity.Question;
import com.univwang.myojbackendmodel.model.entity.QuestionSubmit;
import lombok.Data;

import java.util.List;

/**
 * 定义策略中的上下文
 */

@Data
public class JudgeContext {
    private Integer exitCode;
    private JudgeInfo judgeInfo;

    private List<String> inputList;

    private List<String> outputList;

    private Question question;

    private List<JudgeCase> judgeCaseList;

    private QuestionSubmit questionSubmit;
}
