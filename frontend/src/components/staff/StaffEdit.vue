<script setup lang="ts">

import {onMounted, reactive} from "vue";
import {editStaffUsingPost, getStaffDetailUsingPost} from "@/api/staffController.ts";
import {message} from "ant-design-vue";
const labelCol = { style: { width: '150px' } };
const wrapperCol = { span: 14 };
const props = defineProps({
  staffId: Number
})
const emit = defineEmits(['close'])


let staffInfo = reactive<API.Staff>({
  staffId: 0,
  wxUserId: '',
  name: '',
  phone: '',
  role: '',
})

const getStaffDetail = async () => {
  const res = await getStaffDetailUsingPost ({
    staffId: props.staffId
  })
  if(res.data.code === 0) {
    staffInfo = Object.assign(staffInfo, res.data.data)
  }
}

const onSubmit = async () => {
  if(staffInfo.role === '' || staffInfo.name === '' || staffInfo.phone === '' || staffInfo.wxUserId === '' || staffInfo.staffId === 0) {
    message.error('请填写完整信息')
    return
  }
  console.log(staffInfo)
  const res = await editStaffUsingPost(staffInfo)
  if(res.data.code === 0) {
    message.success('编辑成功')
    emit('close')
  }else {
    message.error(res.data.message)
  }
}

const onClose = () => {
  emit('close')
}

onMounted(()=>{
  getStaffDetail()
})

</script>

<template>
  <a-form :model="staffInfo" :label-col="labelCol" :wrapper-col="wrapperCol">
    <a-form-item label="员工ID" required>
      <a-input v-model:value="staffInfo.staffId" disabled />
    </a-form-item>
    <a-form-item label="员工企微ID" required>
      <a-input v-model:value="staffInfo.wxUserId" disabled />
    </a-form-item>
    <a-form-item label="姓名" required>
      <a-input v-model:value="staffInfo.name" />
    </a-form-item>
    <a-form-item label="手机号" required>
      <a-input v-model:value="staffInfo.phone" />
    </a-form-item>
    <a-form-item label="角色" required>
      <a-select
          ref="select"
          v-model:value="staffInfo.role"
          style="width: 120px"
      >
        <a-select-option value="hr">hr</a-select-option>
        <a-select-option value="admin">admin</a-select-option>
      </a-select>
    </a-form-item>

    <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button type="primary" @click="onSubmit">编辑</a-button>
      <a-button style="margin-left: 10px" @click="onClose">取消</a-button>
    </a-form-item>
  </a-form>
</template>

<style scoped>

</style>
