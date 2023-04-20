<template>
  <div class="app-container">
    <!--表单-->
    <el-form :inline="true" class="demo-form-inline">
      <el-form-item>
        <el-select v-model="searchObj.type" clearable placeholder="请选择">
          <el-option label="登录数统计" value="login_num" />
          <el-option label="注册数统计" value="register_num" />
          <el-option label="播放数统计" value="video_view_num" />
          <el-option label="新增影视统计" value="video_num" />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="searchObj.begin"
          type="date"
          placeholder="选择开始日期"
          value-format="yyyy-MM-dd"
        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="searchObj.end"
          type="date"
          placeholder="选择截止日期"
          value-format="yyyy-MM-dd"
        />
      </el-form-item>
      <el-button
        :disabled="btnDisabled"
        type="primary"
        icon="el-icon-search"
        @click="showChart()"
        >查询</el-button
      >
    </el-form>
    <div class="chart-container">
      <div id="chart" class="chart" style="height:500px;width:100%" />
    </div>
  </div>
</template>

<script>
import echarts from "echarts";
import staApi from "@/api/sta.js";
export default {
  data() {
    return {
      searchObj: {
        type: "",
        begin: "",
        end: ""
      },
      btnDisabled: false,
      chart: null,
      title: "",
      xData: [],
      yData: []
    };
  },
  methods: {
    showChart() {
      this.initChartData();
    },
    // 准备图表数据
    initChartData() {
      staApi.showChart(this.searchObj).then(response => {
        // 数据
        this.yData = response.data.dataList;
        // 横轴时间
        this.xData = response.data.dateList;
        // 当前统计类别
        switch (this.searchObj.type) {
          case "register_num":
            this.title = "学员注册数统计";
            break;
          case "login_num":
            this.title = "学员登录数统计";
            break;
          case "video_view_num":
            this.title = "课程播放数统计";
            break;
          case "course_num":
            this.title = "每日课程数统计";
            break;
        }
        this.setChart();
      });
    },
    // 设置图标参数
    setChart() {
      // 基于准备好的dom，初始化echarts实例
      this.chart = echarts.init(document.getElementById("chart"));
      // console.log(this.chart)
      // 指定图表的配置项和数据
      var option = {
        xAxis: {
          type: "category",
          data: this.xData //-------绑定数据
        },
        // y轴是数据轴（连续数据）
        yAxis: {
          type: "value"
        },
        // 系列列表。每个系列通过 type 决定自己的图表类型
        series: [
          {
            // 系列中的数据内容数组
            data: this.yData, //-------绑定数据
            // 折线图
            type: "line"
          }
        ]
      };
      this.chart.setOption(option);
    }
  }
};
</script>

<style scoped></style>
