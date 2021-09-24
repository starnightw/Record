<template>
  <div class="common-layout">
    <el-container>
<!--      <el-header>-->
<!--        <Header></Header>-->
<!--      </el-header>-->
      <Menu></Menu>
      <el-main  v-bind:style="{minHeight: Height+'px'}">
        <div class="container">
          <div class="chq_body">
            <router-view/>
          </div>
        </div>
      </el-main>
      <el-footer>
        <Footer></Footer>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
// eslint-disable-next-line import/extensions
import Menu from '@/components/Menu';
// eslint-disable-next-line import/extensions
// import Header from '@/components/Header';
// eslint-disable-next-line import/extensions
import Footer from '@/components/Footer';

export default {
  components: {
    Menu,
    // Header,
    Footer,
  },
  data() {
    return {
      Height: 0,
    };
  },
  mounted() {
    // 动态设置内容高度 让footer始终居底   header+footer的高度是240
    this.Height = document.documentElement.clientHeight - 160;
    // 监听浏览器窗口变化
    window.onresize = () => { this.Height = document.documentElement.clientHeight - 160; };
  },
};
</script>

<style lang="less">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  //text-align: center;
  color: #2c3e50;
}
// 去除百边
body{
  margin:0;
  padding:0;
}

// 修改组件默认样式
.common-layout {
  background-color: #e7e7e7;
  margin:0 auto
}

.common-layout .el-header {
  padding: 0;
  height: 100px;
}

.common-layout .el-main {
  padding-bottom: 0;
}

.common-layout .el-footer {
  padding: 0;
}

// 根据主页面大小进行变化
@media (min-width: 768px) {
  .container {
    width: 750px;
  }
}
@media (min-width: 992px) {
  .container {
    width: 970px;
  }
}
@media (min-width: 1200px) {
  .container {
    width: 1170px;
  }
  .navbar-brand {
    margin-left: 40px;
    margin-right: 70px;
  }
}

.container {
  padding-right: 15px;
  padding-left: 15px;
  margin-right: auto;
  margin-left: auto;
}

.el-header{
  background-image: linear-gradient(to right, cyan , blue);
  margin-bottom: 0;
  border: medium none;
}

.el-main .container{
  padding: 25px 15px 20px;
  background-color: #FFF;
}

.chq_body {
  padding: 0 30px;
  background-color: #FFF;
}

</style>
