<template>
  <div class="login_box">
    <h1 style="width: 50%">{{ title }}</h1>
    <!-- 登录表单区域-->
    <el-form ref="loginFormRef" :model="loginForm"
             :rules="loginFormRules" label-width="0px" class="login_form">
      <!-- 用户名、账号-->
      <el-form-item prop="username">
        <el-input v-model="loginForm.username"
                  prefix-icon="el-icon-s-custom" placeholder="请输入用户名"></el-input>
      </el-form-item>
      <!-- 密码-->
      <el-form-item prop="password">
        <el-input v-model="loginForm.password" prefix-icon="el-icon-lock"
                  type="password" placeholder="请输入密码"></el-input>
      </el-form-item>
      <!-- 按钮区域-->
      <el-form-item>
        <el-button type="primary" @click="login" @keyup.enter="login">{{ btnName }}</el-button>
        <el-button type="info" @click="resetLoginForm">重置</el-button>
      </el-form-item>
      <h3>需拥有<a href="https://xn--678-im2i.ltd/">QLUOJ</a>用户身份,如果没有请移步注册</h3>
    </el-form>
  </div>
</template>

<script>
import { ElMessage } from 'element-plus';
import axios from 'axios';
import Cookies from 'js-cookie';

export default {
  name: 'Login',
  props: {
    title: String,
    btnName: String,
    url: String,
  },
  data() {
    return {
      // 这里是登录表单的数据绑定对象   username
      loginForm: {
        username: '',
        password: '',
      },
      // 这是表单的验证规则对象
      loginFormRules: {
        // 验证用户名是否合法
        username: [
          {
            required: true,
            message: '请输入用户名',
            trigger: 'blur',
          },
          {
            min: 3,
            max: 10,
            message: '用户名的长度应在 3 到 10 个字符',
            trigger: 'blur',
          },
        ],
        // 验证密码是否合法
        password: [
          {
            required: true,
            message: '请输入密码',
            trigger: 'blur',
          },
          {
            min: 6,
            max: 15,
            message: '密码的长度应在 6 到 15 个字符',
            trigger: 'blur',
          },
        ],
      },
    };
  },
  methods: {
    // 点击重置按钮，重置登录表单
    resetLoginForm() {
      // console.log(this);
      this.$refs.loginFormRef.resetFields();
    },
    // 点击登录 实现登录方法
    login() {
      //   ElMessage.info('正在实现，本页面暂仅考虑管理员登录')
      // }
      //   ElMessage.success('正在登陆请稍等')
      if (this.loginForm.username === '' && this.loginForm.password === '') {
        ElMessage.error('请输入用户名及密码');
      } else {
        // eslint-disable-next-line no-console
        console.log(this.loginForm.username);
        // eslint-disable-next-line no-console
        console.log(this.loginForm.password);
        const params = {
          username: this.loginForm.username,
          password: this.loginForm.password,
        };
        // 使用用户名密码登录
        axios.post(this.url, params)
          // eslint-disable-next-line consistent-return
          .then((response) => {
            // 响应成功回调
            if (response.data.status === 200) {
              ElMessage.success(response.data.msg);
              // window.localStorage.setItem('token', response.data.token)
              Cookies.set('token', response.data.token, { expires: 7 });
              window.location.pathname = '/administrator';
              return response;
            }
            ElMessage.error(response.data.msg);
          })
          .then((response) => {
            // eslint-disable-next-line no-console
            console.log(response);
          })
          .catch((reject) => {
            // eslint-disable-next-line no-console
            console.log(reject);
          });
      }
    },
  },
};
</script>

<style scoped>
/*.login_box {*/
/*  width: 350px;*/
/*  !*height: 250px;*!*/
/*  background-color: #fff;*/
/*  border-radius: 3px;*/
/*  margin: 10px auto;*/
/*}*/

/*.login_form {*/
/*  width: 80%;*/
/*  padding: 10px 10%;*/
/*}*/
</style>
