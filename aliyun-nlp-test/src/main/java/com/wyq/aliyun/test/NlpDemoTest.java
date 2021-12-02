package com.wyq.aliyun.test;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.alinlp.model.v20200629.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.wyq.aliyun.util.ExcelWriteUtils;
import lombok.Data;
import org.apache.commons.codec.binary.StringUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author xxx
 */
public class NlpDemoTest {
    private IAcsClient client;

    ExecutorService pool = Executors.newFixedThreadPool(1);

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
    public void excelTest() throws Exception {
        String filePath = "/Users/yongqiwang/Downloads/商机对话.xlsx";
        File file = new File(filePath);
        XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(file));
        XSSFSheet sheet = wb.getSheetAt(0);
        Set<String> result = new HashSet<>();
        for (Row row : sheet) {
            result.add(row.getCell(0).getStringCellValue());
        }

        String exportPath = "/Users/yongqiwang/Downloads/result.xlsx";
        List<String> rowTitles = Lists.newArrayList("文本", "过滤文本", "实体识别", "智能分类");

        List<BatchData> list = Lists.newArrayList();

        int count = 0;
        List<Integer> times = Lists.newArrayList();
        for (String s : result) {
            count++;
//            pool.execute(() -> {
//                BatchData batchData = batchTest(s);
//                list.add(batchData);
//            });
            BatchData batchData = batchTest(s, times);
            list.add(batchData);

            if (count % 10 == 0) {
                System.out.println("已识别：" + count + "条");
            }
        }

        System.out.println("max: " + times.stream().max(Comparator.naturalOrder()).get());
        System.out.println("avg:" + times.stream().mapToInt(Integer::intValue).average().orElse(0D));

//        while (count != list.size()) {
//            Thread.sleep(2000);
//        }
        List<List<String>> data = Lists.newArrayList();
        for (BatchData batchData : list) {
            List<String> row = Lists.newArrayList();
            row.add(batchData.getText());
            row.add(batchData.getDealText());
            row.add(batchData.getNerResult());
            row.add(batchData.getTcResult());
            data.add(row);
        }

        ExcelWriteUtils.writeExcel(exportPath, rowTitles, data);
    }

    private BatchData batchTest(String text, List<Integer> times) {
        BatchData batchData = new BatchData();
        batchData.setText(text);


//        String p = "@(.+?)\\s";
        String p = "@(.+?) |@(.+?)(\\s|$)";
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(text);

        String dealText = matcher.replaceAll("");
        batchData.setDealText(dealText);

//        GetNerChEcomResponse nerResponse = sendNerRequest(dealText);
        long start = System.currentTimeMillis();
        GetNerCustomizedChEcomResponse nerResponse = sendNerCusRequest(dealText);
        Integer cost = Integer.parseInt(Long.toString((System.currentTimeMillis() - start)));
        times.add(cost);
        if (null != nerResponse && !org.springframework.util.StringUtils.isEmpty(nerResponse.getData())) {
            NerResult nerResult = JSON.parseObject(nerResponse.getData(), NerResult.class).buildRequestId(nerResponse.getRequestId());
            batchData.setNerResult(JSON.toJSONString(nerResult.getResult()));
        }

        GetTcChEcomResponse tcResponse = sendTcRequest(dealText);
        if (tcResponse == null || org.springframework.util.StringUtils.isEmpty(tcResponse.getData())) {
            return batchData;
        }
        TcResult tcResult = JSON.parseObject(tcResponse.getData(), TcResult.class).buildRequestId(tcResponse.getRequestId());
        batchData.setTcResult(tcResult.getResult().getLabelName());

        return batchData;
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

    private GetNerCustomizedChEcomResponse sendNerCusRequest(String text) {
        GetNerCustomizedChEcomRequest request = new GetNerCustomizedChEcomRequest();
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
        if (org.springframework.util.StringUtils.isEmpty(text)) {
            return null;
        }
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
//        private String synonym;

        /**
         * 权重
         */
//        private String weight;

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

    @Data
    static class BatchData implements Serializable {
        private static final long serialVersionUID = 409473033961207246L;

        private String text;

        private String dealText;

        private String nerResult;

        private String tcResult;
    }


    public static void main(String args[]) {
//        String str = "@老爸评测 菠萝 有推荐的咖啡吗？";
        String str = "那个铁剂瓶装的@你好啊 有没有？@佳佳";
        String p = "@(.+?) |@(.+?)(\\s|$)";
        Pattern pattern = Pattern.compile(p);
        Matcher matcher = pattern.matcher(str);
        int count = 0;
        while (matcher.find()) {
            count++;
        }

        String s = matcher.replaceAll("");
        System.out.println(s);
    }
}
