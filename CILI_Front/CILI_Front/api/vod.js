import request from '@/utils/requests'



export default {
  getPlayURL(v_source_id) {
    return request({
      url: `/vod/video/getPlayURL/${v_source_id}`,
      method: 'get'

    })
  }
}
