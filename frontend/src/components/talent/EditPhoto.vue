<script setup lang="ts">

import {ref} from "vue";
import {editPhotoUsingPost} from "@/api/talentController.ts";
import {message} from "ant-design-vue";

const props = defineProps<{
  talentId: Number;
}>();
const emit = defineEmits(['close'])

const photo  = ref([]);

const sumbit = async () => {
  if (photo.value.length === 0) {
    message.error('请上传图片')
    return
  }
  const res = await editPhotoUsingPost({
    talentId: props.talentId
  }, {
    photo: photo.value[0].file
  })
  if(res.data.code === 0) {
    message.success('编辑成功')
    emit('close')
  }else {
    message.error(res.data.message)
  }
}

</script>

<template>
  <a-form-item label="个人照片" name="photo" required>
    <van-uploader v-model="photo" max-count="1" />
  </a-form-item>
  <a-button type="primary" @click="sumbit">编辑头像</a-button>
</template>

<style scoped>

</style>
