import request from '@/utils/request'


export default {
  //用于查询起始日期到结束日期之间的折线图
  showChart(searchObj) {
    return request({
      url: `statistic/show-chart/${searchObj.begin}/${searchObj.end}/${searchObj.type}`,
      method: 'get'
    })
  }


}
