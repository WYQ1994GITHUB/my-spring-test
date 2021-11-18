package com.wyq.aliyun.test;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alinlp.model.v20200629.GetNerChEcomRequest;
import com.aliyuncs.alinlp.model.v20200629.GetNerChEcomResponse;
import com.aliyuncs.alinlp.model.v20200629.GetTcChEcomRequest;
import com.aliyuncs.alinlp.model.v20200629.GetTcChEcomResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import lombok.Data;
import org.apache.commons.codec.binary.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 * @author xxx
 */
public class NlpDemoTest {
    private IAcsClient client;

    @Before
    public void initClinet() {
        // 创建DefaultAcsClient实例并初始化
        DefaultProfile defaultProfile = DefaultProfile.getProfile(
                "cn-hangzhou",
                "",
                "");

        client = new DefaultAcsClient(defaultProfile);
    }

    @Test
    public void nlpTest() {
        String text = "华为Mate40 Pro";
        GetNerChEcomResponse nerResponse = sendNerRequest(text);
        if (null == nerResponse || org.springframework.util.StringUtils.isEmpty(nerResponse.getData())) {
            return;
        }
        NerResult nerResult = JSON.parseObject(nerResponse.getData(), NerResult.class).buildRequestId(nerResponse.getRequestId());
        if (CollectionUtils.isEmpty(nerResult.getResult())) {
            return;
        }
        System.out.println(nerResult);
        Optional<NerData> optional = nerResult.getResult().stream()
                .filter(nlpData -> StringUtils.equals("品牌", nlpData.getTag()) || StringUtils.equals("品类", nlpData.getTag())).findFirst();
        if (!optional.isPresent()) {
            return;
        }
        GetTcChEcomResponse tcResponse = sendTcRequest(text);
        if (null == tcResponse || org.springframework.util.StringUtils.isEmpty(tcResponse.getData())) {
            return;
        }
        TcResult tcResult = JSON.parseObject(tcResponse.getData(), TcResult.class).buildRequestId(tcResponse.getRequestId());
        System.out.println(tcResult.getResult().getLabelName());
    }

    private GetNerChEcomResponse sendNerRequest(String text) {
        // 创建API请求并设置参数
        GetNerChEcomRequest request = new GetNerChEcomRequest();
        request.setText(text);
        request.setServiceCode("alinlp");
        request.setLexerId("ECOM");
        try {
            return client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    private GetTcChEcomResponse sendTcRequest(String text) {
        // 创建API请求并设置参数
        GetTcChEcomRequest request = new GetTcChEcomRequest();
        request.setServiceCode("alinlp");
        request.setText(text);
        try {
            return client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Data
    static class NerResult implements Serializable {
        private static final long serialVersionUID = 4967295451181755043L;
        private List<NerData> result;

        private Boolean success;

        private String requestId;

        private NerResult buildRequestId(String requestId) {
            this.requestId = requestId;
            return this;
        }
    }

    @Data
    static class NerData implements Serializable {
        private static final long serialVersionUID = 2431939356156435808L;
        /**
         * 同义词
         */
        private String synonym;

        /**
         * 权重
         */
        private String weight;

        /**
         * 类别
         */
        private String tag;

        /**
         * 实体
         */
        private String word;
    }

    @Data
    static class TcResult implements Serializable {
        private static final long serialVersionUID = 5973172071869535418L;

        private TcData result;

        private Boolean success;

        private String tracerId;

        private String requestId;

        private TcResult buildRequestId(String requestId) {
            this.requestId = requestId;
            return this;
        }
    }

    @Data
    static class TcData implements Serializable {
        private static final long serialVersionUID = 455000650438155109L;

        private String labelName;
    }

}
