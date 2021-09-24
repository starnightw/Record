<template>
  <div style="padding: 15px; width: 50%; margin: 0 auto;" >
    <div v-html="recordContent"></div>
  </div>
</template>

<script>
import marked from 'marked';
import axios from 'axios';

export default {
  name: 'Cloud',
  data() {
    return {
      recordContent: '', // 云盘内容
    };
  },
  mounted() {
    // 获取数据渲染页面
    axios.get('/api/cloud/getCloud')
      .then((response) => {
        // 响应成功回调
        this.recordContent = marked(response.data[0].recordContent);
        // console.log(response.data)
        // ElMessage.info('贡献榜'+ response)
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
