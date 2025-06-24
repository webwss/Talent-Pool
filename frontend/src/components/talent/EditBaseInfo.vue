<script setup lang="ts">

import {onMounted, reactive, ref} from "vue";
import {editBaseInfoUsingPost, getTalentDetailUsingPost} from "@/api/talentController.ts";
import {JOB_STATUS, MARRIAGE, RESULT_STATUS, SEX} from "@/constant/constant.ts";
import {getListUsingGet} from "@/api/postController.ts";
import {getEducationListUsingGet} from "@/api/educationController.ts";
import {message} from "ant-design-vue";
import { Dayjs } from 'dayjs';
// 岗位列表
const postList = ref<API.Post>([])
// 学历列表
const educationList = ref<API.Education>([])


// 获取岗位列表
const getPostList = async () => {
  const res = await getListUsingGet()
  postList.value = res.data.data
}

// 获取学历列表
const getEducationList = async () => {
  const res = await getEducationListUsingGet()
  educationList.value = res.data.data
}

const labelCol = { style: { width: '150px' } };
const wrapperCol = { span: 14 };


const props = defineProps<{
  id: Number
}>()
const emit = defineEmits(['close'])

let talentInfo = reactive<API.TalentVO>({})
// 获取人才信息根据id
const getTalentInfo = async () => {
  const res = await getTalentDetailUsingPost({
    talentId: props.id
  })
  if(res.data.code === 0) {
    talentInfo = Object.assign(talentInfo,res.data.data)
  }
}

// 提交
const onSubmit = async () => {
  // 校验是否有必填项
  if(talentInfo.name === '' || talentInfo.postId === '' || talentInfo.educationId === '' || talentInfo.phone === '' || talentInfo.sex === '' || talentInfo.age === '' || talentInfo.marriage === '' || talentInfo.currentSalary === '' || talentInfo.expectedSalary ==='') {
    message.error('请填写必填项');
    return
  }
  const res = await editBaseInfoUsingPost(talentInfo)
  if(res.data.code === 0) {
    message.success('修改人才成功');
    emit('close', talentInfo)
  } else {
    message.error('修改人才失败')
  }
}

const onChange = (value: Dayjs, dateString: string) => {
  talentInfo.nextInterviewTime = value
};


onMounted(() => {
  getPostList()
  getEducationList()
  getTalentInfo()
})


</script>

<template>
  <a-form :model="talentInfo" :label-col="labelCol" :wrapper-col="wrapperCol">
    <a-row>
      <a-col :span="12">
        <a-form-item label="人才姓名" name="name" :rules="{ required: true, message: '请输入人才姓名'}">
          <a-input v-model:value="talentInfo.name" />
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="面试岗位" name="postId" required>
          <a-select v-model:value="talentInfo.postId" placeholder="请选择面试岗位">
            <a-select-option v-for="item in postList" :value="item.postId">{{item.postName}}</a-select-option>
          </a-select>
        </a-form-item>
      </a-col>
    </a-row>

    <a-row>
      <a-col :span="12">
        <a-form-item label="学历" name="educationId" required>
          <a-select v-model:value="talentInfo.educationId" placeholder="请选择学历">
            <a-select-option v-for="item in educationList" :value="item.educationId">{{item.educationName}}</a-select-option>
          </a-select>
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="联系方式" name="phone" :rules="{ required: true, message: '请输入联系方式'}">
          <a-input v-model:value="talentInfo.phone"  />
        </a-form-item>
      </a-col>
    </a-row>

    <a-row>
      <a-col :span="12">
        <a-form-item label="性别" name="sex" :rules="{ required: true, message: '请选择性别'}">
          <a-radio-group v-model:value="talentInfo.sex">
            <a-radio v-for="item in SEX" :value="item.value" name="sex">{{item.label}}</a-radio>
          </a-radio-group>
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="年龄" name="age" :rules="{ required: true, message: '请输入年龄'}">
          <a-input v-model:value="talentInfo.age" />
        </a-form-item>
      </a-col>
    </a-row>

    <a-row>
      <a-col :span="12">
        <a-form-item label="婚育情况" name="marriage" :rules="{ required: true, message: '请选择婚育情况'}">
          <a-radio-group v-model:value="talentInfo.marriage">
            <a-radio v-for="item in MARRIAGE" :value="item.value" name="marriage">{{item.label}}</a-radio >
          </a-radio-group>
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="当前薪资" name="currentSalary" :rules="{ required: true, message: '请输入当前薪资'}">
          <a-input v-model:value="talentInfo.currentSalary" />
        </a-form-item>
      </a-col>
      <a-col :span="8">
      </a-col>
    </a-row>

    <a-row>
      <a-col :span="12">
        <a-form-item label="期望薪资" name="expectedSalary" :rules="{ required: true, message: '请输入期望薪资'}">
          <a-input v-model:value="talentInfo.expectedSalary" />
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="工作状态" name="jobStatus">
          <a-radio-group v-model:value="talentInfo.jobStatus">
            <a-radio v-for="item in JOB_STATUS" :value="item.value" name="jobStatus">{{item.label}}</a-radio>
          </a-radio-group>
        </a-form-item>
      </a-col>
    </a-row>


    <a-row>
      <a-col :span="12">
        <a-form-item label="工作年限（年）">
          <a-input v-model:value="talentInfo.jobYear" />
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="地址">
          <a-input v-model:value="talentInfo.address" />
        </a-form-item>
      </a-col>
    </a-row>




    <a-row>
      <a-col :span="12">
        <a-form-item label="目前岗位">
          <a-input v-model:value="talentInfo.currentPost" />
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="离职原因">
          <a-input v-model:value="talentInfo.reasonForLeaving" />
        </a-form-item>
      </a-col>
    </a-row>

    <a-row>
      <a-col :span="12">
        <a-form-item label="优势">
          <a-input v-model:value="talentInfo.advantage" />
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="劣势">
          <a-input v-model:value="talentInfo.disadvantage" />
        </a-form-item>
      </a-col>
    </a-row>

    <a-row>
      <a-col :span="12">
        <a-form-item label="下次面试时间">
          <a-date-picker show-time placeholder="选择下次面试时间" @change="onChange" />
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="面试结果" name="result">
          <a-radio-group v-model:value="talentInfo.result">
            <a-radio v-for="item in RESULT_STATUS" :value="item.value" name="result">{{item.label}}</a-radio>
          </a-radio-group>
        </a-form-item>
      </a-col>
    </a-row>







    <div style="margin-top: 30px"></div>
    <a-row>
      <a-col :span="12" style="text-align: right">
        <a-button type="primary" @click="onSubmit" >编辑人才基本信息</a-button>
      </a-col>

    </a-row>

  </a-form>
</template>

<style scoped>

</style>
