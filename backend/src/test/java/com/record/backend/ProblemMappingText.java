package com.record.backend;

import com.record.backend.mapping.ProblemMapping;
import com.record.backend.mapping.RecordMapping;
import com.record.backend.mapping.SolutionMapping;
import com.record.backend.mapping.UserMapping;
import com.record.backend.pojo.Problem;
import com.record.backend.pojo.Solution;
import com.record.backend.pojo.User;
import com.record.backend.utils.Recommend;
import com.record.backend.utils.RecommendUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author: Along
 * @Date: Created in 21:58 2021/9/3
 **/
@SpringBootTest(classes = BackendApplication.class)
public class ProblemMappingText {

    // @Autowired
    // private ProblemMapping problemMapping;
    //
    // @Test
    // void selectAllProblem(){
    //     List<Problem> list = problemMapping.selectAllProblem();
    //     for(Object o : list){
    //         System.out.println(o);
    //     }
    // }
    @Autowired
    private UserMapping userMapping;
    @Autowired
    private RecordMapping recordMapping;
    @Test
    void test() {
        System.out.println(recordMapping.selectAllRecordUser(1).toString());
    }
    //
    // @Autowired
    // private SolutionMapping solutionMapping;
    //
    // @Test
    // void useIdSolution() {
    //     List<Solution> allList = solutionMapping.selectByUserIdAndScore(1,0);
    //     List<Solution> list = solutionMapping.selectByUserIdAndScore(1,100);
    //     for(Object o : list) {
    //         System.out.println(o);
    //     }
    //     double a = ((double)list.size()/(double)allList.size())*100;
    //     System.out.println("通过率为"+a+"%");
    //     System.out.println("一共有"+list.size()+"次成功提交");
    //     System.out.println("一共有"+allList.size()+"次提交");
    // }
    //
    // @Test
    // void test2() {
    //     long startTime = System.currentTimeMillis();    //获取开始时间
    //
    //     //输入用户总量
    //     List<User> users = userMapping.getAllSolutionAsUsers();
    //     Collections.sort(users);
    //     System.out.println("------------------------------------");
    //     System.out.println(users);
    //     System.out.println("------------------------------------");
    //     Recommend recommend = new Recommend();
    //     List<Solution> recommendationMovies = recommend.recommend(1, users);
    //     System.out.println("-----------------------");
    //     System.out.println("推荐结果如下：");
    //     for (Solution solution : recommendationMovies) {
    //         System.out.println("题目："+solution.getProblem_id()+" ,得分："+solution.score);
    //     }
    //     long endTime = System.currentTimeMillis();    //获取结束时间
    //     System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间
    // }

    @Test
    void test3() {
        RecommendUtils recommendUtils = new RecommendUtils();
        recommendUtils.scheduledTask();
    }
}
