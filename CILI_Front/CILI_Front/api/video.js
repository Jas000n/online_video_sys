import request from '@/utils/requests'
export default {
  //前台查询创作者列表
  getVideoFrontList(page,limit,searchObj) {
    return request({
      url: `service/videoFront/getFrontVideoList/${page}/${limit}`,
      method: 'post',
      data:searchObj
    })
  },
  //查询所有分类
  getAllClassification() {
    return request({
      url: `service/classification/getAllClassification`,
      method: 'get'
    })
  },
  // //获取影视详情页需要的数据
  // getVideoInfo(id){
  //   return request({
  //     url: `/service/videoFront/getFrontVideoInfo/${id}`,
  //     method: 'get'
  //   })
  // },
  //获取影视详情页需要的数据,包含有没有购买的信息
  getVideoInfo(id){
    return request({
      url: `/service/videoFront/getVideoInfo/${id}`,
      method: 'get',
      headers:{
        'token':localStorage.getItem("cili_token")
      }
    })
  },
  //根据推荐系统生成的影视id, 获取影视的详细信息
  getRecVideoInfo(list){
    return request({
      url: `service/videoFront/getRecVideoList`,
      method: 'post',
      data:list
    })
  },


}
