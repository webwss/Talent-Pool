<script setup lang="ts">


import {ref} from "vue";
import {addResumesUsingPost} from "@/api/resumeController.ts";
import {message} from "ant-design-vue";

const props = defineProps<{
  talentId: Number
}>()
const emit = defineEmits(['close'])

const resume = ref([])

const submit = async () => {
  if(resume.value.length === 0) {
    message.error('请上传简历')
    return
  }

  const formData = new FormData()
  formData.append('resume', resume.value[0].file)
  const res = await addResumesUsingPost({
        talentId: props.talentId
      },
      formData)
  if(res.data.code === 0) {
    message.success('新增成功')
    emit('close')
  }else {
    message.error('新增失败')
  }

}

</script>

<template>
  <a-form-item label="简历" name="photo" required>
    <van-uploader v-model="resume" max-count="1" />
  </a-form-item>
  <a-button type="primary" @click="submit">新增简历</a-button>
</template>

<style scoped>

</style>
