<script setup lang="ts">
import {reactive} from "vue";
import {Dayjs} from "dayjs";
import {addInterviewUsingPost} from "@/api/interviewController.ts";
import {message} from "ant-design-vue";
const labelCol = { style: { width: '150px' } };
const wrapperCol = { span: 14 };
const emit = defineEmits(['close'])
const props = defineProps({
  talentId: Number
})
const interViewInfo = reactive<API.InterviewAddDTO>({
  talentId: 0,
  interviewTime:'',
  interviewAccess:'',
  interviewer:''
})
interViewInfo.talentId = props.talentId

const onChange = (value: Dayjs, dateString: string) => {
  interViewInfo.interviewTime = value
};

const onSubmit = async () => {
  if(interViewInfo.interviewTime === '' || interViewInfo.interviewAccess === '' || interViewInfo.interviewer === '') {
    message.error('请填写完整信息')
    return
  }
  const res = await addInterviewUsingPost(interViewInfo)
  if (res.data.code === 0) {
    message.success("添加成功")
    emit('close')
  } else {
    message.error(res.data.message)
  }
}

</script>

<template>
  <a-form :model="interViewInfo" :label-col="labelCol" :wrapper-col="wrapperCol">
    <a-row>
      <a-col :span="24">
        <a-form-item label="人才ID" name="talentId" required>
          <a-input v-model:value="interViewInfo.talentId" disabled />
        </a-form-item>
      </a-col>
    </a-row>
    <a-row>
      <a-col :span="24">
        <a-form-item label="面试官" name="interviewer" required>
          <a-input v-model:value="interViewInfo.interviewer" />
        </a-form-item>
      </a-col>
    </a-row>
    <a-row>
      <a-col :span="24">
        <a-form-item label="面试时间" name="interviewTime" required>
          <a-date-picker show-time placeholder="选择沟通时间" @change="onChange" />
        </a-form-item>
      </a-col>
    </a-row>
    <a-row>
      <a-col :span="24">
        <a-form-item label="面试评估" name="interviewAccess" required>
          <a-textarea  v-model:value="interViewInfo.interviewAccess" />
        </a-form-item>
      </a-col>
    </a-row>



    <div style="margin-top: 30px"></div>
    <a-row>
      <a-col :span="12" style="text-align: right">
        <a-button type="primary" @click="onSubmit" >添加面试记录</a-button>
      </a-col>

    </a-row>

  </a-form>
</template>

<style scoped>

</style>
