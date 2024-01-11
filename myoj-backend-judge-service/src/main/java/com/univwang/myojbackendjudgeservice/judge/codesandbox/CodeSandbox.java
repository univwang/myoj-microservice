package com.univwang.myojbackendjudgeservice.judge.codesandbox;


import com.univwang.myojbackendmodel.model.codesandbox.ExecCuteCodeRequest;
import com.univwang.myojbackendmodel.model.codesandbox.ExecuteCodeResponse;

public interface CodeSandbox  {
    /**
     * 执行代码
     * @param execCuteCodeRequest
     * @return ExecuteCodeResponse
     */
    ExecuteCodeResponse executeCode(ExecCuteCodeRequest execCuteCodeRequest);

}
