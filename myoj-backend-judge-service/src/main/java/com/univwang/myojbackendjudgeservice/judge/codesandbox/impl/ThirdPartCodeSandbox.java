package com.univwang.myojbackendjudgeservice.judge.codesandbox.impl;


import com.univwang.myojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.univwang.myojbackendmodel.model.codesandbox.ExecCuteCodeRequest;
import com.univwang.myojbackendmodel.model.codesandbox.ExecuteCodeResponse;

public class ThirdPartCodeSandbox implements CodeSandbox {

    @Override
    public ExecuteCodeResponse executeCode(ExecCuteCodeRequest execCuteCodeRequest) {
        System.out.println("第三方沙箱执行代码");
        return null;
    }
}
