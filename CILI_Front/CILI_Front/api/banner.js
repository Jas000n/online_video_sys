import request from '@/utils/requests'
export default {
  //查询banner信息
  getBanners() {
    return request({
      url: `/cms/frontBannner/getAll`,
      method: 'get'
    })
  }
}
