import { createApp } from 'vue';
import ElementPlus from 'element-plus';
import 'element-plus/dist/index.css';
// import * as echarts from 'echarts';
import App from './App.vue';
import router from './router';
import store from './store';

const app = createApp(App);
app.use(ElementPlus);
// app.echarts = echarts; // 引入echarts 图表库
app.use(store).use(router).mount('#app');
