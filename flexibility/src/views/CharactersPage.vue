<template>
    <div>
      <div class="breadcrumb_box">
        <el-breadcrumb separator-class="el-icon-arrow-right">
          <el-breadcrumb-item :to="{ path: '/' }">人物志</el-breadcrumb-item>
          <el-breadcrumb-item>{{peopleClass}}</el-breadcrumb-item>
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
  name: 'CharactersPage',
  data() {
    return {
      peopleClass: '',
      nameTitle: '',
      recordContent: '', // 人物志内容
    };
  },
  mounted() {
    this.peopleClass = this.$route.query.peopleClass;
    this.nameTitle = this.$route.query.nameTitle;
    // console.log(this.$route.query.nameTitle)
    axios.post('/api/chara/getCharacterByNameTitle', { nameTitle: this.nameTitle })
      .then((response) => {
        // 响应成功回调
        this.recordContent = marked(response.data[0].recordContent);
      })
      .catch((reject) => {
        // console.log(reject)
        this.recordContent = reject;
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
