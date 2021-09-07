// pages/home/index.js
import uCharts from '../../miniprogram_npm/u-charts/u-charts.js';
var _self;
var canvaRadar = null;
Page({

  /**
   * 页面的初始数据
   */
  data: {
    // 页面内容
    timeText:"", // 访问时间可返回的text
    qluojNick: "",
    isLogin: false,
    show: false,
    username: 'Along',
    password: '1111111',
    
    loading: true, // 骨架图显示
    ifNoticeBar: false, //滚动条
    noticeBarText: '', //滚动条内容
    
    userInfo: {},
    hasUserInfo: false,
    canIUseGetUserProfile: false,
    
    // 雷达图
    cWidth: '',
    cHeight: '',
    
    everyDayTeach: [],
    // solution 最近做题
    solution: {},
  },

  getUserInfo(event) {
    console.log(event.detail);
  },

  onOpen() {
    this.setData({ show: true });
  },

  onClose() {
    this.setData({ show: false });
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    let that = this
    this.setData({
      ifNoticeBar: true,
      noticeBarText: "正在加载"
    })
    if (wx.getUserProfile) {
      this.setData({
        canIUseGetUserProfile: true
      })
    }
    // 图表
    _self=this;
    this.cWidth = wx.getSystemInfoSync().windowWidth * 0.9;
    this.cHeight = 500 / 750 * wx.getSystemInfoSync().windowWidth * 0.8;
    this.getServerData();

    this.getEveryDayTeaching()
    
    this.login()
    that.setData({
      loading: false,
    });
    that.setTimeText()
  },

  setTimeText() {
    var hour = new Date().getHours()
    let text=""
    if(hour < 6) text="起的真早！"
    else if (hour < 9) text="早上好！"
    else if (hour < 12)text="上午好！"
    else if (hour < 14)text="中午好！"
    else if (hour < 17)text="下午好！"
    else if (hour < 19)text="傍晚好！"
    else if (hour < 22)text="晚上好！"
    else text="夜深了！"
    this.setData({
      timeText:text
    })
  },

  getUserProfile(e) {
    // 推荐使用wx.getUserProfile获取用户信息，开发者每次通过该接口获取用户个人信息均需用户确认
    // 开发者妥善保管用户快速填写的头像昵称，避免重复弹窗
    wx.getUserProfile({
      desc: '用于完善用户信息', // 声明获取用户个人信息后的用途，后续会展示在弹窗中，请谨慎填写
      success: (res) => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
        wx.setStorageSync("hasUserInfo", true)
        wx.setStorageSync("userInfo",res.userInfo)
      },
      fail: (res)=> {
        wx.hideLoading();
        wx.showModal({
          title: '提示',
          content: '获取信息失败,本程序无法提供服务',
          showCancel: false
        })
      }
    })
  },

  login(){
    var app = getApp();  
    let that = this
    // 登录
    wx.login({
      success (res) {
        console.log('res.code===>'+res.code)
        console.log(app.apiUrl+"/wechat/login")
        console.log(wx.getStorageSync('userInfo'))
        if (res.code) {
          //发起网络请求
          wx.request({
            url: app.apiUrl+"/wechat/login",
            method: "POST",
            data: {
              code: res.code,
            },
            header: {
              'content-type': 'application/json', // 默认值
            },
            // 发送 res.code 到后台换取 openId, sessionKey, unionId
            success: (res) => {
              console.log(res.data)
              if (res.data.code == 200) {
                wx.showToast({
                  title: '登录成功',
                  icon: 'success',
                })
                that.setData({
                  ifNoticeBar: false,
                  isLogin: true,
                  qluojNick: res.data.data.nickName
                })
                that.getTenSolutionNumber()
                wx.setStorageSync("token",res.data.data.token)
                wx.setStorageSync("openid",res.data.data.openid)
                wx.setStorageSync("userid",res.data.data.user_id)
                
              } else if(res.data.code == 201 || res.data.code == 202){
                // 登录错误 
                // wx.navigateTo({
                //   url: '/pages/loginInterface/login',
                // });
                that.setData({
                  ifNoticeBar: true,
                  noticeBarText: res.data.msg
                })
                wx.setStorageSync("token",res.data.data.token)
                wx.setStorageSync("openid",res.data.data.openid)
              }
            },
            fail: ()=> {
              wx.showToast({
                title: '服务器连接失败',
                icon: 'error',
              })
              that.setData({
                ifNoticeBar: true,
                noticeBarText: "服务器连接失败, 请联系管理人员"
              })
            }
          })
        } else {
          console.log('登录失败！' + res.errMsg)
        }
      }
    })
  },

  toAddQLUOJ() {
    // Dialog.alert({
    //   title: '标题',
    //   message: '弹窗内容',
    //   theme: 'round-button',
    // }).then(() => {
    //   // on close
    // });
    wx.navigateTo({
      url:"pages/showpage/show",
    });
  },

  getTenSolutionNumber() {
    var app = getApp();  
    let that = this
    wx.request({
      url: app.apiUrl+"/solution/getallten",
      method: "POST",
      data: {
        userid: wx.getStorageSync('userid')
      },
      header: {
        'content-type': 'application/json', // 默认值
        'token': wx.getStorageSync('token'),
        'openid': wx.getStorageSync('openid')
      },
      success: (res) => {
        console.log(res.data)
        if(res.data.code === 200) {
          that.setData({
           solution: res.data.data.solutions
          })
        }
      },
      fail: ()=> {
        wx.showToast({
          title: '服务器连接失败',
          icon: 'error',
        })
      }
    })
  },

  getEveryDayTeaching() {
    var app = getApp();  
    let that = this
    wx.request({
      url: app.apiUrl+"/everyDay/teaching",
      method: "POST",
      data: {
      },
      header: {
        'content-type': 'application/json', // 默认值
        'token': wx.getStorageSync('token'),
        'openid': wx.getStorageSync('openid')
      },
      success: (res) => {
        console.log(res.data.data.everyDayTeach)
        if(res.data.code === 200) {
          that.setData({
            everyDayTeach: res.data.data.everyDayTeach[9]
          })
        }
      },
      fail: ()=> {
        wx.showToast({
          title: '服务器连接失败',
          icon: 'error',
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },

  toPage(e){
    // 根据传参键值，获取点击事件传来的id值
    let url = e.currentTarget.dataset.url
    if(url){
      wx.navigateTo({
        url: e.currentTarget.dataset.url,
      })
    }else {
      wx.showToast({
        title: '还在开发中~',
        icon: 'none',
        duration: 1000//持续的时间
      })
    }
  },

  updateUserId(){
    var app = getApp();  
    let that = this
    wx.request({
      url: app.apiUrl+"/wechat/updateuserid",
      method: "POST",
      data: {
        openid: wx.getStorageSync('openid'),
        username: that.data.username
      },
      header: {
        'content-type': 'application/json', // 默认值
        'token': wx.getStorageSync('token'),
        'openid': wx.getStorageSync('openid')
      },
      success: (res) => {
        console.log(res.data)
        this.onReady()
        if(res.data.data) {
          that.setData({
            isLogin: true
          })
        }
      },
      fail: ()=> {
        wx.showToast({
          title: '服务器连接失败',
          icon: 'error',
        })
      }
    })
  },

  // 
  getServerData: function() {
    wx.request({
      url: 'https://www.ucharts.cn/data.json',
      data: {
      },
      success: function (res) {
        console.log(res.data.data)
        let Radar = { categories: [], series: [] };
        //这里我后台返回的是数组，所以用等于，如果您后台返回的是单条数据，需要push进去
        Radar.categories = res.data.data.Radar.categories;
        Radar.series = res.data.data.Radar.series;

        _self.showRadar("canvasRadar", Radar);
      },
      fail: () => {
        console.log("请点击右上角【详情】，启用不校验合法域名");
      },
    });
  },
  showRadar(canvasId, chartData) {
    let ctx = wx.createCanvasContext(canvasId, this);
    canvaRadar = new uCharts({
      type: 'radar',
      context: ctx,
      legend: true,
      fontSize: 11,
      background: '#FFFFFF',
      pixelRatio: 1,
      animation: true,
      categories: chartData.categories,
      series: chartData.series,
      xAxis: {
        disableGrid: true,
      },
      yAxis: {
        //disabled:true
      },
      dataLabel: true,
      width: _self.cWidth ,
      height: _self.cHeight ,
      extra: {
        column: {
          type: 'group',
          width: _self.cWidth * 0.45 / chartData.categories.length
        }
      }
    });
  },
  touchRadar(e) {
    canvaRadar.showToolTip(e, {
      formatter: function (item, category) {
        if (typeof item.data === 'object') {
          return category + ' ' + item.name + ':' + item.data.value
        } else {
          return category + ' ' + item.name + ':' + item.data
        }
      }
    });
  },


})