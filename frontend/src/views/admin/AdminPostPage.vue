<script setup lang="ts">
import {onMounted, ref} from "vue";
import {deletePostUsingPost, getListUsingGet} from "@/api/postController.ts";
import PostAdd from "@/components/post/PostAdd.vue";
import {message} from "ant-design-vue";

const columns  = [
  {
    title: '岗位ID',
    dataIndex: 'postId',
    key: 'postId',
    scopedSlots: { customRender: 'postId' },
  },
  {
    title: '岗位名称',
    dataIndex: 'postName',
    key: 'postName',
  },
  {
    title: '操作',
    key: 'action',
  },
]

const postList = ref([])
const loading = ref(true)
const getPostList = async() => {
  const res = await getListUsingGet()
  if(res.data.code === 0) {
    postList.value = res.data.data
    loading.value = false
  }else {
    message.error(res.data.message)
  }
}


// 新增岗位
const addPostBtn = () => {
  addPostShow.value = true
}
const addPostShow = ref(false)
const addPostClose = () => {
  addPostShow.value = false
  getPostList()
}

// 删除岗位
const delPostBtn = async (id:Number) => {
  const res = await deletePostUsingPost({
    postId: id
  })
  if  (res.data.code == 0) {
    message.success('删除成功')
    getPostList()
  } else {
    message.error(res.data.message)
  }
}


onMounted(()=>{
  getPostList()
})

</script>

<template>
  <a-skeleton v-if="loading" />
  <template v-else>
    <!--新增岗位-->
    <template>
      <a-modal v-model:open="addPostShow" title="新增岗位" :footer="null">
        <PostAdd  @close="addPostClose" />
      </a-modal>
    </template>

    <!--  新增岗位-->
    <a-button type="primary" @click="addPostBtn">新增岗位</a-button>
    <a-table :columns="columns" :data-source="postList">
      <template #bodyCell="{ column, record }">
        <template v-if="column.key === 'action'">
          <a-space size="middle">
            <a-popconfirm
                title="是否删除该岗位"
                ok-text="是"
                cancel-text="否"
                @confirm="delPostBtn(record.postId)"
            >
              <a-button type="primary" danger>删除</a-button>
            </a-popconfirm>
          </a-space>
        </template>
      </template>
    </a-table>

    <a-empty v-if="postList.length === 0" description="暂无数据" />
  </template>





</template>



<style scoped>

</style>
