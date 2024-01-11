package com.univwang.myojbackendjudgeservice.judge;



import com.univwang.myojbackendjudgeservice.judge.strategy.DefaultJudgeStrategy;
import com.univwang.myojbackendjudgeservice.judge.strategy.JavaLanguageJudgeStrategy;
import com.univwang.myojbackendjudgeservice.judge.strategy.JudgeContext;
import com.univwang.myojbackendjudgeservice.judge.strategy.JudgeStrategy;
import com.univwang.myojbackendmodel.model.codesandbox.JudgeInfo;
import com.univwang.myojbackendmodel.model.entity.QuestionSubmit;
import org.springframework.stereotype.Service;

/**
 * 判题策略管理
 */

@Service
public class JudgeManager {
    JudgeInfo doJudge(JudgeContext judgeContext) {
        QuestionSubmit questionSubmit = judgeContext.getQuestionSubmit();
        String language = questionSubmit.getLanguage();
        JudgeStrategy judgeStrategy = new DefaultJudgeStrategy();
        if(language.equals("java")) {
            judgeStrategy = new JavaLanguageJudgeStrategy();
        }
        return judgeStrategy.doJudge(judgeContext);
    }
}
