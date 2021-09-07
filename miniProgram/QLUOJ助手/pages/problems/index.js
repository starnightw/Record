// pages/problems/index.js
Page({

  /**
   * 页面的初始数据
   */
  data: {
    activeKey: 0,
    leftHeight: '1000rpx',

    colorArr: ["#EE2C2C", "#ff7070", "#EEC900", "#4876FF", "#ff6100",
      "#7DC67D", "#E17572", "#7898AA", "#C35CFF", "#33BCBA", "#C28F5C",
      "#FF8533", "#6E6E6E", "#428BCA", "#5cb85c", "#FF674F", "#E9967A",
      "#66CDAA", "#00CED1", "#9F79EE", "#CD3333", "#FFC125", "#32CD32",
      "#00BFFF", "#68A2D5", "#FF69B4", "#DB7093", "#CD3278", "#607B8B"],
    // 存储随机颜色
    randomColorArr: [],

    problemTags: {},
    newProblemByTags: {},
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    this.setData({
      leftHeight: wx.getSystemInfoSync().windowHeight*0.95,
    })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {
    this.getAllProblemNumber();
    wx.setNavigationBarTitle({
      title: '各类题集'
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    
  },

  onChange(event){
    var app = getApp();  
    let that = this
    // console.log(this.problemTags)
    wx.request({
      url: app.apiUrl+"/problem/getproblemsbytags",
      method: "POST",
      data: {
        tags: event.detail
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
            newProblemByTags : res.data.data.problem
          })
          that.setColor()
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

  getAllProblemNumber() {
    var app = getApp();  
    let that = this
    wx.request({
      url: app.apiUrl+"/problem/getalltags",
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
            problemTags : res.data.data.Tags,
            newProblemByTags : res.data.data.basic
          })
          that.setColor()
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

  toProblemPage(e){
    // 根据传参键值，获取点击事件传来的id值
    console.log(e.currentTarget.dataset.id)
    wx.navigateTo({
      url: '../problems/problem/index?problem_id=' + e.currentTarget.dataset.id,
    })
  },

  setColor(){
    let that = this,
        labLen = 50,
        colorArr = that.data.colorArr,
        colorLen = colorArr.length,
        randomColorArr = [];
	  //判断执行
    do{
      let random = colorArr[Math.floor(Math.random() * colorLen)];
      randomColorArr.push(random);
      labLen--;
    } while (labLen > 0)
    
    that.setData({ 
      randomColorArr: randomColorArr
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