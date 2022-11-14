<template>
  <AppHeader></AppHeader>
  <div class="login-content">
    <div class="login-flex">
      <div class="login-left">
        <p>万民用户知心托付&nbsp;&nbsp;&nbsp;&nbsp;<span>{{avgRate}}%</span>历史年化收益</p>
        <p>千万级技术研发投入&nbsp;&nbsp;&nbsp;&nbsp;亿级注册资本平台  </p>
      </div>
      <!---->
      <div class="login-box">
        <h3 class="login-title">用户注册</h3>
        <form action="" id="register_Submit">
          <div class="alert-input">
            <input type="text" class="form-border user-num" name="mobile" v-model="phone" @blur="checkPhone" placeholder="请输入11位手机号">
            <div class="input-err">{{phoneErr}}</div>
            <p class="prompt_num"></p>
            <input type="password" placeholder="请输入6-20位英文和数字混合密码" v-model="secret" @blur="checkSecret" class="form-border user-pass" autocomplete name="password">
            <div class="input-err">{{secretErr}}</div>
            <p class="prompt_pass"></p>
            <div class="form-yzm form-border">
              <input class="yzm-write" type="text" v-model="code" @blur="checkCode" placeholder="输入短信验证码">
              <input class="yzm-send" type="text" v-model="yzmText" id="yzmBtn" @click="sendCode">
            </div>
            <div class="input-err">{{codeErr}}</div>
            <p class="prompt_yan"></p>
          </div>
          <div class="alert-input-agree">
            <input type="checkbox" v-model="agree"> 我已阅读并同意<a href="javascript:" target="_blank">《动力金融网注册服务协议》</a>
          </div>
          <div class="alert-input-btn">
            <input type="button" class="login-submit" @click="registerPhone" value="注册">
          </div>
        </form>
        <div class="login-skip">
          已有账号？ <a href="javascript:void(0)" @click="goLink('/user/login')">登录</a>
        </div>
      </div>

    </div>
  </div>
  <AppFooter></AppFooter>
</template>

<!--suppress JSUnresolvedFunction -->
<script>
import AppFooter from "@/components/AppFooter";
import AppHeader from "@/components/AppHeader";
import {doGet, doPost} from "@/request/http";
import layx from "vue-layx";
import md5 from 'js-md5';


export default {
  name: "RegisterView",
  components:{
    AppHeader,
    AppFooter
  },
  data(){
    return{
      avgRate:0.0,
      phone:'13800000001',
      phoneErr:'',
      secret:'111aaa',
      secretErr:'',
      code:'',
      codeErr:'',
      yzmText:'获取验证码',
      leftTime:false,
      agree:false
    }
  },
  mounted() {
    //读取历史年化收益率
    doGet('/app/base/info').then(resp=>{
      this.avgRate = resp.data.info.avgRate;
    })
  },
  methods:{
    checkPhone(){ //检查手机号
      if(!this.phone){
        this.phoneErr='请收入有效的手机号码';
      } else if(this.phone.length !== 11){
        this.phoneErr='手机号是11位的';
      } else if( !/^1[1-9]\d{9}$/.test(this.phone)){
        this.phoneErr='手机号格式不正确';
      } else {
        this.phoneErr='';
      }
    },
    checkSecret(){
      if(!this.secret){
        this.secretErr='请输入密码';
      } else if( this.secret.length < 6 || this.secret.length >16){
        this.secretErr = '密码是6-16位的数字和字母组成的';
      } else if(!/^(([a-zA-Z]+[0-9]+)|([0-9]+[a-zA-Z]+))[a-zA-Z0-9]*/.test(this.secret)){
        this.secretErr='密码必须包含字母和数字';
      } else {
        this.secretErr='';
      }
    },
    checkCode(){
      if(!this.code){
        this.codeErr='请输入短信验证码';
      } else if( this.code.length !== 4){
        this.codeErr='验证码是4位的';
      } else {
        this.codeErr='';
      }
    },
    sendCode(){

      //1leftTime=true:正在倒计时， false，可以倒计时
      if(this.leftTime){
        return;
      }
      //2.检查手机号是正确的
      this.checkPhone();
      if( this.phoneErr === ''){
        this.leftTime = true;
        //倒计时
        let second = 10;
        //初始文本
        this.yzmText=second+"秒后获取";
        let internal  = setInterval(()=>{
          if(second <=1){
            this.yzmText = "获取验证码";
            this.leftTime = false;
            clearInterval(internal);
          } else {
            second = second - 1;
            this.yzmText=second+"秒后获取";
          }
        },1000);

        //调用服务器的短信发送接口
        doGet('/sms/code/reg',{phone:this.phone}).then(resp=>{
          if(resp.data.code === 1000){
            layx.msg('请注意查看短信',{dialogIcon:'success',position:'ct'});
          }
        })

      }
    },
    registerPhone(){
      if( !this.agree ){
        layx.msg('请阅读注册协议',{dialogIcon:'warn',position:'ct'});
        return;
      }

      //注册用户
      this.checkPhone();
      this.checkSecret();
      this.checkCode();
      if( this.phoneErr === '' && this.secretErr === '' && this.codeErr === ''){
        //可以调用服务器接口
        // noinspection JSValidateTypes
        let md5Secret = md5(this.secret);
        doPost('/user/register',{phone:this.phone,secret:md5Secret,code:this.code}).then(resp=>{
          if( resp.data.code === 1){
            //注册成功，跳到登录页面
            this.goLink('/user/login');
          }
        })
      }
    },
    goLink(url,parameter){ //跳转页面
      this.$router.push({
        path:url,
        query: parameter
      })
    }
  }
}
</script>

<style scoped>
.input-err {
  color: red;
  font-size: 18px;
}
</style>