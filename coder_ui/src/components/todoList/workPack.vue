<template>
  <div>
    <el-input :rows="1" type="textarea" v-model="value" placeholder="packName..." @keyup.enter.native="handleEnter"></el-input>


<!--    <el-tree :data="dataList" :props="defaultProps" @node-click="showPackTodoList"></el-tree>-->

    <p v-for="item in dataList" :key="item.id" class="packName" style="display: flex; align-content: center">
      <el-progress
              :stroke-width="5"
              :width="20"
              type="circle"
              :percentage="item.progress"
              :show-text="false"
              :status="item.progress == 100 ? 'success' : null"
      ></el-progress>

      <span @dblclick="showPackDetail(item)" @click="showPackTodoList(item)">{{ item.name }}</span>
    </p>

    <TodoDrawer
            ref="todoDrawerRef"
            @updateSuccess="updateSuccess"
    ></TodoDrawer>
  </div>
</template>

<script>
  import TodoDrawer from "./todoDrawer";

  import {addWorkPack, getList} from "../../api/todoList/workPackAPI";
  import {textTrim} from "../../utils/StrUtil";
  import {newMessage} from "../../utils/InfoUtil";
  // import {handleTree} from "../../utils/ListUtils";

  export default {
    name: "workPack",
    components:{
      TodoDrawer
    },
    data(){
      return{
        value: null,
        dataList: [],
        clickTimer: false,
        defaultProps: {
          children: 'children',
          label: 'name'
        }
      }
    },
    mounted() {
      this.getList();
    },
    methods:{
      // 获取工作包数据
      getList(){
        getList().then(res => {
          // this.dataList = handleTree(res.data.records, "id", "pid", null)
          this.dataList = res.data.records
          console.log(this.dataList)
        })
      },
      // 工作包输入框回车事件
      handleEnter(){
        if(this.value == null || textTrim(this.value, /[\r\n]/g) == ''){
          newMessage("warning", '内容为空，保存失败')
        }else{
          this.insertPack();
        }
        this.value = null;
      },
      // 新增工作包
      insertPack(){
        addWorkPack({
          name: this.value,
        }).then(() => {
          this.getList();
        })
      },
      handleNodeClick(data) {
        console.log(data);
      },
      // 单击展示工作包任务
      showPackTodoList(item){
        if (this.clickTimer) {
          window.clearTimeout(this.clickTimer);
          this.clickTimer = null;
        }
        this.clickTimer = window.setTimeout(function() {
          console.log("你单击了我");
        }, 300);
        this.$emit("showPackTodoList", item)
      },
      // 双击可修改工作包信息
      showPackDetail(item){
        if (this.clickTimer) {
          window.clearTimeout(this.clickTimer);
          this.clickTimer = null;
        }
        this.$refs.todoDrawerRef.handleOpen("workPack", item.id);
      },
      // 操作成功回调
      updateSuccess(){
        this.getList();
      }
    }
  }
</script>

<style scoped>

  .packName{
    cursor: pointer;
    margin-left: 1rem;
  }

</style>