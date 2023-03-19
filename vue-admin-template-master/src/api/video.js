import request from '@/utils/request'

export default {
  //上传影视信息到后端
    addVideoInfo(videoInfo){
      return request(
        {
          url: '/service/video/addVideoInfo',
          method: 'post',
          data:videoInfo
        }
      )

  },
  //查询所有影视创作者
  getListProducer(){
    return request(
      {
        url: '/proservice/producer/findAll',
        method: 'get'
      }
    )
  }
}
