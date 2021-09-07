// pages/problems/problem/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    problem: {}

  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let data = JSON.parse(options.problem_id)
    console.log(data)
    this.getProblemById(data);
  },

  getProblemById(problem_id) {
    var app = getApp();  
    let that = this
    wx.request({
      url: app.apiUrl+"/problem/getproblembyid",
      method: "POST",
      data: {
        problem_id: problem_id
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
            problem: res.data.data.problem
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
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    wx.setNavigationBarTitle({
      title: '题目详情'
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

  }
})