package cases;

import com.alibaba.fastjson.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.HttpPostman;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiaoyang on 2016/10/14.
 */
public class BetaTest {

    @Test(groups = { "functest" })
    public void Test1()throws Exception{
        String url = "http://comment.qa04.ymt360.com/comment/service/info";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("channel_id", 300);
        JSONObject result= HttpPostman.post(url,params);

        Assert.assertEquals(result.getString("errno"),"1000");

    }

    @Test(groups = { "functest" })
    public void Test2()throws Exception{
        String url = "http://comment.qa04.ymt360.com/comment/service/info";
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("channel_id", 300);
        JSONObject result= HttpPostman.post(url,params);

        Assert.assertEquals(result.getString("errno"),"1000");

    }

}
