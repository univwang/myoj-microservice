package com.univwang.myojbackendmodel.model.codesandbox;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExecCuteCodeRequest {

    /**
     * 输入
     */
    private List<String> inputList;
    /**
     * 代码
     */
    private String code;
    /**
     * 语言
     */
    private String language;

}
