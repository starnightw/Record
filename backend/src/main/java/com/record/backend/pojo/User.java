package com.record.backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Along
 * @Date: Created in 22:30 2021/9/1
 * 简单的User
 * id  username password nickname
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Comparable<User> {
    private Integer id;
    public String username;
    private String nickname;
    private String password;
    public List<Solution> solutionList = new ArrayList<>();

    public User(Integer id) {
        this.id = id;
    }

    public User set(List<Solution> solutionList) {
        this.solutionList = solutionList;
        return this;
    }

    public Solution find(Integer problemId) {
        for (Solution solution : solutionList) {
            if (solution.getProblem_id().equals(problemId)) {
                return solution;
            }
        }
        return null;
    }

    public User set(int problemId, int score) {
        this.solutionList.add(new Solution(problemId, score));
        return this;
    }

    @Override
    public int compareTo(User o) {
        return id < o.id ? -1 : 1;
    }

    // @Override
    // public String toString() {
    //     return "User{" +
    //             "username='" + username + '\'' +
    //             '}';
    // }
}
