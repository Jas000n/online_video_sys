import request from '@/utils/requests'
export default {
  //收藏影视
  likeVideo(id) {
    return request({
      url: `/rec/like/${id}`,
      method: 'get'

    })
  },
  //取消收藏影视
  dislikeVideo(id) {
    return request({
      url: `/rec/dislike/${id}`,
      method: 'get'

    })
  },
  //获取收藏状态
  getLikeState(id) {
    return request({
      url: `/rec/getLikeState/${id}`,
      method: 'get'

    })
  }
}
