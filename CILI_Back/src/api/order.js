import request from '@/utils/request'


export default {
  //获取全部订单
  getAll() {
    return request({
      url: `order/order/getAll`,
      method: 'get'
    })
  },
  //创作者列表（条件查询分页）
  //current 当前页，limit每页上限，orderQuery条件对象
  getOrderListPage(current,limit,orderQuery) {
     // console.log(current,"current123")
    return request({
      url: `/order/order/pageOrderCondition/${current}/${limit}`,
      method: 'post',
      //data表示把对象转化为json传递到接口里
      data: orderQuery
    })
  },
  //删除订单
  removeOrderByID(id){
    return request({
        url:`/order/order/delete/${id}`,
        method:"delete"
      }

    )
  },


}
