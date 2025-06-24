<script lang="ts" setup>
import { ref } from 'vue';
import { UploadOutlined } from '@ant-design/icons-vue';
import { message } from 'ant-design-vue';
import type { UploadProps } from 'ant-design-vue';
import {addTalentBatchUsingPost} from "@/api/talentController.ts";
import {useRouter} from "vue-router";

const fileList = ref<UploadProps['fileList']>([]);
const uploading = ref<boolean>(false);

const router = useRouter()

const handleRemove: UploadProps['onRemove'] = file => {
  const index = fileList.value.indexOf(file);
  const newFileList = fileList.value.slice();
  newFileList.splice(index, 1);
  fileList.value = newFileList;
};

const beforeUpload: UploadProps['beforeUpload'] = file => {
  fileList.value = [...(fileList.value || []), file];
  return false;
};

const handleUpload = async () => {
  const res = await addTalentBatchUsingPost({
    file: fileList.value,
  })
  if(res.data.code === 0) {
    message.success('上传成功');
    router.push({
      path: '/talent',
    })
  }else {
    message.error(res.data.message)
  }

};
</script>

<template>
  <div class="clearfix">
    <a-upload :file-list="fileList" :before-upload="beforeUpload" @remove="handleRemove">
      <a-button>
        <upload-outlined></upload-outlined>
        Select File
      </a-button>
    </a-upload>
    <a-button
        type="primary"
        :disabled="fileList.length === 0"
        :loading="uploading"
        style="margin-top: 16px"
        @click="handleUpload"
    >
      {{ uploading ? '上传中...' : '开始上传' }}
    </a-button>
  </div>
</template>


