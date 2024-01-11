package com.univwang.myojbackendjudgeservice.judge.codesandbox;

import com.univwang.myojbackendmodel.model.codesandbox.ExecCuteCodeRequest;
import com.univwang.myojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class CodeSandboxProxy implements CodeSandbox {

    private CodeSandbox codeSandbox;

    @Override
    public ExecuteCodeResponse executeCode(ExecCuteCodeRequest execCuteCodeRequest) {
        log.info("代理沙箱请求" + execCuteCodeRequest.toString());
        ExecuteCodeResponse response = codeSandbox.executeCode(execCuteCodeRequest);
        log.info("代理沙箱响应" + response.toString());
        return response;
    }
}
