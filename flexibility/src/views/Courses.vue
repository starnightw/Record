<template>
  <!--  课程-->
  <div>   <!--  @tab-click="handleClick"-->
    <el-tabs v-model="courseClass" @tab-click="handleClick" v-bind:style="{minHeight: Height+'px'}">
      <el-tab-pane label="吴老师课程" name="courseWu">
        <el-row>
          <el-col :span="6">
            <el-menu :default-active=index style="border: 1px solid #eaeaea;">
              <!--              <el-scrollbar style="height:100%">-->
              <el-menu-item v-for="(item, index) in coursesWuList"
                            :index='index' @click='changePage1(index)' :key="index">
                {{ item.name }}
              </el-menu-item>
              <!--              </el-scrollbar>-->
            </el-menu>
          </el-col>
          <el-col :span="18" style="background-color: #f2f2f5">
            <el-container class='outter-container'>
              <el-container class='frame-container' v-if="this.ifCourseWuVideo === 'true'">
                <h4>{{ this.nowTimeShowTitle1 }}</h4>
                <iframe
                  class='frame'
                  :src='this.nowTimeShowVideo1'
                  border='0'
                  framespacing='0'
                  allowfullscreen='true'
                >
                </iframe>
              </el-container>
              <h2 v-if="this.ifCourseWuVideo === 'false'">视频还未上线</h2>
            </el-container>
          </el-col>
        </el-row>
      </el-tab-pane>
      <el-tab-pane label="集训队每日一题" name="everyDayOne">
        <el-row>
          <el-col :span="6">
            <!--            <el-scrollbar style="height:100%">-->
            <el-menu :default-active=index style="border: 1px solid #eaeaea;">
              <el-menu-item v-for="(item, index) in coursesEveryDayList"
                            :index='index' @click='changePage2(index)' :key="index">
                {{ item.name }}
              </el-menu-item>
            </el-menu>
            <!--            </el-scrollbar>-->
          </el-col>
          <el-col :span="18" style="background-color: #f2f2f5">
            <el-container class='outter-container'>
              <el-container class='frame-container' v-if="this.ifCourseEveryVideo === 'true'">
                <el-row justify="end">
                  <el-col :span="15">
                    <h4>{{ this.nowTimeShowTitle2 }}</h4>
                  </el-col>
                  <el-col :span='3'>
                    <el-button type="text" @click="open()">更多>></el-button>
                  </el-col>
                </el-row>
                <iframe
                  class='frame'
                  :src='this.nowTimeShowVideo2'
                  border='0'
                  framespacing='0'
                  allowfullscreen='true'
                >
                </iframe>
                <div style="text-align: left; width: 90%; padding-left: 5%" v-html="blog"></div>
              </el-container>
              <h2 v-if="this.ifCourseEveryVideo === 'false'">
                <div style="padding: 15px; margin: 0 auto; text-align: center">
                  <h4>{{ this.nowTimeShowTitle2 }}</h4>
                  <div v-html="blog"></div>
                </div>
              </h2>
            </el-container>
          </el-col>
        </el-row>
      </el-tab-pane>
    </el-tabs>
    <el-container class='down vertial-hide'>
      <el-button-group>
        <el-button class='down-button' @click='downVideo'>
          下载本节课视频
        </el-button>
        <el-button type='primary' class='down-button' @click='downPdf'>
          下载本节课配套文档
        </el-button>
      </el-button-group>
    </el-container>
  </div>
</template>

<script>
import marked from 'marked';
import axios from 'axios';

export default {
  name: 'Courses',
  data() {
    return {
      current: 0,
      Height: 0,
      courseClass: 'courseWu',
      index: 0,
      // 当前吴老师课程的界面内容
      nowTimeShowIndex1: 0,
      nowTimeShowTitle1: '',
      nowTimeShowVideo1: '',
      coursesWuList: [],
      ifCourseWuVideo: 'true',

      // 当前每日一题界面的内容
      nowTimeShowIndex2: 0,
      nowTimeShowTitle2: '',
      nowTimeShowVideo2: '',
      coursesEveryDayList: [],
      ifCourseEveryVideo: 'true',
      title: '',
      blog: '',
    };
  },
  mounted() {
    // 动态设置内容高度 让footer始终居底   header+footer的高度是240
    this.Height = document.documentElement.clientHeight - 240;
    // 监听浏览器窗口变化
    window.onresize = () => {
      this.Height = document.documentElement.clientHeight - 240;
    };
    console.log('4');
    this.getWuList();
    console.log('5');
    this.getEveryDayList();
  },
  watch: {
    // 此处监听courseClass变量，当期有变化时执行
    courseClass() {
      this.handleClick();
    },
    index() {
      this.$nextTick(function () {
        console.log('3');
        this.hadChange();
      });
    },
    immediate: true, // watch侦听操作内的函数会立刻被执行
  },
  methods: {

    handleClick() {
      // eslint-disable-next-line max-len
      // this.courseClass === 'courseWu' ? this.courseClass = 'courseWu': this.courseClass = 'everyDayOne'
      // eslint-disable-next-line no-console
      console.log(this.courseClass);
      this.index = 0;
      this.$router.push({
        query: {
          ...this.$route.query,
          courseClass: this.courseClass,
          index: this.index,
        },
      });
      // this.hadChange()
    },

    async hadChange() {
      this.courseClass = this.$route.query.courseClass;
      this.index = this.$route.query.index;
      // eslint-disable-next-line no-console
      console.log(`${this.courseClass}====2${this.index}`);
      if (!this.courseClass || !this.index) {
        this.courseClass = 'courseWu';
        this.index = 0;
        await this.$router.push({
          query: {
            ...this.$route.query,
            courseClass: this.courseClass,
            index: this.index,
          },
        });
      } else {
        await this.$router.push({
          query: {
            ...this.$route.query,
            courseClass: this.courseClass,
            index: this.index,
          },
        });
        if (this.courseClass === 'courseWu') {
          this.nowTimeShowIndex1 = this.index;
        } else {
          this.nowTimeShowIndex2 = this.index;
        }
      }
      if (this.courseClass === 'courseWu') {
        // eslint-disable-next-line no-console
        console.log('运行 Wu');
        await this.changePage1(this.index);
        // eslint-disable-next-line no-console
        console.log('运行 Wu2');
        // console.log(this.courseClass+'====WU'+this.index)
      } else if (this.courseClass === 'everyDayOne') {
        // eslint-disable-next-line no-console
        console.log('运行 oen');
        await this.changePage2(this.index);
        // eslint-disable-next-line no-console
        console.log('运行 one2');
        // console.log(this.courseClass+'====ONE'+this.index)
      }
    },

    async getWuList() {
      // eslint-disable-next-line no-console
      console.log('7');
      // 获取数据渲染页面  吴老师课程
      await axios.get('/api/course/getCourseWusList')
        .then((response) => {
          // 响应成功回调
          this.coursesWuList = response.data;
          // eslint-disable-next-line no-console
          console.log(this.coursesWuList);
          this.nowTimeShowTitle1 = response.data[0].title;
          this.nowTimeShowVideo1 = response.data[0].videoLink;
          if (response.data[0].videoLink === '') {
            this.ifCourseWuVideo = 'false';
          }
        })
        .catch((reject) => {
          // eslint-disable-next-line no-console
          console.log(reject);
        });
      // eslint-disable-next-line no-console
      console.log('1');
      await this.hadChange();
    },

    // 获取数据渲染页面  每日一题
    async getEveryDayList() {
      // eslint-disable-next-line no-console
      console.log('6');
      await axios.get('/api/courseday/getCourseEveryDayWusList')
        .then((response) => {
          // 响应成功回调
          this.coursesEveryDayList = response.data;
          // eslint-disable-next-line no-console
          console.log(this.coursesEveryDayList);
          this.nowTimeShowTitle2 = response.data[0].title;
          this.nowTimeShowVideo2 = response.data[0].videoLink;
          if (response.data[0].videoLink === '') {
            this.ifCourseEveryVideo = 'false';
          }
        })
        .catch((reject) => {
          // eslint-disable-next-line no-console
          console.log(reject);
        });
      // eslint-disable-next-line no-console
      console.log('2');
      await this.hadChange();
    },

    changePage1(index) {
      this.index = index;
      // eslint-disable-next-line no-console
      console.log(`运行 Wu${index}`);
      this.nowTimeShowIndex1 = index;
      this.$router.push({
        query: {
          ...this.$route.query,
          courseClass: this.courseClass,
          index,
        },
      });
      // eslint-disable-next-line no-console
      console.log(this.coursesWuList);
      this.nowTimeShowTitle1 = this.coursesWuList[this.nowTimeShowIndex1].title;
      // eslint-disable-next-line no-console
      console.log(this.nowTimeShowTitle1);
      this.nowTimeShowVideo1 = this.coursesWuList[this.nowTimeShowIndex1].videoLink;
      if (this.coursesWuList[this.nowTimeShowIndex1].videoLink === '') {
        this.ifCourseWuVideo = 'false';
      } else {
        this.ifCourseWuVideo = 'true';
      }
    },

    changePage2(index) {
      this.index = index;
      this.nowTimeShowIndex2 = index;
      // eslint-disable-next-line no-console
      console.log(`运行 One${index}`);
      this.$router.push({
        query: {
          ...this.$route.query,
          courseClass: this.courseClass,
          index,
        },
      });
      // eslint-disable-next-line no-console
      console.log(this.coursesEveryDayList);
      this.nowTimeShowTitle2 = this.coursesEveryDayList[this.nowTimeShowIndex2].title;
      // eslint-disable-next-line no-console
      console.log(this.nowTimeShowIndex2);
      this.nowTimeShowVideo2 = this.coursesEveryDayList[this.nowTimeShowIndex2].videoLink;
      if (this.coursesEveryDayList[this.nowTimeShowIndex2].videoLink === '') {
        this.ifCourseEveryVideo = 'false';
      } else {
        this.ifCourseEveryVideo = 'true';
      }
      this.title = this.coursesEveryDayList[this.nowTimeShowIndex2].title;
      this.blog = marked(this.coursesEveryDayList[this.nowTimeShowIndex2].recordContent);
    },

    open() {
      // eslint-disable-next-line max-len
      this.$alert(marked(this.coursesEveryDayList[this.nowTimeShowIndex2].recordContent), this.coursesEveryDayList[this.nowTimeShowIndex2].title, {
        confirmButtonText: '确定',
        dangerouslyUseHTMLString: true,
      });
    },

    // 下载实现方法
    downPdf() {
      if (this.activeName === 'first') {
        window.location = `https://icpc.qlu.edu.cn/course/assets/courseLink${this.coursesWuList[this.nowTimeShowIndex1].downPdfLink}`;
      } else {
        window.location = `https://icpc.qlu.edu.cn/course/assets/courseLink${this.coursesEveryDayList[this.nowTimeShowIndex2].downPdfLink}`;
      }
    },
    downVideo() {
      if (this.activeName === 'first') {
        window.location = `https://icpc.qlu.edu.cn/course/assets/courseLink${this.coursesWuList[this.nowTimeShowIndex1].downVideoLink}`;
      } else {
        window.location = `https://icpc.qlu.edu.cn/course/assets/courseLink${this.coursesEveryDayList[this.nowTimeShowIndex2].downVideoLink}`;
      }
    },
  },
};
</script>

<style scoped>
.el-container {
  color: rgb(34, 34, 34);
  display: flex;
  min-width: 360px;
}

.el-menu-item {
  overflow-x: auto;
  overflow-y: hidden;
  display: flex;
  flex-direction: row;
  column-count: 1;
}

.el-menu-item::-webkit-scrollbar {
  display: none;
}

.el-aside {
  flex: 1;
  transition: 500ms;
}

/* .el-aside:hover {
  flex: 2;
} */

.container {
  width: 90%;
  padding-left: 10%;
}

.outter-container {
  flex: 4 !important;
  text-align: center;
}

.frame-container {
  padding: 20px;
  flex-direction: column !important;
  background: rgb(242, 242, 245);
}

h4 {
  margin-block-start: 0.5em;
  margin-block-end: 0.8em;
  color: rgb(21, 41, 112);
  font-weight: 600;
}

.frame {
  /*padding-left: 10%;*/
  height: 500px;
  width: 100%;
  max-width: 800px;
  transition: 200ms;
}

.down {
  position: absolute;
  right: 0;
  /*bottom: 20px;*/
  padding-top: 35px;
}

.down-button {
  width: 160px;
}

@media (max-height: 560px) {
  .vertial-hide {
    display: none;
  }
}

@media (min-width: 1150px) {
  .frame {
    height: 600px;
    max-width: 900px;
  }
}

@media (max-width: 1149px) {
  .frame {
    height: 450px;
    max-width: 700px;
  }
}

@media (max-width: 640px) {
  .el-container {
    flex-direction: column-reverse !important;
  }

  .el-header {
    text-align: center;
    line-height: 40px;
    height: 40px !important;
    font-size: 1rem;
  }

  .outter-container {
    padding: 0;
  }

  .frame-container {
    padding: 0;
  }

  .frame-container h4 {
    margin-left: 10px;
  }

  .frame {
    height: 250px;
  }

  .el-aside {
    width: 100% !important;
  }
}
</style>
