import request from '@/utils/requests'
export default {
  //前台查询创作者列表
  getProducerFrontList(page,limit) {
    return request({
      url: `service/producerFront/getProducerFrontList/${page}/${limit}`,
      method: 'get'
    })
  },
  getProducerAndHisVideos(id) {
    return request({
      url: `service/producerFront/getProducerAndHisVideos/${id}`,
      method: 'get'
    })
}

}
