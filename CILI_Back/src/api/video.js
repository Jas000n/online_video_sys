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
        url: '/service/producer/findAll',
        method: 'get'
      }
    )
  },

  //根据影视id查询影视信息
  getVideoInfo(videoId){
    return request(
      {
        url: `/service/video/getVideoInfo/${videoId}`,
        method: 'get'
      }
    )
  },
  //修改影视信息
  updateVideoInfo(videoInfo){
    return request(
      {
        url: `/service/video/updateVideoInfo`,
        method: 'post',
        data:videoInfo
      }
    )
  },
  //根据影视id查询videInfoVO信息,用于publish数据页面数据显示
  getPublishVideoInfoVOById(videoId){
    return request(
      {
        url: `/service/video/getPublishVideoInfo/${videoId}`,
        method: 'get'
      }
    )

  },
  publishVideo(videoId){
    return request(
      {
        url: `/service/video/publishVideo/${videoId}`,
        method: 'post'
      }
    )

  },
  //current 当前页，limit每页上限，producerQuery条件对象
  getVideoListPage(current,limit,videoQuery) {
    // console.log(current,"current123")
    return request({
      url: `/service/video/pageVideoCondition/${current}/${limit}`,
      method: 'post',
      //data表示把对象转化为json传递到接口里
      data: videoQuery
    })
  },
  //通过影视id删除
  removeVideoByID(id){
    return request({
        url:`/service/video/delete/${id}`,
        method:"delete"
      }

    )
  },
}
