package com.example.demo.core.crp;

import com.example.demo.core.response.BaseResponse;
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
public class OldStatResponse extends BaseResponse {

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

    /**
     * 心脏病人数
     */
    private int numberOfA;

    /**
     * 糖尿病数量
     */
    private int numberOfB;

    /**
     * 高血压数量
     */
    private int numberOfC;

    /**
     * 高血脂数量
     */
    private int numberOfD;

}
