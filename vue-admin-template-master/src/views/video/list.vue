<template>
  <div>
    <div class = "app-container">
      影视列表
    </div>
    <el-form :inline="true" :model="videoQuery" class="demo-form-inline">
      <el-form-item label="姓名">
        <el-input v-model="videoQuery.name" placeholder="模糊姓名查询"></el-input>
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="videoQuery.begin"
          type="datetime"
          placeholder="请选择注册开始时间"
          value-format="yyyy-MM-dd HH:mm:ss"
          default-time="00:00:00"

        />
      </el-form-item>
      <el-form-item>
        <el-date-picker
          v-model="videoQuery.end"
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
        prop="title"
        label="名称"
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

          <router-link :to="'/video/info'">
            <el-button
              size="mini"
            >编辑影视基本信息
            </el-button>
          </router-link>
          <router-link :to="'/video/info'">
            <el-button
              size="mini"
            >编辑影视大纲信息
            </el-button>
          </router-link>

          <el-button
            size="mini"
            type="danger"
            @click="removeVideoByID(scope.row.id,scope.row.title)"
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
import videoApi from '@/api/video'
export default {
  data() {
    return {
      list: null, // 查询之后接口返回的数据
      page: 1, // 当前页
      limit: 10, // 每页记录数
      total: 0, // 总记录数
      videoQuery: {}
    }
  },
  // 页面渲染之前执行
  created() {
    this.getList()
  },
  methods: {
    // 获取影视列表
    getList(page =1,limit=10) {
      this.page = page
      this.limit = limit
      videoApi.getVideoListPage(this.page, this.limit, this.videoQuery)
        .then(response => {
          console.log(response)
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
      this.videoQuery = {}
      this.getList()
    },
    //删除影视
    removeVideoByID(id,title){
      console.log(id);
      this.$confirm('此操作将永久删除该影视:'+title+', 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        videoApi.removeVideoByID(id)
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
