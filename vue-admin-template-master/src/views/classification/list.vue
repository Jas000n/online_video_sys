<template>
  <div class="app-container">
    <el-input v-model="filterText" placeholder="Filter keyword" style="margin-bottom:30px;" />

    <el-tree
      ref="tree2"
      :data="data2"
      :props="defaultProps"
      :filter-node-method="filterNode"
      class="filter-tree"
      default-expand-all
    />

  </div>
</template>

<script>
import classificationApi from "@/api/classification.js"
export default {

  data() {
    return {
      filterText: '',
      data2: [],
      defaultProps: {
        children: 'children',
        label: 'title'
      }
    }
  },
  watch: {
    filterText(val) {
      this.$refs.tree2.filter(val)
    }
  },
  created() {
    this.getAllClassification();
  },
  methods: {
    getAllClassification(){
      classificationApi.getClassificationList()
        .then(response => {
          this.data2 = response.data.list;
          console.log(response.data.list)
        })
      console.log(this.data2)


    },
    filterNode(value, data) {
      if (!value) return true
      return data.title.toLowerCase().indexOf(value) !== -1
    }
  }
}
</script>
