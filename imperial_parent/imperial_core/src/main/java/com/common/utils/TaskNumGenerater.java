package com.common.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * @ author     :LianZheng
 * @ Description:任务编号生成，当前格式为：ITL+日期+4位自增数（补零）
 * @ Date       :2020-08-31
*/
public class TaskNumGenerater {
    private static final String SERIAL_NUMBER = "XXXX"; // 流水号格式
    private static TaskNumGenerater taskNumGenerater = null;

    private TaskNumGenerater() {
    }

    /**
     * 取得PrimaryGenerater的单例实现
     *
     * @return
     */
    public static TaskNumGenerater getInstance() {
        if (taskNumGenerater == null) {
            synchronized (TaskNumGenerater.class) {
                if (taskNumGenerater == null) {
                    taskNumGenerater = new TaskNumGenerater();
                }
            }
        }
        return taskNumGenerater;
    }

    /**
     * 生成下一个编号
     */
    public synchronized String generaterNextNumber(String sno) {
        String id = "ITL";
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        if (sno == null) {
            id += formatter.format(date) + "0001";
        } else {
            int count = SERIAL_NUMBER.length();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < count; i++) {
                sb.append("0");
            }
            DecimalFormat df = new DecimalFormat("0000");
            id += formatter.format(date)
                    + df.format(1 + Integer.parseInt(sno.substring(sno.length()-SERIAL_NUMBER.length(), sno.length())));
        }
        return id;
    }
}
