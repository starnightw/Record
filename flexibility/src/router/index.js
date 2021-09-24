import { createRouter, createWebHistory } from 'vue-router';
// eslint-disable-next-line import/extensions
import Cookies from 'js-cookie';
// eslint-disable-next-line import/extensions
import Characters from '../views/Characters';
// eslint-disable-next-line import/extensions
import CharactersPage from '../views/CharactersPage';
// eslint-disable-next-line import/extensions
import Contribution from '../views/Contribution';
// eslint-disable-next-line import/extensions
import Home from '../views/Home';

const routes = [
  {
    // 个人分析主页
    path: '/',
    name: 'Home',
    component: Home,
  },
  {
    // 人物志
    path: '/characters',
    name: 'Characters',
    component: Characters,
  },
  {
    // 人物志详情
    path: '/character',
    name: 'Character',
    component: CharactersPage,
  },
  {
    // 贡献  成果 详情界面
    path: '/contribution',
    name: 'Contribution',
    component: Contribution,
  },
  {
    // 赛事列表
    path: '/event',
    name: 'Events',
    // eslint-disable-next-line import/extensions
    component: () => import('../views/Events'),
  },
  {
    // 吴老师课程
    path: '/course',
    name: 'Courses',
    // eslint-disable-next-line import/extensions
    component: () => import('../views/Courses'),
  },
  {
    // VJudge题集
    path: '/vJudge',
    name: 'VJudges',
    // eslint-disable-next-line import/extensions
    component: () => import('../views/VJudges'),
  },
  {
    // ICPC私云
    path: '/cloud',
    name: 'Cloud',
    // eslint-disable-next-line import/extensions
    component: () => import('../views/Cloud'),
  },
  {
    // 登录
    path: '/login',
    name: 'Login',
    // eslint-disable-next-line import/extensions
    component: () => import('../views/Login'),
  },
  {
    // 管理页面
    path: '/administrator',
    name: 'Admin',
    // eslint-disable-next-line import/extensions
    component: () => import('../views/AdminPages'),
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes,
});

// 全局路由守卫
router.beforeEach((to, from, next) => {
  // 路由跳转前监控(保证登录状态)
  // console.log("window+token==>"+window.localStorage.getItem('token'))
  // console.log("cookies+token==>"+Cookies.get('token'));
  // let token = window.localStorage.getItem('token')
  const token = Cookies.get('token');
  // 登录验证：如果本地没有储存用户且不在登录页面则跳转
  if (to.path === '/administrator' && !token) {
    next({ path: '/login' });
  } else if (to.path === '/administrator') {
    next();
  } else {
    next();
  }
});

export default router;
