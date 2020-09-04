package com.project.app.imperial.service;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;


public interface IImpDDUEmailService {
    /**
     * 发送DDU邮件
     * */
    public Map<String, Integer> DDUSendEmail(String mainOrderNoVal, String serverUrl);



}
