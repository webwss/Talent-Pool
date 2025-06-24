<script setup lang="ts">

import {onMounted, reactive, ref} from 'vue'
import {getListUsingGet} from "@/api/postController.ts";
import {getEducationListUsingGet} from "@/api/educationController.ts";
import {
  JOB_STATUS,
  MARRIAGE, RESULT_STATUS,
  SEX
} from "@/constant/constant.ts";
import {addTalentUsingPost} from "@/api/talentController.ts";
import {message} from "ant-design-vue";
import {useRouter} from "vue-router";
import TalentAddBatch from "@/components/talent/TalentAddBatch.vue";
import {uploadBathDataUsingGet} from "@/api/dataController.ts";

const router = useRouter()


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
// 新增人才信息
let talentAddInfo = reactive<API.addTalentUsingPOSTParams>({})
// 个人照片
const photo  = ref([])
// 简历列表
const resumeList = ref([])

// 提交按钮
const onSubmit = async () => {

  // 校验是否有必填项
  if(talentAddInfo.name === '' || talentAddInfo.postId === '' || talentAddInfo.educationId === '' || talentAddInfo.phone === '' || talentAddInfo.sex === '' || talentAddInfo.age === '' || talentAddInfo.marriage === '' || talentAddInfo.currentSalary === '' || talentAddInfo.expectedSalary ==='' || photo.value.length === 0) {
    message.error('请填写必填项');
    return
  }

  const resumes = resumeList?.value.map((item) => {
    return item.file
  })
  const res = await addTalentUsingPost(talentAddInfo, {
    resume: resumes,
  }, photo.value[0].file)
  console.log(res)
  if (res.data.code === 0) {
    message.success('新增人才成功');
    talentAddInfo = {}
    photo.value = []
    resumeList.value = []
    router.push({
      path: '/talent',
    })
  } else {
    message.error('新增人才失败')
  }
};


// 批量上传
const uploadResumeBtn = async () => {
  uploadResumeShow.value = true
};
const uploadResumeShow = ref(false)



// 下载批量上传参考文档
const downloadTemplate = ref([])
const open = ref(false)
const downloadTemplateBtn = async () => {
  const res = await uploadBathDataUsingGet();
  if(res.data.code === 0) {
    downloadTemplate.value = res.data.data
    open.value = true
  }else {
    message.error(res.data.message)
  }
}


onMounted(()=>{
  getPostList()
  getEducationList()
})


</script>

<template>
  <a-modal v-model:open="open" title="查看批量上传参考" :footer="null">
    <a-row>
      <a-col :span="12">
        <a-timeline>
          <a-timeline-item v-for="item in downloadTemplate.posts" :key="item.postId">职位：{{ item.postName }}==>值：{{item.postId}}</a-timeline-item>
        </a-timeline>
      </a-col>
      <a-col :span="12">
        <a-timeline>
          <a-timeline-item v-for="item in downloadTemplate.educations" :key="item.educationId">学历：{{ item.educationName }}==>值：{{item.educationId}}</a-timeline-item>
        </a-timeline>
        <a-timeline>
          <a-timeline-item v-for="item in downloadTemplate.sex" :key="item.value">性别：{{ item.name }}==>值：{{item.value}}</a-timeline-item>
        </a-timeline>
        <a-timeline>
          <a-timeline-item v-for="item in downloadTemplate.workStatus" :key="item.value">工作状态：{{ item.name }}==>值：{{item.value}}</a-timeline-item>
        </a-timeline>
        <a-timeline>
          <a-timeline-item v-for="item in downloadTemplate.maritalStatus" :key="item.value">婚育情况：{{ item.name }}==>值：{{item.value}}</a-timeline-item>
        </a-timeline>
      </a-col>
    </a-row>



  </a-modal>
  <a-form :model="talentAddInfo" :label-col="labelCol" :wrapper-col="wrapperCol">
    <a-row>
      <a-col :span="12">
        <a-form-item label="人才姓名" name="name" :rules="{ required: true, message: '请输入人才姓名'}">
          <a-input v-model:value="talentAddInfo.name" />
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="面试岗位" name="postId"  required>
          <a-select v-model:value="talentAddInfo.postId" placeholder="请选择面试岗位"  >
            <a-select-option v-for="item in postList" :value="item.postId">{{item.postName}}</a-select-option>
          </a-select>
        </a-form-item>
      </a-col>
    </a-row>

    <a-row>
      <a-col :span="12">
        <a-form-item label="学历" name="educationId" required>
          <a-select v-model:value="talentAddInfo.educationId" placeholder="请选择学历">
            <a-select-option v-for="item in educationList" :value="item.educationId">{{item.educationName}}</a-select-option>
          </a-select>
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="联系方式" name="phone" :rules="{ required: true, message: '请输入联系方式'}">
          <a-input v-model:value="talentAddInfo.phone"  />
        </a-form-item>
      </a-col>
    </a-row>

    <a-row>
      <a-col :span="12">
        <a-form-item label="性别" name="sex" :rules="{ required: true, message: '请选择性别'}">
          <a-radio-group v-model:value="talentAddInfo.sex">
            <a-radio v-for="item in SEX" :value="item.value" name="sex">{{item.label}}</a-radio>
          </a-radio-group>
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="出生年月日" name="age" :rules="{ required: true, message: '请输入出生年月日'}">
          <a-input v-model:value="talentAddInfo.age" />
        </a-form-item>
      </a-col>
    </a-row>

    <a-row>
      <a-col :span="12">
        <a-form-item label="婚育情况" name="marriage" :rules="{ required: true, message: '请选择婚育情况'}">
          <a-radio-group v-model:value="talentAddInfo.marriage">
            <a-radio v-for="item in MARRIAGE" :value="item.value" name="marriage">{{item.label}}</a-radio >
          </a-radio-group>
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="当前薪资" name="currentSalary" :rules="{ required: true, message: '请输入当前薪资'}">
          <a-input v-model:value="talentAddInfo.currentSalary" />
        </a-form-item>
      </a-col>
      <a-col :span="8">
      </a-col>
    </a-row>

    <a-row>
      <a-col :span="12">
        <a-form-item label="期望薪资" name="expectedSalary" :rules="{ required: true, message: '请输入期望薪资'}">
          <a-input v-model:value="talentAddInfo.expectedSalary" />
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="工作状态" name="jobStatus">
          <a-radio-group v-model:value="talentAddInfo.jobStatus">
            <a-radio v-for="item in JOB_STATUS" :value="item.value" name="jobStatus">{{item.label}}</a-radio>
          </a-radio-group>
        </a-form-item>
      </a-col>
    </a-row>


    <a-row>
      <a-col :span="12">
        <!--    个人照片-->
        <a-form-item label="个人照片" name="photo" required>
          <van-uploader v-model="photo" max-count="1" />
        </a-form-item>

      </a-col>
      <a-col :span="12">
        <!--    简历-->
        <a-form-item label="简历" name="resumeList">
          <van-uploader v-model="resumeList" max-count="4" />
        </a-form-item>
      </a-col>
    </a-row>

    <a-row>
      <a-col :span="12">
        <a-form-item label="工作年限（年）">
          <a-input v-model:value="talentAddInfo.jobYear" />
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="地址">
          <a-input v-model:value="talentAddInfo.address" />
        </a-form-item>
      </a-col>
    </a-row>




    <a-row>
      <a-col :span="12">
        <a-form-item label="目前岗位">
          <a-input v-model:value="talentAddInfo.currentPost" />
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="离职原因">
          <a-input v-model:value="talentAddInfo.reasonForLeaving" />
        </a-form-item>
      </a-col>
    </a-row>

    <a-row>
      <a-col :span="12">
        <a-form-item label="优势">
          <a-input v-model:value="talentAddInfo.advantage" />
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="劣势">
          <a-input v-model:value="talentAddInfo.disadvantage" />
        </a-form-item>
      </a-col>
    </a-row>



    <a-row>
      <a-col :span="12">
        <a-form-item label="面试结果" name="result">
          <a-radio-group v-model:value="talentAddInfo.result">
            <a-radio v-for="item in RESULT_STATUS" :value="item.value" name="result">{{item.label}}</a-radio>
          </a-radio-group>
        </a-form-item>
      </a-col>
      <a-col :span="12">
        <a-form-item label="下次面试时间">
          <a-input v-model:value="talentAddInfo.nextInterviewTime" />
        </a-form-item>
      </a-col>
    </a-row>


    <div style="margin-top: 30px"></div>
    <a-row>
      <a-col :span="12" style="text-align: right">
        <a-button type="primary" @click="onSubmit" >新增人才</a-button>
      </a-col>
      <a-col :span="12" style="text-align: center">
        <a-button @click="uploadResumeBtn">批量上传（仅支持xlsx格式文件）</a-button>
        <br>
        <br>
        <a-button @click="downloadTemplateBtn">查看批量上传参考</a-button>&nbsp;&nbsp;
        <a-button >
          <a>下载批量上传模板</a>
        </a-button>
      </a-col>
    </a-row>

  </a-form>

  <!--  批量上传-->
  <a-modal v-model:visible="uploadResumeShow" title="批量上传人才信息" :footer="null">
    <TalentAddBatch />
  </a-modal>

</template>

<style scoped>

</style>
