import request from '@/utils/requests'
export default {
  //查询主页热门影视和著名创作者信息
  getIndexVideosAndProducers() {
    return request({
      url: `/service/indexFront/index`,
      method: 'get'
    })
  }
}
