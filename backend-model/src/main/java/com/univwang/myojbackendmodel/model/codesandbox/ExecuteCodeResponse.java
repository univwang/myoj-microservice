package com.univwang.myojbackendmodel.model.codesandbox;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ExecuteCodeResponse {
    /**
     * 输出
     */
    private Integer exitCode;

    private List<String> outputList;
    /**
     * 接口信息
     */
    private String message;
    /**
     * 执行状态
     */
    private Integer status;
    /**
     * 评测信息
     */
    private JudgeInfo judgeInfo;
}
