import request from '@/utils/request'

export default{


    //1. 创作者列表（条件查询分页）
    //current 当前页，limit每页上限，producerQuery条件对象
    getProducerListPage(current,limit,producerQuery) {
      // console.log(current,"current123")
        return request({
      url: `/proservice/producer/pageProducerCondition/${current}/${limit}`,
      method: 'post',
      //data表示把对象转化为json传递到接口里
      data: producerQuery
    })
  }


}
