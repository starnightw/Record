<template>
  <div class="login_container">
    <div class="breadcrumb_box">
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>管理员登录</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <LoginBox></LoginBox>
  </div>
</template>

<script>
import LoginBox from '@/components/utils/Login.vue';

export default {
  name: 'Login',
  components: {
    // eslint-disable-next-line vue/no-unused-components
    LoginBox,
  },
};
</script>

<style scoped>
.login_container {
  text-align: center
}

.breadcrumb_box {
  height: 30px;
  padding-left: 30px;
  padding-top: 20px;
  background-color: #f5f5f5;
}
</style>
