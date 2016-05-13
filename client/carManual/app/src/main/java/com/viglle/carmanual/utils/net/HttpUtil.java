package com.viglle.carmanual.utils.net;

import android.os.AsyncTask;

import com.viglle.carmanual.utils.LogUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/4/19.
 */
public class HttpUtil {

    public static final String HOST_URL="http://192.168.16.198:8082/";
    public static final String POST="POST";
    public static final String GET="GET";
    private static OkHttpClient client = new OkHttpClient();

    /**
     *
     * @param method 请求方式 默认POST;method=GET
     * @param url
     * @param params
     * @param handler
     */
    public static void http(String method,String url,List<TwoValues<String,String>>params,HttpHandlerInterface handler){
        if(method==null||method.equals("")||method.equalsIgnoreCase(HttpUtil.POST)){
            httpPost(url,params,handler);
        }else if(method.equalsIgnoreCase(HttpUtil.GET)){
            httpGet(url,params,handler);
        }else {
            httpPost(url,params,handler);
        }
    }

    public static void httpGet(List<TwoValues<String,String>>params,HttpHandlerInterface handler){
        String url=getHost()+buildGetParams(params);
        httpGet(url,handler);
    }
    public static void httpGet(String url,List<TwoValues<String,String>>params,HttpHandlerInterface handler){
        url=url+buildGetParams(params);
        httpGet(url,handler);
    }


    public static void httpGet(String url,HttpHandlerInterface handler){

        Request request=new Request.Builder()
                .url(url)
                .get()
                .build();
        sendRequest(request, handler);
    }

    public static void httpPost(List<TwoValues<String,String>>params,HttpHandlerInterface handler){
        String url=getHost();
        httpPost(url, params, handler);
    }

    public static void httpPost(String url,List<TwoValues<String,String>>params,HttpHandlerInterface handler){
        RequestBody body = buildPostParams(params);
        httpPost(url, body, handler);
    }


    public static void httpPost(String url,RequestBody body,HttpHandlerInterface handler){
        Request.Builder builder=new Request.Builder();
        if(body!=null){
            builder.post(body);
        }
        Request request=builder.url(url).build();
        sendRequest(request, handler);
    }

    public static void httpPost(String url,HttpHandlerInterface handler){
        List<TwoValues<String,String>>list = new ArrayList<>();
        httpPost(url, list, handler);
    }

    /**
     * 构建　GET　请求参数
     * @param params
     * @return
     */
    private static String buildGetParams(List<TwoValues<String,String>>params){
        if(params==null||params.isEmpty()){
            return "";
        }
        String limit="?";
        StringBuffer stringBuffer=new StringBuffer();
        for(TwoValues<String,String> model:params){
            stringBuffer.append(limit).append(model.key).append("=").append(model.value);
            limit="&";
        }
//        Log.i("getParamStr", "getParamStr=" + stringBuffer.toString());
        return stringBuffer.toString();
    }


    /**
     * 构建　POST 请求参数
     * @param params
     * @return
     */
    private static RequestBody buildPostParams(List<TwoValues<String,String>>params){
        if(params==null||params.isEmpty()){
            return null;
        }

        FormBody.Builder builder=new FormBody.Builder();
        for(TwoValues<String,String> model:params){
            builder.add(model.key, model.value);
        }
        RequestBody body=builder.build();
        return body;
    }

    private static String getHost(){
        return HOST_URL;
    }


    public static void sendRequest( Request request,final HttpHandlerInterface handler) {
        new AsyncTask<Request,Integer,HttpResultModel>(){
            @Override
            protected HttpResultModel doInBackground(Request... params) {
                Request tempRequest=params[0];
                HttpResultModel model = new HttpResultModel();
                if(tempRequest==null){
                    model.code=-1;
                    model.ex=new IOException("request Entity is null");
                    model.data="";
                    return model;
                }
                try {
                    Response response=client.newCall(params[0]).execute();
                    if(!response.isSuccessful()){
                        model.code=response.code();
                        model.data="";
                        LogUtil.log_i("请求失败 url="+ tempRequest.url().toString());
                        LogUtil.log_i("Http状态码 code="+model.code);
                    }else{
                        String result=response.body().string();
                        model.code=response.code();
                        model.data=result;
                        LogUtil.log_i("请求成功 url="+tempRequest.url().toString());
                        LogUtil.log_i("返回的数据 data="+result);
                        return model;
                    }

                } catch (IOException e) {
                    LogUtil.log_i("客户端IO异常 url=" + tempRequest.url().toString() );
                    e.printStackTrace();
                    model.data="";
                    model.ex=e;
                    return model;
                }
                return null;
            }

            @Override
            protected void onPostExecute(HttpResultModel resultModel) {
                super.onPostExecute(resultModel);
                if(handler==null){
                    LogUtil.log_w(new Exception("HttpHandlerInterface is null"));
                    return;
                }
                if(resultModel==null){
                    handler.onFailure(-1,new IOException("UnCheckedException"));
                    return;
                }
                if(resultModel.code==200){
                    handler.onSuccess(resultModel.data);
                }else{
                    handler.onFailure(resultModel.code,resultModel.ex);
                }
            }
        }.execute(request);

    }
    public static class HttpResultModel{
        public int code;
        public String data;
        public IOException ex;
    }
}
