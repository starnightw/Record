// pages/contests/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    active: 0,// 默认页面
    activeNames: ['1'],

    timestamp: '', // 当前时间

    atcoder: {},
    codeforces: {},
    qluoj: {},

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.getAllContestByType()
    var timestamp = Date.parse(new Date());
    this.setData({
      timestamp : timestamp / 1000
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    wx.setNavigationBarTitle({
      title: '相关比赛'
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  getAllContestByType() {
    var app = getApp();  
    let that = this
    // console.log(this.problemTags)
    wx.request({
      url: app.apiUrl+"/contest/getallbytype",
      method: "POST",
      data: {
      },
      header: {
        'content-type': 'application/json', // 默认值
        'token': wx.getStorageSync('token'),
        'openid': wx.getStorageSync('openid')
      },
      success: (res) => {
        console.log(res.data)
        if(res.data.code === 200) {
          this.setData({
            atcoder: res.data.data.atcoder,
            codeforces: res.data.data.codeforces,
            qluoj: res.data.data.qluoj,
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
  onChangeActive(event) {
    // wx.showToast({
    //   title: `切换到标签 ${event.detail.name}`,
    //   icon: 'none',
    // });
  },
  onChangeActiveNames(event) {
    this.setData({
      activeNames: event.detail,
    });
  },

  copy: function(event){
    console.log("value："+event.currentTarget.dataset.value)
    wx.setClipboardData({
      data: event.currentTarget.dataset.value,
      success: function (res) {
        console.log("复制成功")
      }
    });
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

  }
})