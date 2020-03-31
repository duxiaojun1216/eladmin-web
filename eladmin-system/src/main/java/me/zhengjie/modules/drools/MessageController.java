package me.zhengjie.modules.drools;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.zhengjie.aop.log.Log;
import me.zhengjie.common.utils.ResultUtil;
import me.zhengjie.common.vo.Result;
import me.zhengjie.config.drools.DroolsUtils;
import me.zhengjie.config.drools.Message;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@Api(tags = "测试")
@RestController
@RequestMapping("/api/message")
public class MessageController {
    @Log("测试")
	@GetMapping(value = "/getSubsidyMoney")
    @ApiOperation("测试")
    public Result<Object> getSubsidyMoney(HttpServletRequest request) throws ParseException {
        //String startTime = request.getParameter("startTime");
       // String endTime = request.getParameter("endTime");
        String nowTime = request.getParameter("nowTime");

        int fcType = Integer.parseInt(request.getParameter("fcType"));
        double money = Double.parseDouble(request.getParameter("money"));

        SimpleDateFormat yyyy = new SimpleDateFormat("yyyy-MM-dd");
        //Date parse = yyyy.parse(startTime);
        //Date parse2 = yyyy.parse(endTime);
        Date parse3 = yyyy.parse(nowTime);

        SimpleDateFormat dd = new SimpleDateFormat("dd-MM-yyyy");
       // startTime = dd.format(parse);
        //endTime = dd.format(parse2);
        nowTime = dd.format(parse3);
        Message message = DroolsUtils.getResultMoney(fcType, nowTime, money);
        return new ResultUtil<Object>().setData(message);
    }

//    @Autowired
//    private QueueProducer queueProducer;
//    //注入存放消息的队列，用于下列方法一
//    @GetMapping(value = "/testQueue", produces = "text/html;charset=UTF-8;")
//    public String testQueue() {
//        String queueName = "QUEUE_RECEIVE_ADD_FIREND"; //自定义队列名称
//
//        for (int i = 0; i < 10; i++) {
//            Message message = new Message();
//            message.setStatus(1);
//            message.setMessage("小明" + i);
//            queueProducer.sendObjectMessage(queueName, message);   //发送到MQS
//        }
//        return "消息已经发送";
//    }

//    @GetMapping(value = "/testExportExcel", produces = "text/html;charset=UTF-8;")
//    public void testExportExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        String title = "xxxxx测试";
//        String[] rowsName = new String[]{"序号","地区","申报对象类型","标准化厂房","营业用房"
//                ,"自住住房","二手营业用房","二手自住住房","车位","合计","区合计"};
//        List<Object[]> dataList = new ArrayList<Object[]>();
//        Object[] objs = null;
//        for (int i = 0; i < 15; i++) {
//            objs = new Object[rowsName.length];
//            objs[0] = i;
//            objs[1] = "11111";
//            objs[2] = "22222";
//            objs[3] = "33333";
//            objs[4] = "44444";
//            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            String date = "2020-03-30";
//            objs[5] = date;
//            objs[6] = "44444";
//            objs[7] = "44444";
//            objs[8] = "44444";
//            objs[9] = "44444";
//            objs[10] = "44444";
//            dataList.add(objs);
//        }
//        ExportExcel ex = new ExportExcel(title, rowsName, dataList);
//        ex.exportExcelData(response);
//    }
}
