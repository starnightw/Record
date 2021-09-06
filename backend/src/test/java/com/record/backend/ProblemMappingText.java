package com.record.backend;

import com.record.backend.mapping.ProblemMapping;
import com.record.backend.pojo.Problem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

/**
 * @Author: Along
 * @Date: Created in 21:58 2021/9/3
 **/
@SpringBootTest(classes = BackendApplication.class)
public class ProblemMappingText {

    @Autowired
    private ProblemMapping problemMapping;

    @Test
    void selectAllProblem(){
        List<Problem> list = problemMapping.selectAllProblem();
        for(Object o : list){
            System.out.println(o);
        }
    }
}
