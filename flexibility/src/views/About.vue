<template>
  <div class="about" id="main">
    <h1>This is an about page</h1>
    <el-row>
      <el-button>默认按钮</el-button>
      <el-button type="primary">主要按钮</el-button>
      <el-button type="success">成功按钮</el-button>
      <el-button type="info">信息按钮</el-button>
      <el-button type="warning">警告按钮</el-button>
      <el-button type="danger">危险按钮</el-button>
    </el-row>
    <div id="myChart" :style="{ width: '600px', height: '300px' }"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts'; // 引入echarts 图表库

export default {
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

<style lang="less" scoped>
#main {
  height: 400px;
  width: 100%;
}
</style>
