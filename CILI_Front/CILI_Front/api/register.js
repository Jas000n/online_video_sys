import request from '@/utils/requests'
export default {
  //根据手机号码发送短信
  getMobile(mobile) {
    return request({
      url: `/msm/send/${mobile}`,
      method: 'get'

    })
  },
  //用户注册
  submitRegister(formItem) {
    return request({
      url: `/ucenter/register`,
      method: 'post',
      data: formItem
    })
  }
}
