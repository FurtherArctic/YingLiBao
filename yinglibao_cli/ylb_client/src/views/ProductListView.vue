<template>
  <AppHeader/>
  <div class="content clearfix">
    <!--排行榜-->
    <ul class="rank-list">
      <li v-for="(rank,index) in bidRank" :key="index">
        <img src="@/assets/image/list-rank1.png" alt="">
        <p class="rank-list-phone">{{ rank.phone }}</p>
        <span>{{ rank.score }}元</span>
      </li>

    </ul>
    <!--产品列表-->
    <ul class="preferred-select clearfix">
      <li>
        <h3 class="preferred-select-title">
          <span>满月宝</span>
          <img src="@/assets/image/1-bg1.jpg" alt="">
        </h3>
        <div class="preferred-select-number">
          <p><b>4.9</b>%</p>
          <span>历史年化收益率</span>
        </div>
        <div class="preferred-select-date">
          <div>
            <span>投资周期</span>
            <p><b>1</b>个月</p>
          </div>
          <div>
            <span>余利可投资金额</span>
            <p><b>250000.0</b>元</p>
          </div>
        </div>
        <p class="preferred-select-txt">
          优选计划项目，投资回报周期1个月，起点低，适合短期资金周转、对流动性要求高的投资人。
        </p>
        <a href="javascript:;" target="_blank" class="preferred-select-btn">立即投资</a>
      </li>
      <li>
        <h3 class="preferred-select-title">
          <span>季度宝</span>
          <img src="@/assets/image/1-bg2.jpg" alt="">
        </h3>
        <div class="preferred-select-number">
          <p><b>6.2</b>%</p>
          <span>历史年化收益率</span>
        </div>
        <div class="preferred-select-date">
          <div>
            <span>投资周期</span>
            <p><b>1</b>个月</p>
          </div>
          <div>
            <span>余利可投资金额</span>
            <p class="preferred-select-date-no"><b>0.0</b>元</p>
          </div>
        </div>
        <p class="preferred-select-txt">
          优选计划项目，投资回报周期1个月，起点低，适合短期资金周转、对流动性要求高的投资人。
        </p>
        <a href="javascript:;" target="_blank" class="preferred-select-btn">立即投资</a>
      </li>
      <li>
        <h3 class="preferred-select-title">
          <span>双季宝</span>
          <img src="@/assets/image/1-bg3.jpg" alt="">
        </h3>
        <div class="preferred-select-number">
          <p><b>7.1</b>%</p>
          <span>历史年化收益率</span>
        </div>
        <div class="preferred-select-date">
          <div>
            <span>投资周期</span>
            <p><b>1</b>个月</p>
          </div>
          <div>
            <span>余利可投资金额</span>
            <p><b>250000.0</b>元</p>
          </div>
        </div>
        <p class="preferred-select-txt">
          优选计划项目，投资回报周期1个月，起点低，适合短期资金周转、对流动性要求高的投资人。
        </p>
        <a href="javascript:;" target="_blank" class="preferred-select-btn">立即投资</a>
      </li>
      <li>
        <h3 class="preferred-select-title">
          <span>年度宝</span>
          <img src="@/assets/image/1-bg4.jpg" alt="">
        </h3>
        <div class="preferred-select-number">
          <p><b>4.9</b>%</p>
          <span>历史年化收益率</span>
        </div>
        <div class="preferred-select-date">
          <div>
            <span>投资周期</span>
            <p><b>1</b>个月</p>
          </div>
          <div>
            <span>余利可投资金额</span>
            <p><b>250000.0</b>元</p>
          </div>
        </div>
        <p class="preferred-select-txt">
          优选计划项目，投资回报周期1个月，起点低，适合短期资金周转、对流动性要求高的投资人。
        </p>
        <a href="javascript:;" target="_blank" class="preferred-select-btn">立即投资</a>
      </li>
      <li>
        <h3 class="preferred-select-title">
          <span>满月宝</span>
          <img src="@/assets/image/1-bg1.jpg" alt="">
        </h3>
        <div class="preferred-select-number">
          <p><b>4.9</b>%</p>
          <span>历史年化收益率</span>
        </div>
        <div class="preferred-select-date">
          <div>
            <span>投资周期</span>
            <p><b>1</b>个月</p>
          </div>
          <div>
            <span>余利可投资金额</span>
            <p><b>250000.0</b>元</p>
          </div>
        </div>
        <p class="preferred-select-txt">
          优选计划项目，投资回报周期1个月，起点低，适合短期资金周转、对流动性要求高的投资人。
        </p>
        <a href="javascript:;" target="_blank" class="preferred-select-btn">立即投资</a>
      </li>
      <li>
        <h3 class="preferred-select-title">
          <span>季度宝</span>
          <img src="@/assets/image/1-bg2.jpg" alt="">
        </h3>
        <div class="preferred-select-number">
          <p><b>6.2</b>%</p>
          <span>历史年化收益率</span>
        </div>
        <div class="preferred-select-date">
          <div>
            <span>投资周期</span>
            <p><b>1</b>个月</p>
          </div>
          <div>
            <span>余利可投资金额</span>
            <p class="preferred-select-date-no"><b>0.0</b>元</p>
          </div>
        </div>
        <p class="preferred-select-txt">
          优选计划项目，投资回报周期1个月，起点低，适合短期资金周转、对流动性要求高的投资人。
        </p>
        <a href="javascript:;" target="_blank" class="preferred-select-btn">立即投资</a>
      </li>
      <li>
        <h3 class="preferred-select-title">
          <span>双季宝</span>
          <img src="@/assets/image/1-bg3.jpg" alt="">
        </h3>
        <div class="preferred-select-number">
          <p><b>7.1</b>%</p>
          <span>历史年化收益率</span>
        </div>
        <div class="preferred-select-date">
          <div>
            <span>投资周期</span>
            <p><b>1</b>个月</p>
          </div>
          <div>
            <span>余利可投资金额</span>
            <p><b>250000.0</b>元</p>
          </div>
        </div>
        <p class="preferred-select-txt">
          优选计划项目，投资回报周期1个月，起点低，适合短期资金周转、对流动性要求高的投资人。
        </p>
        <a href="javascript:;" target="_blank" class="preferred-select-btn">立即投资</a>
      </li>
      <li>
        <h3 class="preferred-select-title">
          <span>年度宝</span>
          <img src="@/assets/image/1-bg4.jpg" alt="">
        </h3>
        <div class="preferred-select-number">
          <p><b>4.9</b>%</p>
          <span>历史年化收益率</span>
        </div>
        <div class="preferred-select-date">
          <div>
            <span>投资周期</span>
            <p><b>1</b>个月</p>
          </div>
          <div>
            <span>余利可投资金额</span>
            <p><b>250000.0</b>元</p>
          </div>
        </div>
        <p class="preferred-select-txt">
          优选计划项目，投资回报周期1个月，起点低，适合短期资金周转、对流动性要求高的投资人。
        </p>
        <a href="javascript:;" target="_blank" class="preferred-select-btn">立即投资</a>
      </li>
      <li>
        <h3 class="preferred-select-title">
          <span>满月宝</span>
          <img src="@/assets/image/1-bg1.jpg" alt="">
        </h3>
        <div class="preferred-select-number">
          <p><b>4.9</b>%</p>
          <span>历史年化收益率</span>
        </div>
        <div class="preferred-select-date">
          <div>
            <span>投资周期</span>
            <p><b>1</b>个月</p>
          </div>
          <div>
            <span>余利可投资金额</span>
            <p><b>250000.0</b>元</p>
          </div>
        </div>
        <p class="preferred-select-txt">
          优选计划项目，投资回报周期1个月，起点低，适合短期资金周转、对流动性要求高的投资人。
        </p>
        <a href="javascript:;" target="_blank" class="preferred-select-btn">立即投资</a>
      </li>
    </ul>

    <!--分页-->
    <div class="page_box">
      <ul class="pagination">
        <li class="disabled"><span>首页</span></li>
        <li><a href="">上一页</a></li>
        <li class="active"><span>1</span></li>
        <li><a href="">下一页</a></li>
        <li><a href="">尾页</a></li>
        <li class="totalPages"><span>共10页</span></li>
      </ul>
    </div>

  </div>
  <AppFooter/>
</template>

<script>
import AppHeader from "@/components/AppHeader";
import AppFooter from "@/components/AppFooter";
import {doGet} from "@/request/http";

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
      }]
    }
  },
  mounted() {
    doGet('/bid/rank').then(resp => {
      this.bidRank = resp.data.info;
    })
  }
}
</script>

<style scoped>

</style>