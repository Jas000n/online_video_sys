<template>
  <div>
    <div class = "app-container">
      创作者列表
    </div>
    <el-form :inline="true" :model="producerQuery" class="demo-form-inline">
      <el-form-item label="姓名">
        <el-input v-model="producerQuery.name" placeholder="模糊姓名查询"></el-input>
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="producerQuery.begin"
          type="datetime"
          placeholder="请选择注册开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"

        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="producerQuery.end"
          type="datetime"
          placeholder="请选择注册结束时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"

        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="getList()">查询</el-button>
        <el-button type="default" @click="reset()">清空</el-button>
      </el-form-item>

    </el-form>


    <el-table
      :data="list"
      :default-sort = "{prop: 'gmtCreate', order: 'descending'}"
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
          <router-link :to="'/producer/edit/' +scope.row.id">
            <el-button
              size="mini"
            >编辑
            </el-button>
          </router-link>

          <el-button
            size="mini"
            type="danger"
            @click="removeProducerByID(scope.row.id,scope.row.name)"
          >删除
          </el-button>
        </template>
      </el-table-column>

    </el-table>
    <div class="block">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="page"
        :page-sizes="[5, 10, 15, 20]"
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
      limit: 10, // 每页记录数
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
    getList(page =1,limit=10) {
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
    },
    //重置数据
    reset(){
      console.log("reset!!!")
      this.producerQuery = {}
      this.getList()
    },
  //删除创作者
    removeProducerByID(id,name){
      console.log(id);
      this.$confirm('此操作将永久删除该创作者:'+name+', 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        producer.removeProducerByID(id)
          .then(response =>{
            //删除成功
            this.getList();
            this.$message({
              type: 'success',
              message: '删除成功!'
            });
          })
          .catch(error =>{
            this.$message({
              type: 'error',
              message: '删除失败!'
            });
          })
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        });
      });


    },

  }
}
</script>
