<template>
  <el-row class="row-bg" justify="space-between">
    <el-col :span="6"><h2>后台界面</h2></el-col>
    <el-col :span="4" style="margin: 15px; text-align: right;">
      <el-button type="danger" @click="quitLogin">退出登录</el-button>
    </el-col>
  </el-row>
  <el-row class="tac">
    <el-col :span="6">
      <el-menu
          :default-active=activeIndex
          class="el-icon-s-custom"
          @open="handleOpen"
          @close="handleClose"
          style="width: 90%;"
      >
        <el-submenu index="1">
          <template #title>
            <i class="el-icon-s-custom"></i>
            <span>人物志页面</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="1-1" @click="setActiveIndex('1-1')">
              <i class="el-icon-user"></i>
              <span>人物志相关</span>
            </el-menu-item>
            <el-menu-item index="1-2" @click="setActiveIndex('1-2')">
              <i class="el-icon-collection"></i>
              <span>贡献资助记录</span>
            </el-menu-item>
            <el-menu-item index="1-3" @click="setActiveIndex('1-3')">
              <i class="el-icon-trophy"></i>
              <span>成果、已获得的奖项</span>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-submenu index="2">
          <template #title>
            <i class="el-icon-video-camera-solid"></i>
            <span>视频课程</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="2-1" @click="setActiveIndex('2-1')">
              <i class="el-icon-s-management"></i>
              <span>吴老师课程</span>
            </el-menu-item>
            <el-menu-item index="2-2" @click="setActiveIndex('2-2')">
              <i class="el-icon-s-opportunity"></i>
              <span>集训队每日一题</span>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>
        <el-menu-item index="3" @click="setActiveIndex('3')">
          <i class="el-icon-s-order"></i>
          <span>训练题集</span>
        </el-menu-item>
        <el-menu-item index="4" @click="setActiveIndex('4')">
          <i class="el-icon-upload"></i>
          <span>ICPC私云</span>
        </el-menu-item>
      </el-menu>
    </el-col>
    <el-col :span="18">
      <div v-if="activeIndex === '1-1'">
        <set-characters></set-characters>
      </div>
      <div v-if="activeIndex === '1-2'">
        <set-contribution></set-contribution>
      </div>
      <div v-if="activeIndex === '1-3'">
        <set-contri-prize></set-contri-prize>
      </div>
      <div v-if="activeIndex === '2-1'">
        <set-course></set-course>
      </div>
      <div v-if="activeIndex === '2-2'">
        <set-course-everyday></set-course-everyday>
      </div>
      <div v-if="activeIndex === '3'">
        <set-v-judge></set-v-judge>
      </div>
      <div v-if="activeIndex === '4'">
        <set-cloud></set-cloud>
      </div>
    </el-col>
  </el-row>
</template>

<script>
import { ElMessage } from 'element-plus';
import Cookies from 'js-cookie';
import axios from 'axios';
// eslint-disable-next-line import/extensions
import SetCharacters from '../components/adminUtils/setCharacters';
// eslint-disable-next-line import/extensions
import SetContribution from '../components/adminUtils/setContribution';
// eslint-disable-next-line import/extensions
import SetContriPrize from '../components/adminUtils/setContriPrize';
// eslint-disable-next-line import/extensions
import SetCourse from '../components/adminUtils/setCourseWu';
// eslint-disable-next-line import/extensions
import SetCourseEveryday from '../components/adminUtils/setCourseEveryday';
// eslint-disable-next-line import/extensions
import SetCloud from '../components/adminUtils/setCloud';
// eslint-disable-next-line import/extensions
import SetVJudge from '../components/adminUtils/setVJudge';

export default {
  name: 'AdminPages',
  // eslint-disable-next-line max-len
  components: {
    // eslint-disable-next-line max-len
    SetVJudge, SetCloud, SetCourseEveryday, SetCourse, SetContriPrize, SetContribution, SetCharacters,
  },
  data() {
    return {
      // 默认显示页面
      activeIndex: '1-1',
    };
  },
  methods: {
    handleOpen(key, keyPath) {
      // eslint-disable-next-line no-console
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      // eslint-disable-next-line no-console
      console.log(key, keyPath);
    },

    quitLogin() {
      Cookies.remove('token');
      ElMessage.success('成功退出登录');
      this.$router.replace('/');
    },

    setActiveIndex(index) {
      this.activeIndex = index;
    },
  },
  mounted() {
    const token = Cookies.get('token');
    // console.log("menu+token==>"+token)
    // 调用验证token的程序
    if (token) {
      const params = {
        token,
      };
      axios.post('/api/user/verifyToken', params)
        .then((response) => {
          if (response.data.status === 200) {
            ElMessage.success(response.data.msg);
          } else {
            ElMessage.error(response.data.msg);
            Cookies.remove('token');
            this.$router.push('/login');
          }
        });
    } else {
      this.$router.push('/login');
    }
  },
};
</script>

<style scoped>

</style>
