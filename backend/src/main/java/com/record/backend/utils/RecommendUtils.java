package com.record.backend.utils;

import com.record.backend.mapping.RecordMapping;
import com.record.backend.mapping.UserMapping;
import com.record.backend.pojo.Record;
import com.record.backend.pojo.Record_User;
import com.record.backend.pojo.Solution;
import com.record.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Along
 * @Date: Created in 13:51 2021/9/24
 * 定时 运行推荐算法
 **/
@Component
public class RecommendUtils {

    @Autowired
    private UserMapping userMapping;

    @Autowired
    private RecordMapping recordMapping;

    // @Scheduled(cron="0 0 2 * * ? ") // 每天的两点运行一次
    // @Scheduled(fixedRate = 10000)
    public void scheduledTask() {
        List<Record_User> record_userList = recordMapping.selectAllRecordUser(1);
        System.out.println(record_userList);
        //输入用户的所有信息
        for(Record_User record_user : record_userList){
            List<User> users = userMapping.getAllSolutionAsUsers();
            Recommend recommend = new Recommend();
            System.out.println("-----------------------");
            System.out.println("当前的user为"+record_user);
            System.out.println("-----------------------");
            List<Solution> recommendationMovies = recommend.recommend(record_user.getUser_id(), users);
            System.out.println("-----------1------------");
            List<Record> recordList = new ArrayList<>();
            System.out.println("推荐结果如下：");
            for (Solution solution : recommendationMovies) {
                Record record = new Record();
                System.out.println("solution.getProblem_id()-----"+solution.getProblem_id());
                record.setRecord_id(record_user.getUser_id());
                record.setProblem_id(solution.getProblem_id());
                record.setStatus(0);
                recordList.add(record);
            }
            recordMapping.addRecord(recordList);
            System.out.println(recordList);
        }
    }
}
