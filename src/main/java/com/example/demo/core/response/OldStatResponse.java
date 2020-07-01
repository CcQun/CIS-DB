package com.example.demo.core.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 *@Author fyl
 *@Date 2020/7/1
 * 老人信息统计
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OldStatResponse extends BaseResponse{

    private int totalNumber;

    /**
     * 60岁以下老人数量
     */
    private int numberOfL1;

    /**
     * 60-70岁老人数量
     */
    private int numberOfL2;

    /**
     * 70岁以上老人数量
     */
    private int numberOfL3;

    /**
     * 女性数量
     */
    private int numberOfFe;

    /**
     * 男性数量
     */
    private int numberOfMa;
}
