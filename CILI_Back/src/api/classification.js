import request from '@/utils/request'

//获得影视分类
export default {

  getClassificationList() {
    return request({
      url: '/service/classification/getAllClassification/',
      method: 'get',
    })
  }

}
