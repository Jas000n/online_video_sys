<template>
  <div>
  <el-table
    :data="tableData"
    border
    style="width: 100%">
    <el-table-column
      fixed
      prop="id"
      label="id"
      width="150">
    </el-table-column>
    <el-table-column
      prop="title"
      label="名称"
      width="120">
    </el-table-column>
    <el-table-column
      label="图片"
      width="120">
      <!-- 图片的显示 -->
      <template   slot-scope="scope">
        <img :src="scope.row.imageUrl"  min-width="70" height="70" />
      </template>
    </el-table-column>
    <el-table-column
      prop="linkUrl"
      label="跳转链接"
      width="120">
    </el-table-column>
    <el-table-column
      prop="sort"
      label="排序"
      width="120">
    </el-table-column>
    <el-table-column
      prop="gmtCreate"
      label="创建时间"
      width="120">
    </el-table-column>
    <el-table-column
      prop="gmtModified"
      label="修改时间"
      width="120">
    </el-table-column>
    <el-table-column
      fixed="right"
      label="操作"
      width="100">
      <template slot-scope="scope">
        <el-button @click="edit(scope.row)" type="text" size="small">编辑</el-button>
        <el-button type="text" size="small" @click="removeBannerByID(scope.row.id,scope.row.title)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>

<!--    //弹出式表格-->
  <el-dialog title="轮播图修改" :visible.sync="dialogFormVisible">
    <el-form :model="formData">
      <el-form-item label="跳转链接" >
        <el-input v-model="formData.linkUrl" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="轮播图链接" :label-width=400>
        <el-input v-model="formData.imageUrl" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="轮播图名称" :label-width=400>
        <el-input v-model="formData.title" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item label="轮播图排序" :label-width=400>
        <el-input v-model="formData.sort" autocomplete="off"></el-input>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">取 消</el-button>
      <el-button type="primary" @click="updateBanner">确 定</el-button>
    </div>
  </el-dialog>
  </div>
</template>

<script>
import cmsApi from '@/api/cms'
export default {
  methods: {
    //更新banner
    updateBanner(){
      this.dialogFormVisible = false
      cmsApi.updateBanner(this.formData)
        .then(result => {
          console.log(result)
        })
    },
    //修改banner
    edit(row) {
      console.log(row);
      this.formData =  row
      this.dialogFormVisible = true
    },
    //初始化数据
    initTableData(){
      cmsApi.getAll()
        .then(result => {
          console.log(result)
          this.tableData = result.data.banners
        })
    },
    //删除banner
    removeBannerByID(id,title){
      console.log(id);
      this.$confirm('此操作将永久删除该轮播图:'+title+', 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        cmsApi.removeBannerByID(id)
          .then(response =>{
            //删除成功
            this.initTableData()
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
  },
  created() {
    this.initTableData()
  },
  data() {
    return {
      dialogFormVisible:false,
      tableData: [],
      formData:[],
    }
  }
}
</script>
