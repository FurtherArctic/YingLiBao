<!--suppress ALL -->
<template>
  <AppHeader></AppHeader>
  <div class="login-content">
    <div class="login-flex">
      <div class="login-left">
        <h3>加入动力金融网</h3>
        <p>坐享<span>{{ appinfo.avgRate }}%</span>历史年化收益</p>
        <p>平台用户<span>{{ appinfo.registerUsers }}</span>位 </p>
        <p>累计成交金额<span>{{ appinfo.sumBidMoney }}</span>元</p>
      </div>
      <!---->
      <div class="login-box">
        <h3 class="login-title">欢迎登录</h3>
        <form action="" id="login_Submit">
          <div class="alert-input">
            <input type="text" class="form-border user-num" name="mobile" v-model="phone" @blur="checkPhone"
                   placeholder="请输入11位手机号">
            <div class="input-err">{{ phoneErr }}</div>
            <p class="prompt_num"></p>
            <input type="password" placeholder="请输入登录密码" class="form-border user-pass" v-model="secret"
                   @blur="checkSecret" name="password">
            <div class="input-err">{{ secretErr }}</div>
            <p class="prompt_pass"></p>
            <div class="form-yzm form-border">
              <input class="yzm-write" type="text" placeholder="输入短信验证码" v-model="code" @blur="checkCode">
              <input class="yzm-send" type="text" value="获取验证码" @click="sendLoginCode" id="yzmBtn">
            </div>
            <div class="input-err">{{ codeErr }}</div>
            <p class="prompt_yan"></p>
          </div>
          <div class="alert-input-btn">
            <input type="button" class="login-submit" @click="userLogin" value="登录">
          </div>
        </form>
      </div>
    </div>
  </div>

  <AppFooter></AppFooter>
</template>

<script>
import AppFooter from "@/components/AppFooter";
import AppHeader from "@/components/AppHeader";
import {doGet, doPost} from "@/request/http";
import layx from "vue-layx";
import md5 from "js-md5";

export default {
  name: "LoginView",
  components: {
    AppHeader,
    AppFooter
  },
  data() {
    return {
      appinfo: {
        registerUsers: 0,
        sumBidMoney: 0,
        avgRate: 0.0
      },
      phone: '13800000000',
      phoneErr: '',
      secret: '111aaa',
      secretErr: '',
      code: '',
      codeErr: ''
    }
  },
  mounted() {
    doGet('/app/base/info').then(resp => {
      this.appinfo = resp.data.info;
    })
  },
  methods: {
    goLink(url, parameter) { //跳转页面
      this.$router.push({
        path: url,
        query: parameter
      })
    },
    checkPhone() {
      if (!this.phone) {
        this.phoneErr = '请收入有效的手机号码';
      } else if (this.phone.length !== 11) {
        this.phoneErr = '手机号是11位的';
      } else if (!/^1[1-9]\d{9}$/.test(this.phone)) {
        this.phoneErr = '手机号格式不正确';
      } else {
        this.phoneErr = '';
      }
    },
    checkSecret() {
      if (!this.secret) {
        this.secretErr = '请输入密码';
      } else if (this.secret.length < 6 || this.secret.length > 16) {
        this.secretErr = '密码是6-16位的数字和字母组成的';
      } else if (!/^(([a-zA-Z]+[0-9]+)|([0-9]+[a-zA-Z]+))[a-zA-Z0-9]*/.test(this.secret)) {
        this.secretErr = '密码必须包含字母和数字';
      } else {
        this.secretErr = '';
      }
    },
    checkCode() {
      if (!this.code) {
        this.codeErr = '请输入短信验证码';
      } else if (this.code.length !== 6) {
        this.codeErr = '验证码是6位的';
      } else {
        this.codeErr = '';
      }
    },
    sendLoginCode() {
      this.checkPhone();
      if (this.phoneErr === '') {
        doGet('/sms/code/login', {phone: this.phone}).then(resp => {
          if (resp.data.code === 1000) {
            layx.msg('请注意查看短信', {dialogIcon: 'success', position: 'ct'});
          }
        })
      }
    },
    userLogin() { //登录
      this.checkPhone();
      this.checkSecret();
      this.checkCode();
      if (this.phoneErr === '' && this.secretErr === '' && this.codeErr === '') {
        //密码md5
        let param = {phone: this.phone, secret: md5(this.secret), code: this.code};
        doPost('/user/login', param).then(resp => {
          console.log("登录后的输出")
          console.log(resp)
          if (resp.data.code === 1) { //登录成功 ,存储token和用户信息
            //使用SessionStorage
            let json = JSON.stringify(resp.data.info);
            window.sessionStorage.setItem("ylb-user", json);
            //1.如果用户没有实名， 跳到实名认证； 2.如果已经实名，跳转到用户中心。
            this.goLink('/user/realname');
          }
        })
      }
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