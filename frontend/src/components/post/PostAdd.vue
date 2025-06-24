<script setup lang="ts">

import {addPostUsingPost} from "@/api/postController.ts";
import {message} from "ant-design-vue";
import {reactive, ref} from "vue";
const labelCol = { style: { width: '150px' } };
const wrapperCol = { span: 14 };

const emit = defineEmits(['close'])


const addPostInfo = reactive({
  postName: ''
})

const onSubmit = async () => {
  if(addPostInfo.postName === '') {
    message.error('请输入岗位名称')
    return
  }
  const res = await addPostUsingPost({
    postName: addPostInfo.postName
  })
  if (res.data.code === 0) {
    message.success('新增成功')
    emit('close')
  }
}

const onClose = () => {
  emit('close')
}

</script>

<template>
  <a-form :model="addPostInfo" :label-col="labelCol" :wrapper-col="wrapperCol">
    <a-form-item label="岗位名称" required>
      <a-input v-model:value="addPostInfo.postName" />
    </a-form-item>

    <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button type="primary" @click="onSubmit">新增</a-button>
      <a-button style="margin-left: 10px" @click="onClose">取消</a-button>
    </a-form-item>
  </a-form>
</template>

<style scoped>

</style>
