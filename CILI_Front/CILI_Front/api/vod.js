import request from '@/utils/requests'



export default {
  getPlayAuth(vid) {
    return request({
      url: `${api_name}/get-play-auth/${vid}`,
      method: 'get'

    })
  }
}
