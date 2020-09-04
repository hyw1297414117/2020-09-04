package com.project.app.imperial.serviceImpl;

import com.common.utils.MailUtils;
import com.project.app.imperial.domain.ImpBasicData;
import com.project.app.imperial.mapper.ImpBasicDataMapper;
import com.project.app.imperial.service.IImpDDUEmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class ImplDDUEmailSeriviceImpl implements IImpDDUEmailService {
    public static final Logger logger = LoggerFactory.getLogger(MailUtils.class);
    @Autowired
    private MailUtils mailUtils;
    @Autowired
    private TemplateEngine templateEngine;
    @Autowired
    private ImpBasicDataMapper impBasicDataMapper;
    @Override
    public Map<String, Integer> DDUSendEmail(String mainOrderNoVal, String serverUrl) {
        //          渲染html模板
        Context context = new Context();
        context.setVariable("url", serverUrl);
        String emailContent = null;
        File f = new File(this.getClass().getResource("/").getPath());
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("mainOrderNo", mainOrderNoVal);
        map.put("incoterms", "DDU");
        map.put("submitFlag", 1);
        List<ImpBasicData> list = impBasicDataMapper.selectImpBasicDataList(map);
        int i = 0;
        Map<String, Integer> amount = new HashMap<>();
        int scuueed = 0;
        int fail = 0;
        for (i = 0; i < list.size(); i++) {
            context.setVariable("shipperName", list.get(i).getShipperName());
            context.setVariable("reference1", list.get(i).getReference1());
            context.setVariable("line_address_1", list.get(i).getLineAddress1());
            context.setVariable("tacking_number_1", list.get(i).getTackingNumber1());
            emailContent = templateEngine.process("app/agent/DDUEmail", context);
            boolean resultofenforcement = mailUtils.sendAttachmentMailDDU(list.get(i).getEmail(), "DDU邮件", emailContent, f.getPath());//发邮件
            if (resultofenforcement) {
                scuueed++;
            } else {
                fail++;
            }
            //给所有人发邮件
            //mailUtils.sendAttachmentMailDDU(imp.getEmail(), "DDU邮件", emailContent,courseFile);//发邮件

        }
        amount.put("succeed", scuueed);
        amount.put("fail", fail);
        return amount;
    }



}
