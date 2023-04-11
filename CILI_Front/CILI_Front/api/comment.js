import request from '@/utils/requests'

export default {
  //分页查询评论
  getPageList(episodeId,current, limit) {
    return request({
      url: `/service/comment/getComments/${episodeId}/${current}/${limit}`,
      method: 'get',
      // params: {videoId}
    })
  },
  //新增评论
  addComment(comment) {
    return request({
      url: `/service/comment/addComment`,
      method: 'post',
      data: comment
    })
  }
}
