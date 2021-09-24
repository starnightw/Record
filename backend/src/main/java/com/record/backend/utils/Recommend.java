package com.record.backend.utils;

import com.record.backend.pojo.Solution;
import com.record.backend.pojo.User;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @Author: Along
 * @Date: Created in 10:19 2021/9/21
 **/
public class Recommend {

    /**
     * 在给定userId的情况下，计算其他用户和它的距离并排序
     * @param id
     * @return
     */
    private Map<Double, Integer> computeNearestNeighbor(Integer id, List<User> users) {
        System.out.println("当前的userid为"+id);
        Map<Double, Integer> distances = new TreeMap<>();

        int mid = binarySearch(id,0,users.size()-1,users);
        // System.out.println(users.size());
        // System.out.println(mid);
        User u1 = users.get(mid);
        System.out.println("当前的user为"+u1);

        for (int i = 0; i < users.size(); i++) {
            User u2 = users.get(i);
            System.out.println("u2----"+u2);
            if (!u2.getId().equals(id)) {
                double distance = pearson_dis(u2.solutionList, u1.solutionList);
                distances.put(distance, u2.getId());
                System.out.println("distance----"+distance);
            }
        }
        System.out.println("该用户:"+ id+"与其他用户的皮尔森相关系数 -> " + distances);
        return distances;
    }


    /**
     * 计算2个打分序列间的pearson距离
     * 选择公式四进行计算
     * @param solutionList1
     * @param solutionList2
     * @return
     */
    private double pearson_dis(List<Solution> solutionList1, List<Solution> solutionList2) {
        int n=solutionList1.size()-2;
        List<Integer> rating1ScoreCollect = solutionList1.stream().map(A -> A.score).collect(Collectors.toList());
        List<Integer> rating2ScoreCollect = solutionList2.stream().map(A -> A.score).collect(Collectors.toList());

        double Ex= rating1ScoreCollect.stream().mapToDouble(x->x).sum();
        double Ey= rating2ScoreCollect.stream().mapToDouble(y->y).sum();
        double Ex2=rating1ScoreCollect.stream().mapToDouble(x->Math.pow(x,2)).sum();
        double Ey2=rating2ScoreCollect.stream().mapToDouble(y->Math.pow(y,2)).sum();
        double Exy= IntStream.range(0,n).mapToDouble(i->rating1ScoreCollect.get(i)*rating2ScoreCollect.get(i)).sum();
        double numerator=Exy-Ex*Ey/n;
        double denominator=Math.sqrt((Ex2-Math.pow(Ex,2)/n)*(Ey2-Math.pow(Ey,2)/n));
        if (denominator==0) return 0.0;
        // System.out.println(numerator/denominator);
        return numerator/denominator;
    }


    public List<Solution> recommend(Integer id, List<User> users) {
        // System.out.println("当前的userid为"+id);
        int mid = 0;
        //找到最近邻
        Map<Double, Integer> distances = computeNearestNeighbor(id, users);
        Integer nearest = distances.values().iterator().next();
        System.out.println("最近邻id -> " + nearest);

        //找到最近邻看过，但是我们没看过的电影，计算推荐
        User neighborRatings;
        mid = binarySearch(nearest,0,users.size()-1,users);
        neighborRatings = users.get(mid);
        System.out.println("最近邻---"+ neighborRatings.getId() +"---做过的题目id -> " + neighborRatings.solutionList);

        User userRatings;
        mid = binarySearch(id,0,users.size()-1,users);
        userRatings = users.get(mid);
        System.out.println("用户做过的题目id -> " + userRatings.solutionList);

        //根据自己和邻居的电影计算推荐的电影
        List<Solution> recommendationMovies = new ArrayList<>();
        for (Solution solution : neighborRatings.solutionList) {
            if (userRatings.find(solution.getProblem_id()) == null) {
                recommendationMovies.add(solution);
            }
        }
        Collections.sort(recommendationMovies);
        return recommendationMovies;
    }

    // 二分查找相应的 user
    public int binarySearch(Integer id, int left, int right, List<User> users ) {
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = users.get(mid).getId();

        if (id > midVal) { // 向 右递归
            return binarySearch(id, mid + 1, right, users);
        } else if (id < midVal) { // 向左递归
            return binarySearch(id, left, mid - 1, users);
        } else {
            return mid;
        }
    }

}
