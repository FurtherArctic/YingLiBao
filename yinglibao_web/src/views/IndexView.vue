<template>
  <!--公共头部-->
  <AppHeader></AppHeader>


  <!--banner-->
  <div class="banner-content">
    <div class="swiper-container banner-lb">
      <div class="swiper-wrapper">
        <div class="swiper-slide">
          <a href="javascript:void(0)">
            <img src="@/assets/image/banner.jpg" alt="">
          </a>
        </div>
        <div class="swiper-slide">
          <a href="javascript:void(0)">
            <img src="@/assets/image/banner.jpg" alt="">
          </a>
        </div>
      </div>
    </div>
    <div class="banner-abs">
      <div class="banner-abs-box">
        <div class="banner-abs-title">动力金融网历史年化收益率</div>
        <b>{{ appInfo.avgRate }}<i>%</i></b>
        <p>平台用户数</p>
        <span>{{ appInfo.registerUserCount }}<i>位</i></span>
        <p class="banner-abs-border">累计成交金额</p>
        <span>{{ appInfo.sumBidMoney }}<i>元</i></span>
      </div>
    </div>
  </div>
  <div class="banner-list">
    <ul>
      <li>
        <img src="@/assets/image/banner-tag1.png" alt="">
        <p>
          <b>实力雄厚</b>
          <span>亿级注册资本 ,千万技术投入</span>
        </p>
      </li>
      <li>
        <img src="@/assets/image/banner-tag2.png" alt="">
        <p>
          <b>风控严苛</b>
          <span>30道风控工序，60项信用审核</span>
        </p>
      </li>
      <li>
        <img src="@/assets/image/banner-tag3.png" alt="">
        <p>
          <b>投资省心</b>
          <span>资金安全风控，银行安全托管</span>
        </p>
      </li>
    </ul>
  </div>

  <!--产品-->
  <div class="content">
    <h2 class="public-title"><span>新手宝</span></h2>
    <div class="new-user" v-for="product in newList" :key="product.id">
      <div class="new-user-sm">
        <span>{{ product.bidMinLimit }}元起投</span>
        <span>投资最高限额{{ product.bidMaxLimit }}元</span>
        <span>当日即系</span>
      </div>
      <div class="new-user-number">
        <ul>
          <li>
            <p><b>{{ product.rate }}</b>%</p>
            <span>历史年化收益率</span>
          </li>
          <li>
            <p><b>{{ product.cycle }}</b>个月</p>
            <span>投资周期</span>
          </li>
          <li>
            <p><b>{{ product.leftProductMoney }}</b>元</p>
            <span>利余可投资金额</span>
          </li>
        </ul>
        <a href="javascript:void(0)" @click="goLink('/product/detail',{pid : product.id})"
           class="new-user-btn">立即投资</a>
      </div>
      <span class="new-tag">新用户专享</span>
    </div>

    <h2 class="public-title"><span>优选产品</span>
      <a href="javascript:void(0)" @click="goLink('/product/list',{pType:1})"
         class="public-title-more">查看更多产品>></a></h2>
    <ul class="preferred-select clearfix">

      <li v-for="(product,index) in goodList" :key="product.id">
        <h3 class="preferred-select-title">
          <span>{{ product.productName }}</span>
          <img src="@/assets/image/1-bg1.jpg" alt="" v-if="index===0">
          <img src="@/assets/image/1-bg2.jpg" alt="" v-else-if="index===1">
          <img src="@/assets/image/1-bg3.jpg" alt="" v-else>
        </h3>
        <div class="preferred-select-number">
          <p><b>{{ product.rate }}</b>%</p>
          <span>历史年化收益率</span>
        </div>
        <div class="preferred-select-date">
          <div>
            <span>投资周期</span>
            <p><b>{{ product.cycle }}</b>个月</p>
          </div>
          <div>
            <span>余利可投资金额</span>
            <p><b>{{ product.leftProductMoney }}</b>元</p>
          </div>
        </div>
        <p class="preferred-select-txt">
          优选计划项目，投资回报周期{{ product.cycle }}个月，起点低，适合短期资金周转、对流动性要求高的投资人。
        </p>
        <a href="javascript:void(0)" @click="goLink('/product/detail',{pid: product.id})"
           class="preferred-select-btn">立即投资</a>
      </li>
    </ul>

    <h2 class="public-title"><span>散标产品</span>
      <a href="javascript:void(0)" @click="goLink('/product/list',{pType:2})"
         class="public-title-more">查看更多产品>></a>
    </h2>
    <ul class="preferred-select clearfix">
      <li v-for="product in bulkList" :key="product.id">
        <h3 class="preferred-select-title1">{{ product.productName }}
          <span>散标</span>
        </h3>
        <div class="preferred-select-number">
          <p><b>{{ product.rate }}</b>%</p>
          <span>历史年化收益率</span>
        </div>
        <div class="preferred-select-date">
          <div>
            <span>投资周期</span>
            <p><b>{{ product.cycle }}</b>个月</p>
          </div>
          <div>
            <span>余利可投资金额</span>
            <p><b>{{ product.leftProductMoney }}</b>元</p>
          </div>
        </div>
        <p class="preferred-select-txt">
          优选计划项目，投资回报周期{{ product.cycle }}个月，起点低，适合短期资金周转、对流动性要求高的投资人。
        </p>
        <a href="javascript:void(0)" @click="goLink('/product/detail',{pid:product.id})" class="preferred-select-btn">立即投资</a>
      </li>
    </ul>

  </div>

  <!--说明-->
  <div class="information-box">
    <ul>
      <li>
        <img src="@/assets/image/2-icon1.png" alt="">
        <p>优质借款</p>
        <span>严苛风控，多重审核</span>

      </li>
      <li>
        <img src="@/assets/image/2-icon2.png" alt="">
        <p>次日计息</p>
        <span>闪电成交，谁比我快</span>
      </li>
      <li>
        <img src="@/assets/image/2-icon3.png" alt="">
        <p>全年无休</p>
        <span>百万用户，一路同行</span>
      </li>
      <li>
        <img src="@/assets/image/2-icon4.png" alt="">
        <p>知心托付</p>
        <span>百万用户，一路同行</span>
      </li>
      <li>
        <img src="@/assets/image/2-icon5.png" alt="">
        <p>技术保障</p>
        <span>千万投入，专注研发</span>
      </li>
    </ul>
  </div>

  <!--公共底部-->
  <AppFooter></AppFooter>


</template>

<script>
import AppHeader from "@/components/AppHeader";
import AppFooter from "@/components/AppFooter";
import {doGet} from "@/request/http.js";


export default {
  name: "IndexView",
  components: {
    AppHeader,
    AppFooter
  },
  data() {
    return {
      appInfo: {
        registerUserCount: 0,
        sumBidMoney: 0,
        avgRate: 0.0
      },
      newList: [{
        id: 0,
        productName: "",
        rate: 0,
        cycle: 0,
        productType: 0,
        productNo: "",
        productMoney: 0,
        leftProductMoney: 0,
        bidMinLimit: 0,
        bidMaxLimit: 0
      }],
      goodList: [{
        id: 0,
        productName: "",
        rate: 0,
        cycle: 0,
        productType: 0,
        productNo: "",
        productMoney: 0,
        leftProductMoney: 0,
        bidMinLimit: 0,
        bidMaxLimit: 0
      }],
      bulkList: [{
        id: 0,
        productName: "",
        rate: 0,
        cycle: 0,
        productType: 0,
        productNo: "",
        productMoney: 0,
        leftProductMoney: 0,
        bidMinLimit: 0,
        bidMaxLimit: 0
      }]
    }
  },
  mounted() {
    doGet('/app/base/info').then(resp => {
      this.appInfo = resp.data.info;
    })

    doGet('/products/three').then(a => {
      this.newList = a.data.info.newList;
      this.goodList = a.data.info.goodList;
      this.bulkList = a.data.info.bulkList;
    })
  },
  methods: {
    goLink(url, parameter) {
      this.$router.push({
        path: url,
        query: parameter
      })
    }
  }
}
</script>

<style scoped>

</style>