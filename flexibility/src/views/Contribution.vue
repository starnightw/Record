<template>
  <div>
    <div class="breadcrumb_box">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">主页</el-breadcrumb-item>
        <el-breadcrumb-item>{{nameTitle}}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <div style="margin: 50px; width: 80%; padding-left: 10%">
      <div v-html="recordContent"></div>
    </div>
  </div>
</template>

<script>
import marked from 'marked';
import axios from 'axios';

export default {
  name: 'Contribution',
  data() {
    return {
      nameTitle: '',
      recordContent: '', // 内容
    };
  },
  mounted() {
    this.nameTitle = this.$route.query.pageClass;
    // console.log(this.$route.query.nameTitle)
    // 获取数据渲染页面
    axios.get('/api/contri/getContribution')
      .then((response) => {
        // 响应成功回调
        if (this.$route.query.pageClass === '贡献资助记录') {
          this.recordContent = marked(response.data[0].synopsisContent);
        } else if (this.$route.query.pageClass === '成果已获得的奖项') {
          this.recordContent = marked(response.data[1].synopsisContent);
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
.breadcrumb_box{
  height: 30px;
  padding-left: 30px;
  padding-top: 20px;
  background-color: #f5f5f5;
}
</style>
