package com.univwang.myojbackendquestionservice.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.univwang.myojbackendmodel.model.dto.questionsubmit.QuestionSubmitAddRequest;
import com.univwang.myojbackendmodel.model.dto.questionsubmit.QuestionSubmitQueryRequest;
import com.univwang.myojbackendmodel.model.entity.QuestionSubmit;
import com.univwang.myojbackendmodel.model.entity.User;
import com.univwang.myojbackendmodel.model.vo.QuestionSubmitVO;


/**
* @author 11516
* @description 针对表【question_submit(题目提交)】的数据库操作Service
* @createDate 2023-12-13 21:31:08
*/
public interface QuestionSubmitService extends IService<QuestionSubmit> {
    /**
     * 提交
     *
     * @param questionId
     * @param loginUser
     * @return
     */
    long doQuestionSubmit(QuestionSubmitAddRequest questionId, User loginUser);

    /**
     * 提交提交（内部服务）
     *
     * @param userId
     * @param questionId
     * @return
     */


    /**
     * 获取查询条件
     *
     * @param questionSubmitQueryRequest
     * @return
     */
    QueryWrapper<QuestionSubmit> getQueryWrapper(QuestionSubmitQueryRequest questionSubmitQueryRequest);


    Page<QuestionSubmitVO> getQuestionSubmitVOPage(Page<QuestionSubmit> questionSubmitPage, User loginUser);

    QuestionSubmitVO getQuestionSubmitVO(QuestionSubmit questionSubmit, User loginUser);
}
