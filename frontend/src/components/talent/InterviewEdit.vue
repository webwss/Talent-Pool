<script setup lang="ts">

import {onMounted, reactive} from "vue";
import {message} from "ant-design-vue";
import {editInterviewUsingPost, getInterviewDetailUsingPost} from "@/api/interviewController.ts";
import dayjs, {type Dayjs} from "dayjs";
const labelCol = { style: { width: '150px' } };
const wrapperCol = { span: 14 };
const props = defineProps({
  interviewId: Number
})
const emit = defineEmits(['close'])

let interviewInfo = reactive<API.Interview>({
  talentId: 0,
  interviewTime:'',
  interviewAccess:'',
  interviewer:''
})

const onChange = (value: Dayjs, dateString: string) => {
  interviewInfo.interviewTime = value
};

// 获取面试详情
const getInterviewDetail = async () => {
  const res = await getInterviewDetailUsingPost({
    interviewId: props.interviewId
  })
  if(res.data.code === 0) {
    interviewInfo = Object.assign(interviewInfo, res.data.data)
    interviewInfo.interviewTime = dayjs(interviewInfo.interviewTime)
  }
}

const onSubmit = async () => {
  if(interviewInfo.interviewTime === '' || interviewInfo.interviewAccess === '' || interviewInfo.interviewer === '') {
    message.error('请填写完整信息')
    return
  }
  const res = await editInterviewUsingPost(interviewInfo)
  if (res.data.code == 0) {
    message.success("编辑成功")
    emit('close')
  }else {
    message.error(res.data.message)
  }
}

onMounted(()=>{
  getInterviewDetail()
})

</script>

<template>
  <a-form :model="interviewInfo" :label-col="labelCol" :wrapper-col="wrapperCol">
    <a-row>
      <a-col :span="24">
        <a-form-item label="面试记录ID" name="interactId" required>
          <a-input v-model:value="interviewInfo.interviewId" disabled />
        </a-form-item>
      </a-col>
    </a-row>
    <a-row>
      <a-col :span="24">
        <a-form-item label="面试官" name="interviewer" required>
          <a-input v-model:value="interviewInfo.interviewer" />
        </a-form-item>
      </a-col>
    </a-row>
    <a-row>
      <a-col :span="24">
        <a-form-item label="面试时间" name="interviewTime" required>
          <a-date-picker show-time placeholder="选择沟通时间" @change="onChange" v-model:value="interviewInfo.interviewTime" />
        </a-form-item>
      </a-col>
    </a-row>
    <a-row>
      <a-col :span="24">
        <a-form-item label="面试评估" name="interviewAccess" required>
          <a-textarea  v-model:value="interviewInfo.interviewAccess" />
        </a-form-item>
      </a-col>
    </a-row>



    <div style="margin-top: 30px"></div>
    <a-row>
      <a-col :span="12" style="text-align: right">
        <a-button type="primary" @click="onSubmit" >编辑</a-button>
      </a-col>

    </a-row>

  </a-form>
</template>

<style scoped>

</style>
