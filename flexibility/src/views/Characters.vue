<template>
  <h2>人物志</h2>
  <el-row justify="space-between">
    <el-col :span="17">
      <div v-for="(item,indexClass) in charaClass" :key="indexClass">
        <hr/><div style="padding: 0 15px"><h3><i class="el-icon-user"/>{{item}}</h3></div>
        <el-row>
          <el-col :span="8" v-for="(item, index) in characterData[indexClass]" :key="index">
            <el-card class="box-card" style="text-align: center">
              <template #header>
                <div class="card-header">
                  <span>{{ item.nameTitle }}</span>
                  <router-link :to="{name:'Character',
                    query: {peopleClass: this.charaClass[indexClass],
                    nameTitle: item.nameTitle}}">
                    <el-button type="text">更多>></el-button>
                  </router-link>
                </div>
              </template>
              <el-image class="img" :src="this.urlPath + item.imgUrl" lazy>-->
                <template #placeholder>
                  <div class="image-slot">
                    加载中<span class="dot">...</span>
                  </div>
                </template>
              </el-image>
            </el-card>
          </el-col>
        </el-row>
      </div>
    </el-col>
    <el-col :span="7">
      <div style="padding: 15px; background-color: #f5f5f5" >
        <h2>
          <i class="el-icon-collection" />贡献资助记录
          <router-link :to="{name:'Contribution',query: {pageClass: '贡献资助记录'}}">
            <el-button type="text">详情>></el-button>
          </router-link>
        </h2>
        <hr/>
        <div v-html="recordContent"></div>
      </div>
      <div style="padding: 15px;" >
        <h2>
          <i class="el-icon-trophy" />成果 已获得的奖项
          <router-link :to="{name:'Contribution',query: {pageClass: '成果已获得的奖项'}}">
            <el-button type="text">详情>></el-button>
          </router-link>
        </h2>
        <hr/>
        <div v-html="achievements"></div>
      </div>
    </el-col>
  </el-row>

</template>

<script>
// import http from '../assets/js/http'
import marked from 'marked';
import axios from 'axios';
// import {ElMessage} from "element-plus";
// import VueMarkdown from 'vue-markdown'
export default {
  name: 'characters',
  data() {
    return {
      urlPath: 'https://icpc.qlu.edu.cn/course/assets/charactersImg/', // 图片保存的路径
      charaClass: [], // 类别分类
      characterData: [], // 人物志数据
      recordContent: '', // 答谢榜内容
      achievements: '', // 成果记录展示
    };
  },
  methods: {
    // open( indexClass, index ) {
    //   let title = this.characterData[indexClass][index].charaClass + ':'
    //   +this.characterData[indexClass][index].nameTitle
    //   let blog = marked(this.characterData[indexClass][index].recordContent)
    //   this.$alert(blog, title, {
    //     confirmButtonText: '确定',
    //     dangerouslyUseHTMLString: true
    //
    //   });
    // }
  },
  mounted() {
    let characterData = [];
    // 获取数据渲染页面
    axios.get('/api/chara/getCharactersList')
      .then((response) => {
        // 响应成功回调
        characterData = response.data;
        // console.log(characterData)
        // console.log(characterData[0].charaClass)
        // console.log('getCharactersList人物志'+this.characterData)
        this.charaClass.push(characterData[0].charaClass);
        // eslint-disable-next-line guard-for-in,no-restricted-syntax
        for (const i in characterData) {
          this.charaClass.push(characterData[i].charaClass);
        }
      }).then(() => {
      // 去重
      // eslint-disable-next-line no-plusplus
        for (let i = 0; i < this.charaClass.length; i++) {
          // eslint-disable-next-line no-plusplus
          for (let j = i + 1; j < this.charaClass.length; j++) {
            if (this.charaClass[i] === this.charaClass[j]) {
              this.charaClass.splice(j, 1);
              // eslint-disable-next-line no-plusplus
              j--;
            }
          }
        }
      }).then(() => {
      // eslint-disable-next-line guard-for-in,no-restricted-syntax
        for (const i in this.charaClass) {
        // console.log(this.charaClass[i])
        // 获取每一类别的数据
          axios.post('/api/chara/getCharactersForClassList', { charaClass: this.charaClass[i] })
            .then((response) => {
              // console.log(i)
              // eslint-disable-next-line radix
              this.characterData.splice(parseInt(i), 0, response.data);
            }).catch((reject) => {
            // eslint-disable-next-line no-console
              console.log(reject);
            });
        }
      // console.log('最终的'+this.characterData)
      });

    // axios.post('/api/course/addCourseWu')
    // .then((response) => {
    //   console.log('运行添加课程'+response)
    // });
    // axios.post('/api/courseday/addCourseEveryDay')
    //     .then((response) => {
    //       console.log('运行添加每日一题'+response)
    //     });
    // 获取数据渲染页面
    axios.get('/api/contri/getContribution')
      .then((response) => {
        // 响应成功回调
        this.recordContent = marked(response.data[0].recordContent);
        this.achievements = marked(response.data[1].recordContent);
      })
      .catch((reject) => {
        // eslint-disable-next-line no-console
        console.log(reject);
      });
  },
};
</script>

<style scoped>
.box-card{
  width: 230px;
  /*height: 200px;*/
  margin-bottom: 30px;
}

.el-card {
  /*min-width: 200px;*/
  margin-right: 20px;
  transition: all .3s;
}
.el-card:hover{
  margin-top: -5px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

i {
  padding-right: 5px;
}

</style>
