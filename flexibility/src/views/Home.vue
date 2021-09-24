<template>
  <div class="home">
    <el-row :gutter="40">
      <el-col :span="14">
        <HelloWorld msg="Welcome to Your Vue.js App"/>
        <div id="myChart" :style="{ width: '600px', height: '300px' }"></div>
      </el-col>
      <el-col :span="8">
        <Login title="请先绑定QLUOJ账号" btnName="绑定"></Login>
      </el-col>
    </el-row>
  </div>
</template>

<script>
// @ is an alias to /src
import * as echarts from 'echarts'; // 引入echarts 图表库
import HelloWorld from '@/components/HelloWorld.vue';
// eslint-disable-next-line import/extensions
import Login from '@/components/utils/Login';

export default {
  name: 'Home',
  components: {
    Login,
    HelloWorld,
  },
  mounted() {
    // 基于准备好的dom，初始化echarts实例
    const myChart = echarts.init(document.getElementById('myChart'));
    // 绘制图表
    myChart.setOption({
      title: {
        text: 'ECharts 入门示例',
      },
      tooltip: {},
      xAxis: {
        data: ['衬衫', '羊毛衫', '雪纺衫', '裤子', '高跟鞋', '袜子'],
      },
      yAxis: {},
      series: [
        {
          name: '销量',
          type: 'bar',
          data: [4, 18, 34, 8, 8, 50],
        },
        {
          name: '总量',
          type: 'line',
          data: [5, 20, 36, 10, 10, 20],
        },
      ],
    });
  },
};
</script>
