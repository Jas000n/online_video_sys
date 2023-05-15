import request from '@/utils/request'

//获得影视分类
export default {

  //获取全部banner
  getAll() {
    return request({
      url: '/cms/adminBannner/getAll',
      method: 'get',
    })
  },
  //修改banner
  updateBanner(banner){
    return request({
      url: `/cms/adminBannner/updateBanner`,
      method: 'post',
      data:banner
    })
  },
  //删除banner
  removeBannerByID(id) {
    return request({
      url: `/cms/adminBannner/removeById/${id}`,
      method: 'delete',
    })
  },

}
