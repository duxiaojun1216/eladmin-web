package me.zhengjie.config.drools;

import me.zhengjie.common.utils.SecurityUtil;
import me.zhengjie.utils.SecurityUtils;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DroolsUtils {

    public static Message getResultMoney(int fcType, String nowTime, double money) throws ParseException {
        String format = "dd-MM-yyyy";
        System.setProperty("drools.dateformat", format);
        KieServices ks = KieServices.Factory.get();
        KieContainer kContainer = ks.getKieClasspathContainer();
        KieSession kSession = kContainer.newKieSession("ksession-one");
        DateFormat df = new SimpleDateFormat(format);
        // go !
        Message message = new Message();
        //设置房产类型
        message.setFcType(fcType);
        //message.setStartTime(df.parse(startTime));
        //message.setEndTime(df.parse(endTime));
        message.setNowTime(df.parse(nowTime));
        message.setMoney(money);
        kSession.insert(message);//插入
        kSession.fireAllRules();//执行规则
        kSession.dispose();
        //return "{\"resultMoney\": " + message.getResultMoney() + ",\"resultType\":" + message.getResultType() + "}";
        return message;
    }
}
