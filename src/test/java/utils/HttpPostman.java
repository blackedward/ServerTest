package utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by xiaoyang on 2016/10/13.
 */
public class HttpPostman {

    private static Logger logger = Logger.getLogger("HttpPostman");

    public static JSONObject post(String url,Map<String,Object> reqMap){
        String res = "";
        String qJson = JSON.toJSONString(reqMap);
        List<NameValuePair> reqParams = new ArrayList<NameValuePair>();
        for (String paramname : reqMap.keySet()) {
            Object param = reqMap.get(paramname);
            reqParams.add(new BasicNameValuePair(paramname, String.valueOf(param)));
        }

        logger.info("http connecting...");

        HttpClient hc = new DefaultHttpClient();
        HttpParams pms = hc.getParams();
        HttpConnectionParams.setConnectionTimeout(pms, 7000);
        HttpConnectionParams.setSoTimeout(pms, 7000);

        HttpPost httppost = new HttpPost(url);
        HttpEntity entity = null;

        try {
            // 设置参数
            httppost.setEntity(new UrlEncodedFormEntity(reqParams, HTTP.UTF_8));
            HttpResponse httpresponse = hc.execute(httppost);
            // 获取返回数据
            entity = httpresponse.getEntity();
            res = EntityUtils.toString(entity,"utf-8");
            EntityUtils.consume(entity);
            logger.info(res);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (res == null || res.isEmpty()) {
            throw new RuntimeException("httpreturn cannot be empty");
        }

        JSONObject Res=JSON.parseObject(res);
        return Res;

    }

}




