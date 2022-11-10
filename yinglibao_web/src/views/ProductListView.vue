<template>
  <AppHeader></AppHeader>
  <div class="content clearfix">
    <!--排行榜-->
    <ul class="rank-list">
      <li v-for="(rank,index) in bidRank" :key="index">
        <img src="@/assets/image/list-rank1.png" alt="" v-if="index===0">
        <img src="@/assets/image/list-rank2.png" alt="" v-else-if="index===1">
        <img src="@/assets/image/list-rank3.png" alt="" v-else>
        <p class="rank-list-phone">{{ rank.phone }}</p>
        <span>{{ rank.score }}元</span>
      </li>

    </ul>
    <!--产品列表-->
    <ul class="preferred-select clearfix">
      <li v-for="product in productList" :key="product.id">
        <h3 class="preferred-select-title">
          <span>{{ product.productName }}</span>
          <img src="@/assets/image/1-bg1.jpg" alt="">
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

    <!--分页-->
    <div class="page_box">
      <ul class="pagination">
        <li><a href="javascript:void(0)" @click="firstPage">首页</a></li>
        <li><a href="javascript:void(0)" @click="prePage">上一页</a></li>
        <li class="active"><span>{{ pageInfo.pageNo }}</span></li>
        <li><a href="javascript:void(0)" @click="nextPage()">下一页</a></li>
        <li><a href="javascript:void(0)" @click="lastPage">尾页</a></li>
        <li class="totalPages"><span>共{{ pageInfo.totalPages }}页</span></li>
      </ul>
    </div>

  </div>
  <AppFooter/>
</template>

<script>
import AppHeader from "@/components/AppHeader";
import AppFooter from "@/components/AppFooter";
import {doGet} from "@/request/http.js";
import layx from "vue-layx";

export default {
  name: "ProductListView",
  components: {
    AppHeader,
    AppFooter
  },
  data() {
    return {
      bidRank: [{
        phone: "",
        score: 0
      }],
      productList: [
        {
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
        }
      ],
      pageInfo: {
        pageNo: 1,
        pageSize: 9,
        totalRecords: 0,
        totalPages: 0
      },
      productType: 0
    }
  },
  mounted() {
    //获取路由，路径中的参数pType
    this.productType = this.$route.query.pType;
    this.loadPage(1, 1);

    doGet('/bid/rank').then(resp => {
      this.bidRank = resp.data.info;
    })
  },
  methods: {
    //执行请求，获取产品列表数据
    loadPage(pageNo) {
      doGet('/product/type', {productType: this.productType, pageNo: pageNo}).then(resp => {
        this.productList = resp.data.info.productList;
        this.pageInfo = resp.data.info.pageInfo;
      })
    },

    firstPage() {
      if (this.pageInfo.pageNo === 1) {
        layx.msg("已经是第一页了", {dialogIcon: 'warn', position: 'ct'})
      } else {
        this.loadPage(1);
      }
    },

    lastPage() {
      if (this.pageInfo.pageNo === this.pageInfo.totalPages) {
        layx.msg("已经是最后一页了", {dialogIcon: 'warn', position: 'ct'});
      } else {
        this.loadPage(this.pageInfo.totalPages);
      }
    },

    prePage() {
      if (this.pageInfo.pageNo <= 1) {
        layx.msg("已经是第一页了", {dialogIcon: 'warn', position: 'ct'})
      } else {
        this.loadPage(this.pageInfo.pageNo - 1);
      }
    },
    nextPage() {
      if (this.pageInfo.pageNo >= this.pageInfo.totalPages) {
        layx.msg("已经是最后一页了", {dialogIcon: 'warn', position: 'ct'})
      } else {
        this.loadPage(this.pageInfo.pageNo + 1);
      }
    },
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