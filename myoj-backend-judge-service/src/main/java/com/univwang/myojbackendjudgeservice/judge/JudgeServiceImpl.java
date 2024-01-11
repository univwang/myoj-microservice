package com.univwang.myojbackendjudgeservice.judge;

import cn.hutool.json.JSONUtil;

import com.univwang.myojbackendcommon.common.ErrorCode;
import com.univwang.myojbackendcommon.exception.BusinessException;
import com.univwang.myojbackendjudgeservice.judge.codesandbox.CodeSandbox;
import com.univwang.myojbackendjudgeservice.judge.codesandbox.CodeSandboxFactory;
import com.univwang.myojbackendjudgeservice.judge.codesandbox.CodeSandboxProxy;
import com.univwang.myojbackendjudgeservice.judge.strategy.JudgeContext;
import com.univwang.myojbackendmodel.model.codesandbox.ExecCuteCodeRequest;
import com.univwang.myojbackendmodel.model.codesandbox.ExecuteCodeResponse;
import com.univwang.myojbackendmodel.model.codesandbox.JudgeInfo;
import com.univwang.myojbackendmodel.model.dto.question.JudgeCase;
import com.univwang.myojbackendmodel.model.entity.Question;
import com.univwang.myojbackendmodel.model.entity.QuestionSubmit;
import com.univwang.myojbackendmodel.model.enums.QuestionSubmitStatusEnum;
import com.univwang.myojbackendserviceclient.service.QuestionFeignClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class JudgeServiceImpl implements JudgeService {

    @Resource
    QuestionFeignClient questionFeignClient;

    @Value("${codeSandbox.type:example}")
    private String type;

    @Resource
    JudgeManager judgeManager;

    @Override
    public QuestionSubmit doJudge(long questionSubmitId) {

        //1.获取题目提交和题目信息
        QuestionSubmit submit = questionFeignClient.getQuestionSubmitById(questionSubmitId);
        if(submit == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "题目提交不存在");
        }
        Question question = questionFeignClient.getQuestionById(submit.getQuestionId());
        if(question == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "题目不存在");
        }
        if(!submit.getStatus().equals(QuestionSubmitStatusEnum.WAITING.getValue())) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "题目已经判题");
        }
        //2.更改题目提交状态
        submit.setStatus(QuestionSubmitStatusEnum.RUNNING.getValue());
        boolean update = questionFeignClient.updateQuestionSubmitById(submit);
        if(!update){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "更新题目提交状态失败");
        }
        //3.调用沙箱接口
        CodeSandbox codeSandbox = CodeSandboxFactory.newInstance(type);
        codeSandbox = new CodeSandboxProxy(codeSandbox);
        String code = submit.getCode();
        String language = submit.getLanguage();
        List<JudgeCase> judgeCaseList = JSONUtil.toList(question.getJudgeCase(), JudgeCase.class);
        List<String> inputList = judgeCaseList.stream().map(JudgeCase::getInput).collect(Collectors.toList());
        ExecCuteCodeRequest execCuteCodeRequest = ExecCuteCodeRequest.builder()
                .code(code)
                .language(language)
                .inputList(inputList)
                .build();
        ExecuteCodeResponse response = codeSandbox.executeCode(execCuteCodeRequest);
        //4. 判断沙箱接口返回的结果
        List<String> outputList = response.getOutputList();
        JudgeContext judgeContext = new JudgeContext();
        judgeContext.setJudgeInfo(response.getJudgeInfo());
        judgeContext.setInputList(inputList);
        judgeContext.setOutputList(outputList);
        judgeContext.setQuestion(question);
        judgeContext.setJudgeCaseList(judgeCaseList);
        judgeContext.setQuestionSubmit(submit);
        judgeContext.setExitCode(response.getExitCode());
        JudgeInfo judgeInfo = judgeManager.doJudge(judgeContext);
        //5. 修改数据库题目提交状态
        QuestionSubmit questionSubmitUpdate = new QuestionSubmit();
        questionSubmitUpdate.setId(submit.getId());
        questionSubmitUpdate.setStatus(QuestionSubmitStatusEnum.SUCCESS.getValue());
        questionSubmitUpdate.setJudgeInfo(JSONUtil.toJsonStr(judgeInfo));
        boolean updateById = questionFeignClient.updateQuestionSubmitById(questionSubmitUpdate);
        if(!updateById){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR, "更新题目提交状态失败");
        }
        //6. 返回题目提交信息
        QuestionSubmit questionSubmitResult = questionFeignClient.getQuestionSubmitById(submit.getId());
        return questionSubmitResult;
    }
}
