<script setup lang="ts">

import {onMounted, ref} from "vue";
import {getListUsingGet1} from "@/api/staffController.ts";
import dayjs from "dayjs";
import {DateUtil} from "../../utils/DateUtil.ts";
import PostAdd from "@/components/post/PostAdd.vue";
import StaffEdit from "@/components/staff/StaffEdit.vue";

const columns  = [
  {
    title: '员工ID',
    dataIndex: 'staffId',
    key: 'staffId',
    scopedSlots: { customRender: 'staffId' },
  },
  {
    title: '姓名',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: '用户企微ID',
    dataIndex: 'wxUserId',
    key: 'wxUserId',
  },
  {
    title: '手机号',
    dataIndex: 'phone',
    key: 'phone',
  },
  {
    title: '角色',
    dataIndex: 'role',
    key: 'role',
  },
  {
    title: '创建时间',
    dataIndex: 'createTime',
    key: 'createTime',
  },
  {
    title: '操作',
    key: 'action',
  },
]

const loading = ref(true)

const staffList = ref([])

const getStaffList = async () => {
  const res = await getListUsingGet1()
  if(res.data.code === 0) {
    staffList.value = res.data.data
    loading.value = false
  }
}

// 编辑用户
const editStaffId = ref(0)
const editStaffBtn = (id: Number) => {
  editStaffId.value = id
  editStaffShow.value = true
}
const editStaffShow = ref(false)
const editStaffClose = () => {
  editStaffShow.value = false
  getStaffList()
}

onMounted(()=>{
  getStaffList()
})

</script>

<template>

  <a-skeleton v-if="loading" />
  <!--  编辑用户-->
  <template v-else>
    <!--新增岗位-->
    <template>
      <a-modal v-model:open="editStaffShow" title="编辑用户" :footer="null">
        <StaffEdit :staffId="editStaffId"  @close="editStaffClose" :key="editStaffId" />
      </a-modal>
    </template>

    <a-table :columns="columns" :data-source="staffList">
      <template #bodyCell="{ column, record }">
        <template v-if="column.dataIndex == 'createTime'">
          {{ DateUtil.formatDate(record.createTime) }}
        </template>
        <template v-else-if="column.key === 'action'">
          <a-button type="primary" @click="editStaffBtn(record.staffId)">编辑</a-button>
        </template>

      </template>
    </a-table>

    <a-empty v-if="staffList.length === 0" description="暂无数据" />
  </template>



</template>

<style scoped>

</style>
