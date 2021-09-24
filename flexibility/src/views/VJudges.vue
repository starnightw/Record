<template>
  <el-tabs tab-position="left"  v-bind:style="{minHeight: Height+'px'}">
    <el-tab-pane v-for="(item, index) in vJudgeData" :key="index" :label="item.name">
      <div style="width: 90%; padding-left: 5%" v-html="item.recordContent"></div>
    </el-tab-pane>
  </el-tabs>
</template>

<script>
import marked from 'marked';
import axios from 'axios';

export default {
  name: 'VJudges',
  data() {
    return {
      Height: 0,
      vJudgeData: [],
    };
  },
  methods: {

  },
  mounted() {
    // 动态设置内容高度 让footer始终居底   header+footer的高度是240
    this.Height = document.documentElement.clientHeight - 240;
    // 监听浏览器窗口变化
    window.onresize = () => { this.Height = document.documentElement.clientHeight - 240; };
    // 获取数据渲染页面
    axios.get('/api/vjudge/getVJudge')
      .then((response) => {
        // 响应成功回调
        this.vJudgeData = response.data;
        // console.log(response.data)
        // ElMessage.info('贡献榜'+ response)
      }).then(() => {
      // eslint-disable-next-line guard-for-in,no-restricted-syntax
        for (const i in this.vJudgeData) {
          this.vJudgeData[i].recordContent = marked(this.vJudgeData[i].recordContent);
        }
      })
      .catch((reject) => {
        // eslint-disable-next-line no-console
        console.log(reject);
      });
  },
};
</script>

<style scoped>
</style>
