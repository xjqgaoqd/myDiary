package com.demo.diary.controller;

import com.alibaba.cloudapi.sdk.constant.SdkConstant;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;
import com.alibaba.fastjson.JSONObject;
import com.demo.diary.common.util.HttpApiClientSDKTest;
import com.demo.diary.common.vo.WrappedResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/sdk")
@Api(tags = "SDK接口")
public class SDKTest {

    static{
        //HTTP Client init
        HttpClientBuilderParams httpParam = new HttpClientBuilderParams();
        httpParam.setAppKey("2349a5fd5c1d4436828168e1d4047b0e");
        httpParam.setAppSecret("jBsevRfTpfEoc9zmpRR1ZtTlJU=");
        HttpApiClientSDKTest.getInstance().init(httpParam);


    }

    @PostMapping("/sdkTest")
    @ApiOperation(value = "sdkTest")
    public WrappedResult sdkTest(){
        JSONObject jsonObject = null;
        jsonObject = getEcpRunwayMap();
        return WrappedResult.successWrapedResult(jsonObject);
    }

    public static JSONObject getEcpRunwayMap(){
        final JSONObject[] res = {new JSONObject()};
        HttpApiClientSDKTest.getInstance().getEcpRunwayMap(1 , 10 , "default" , new ApiCallback() {
            @Override
            public void onFailure(ApiRequest request, Exception e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(ApiRequest request, ApiResponse response) {
                try {
                    String a = getResultString(response);
                    System.out.println(a);
                    res[0] = JSONObject.parseObject(a);
                }catch (Exception ex){
                    ex.printStackTrace();
                }
            }
        });
        return res[0];
    }

    private static String getResultString(ApiResponse response) throws IOException {
        StringBuilder result = new StringBuilder();
        result.append("Response from backend server").append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        result.append("ResultCode:").append(SdkConstant.CLOUDAPI_LF).append(response.getCode()).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        if(response.getCode() != 200){
            result.append("Error description:").append(response.getHeaders().get("X-Ca-Error-Message")).append(SdkConstant.CLOUDAPI_LF).append(SdkConstant.CLOUDAPI_LF);
        }

        result.append("ResultBody:").append(SdkConstant.CLOUDAPI_LF).append(new String(response.getBody() , SdkConstant.CLOUDAPI_ENCODING));

        return result.toString();
    }
}
