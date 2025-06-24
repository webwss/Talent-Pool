<script setup lang="ts">

import {onMounted, reactive} from "vue";
import dayjs, {Dayjs} from "dayjs";
import {editInteractUsingPost, getInteractDetailUsingPost} from "@/api/interactController.ts";
import {DateUtil} from "@/utils/DateUtil.ts";
import {message} from "ant-design-vue";
const labelCol = { style: { width: '150px' } };
const wrapperCol = { span: 14 };
const props = defineProps<{
  interactId: Number;
}>();
const emit = defineEmits(['close'])

let interactInfo = reactive<API.Interact>({
  talentId: 0,
  interactStaff: '',
  interactContent: '',
  interactTime: ''
});

const onChange = (value: Dayjs, dateString: string) => {
  interactInfo.interactTime = value
};

// 获取沟通详情
const getInteractDetail = async () => {
  const res = await getInteractDetailUsingPost({
    interactId: props.interactId
  })
  interactInfo = Object.assign(interactInfo, res.data.data)
  interactInfo.interactTime = dayjs(interactInfo.interactTime)
}

const onSubmit = async () => {
  if(interactInfo.interactStaff === '' || interactInfo.interactContent === '' || interactInfo.interactTime === '') {
    message.error("请填写完整信息")
    return
  }
  const res = await editInteractUsingPost(interactInfo)
  if (res.data.code == 0) {
    message.success("编辑成功")
    emit('close')
  }else {
    message.error(res.data.message)
  }
}

onMounted(()=>{
  getInteractDetail()
})

</script>

<template>
  <a-form :model="interactInfo" :label-col="labelCol" :wrapper-col="wrapperCol">
    <a-row>
      <a-col :span="24">
        <a-form-item label="沟通ID" name="interactId" required>
          <a-input v-model:value="interactInfo.interactId" disabled />
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
          <a-date-picker show-time placeholder="选择沟通时间" @change="onChange" v-model:value="interactInfo.interactTime" />
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
        <a-button type="primary" @click="onSubmit" >编辑</a-button>
      </a-col>

    </a-row>

  </a-form>
</template>

<style scoped>

</style>
