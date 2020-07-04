package com.example.demo.core.crp;

import com.example.demo.core.response.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StatResponse extends BaseResponse {
    private int totalNumber;

    /**
     * 30岁以下人员数量
     */
    private int numberOfL1;

    /**
     * 30-40岁人员数量
     */
    private int numberOfL2;

    /**
     * 40岁以上人员数量
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
