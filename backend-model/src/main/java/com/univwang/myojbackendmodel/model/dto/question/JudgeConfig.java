package com.univwang.myojbackendmodel.model.dto.question;

import lombok.Data;

/**
 * 题目用例
 */
@Data
public class JudgeConfig {

    /**
     * 时间限制ms
     */
    private Long timeLimit;

    /**
     * 内存限制kb
     */
    private Long memoryLimit;


    /**
     * 堆栈限制kb
     */
    private Long stackLimit;
}
