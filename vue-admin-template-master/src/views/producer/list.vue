<template>
  <div>
    <div class = "app-container">
      创作者列表
    </div>
    <el-table
      :data="list"
      :default-sort = "{prop: 'id', order: 'ascending'}"
      style="width: 100%">
      <el-table-column
        prop="id"
        label="ID"
        sortable
        width="180"/>
      <el-table-column
        prop="name"
        label="姓名"

        width="180"/>
      <el-table-column
        prop="intro"
        label="个人资料"
        width="180"/>
      <el-table-column
        prop="gmtCreate"
        label="注册时间"
        sortable
        width="180"/>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button
            size="mini"
            >编辑</el-button>
          <el-button
            size="mini"
            type="danger"
            >删除</el-button>
        </template>
      </el-table-column>

    </el-table>
    <div class="block">
      <span class="demonstration">完整功能</span>
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="page"
        :page-sizes="[1, 2, 3, 4]"
        :page-size="limit"
        layout="total, sizes, prev, pager, next, jumper"
        :total="this.total">
      </el-pagination>
    </div>
  </div>

</template>

<script>
import producer from '@/api/producer'
export default {
  data() {
    return {
      list: null, // 查询之后接口返回的数据
      page: 1, // 当前页
      limit: 3, // 每页记录数
      total: 0, // 总记录数
      producerQuery: {}
    }
  },
  // 页面渲染之前执行
  created() {
    this.getList()
  },
  methods: {
    // 获取创作者列表
    getList(page =1,limit=3) {
      this.page = page
      this.limit = limit
      producer.getProducerListPage(this.page, this.limit, this.producerQuery)
        .then(response => {
          // console.log(response)
          this.list = response.data.rows
          this.total = response.data.total
          console.log(this.list)
        })// 请求成功
        .catch(error => { console.log(error) })// 请求失败
    },
    //当分页size变化时触发
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.limit = val;
      this.getList(this.page,this.limit);
    },
    //当分页页数发生变化时触发
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.page = val;
      this.getList(this.page,this.limit);
    }

  }
}
</script>
