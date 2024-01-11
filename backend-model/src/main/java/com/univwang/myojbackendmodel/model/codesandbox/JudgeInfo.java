package com.univwang.myojbackendmodel.model.codesandbox;

import lombok.Data;

/**
 * 判题信息
 */
@Data
public class JudgeInfo {

    /**
     * 程序执行信息
     */
    private String message;

    /**
     * 内存消耗kb
     */
    private Long memory;


    /**
     * 时间消耗ms
     */
    private Long time;
}
