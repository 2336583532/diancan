// pages/base/base.js
wx.cloud.init({})
const db = wx.cloud.database()
const orderCollection = db.collection('order')
Page({

  /**
   * 页面的初始数据
   */
  data: {
  totalNumber:0,
  totalPrice:0,
  cartList: [],
  animationData: {},
  animationMask: {},
  maskVisual: "hidden",
  maskFlag: true,
  userName:"",
  //用户手机号的输入框
  hiddenmodalput:true,
  phone:"",
  },
  mobileInput:function(e){
    this.setData({
      phone:e.detail.value
    })
  },
  addCount: function(e){
    var that = this
    var fid = e.currentTarget.dataset.fid;
    var price=e.currentTarget.dataset.fprice;
    var name=e.currentTarget.dataset.fname;
    this.setData({
      totalNumber:this.data.totalNumber+1,
      totalPrice:this.data.totalPrice+e.currentTarget.dataset.fprice,
    });

    if(this.data.cartList[fid]!=null){
      var temp = 'cartList['+fid+'].number';
      var number = this.data.cartList[fid].number+1;
      this.setData({
        [temp]:number
      })
     
    }else{
      var obj = {};
      obj={
        "id":fid,
        "number":1,
        "name":name,
        "price":price
      }
      var temp = 'cartList['+fid+']';
     this.setData({
      [temp]:obj
     })
    }  
  },
  //点击按钮痰喘指定的hiddenmodalput弹出框
  modalinput:function(){
    this.setData({
     hiddenmodalput: !this.data.hiddenmodalput
    })
},
//取消按钮
cancel: function(){
this.setData({
hiddenmodalput: true
});
},
//确认
confirm: function(e){
  var that = this
this.setData({
  hiddenmodalput: true
 })
 
 var timestamp = Date.parse(new Date());  
    timestamp = timestamp / 1000;
    wx.request({
      url: 'http://localhost:9000/addOrder',
      data:{
        "username":this.data.userName,
        "phone":this.data.phone,
        "orderList":this.data.cartList,
        "date":timestamp,
        "type":"1"
      } ,
      method:"GET",
      header:{
        'content-Type':'application.json'
      },
      success:function(res){
        console.log(res)
        if(res.data==1){
          wx.showToast({  
            title: '下单成功',  
            icon: 'success',  
            duration: 2000  
        }) 
        that.setData({
          cartList:[],
          totalNumber:0,
          totalPrice:0
        })              
        }else{
          wx.showToast({  
            title: '请重试!',  
            icon: 'success',  
            duration: 2000  
            
        })  
        }
      }
    })
},
  minusCount:function(e){
    var that = this
    var fid = e.currentTarget.dataset.fid;
    var price=e.currentTarget.dataset.fprice;
    var name=e.currentTarget.dataset.fname;
    this.setData({
      totalNumber:this.data.totalNumber-1,
      totalPrice:this.data.totalPrice-e.currentTarget.dataset.fprice,
    });

    if(this.data.cartList[fid].number>0){
      var temp = 'cartList['+fid+'].number';
      var number = this.data.cartList[fid].number-1;
      this.setData({
        [temp]:number
      })
    }
  },
  deleteOne:function(e){
    var fid = e.currentTarget.dataset.fid;
    var temp="cartList["+fid+"].number";
    this.setData({
      [temp]:0
    })
    
  },


  cascadeToggle: function(){
    var animation = wx.createAnimation({
      duration: 200,
      timingFunction: 'ease-in-out',
      delay: 0
    });
    this.animation = animation;
    animation.translate(0, -285).step();
    this.setData({
      animationData: this.animation.export(),
    });

    // 遮罩渐变动画
    var animationMask = wx.createAnimation({
      duration: 200,
      timingFunction: 'linear',
    });
    this.animationMask = animationMask;
    animationMask.opacity(0.8).step();
    this.setData({
      animationMask: this.animationMask.export(),
      maskVisual: "show",
      maskFlag: false,
    });
  },
  closeToggle:function(){
  // 购物车关闭动画
  this.animation.translate(0, 285).step();
  this.setData({
    animationData: this.animation.export()
  });

  // 遮罩渐变动画
  this.animationMask.opacity(0).step();
  this.setData({
    animationMask: this.animationMask.export(),
  });

  // 隐藏遮罩层
  this.setData({
    maskVisual: "hidden",
    maskFlag: true
  });
  },
  

  
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    let that = this;
    wx.request({
      
      url: 'http://localhost:9000/getAllFoodMessage',
      data:{},
      method:"GET",
      header:{
        'content-Type':'application.json'
      },
      success:function(res){
        that.setData({
          data:res.data,
        });
      }
    }),
    // 查看是否授权
    wx.getSetting({
      success (res){
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称
          wx.getUserInfo({
            success: function(res) {
              that.setData({
                userName:res.userInfo.nickName
              })
            }
          })
        }
      }
    })
    
    
  },
  bindGetUserInfo (res) {
    if(this.data.cartList == null){
      return;
    }
    var that=this;
    
    if(this.data.userName=="" || this.data.userName==null){
      this.setData({
        userName:res.userInfo.nickName
      })
    }
    that.modalinput();
     
 
  },
  cleanList:function(){
    this.setData({
      cartList:[],
      totalNumber:0,
      totalPrice:0
    })
  },
  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

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
  


})