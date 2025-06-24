<script setup lang="ts">


import {reactive} from "vue";
import {deleteTalentUsingPost} from "@/api/talentController.ts";
import {message} from "ant-design-vue";
const labelCol = { style: { width: '150px' } };
const wrapperCol = { span: 14 };
const props = defineProps({
  talentId: Number
})
const emit = defineEmits(['close'])
const talentDelInfo = reactive<API.TalentDeleteDTO>({})
talentDelInfo.talentId = props.talentId

const onSubmit = async () => {
  const res = await deleteTalentUsingPost(talentDelInfo)
  if(res.data.code === 0) {
    message.success('删除成功')
    emit('close')
  }else {
    message.error(res.data.message)
  }
}

const onClose = () => {
  emit('close')
}


</script>

<template>
  <a-form :model="talentDelInfo" :label-col="labelCol" :wrapper-col="wrapperCol">
    <a-form-item label="人才ID">
      <a-input v-model:value="props.talentId" disabled />
    </a-form-item>
    <a-form-item label="删除原因" required>
      <a-textarea v-model:value="talentDelInfo.delReason"  />
    </a-form-item>

    <a-form-item :wrapper-col="{ span: 14, offset: 4 }">
      <a-button type="primary" @click="onSubmit">提交删除申请</a-button>
      <a-button style="margin-left: 10px" @click="onClose">取消</a-button>
    </a-form-item>
  </a-form>
</template>

<style scoped>

</style>
