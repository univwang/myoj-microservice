package com.univwang.myojbackendjudgeservice.judge.strategy;

import cn.hutool.json.JSONUtil;
import com.univwang.myojbackendmodel.model.codesandbox.JudgeInfo;
import com.univwang.myojbackendmodel.model.dto.question.JudgeConfig;
import com.univwang.myojbackendmodel.model.entity.Question;
import com.univwang.myojbackendmodel.model.enums.JudgeInfoMessageEnum;

import java.util.List;

public class DefaultJudgeStrategy implements JudgeStrategy {
    @Override
    public JudgeInfo doJudge(JudgeContext judgeContext) {
        JudgeInfo judgeInfo = judgeContext.getJudgeInfo();
        List<String> inputList = judgeContext.getInputList();
        List<String> outputList = judgeContext.getOutputList();
        Question question = judgeContext.getQuestion();
//        List<JudgeCase> judgeCaseList = judgeContext.getJudgeCaseList();
        JudgeInfoMessageEnum judgeInfoMessageEnum = null;
        //校验结果
        if(inputList.size() != outputList.size()){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
            judgeInfo.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfo;
        }
        for (int i = 0; i < outputList.size(); i++) {
            if(!inputList.get(i).equals(outputList.get(i))){
                judgeInfoMessageEnum = JudgeInfoMessageEnum.WRONG_ANSWER;
                judgeInfo.setMessage(judgeInfoMessageEnum.getValue());
                return judgeInfo;
            }
        }
        //判断是否超时
        Long memory = judgeInfo.getMemory();
        Long time = judgeInfo.getTime();
        JudgeConfig judgeConfig = JSONUtil.toBean(question.getJudgeConfig(), JudgeConfig.class);
        Long timeLimit = judgeConfig.getTimeLimit();
        Long memoryLimit = judgeConfig.getMemoryLimit();
        if(memory > memoryLimit){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.MEMORY_LIMIT_EXCEEDED;
            judgeInfo.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfo;

        }
        if(time > timeLimit){
            judgeInfoMessageEnum = JudgeInfoMessageEnum.TIME_LIMIT_EXCEEDED;
            judgeInfo.setMessage(judgeInfoMessageEnum.getValue());
            return judgeInfo;
        }
        judgeInfo.setMessage(JudgeInfoMessageEnum.ACCEPTED.getValue());
        return judgeInfo;
    }
}
