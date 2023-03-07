<template>
    <div>
        <div class = "app-container">
        创作者列表
        </div>
        <h1>{{ total }}</h1>
    </div>
    
</template>

<script>
import producer from '@/api/producer'
export default {
    data(){
        return{
            list:null,//查询之后接口返回的数据
            page:1,//当前页
            limit:5,//每页记录数
            total:0,//总记录数
            producerQuery:{}
        }
    },
    //页面渲染之前执行
    created(){
       this.getList()
    },
    methods:{
        //获取创作者列表
        getList(){
            producer.getProducerListPage(this.page,this.limit,this.producerQuery)
                    .then(response =>{
                        // console.log(response)
                        this.list = response.data.rows;
                        this.total = response.data.total;
                        console.log(this.list);
                    })//请求成功
                    .catch(error =>{console.log(error)})//请求失败

        }
    },
    
}
</script>