<script setup lang="ts">

import {reactive} from "vue";
import {Dayjs} from "dayjs";
import {addInteractUsingPost} from "@/api/interactController.ts";
import {message} from "ant-design-vue";
const labelCol = { style: { width: '150px' } };
const wrapperCol = { span: 14 };
const props = defineProps<{
  talentId: Number;
}>();
const emit = defineEmits(['close'])

const interactInfo = reactive<API.InteractAddDTO>({
  talentId: 0,
  interactStaff: '',
  interactContent: '',
  interactTime: ''
});
interactInfo.talentId = props.talentId

const onChange = (value: Dayjs, dateString: string) => {
  interactInfo.interactTime = value
};

const onSubmit = async () => {
  if(interactInfo.interactStaff === '' || interactInfo.interactContent === '' || interactInfo.interactTime === '') {
    message.error("请填写完整信息")
    return
  }
  const res = await addInteractUsingPost(interactInfo)
  if(res.data.code === 0) {
    message.success("添加成功")
    emit('close')
  }else {
    message.error(res.data.message)
  }
}

</script>

<template>
  <a-form :model="interactInfo" :label-col="labelCol" :wrapper-col="wrapperCol">
    <a-row>
      <a-col :span="24">
        <a-form-item label="人才ID" name="talentId" required>
          <a-input v-model:value="interactInfo.talentId" disabled />
        </a-form-item>
      </a-col>
    </a-row>
    <a-row>
      <a-col :span="24">
        <a-form-item label="沟通人" name="interactStaff" required>
          <a-input v-model:value="interactInfo.interactStaff" />
        </a-form-item>
      </a-col>
    </a-row>
    <a-row>
      <a-col :span="24">
        <a-form-item label="沟通时间" name="interactTime" required>
          <a-date-picker show-time placeholder="选择沟通时间" @change="onChange" />
        </a-form-item>
      </a-col>
    </a-row>
    <a-row>
      <a-col :span="24">
        <a-form-item label="沟通内容" name="interactContent" required>
          <a-textarea  v-model:value="interactInfo.interactContent" />
        </a-form-item>
      </a-col>
    </a-row>



    <div style="margin-top: 30px"></div>
    <a-row>
      <a-col :span="12" style="text-align: right">
        <a-button type="primary" @click="onSubmit" >添加沟通记录</a-button>
      </a-col>

    </a-row>

  </a-form>
</template>

<style scoped>

</style>
