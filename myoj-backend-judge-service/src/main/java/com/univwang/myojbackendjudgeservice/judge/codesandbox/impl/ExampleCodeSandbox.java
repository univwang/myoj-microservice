package com.univwang.myojbackendjudgeservice.judge.codesandbox.impl;


import com.univwang.myojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.univwang.myojbackendmodel.model.codesandbox.ExecCuteCodeRequest;
import com.univwang.myojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import com.univwang.myojbackendmodel.model.codesandbox.JudgeInfo;
import com.univwang.myojbackendmodel.model.enums.JudgeInfoMessageEnum;
import com.univwang.myojbackendmodel.model.enums.QuestionSubmitStatusEnum;

import java.util.List;

public class ExampleCodeSandbox implements CodeSandbox {

    @Override
    public ExecuteCodeResponse executeCode(ExecCuteCodeRequest execCuteCodeRequest) {
        List<String> inputList = execCuteCodeRequest.getInputList();
        ExecuteCodeResponse response = new ExecuteCodeResponse();
        response.setOutputList(inputList);
        response.setMessage("测试成功");
        response.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        JudgeInfo judgeInfo = new JudgeInfo();
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        judgeInfo.setMemory(100L);
        judgeInfo.setTime(100L);
        response.setJudgeInfo(judgeInfo);
        return response;
    }
}
