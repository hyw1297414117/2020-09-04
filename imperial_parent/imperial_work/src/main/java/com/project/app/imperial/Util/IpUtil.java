package com.project.app.imperial.Util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;

public class IpUtil {
    private static String DEFAULT = "";

    /**
     * 解析客户端IP
     *内网
     * @return
     */
    public static final String getIpAdd() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    return DEFAULT;
                }
                ipAddress = inet.getHostAddress();
            }
        }
        // 如果通过代理访问,可能获取2个IP,这时候去第二个(代理服务端IP)
        if (ipAddress.split(",").length > 1) {
            ipAddress = ipAddress.split(",")[1].trim();
        }
        return ipAddress;
    }
    //公网
    public static String getIP() {
//        String ip = "http://ip.chinaz.com/getip.aspx";
        String ip = "http://pv.sohu.com/cityjson?ie=utf-8";

        String inputLine = "";
        String read = "";
        try {
            URL url = new URL(ip);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((read = in.readLine()) != null) {
                inputLine += read;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        int indexofdengyu=inputLine.indexOf("=");
        int indexofdohao=inputLine.indexOf(";");
        String innew=inputLine.substring(indexofdengyu+1,indexofdohao);
        System.out.println(innew);
        JSONObject jsonObject =  JSON.parseObject(innew);
        String imei= (String) jsonObject.get("cip");

        return imei;
    }
}