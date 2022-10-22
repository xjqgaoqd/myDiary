package com.demo.diary.common.util;

import com.alibaba.cloudapi.sdk.client.ApacheHttpClient;
import com.alibaba.cloudapi.sdk.enums.HttpMethod;
import com.alibaba.cloudapi.sdk.enums.ParamPosition;
import com.alibaba.cloudapi.sdk.enums.Scheme;
import com.alibaba.cloudapi.sdk.model.ApiCallback;
import com.alibaba.cloudapi.sdk.model.ApiRequest;
import com.alibaba.cloudapi.sdk.model.ApiResponse;
import com.alibaba.cloudapi.sdk.model.HttpClientBuilderParams;

public class HttpApiClientSDKTest extends ApacheHttpClient {
    public final static String HOST = "8fb0a809c8c548edbdafef7b4781e6ae.apigateway.res.sgmc.sgcc.com.cn";
    static HttpApiClientSDKTest instance = new HttpApiClientSDKTest();
    public static HttpApiClientSDKTest getInstance(){return instance;}

    public void init(HttpClientBuilderParams httpClientBuilderParams){
        httpClientBuilderParams.setScheme(Scheme.HTTP);
        httpClientBuilderParams.setHost(HOST);
        super.init(httpClientBuilderParams);
    }




    public void getEcpRunwayMap(Integer pageNum , Integer pageSize , String prjno , ApiCallback callback) {
        String path = "/ast/jsjdqlc/rds/get_ecp_runway_map_cx";
        ApiRequest request = new ApiRequest(HttpMethod.GET , path);
        request.addParam("pageNum" , String.valueOf(pageNum) , ParamPosition.QUERY , true);
        request.addParam("pageSize" , String.valueOf(pageSize) , ParamPosition.QUERY , true);
        request.addParam("prjno" , prjno , ParamPosition.QUERY , false);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse GET_技术监督全流程_跑道图_跑道图建设信息表_ecp_runway_map_查询SyncMode(Integer pageNum , Integer pageSize , String prjno) {
        String path = "/ast/jsjdqlc/rds/get_ecp_runway_map_cx";
        ApiRequest request = new ApiRequest(HttpMethod.GET , path);
        request.addParam("pageNum" , String.valueOf(pageNum) , ParamPosition.QUERY , true);
        request.addParam("pageSize" , String.valueOf(pageSize) , ParamPosition.QUERY , true);
        request.addParam("prjno" , prjno , ParamPosition.QUERY , false);



        return sendSyncRequest(request);
    }
    public void GET_技术监督全流程_分项进度_分项进度表_construction_progress_查询(Integer pageNum , Integer pageSize , String prjno , ApiCallback callback) {
        String path = "/ast/jsjdqlc/rds/get_construction_progress_cx";
        ApiRequest request = new ApiRequest(HttpMethod.GET , path);
        request.addParam("pageNum" , String.valueOf(pageNum) , ParamPosition.QUERY , true);
        request.addParam("pageSize" , String.valueOf(pageSize) , ParamPosition.QUERY , true);
        request.addParam("prjno" , prjno , ParamPosition.QUERY , false);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse GET_技术监督全流程_分项进度_分项进度表_construction_progress_查询SyncMode(Integer pageNum , Integer pageSize , String prjno) {
        String path = "/ast/jsjdqlc/rds/get_construction_progress_cx";
        ApiRequest request = new ApiRequest(HttpMethod.GET , path);
        request.addParam("pageNum" , String.valueOf(pageNum) , ParamPosition.QUERY , true);
        request.addParam("pageSize" , String.valueOf(pageSize) , ParamPosition.QUERY , true);
        request.addParam("prjno" , prjno , ParamPosition.QUERY , false);



        return sendSyncRequest(request);
    }
    public void GET_技术监督全流程_物资_物资表_dws_mat_gyjhjzjh_查询(Integer pageNum , Integer pageSize , String prjcode , ApiCallback callback) {
        String path = "/mat/jsjdqlc/rds/get_dws_mat_gyjhjzjh_cx";
        ApiRequest request = new ApiRequest(HttpMethod.GET , path);
        request.addParam("pageNum" , String.valueOf(pageNum) , ParamPosition.QUERY , true);
        request.addParam("pageSize" , String.valueOf(pageSize) , ParamPosition.QUERY , true);
        request.addParam("prjcode" , prjcode , ParamPosition.QUERY , false);



        sendAsyncRequest(request , callback);
    }

    public ApiResponse GET_技术监督全流程_物资_物资表_dws_mat_gyjhjzjh_查询SyncMode(Integer pageNum , Integer pageSize , String prjcode) {
        String path = "/mat/jsjdqlc/rds/get_dws_mat_gyjhjzjh_cx";
        ApiRequest request = new ApiRequest(HttpMethod.GET , path);
        request.addParam("pageNum" , String.valueOf(pageNum) , ParamPosition.QUERY , true);
        request.addParam("pageSize" , String.valueOf(pageSize) , ParamPosition.QUERY , true);
        request.addParam("prjcode" , prjcode , ParamPosition.QUERY , false);



        return sendSyncRequest(request);
    }
}
