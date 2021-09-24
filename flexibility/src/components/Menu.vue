<template>
  <div class="menu" style="background-color: #f8f8f8;">
    <div class="container" style="text-align:center">
      <el-menu :default-active="activeIndex"
               class="el-menu-demo" :mode=menuMode background-color="#f8f8f8">
        <a class="navbar-brand" href="/" style="color: #13b4ff">QLU 助手</a>
        <el-menu-item :index=item.id
                      v-for="(item, index) in pages" :key="index" @click='pageShow(item.id)'>
          <i :class=item.icon></i>{{item.name}}
        </el-menu-item>

<!--        下边的是管理员管理界面-->
        <el-menu-item v-if="adminIndex === '9'" index=9  key=9 @click='adminShow(9)'>
          <i class="el-icon-user-solid"></i>{{adminPage[0].name}}
        </el-menu-item>
        <el-sub-menu v-if="adminIndex === '10'" index=9 key=10>
          <template #title><i class="el-icon-s-tools"></i>{{adminPage[1].name}}</template>
          <el-menu-item index="9-1">item one</el-menu-item>
          <el-menu-item index="9-2">退出登录</el-menu-item>
        </el-sub-menu>
      </el-menu>
    </div>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus';
import Cookies from 'js-cookie';
import axios from 'axios';

export default {
  name: 'Menu',
  data() {
    return {
      // 默认显示页面
      activeIndex: '3',
      menuMode: 'horizontal',
      adminIndex: '9', // 9 就是还没登陆    10 就是登陆成功
      // 导航使用元素数据   人物志：character
      pages: [
        {
          id: 1,
          icon: 'el-icon-star-on',
          name: '赛事列表',
          path: '/event',
        }, {
          id: 2,
          icon: 'el-icon-s-custom',
          name: '人物志',
          path: '/Characters',
        }, {
          id: 3,
          icon: 'el-icon-s-marketing',
          name: '个人分析',
          path: '/',
        }, {
          id: 4,
          icon: 'el-icon-s-data',
          name: 'DomJudge',
          path: '/domjudge',
        }, {
          id: 5,
          icon: 'el-icon-video-camera-solid',
          name: '视频课程',
          path: '/course',
        }, {
          id: 6,
          icon: 'el-icon-s-order',
          name: '训练题集',
          path: '/vJudge',
        }, {
          id: 7,
          icon: 'el-icon-upload',
          name: 'ICPC私云',
          path: '/cloud',
        }, {
          id: 8,
          icon: 'el-icon-caret-right',
          name: '返回QLU OJ',
          path: 'http://icpc.qlu.edu.cn/#/',
        },
        // {
        //   id: 8,
        //   icon: 'el-icon-info',
        //   name: '关于',
        //   path: '/about'
        // }
      ],
      adminPage: [
        {
          id: 9,
          icon: 'el-icon-user-solid',
          name: '管理员登录',
          path: '/login',
        },
        {
          id: 10,
          icon: 'el-icon-s-tools',
          name: '后台管理',
          path: '/administrator',
        },
      ],
    };
  },
  // 路由变化
  watch: {
    $route: 'getPath',
  },
  methods: {
    // 监听路由变化
    getPath() {
      // console.log(this.$route.path);
      // eslint-disable-next-line no-restricted-syntax
      for (const i in this.pages) {
        if ((this.$route.path) === this.pages[i].path) {
          this.activeIndex = this.pages[i].id;
        }
      }
    },
    // 切换导航页面功能实现
    pageShow(index) {
      this.activeIndex = index;
      // eslint-disable-next-line no-console
      console.log(this.activeIndex);
      if (index === 4) {
        window.open('http://icpc.qlu.edu.cn/domjudge', '_self');
      } else if (index === 8) {
        window.open('http://icpc.qlu.edu.cn/#/', '_self');
      } else if (this.$router.currentRoute.value !== this.pages[index - 1].path) {
        this.$router.push(this.pages[index - 1].path);
      }
    },
    // admin 登录实现
    adminShow(index) {
      this.activeIndex = index;
      if (index === 9) {
        this.$router.push(this.adminPage[index - 9].path);
      } else if (index === 10) {
        this.$router.push(this.adminPage[index - 9].path);
      }
    },
  },
  mounted() {
    // let token = window.localStorage.getItem('token')
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
            this.adminIndex = '9';
          } else {
            ElMessage.error(response.data.msg);
            Cookies.remove('token');
            this.$router.push('/login');
            this.adminIndex = '8';
          }
        });
    } else {
      this.adminIndex = '8';
    }
  },
};

</script>

<style scoped>
  .menu {
    background-color: #FFFFFF;
  }

  .navbar-brand {
    /*color: #777;*/
    float: left;
    height: 30px;
    padding: 25px 25px;
    font-size: 20px;
    line-height: 30px;
  }

  .menu .el-sub-menu{
    padding-top: 10px;
  }

  .menu .el-menu-item{
    padding: 10px 10px 0;
  }

  .el-menu{
    text-align: justify;
  }

  .el-menu-item [class^=el-icon-] {
    margin-right: 0;
    margin-left: 5px;

  }

  a{text-decoration: none;}
</style>
