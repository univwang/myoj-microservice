package com.univwang.myojbackendmodel.model.vo;


import cn.hutool.json.JSONUtil;
import com.univwang.myojbackendmodel.model.codesandbox.JudgeInfo;
import com.univwang.myojbackendmodel.model.entity.QuestionSubmit;
import com.univwang.myojbackendmodel.model.enums.QuestionSubmitStatusEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 题目封装类
 * @TableName question
 */
@Data
public class QuestionSubmitVO implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 编程语言
     */
    private String language;

    /**
     * 用户代码
     */
    private String code;

    /**
     * 判题信息（json 对象）
     */
    private JudgeInfo judgeInfo;

    /**
     * 判题状态（0 - 待判题、1 - 判题中、2 - 成功、3 - 失败）
     */
    private Integer status;

    private String statusText;
    /**
     * 题目 id
     */
    private Long questionId;

    /**
     * 创建用户 id
     */
    private Long userId;

    private Date createTime;


    private static final long serialVersionUID = 1L;


    public static QuestionSubmit voToObj(QuestionSubmitVO questionSubmitVO) {
        if (questionSubmitVO == null) {
            return null;
        }
        QuestionSubmit questionSubmit = new QuestionSubmit();
        BeanUtils.copyProperties(questionSubmitVO, questionSubmit);
        String jsonStr = JSONUtil.toJsonStr(questionSubmitVO.getJudgeInfo());
        questionSubmit.setJudgeInfo(jsonStr);
        return questionSubmit;
    }

    /**
     * 对象转包装类
     *
     * @param questionSubmit
     * @return
     */
    public static QuestionSubmitVO objToVo(QuestionSubmit questionSubmit) {
        if (questionSubmit == null) {
            return null;
        }
        QuestionSubmitVO questionSubmitVO = new QuestionSubmitVO();
        BeanUtils.copyProperties(questionSubmit, questionSubmitVO);

        JudgeInfo bean = JSONUtil.toBean(questionSubmit.getJudgeInfo(), JudgeInfo.class);
        questionSubmitVO.setJudgeInfo(bean);
        questionSubmitVO.setStatusText(Objects.requireNonNull(QuestionSubmitStatusEnum.getEnumByValue(questionSubmit.getStatus())).getText());
        return questionSubmitVO;
    }
}