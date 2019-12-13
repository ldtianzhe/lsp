package com.lsp.common.utils.ip;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSONObject;
import com.lsp.common.utils.StringUtils;
import com.lsp.common.utils.http.HttpUtils;

/**
 * 获取地址类
 *
 * @author lsp
 */
public class AddressUtils
{
    private static final Logger log = LoggerFactory.getLogger(AddressUtils.class);

    //淘宝接口
    //public static final String IP_URL = "http://ip.taobao.com/service/getIpInfo.php";
    //360搜索
    public static final String IP_URL = "https://m.so.com/position";



    public static String getRealAddressByIP(String ip)
    {
        String address = "XX XX";
        // 内网不查询
        if (IpUtils.internalIp(ip))
        {
            return "内网IP";
        }
        String rspStr = HttpUtils.sendPost(IP_URL, "ip=" + ip);
        if (StringUtils.isEmpty(rspStr))
        {
            log.error("获取地理位置异常 {}", ip);
            return address;
        }
        JSONObject obj = JSONObject.parseObject(rspStr);
        JSONObject data = obj.getObject("data", JSONObject.class);
        JSONObject position = data.getObject("position",JSONObject.class);
        String province = position.getString("province");
        String city = position.getString("city");
        address = province + " " + city;
        return address;
    }
}
