<script setup lang="ts">
import {onMounted, reactive, ref} from "vue";
import {useRoute} from "vue-router";
import {getTalentDetailUsingPost} from "@/api/talentController.ts";
import html2canvas from 'html2canvas'
import JsPDF from 'jspdf'
import { QuestionCircleOutlined } from '@ant-design/icons-vue';
import {
  JOB_STATUS_ENU,
  MARRIAGE_STATUS_ENU,
  RESULT_STATUS,
  RESULT_STATUS_ENU,
  SEX,
  SEX_STATUS_ENU
} from "../../constant/constant.ts";
import {DateUtil} from "../../utils/DateUtil.ts";
import EditBaseInfo from "@/components/talent/EditBaseInfo.vue";
import EditPhoto from "@/components/talent/EditPhoto.vue";
import AddInteract from "@/components/talent/InteractAdd.vue";
import EditInteract from "@/components/talent/InteractEdit.vue";
import {deleteInteractUsingPost} from "@/api/interactController.ts";
import {message} from "ant-design-vue";
import InterviewAdd from "@/components/talent/InterviewAdd.vue";
import InterviewEdit from "@/components/talent/InterviewEdit.vue";
import ResumeAdd from "@/components/talent/ResumeAdd.vue";
import {deleteInterviewUsingPost} from "@/api/interviewController.ts";
import {deleteResumeUsingPost} from "@/api/resumeController.ts";
import TalentDel from "@/components/talent/TalentDel.vue";
import {generateQuestionsUsingPost} from "@/api/resumeRequestionController.ts";


const route = useRoute()
// 人才id
const talentId = ref(route.params.id)

// 人才信息
let talentInfo = reactive<API.TalentVO>({})
const loading = ref(true)

// 获取人才信息
const getTalentInfo = async () => {
  const res = await getTalentDetailUsingPost({
    talentId: talentId.value
  })
  if(res.data.code === 0) {
    talentInfo = Object.assign(talentInfo, res.data.data)
    loading.value = false
  }
}

// 编辑人才基本信息按钮
const editBaseInfoBtn = () => {
  editBaseInfoShow.value = true
}
const editBaseInfoShow = ref(false)
const editBaseInfoClose = () => {
  editBaseInfoShow.value = false
  getTalentInfo()
}

// 编辑头像信息
const editPhotoBtn = () => {
  editPhotoShow.value = true
}
const editPhotoShow = ref(false)
const editPhotoClose = () => {
  editPhotoShow.value = false
  getTalentInfo()
}


// 新增沟通
const addInteractBtn = () => {
  addInteractShow.value = true
}
const addInteractShow = ref(false)
const addInteractClose = () => {
  addInteractShow.value = false
  getTalentInfo()
}

// 编辑沟通记录
const editInteractId = ref(0)
const editInteractBtn = (id:  number) => {
  editInteractId.value = id
  editInteractShow.value = true
}
const editInteractShow = ref(false)
const editInteractClose = () => {
  editInteractShow.value = false
  getTalentInfo()
}

// 删除沟通记录
const deleteInteractBtn = async (interactId: number) => {
  const res = await deleteInteractUsingPost({
    interactId: interactId
  })
  if(res.data.code === 0) {
    message.success('删除成功')
    getTalentInfo()
  }else {
    message.error(res.data.message)
  }
}

// 新增面试记录
const addInterviewBtn = () => {
  addInterviewShow.value = true
}
const addInterviewShow = ref(false)
const addInterviewClose = () => {
  addInterviewShow.value = false
  getTalentInfo()
}


// 编辑沟通记录
const editInterviewId = ref(0)
const editInterviewBtn = (id:  number) => {
  editInterviewId.value = id
  editInterviewShow.value = true
}
const editInterviewShow = ref(false)
const editInterviewClose = () => {
  editInterviewShow.value = false
  getTalentInfo()
}
// 删除沟通记录
const deleteInterviewBtn = async (interviewId: number) => {
  const res = await deleteInterviewUsingPost({
    interviewId: interviewId
  })
  if(res.data.code === 0) {
    message.success('删除成功')
    getTalentInfo()
  }else {
    message.error(res.data.message)
  }
}

// 添加简历
const addResumeBtn = () => {
  addResumeShow.value = true
}
const addResumeShow = ref(false)
const addResumeClose = () => {
  addResumeShow.value = false
  getTalentInfo()
}

// 删除简历
const deleteResumeBtn = async (resumeId: number) => {
  const res = await deleteResumeUsingPost({
    resumeId: resumeId
  })
  if(res.data.code === 0) {
    message.success('删除成功')
    getTalentInfo()
  }else {
    message.error(res.data.message)
  }
}

// 删除该人才
const delTalentBtn = async () => {
  delTalentShow.value = true
}
const delTalentShow = ref(false)
const delTalentClose = () => {
  delTalentShow.value = false
  getTalentInfo()
}

// 生成面试题
const generateQuestionBtn = async (resumeId) => {
  const res = await generateQuestionsUsingPost({
    resumeId: resumeId
  })
  if (res.data.code === 0) {
    message.success('面试题正在生成中')
    getTalentInfo()
  }else {
    message.error(res.data.message)
  }
}

const exportImg = async () => {
  // 当下载pdf时，若不在页面顶部会造成PDF样式不对,所以先回到页面顶部再下载
  let top = document.getElementById("#canvasToPic")
  if (top != null) {
    top.scrollIntoView()
    top = null
  }
  html2canvas(document.querySelector("#canvasToPic"), {
    scale: 3, // 提高分辨率
    logging: true, // 启用日志
    allowTaint: true,
    useCORS: true
  }).then(function(canvas) {
    // 获取canvas画布的宽高
    let contentWidth = canvas.width
    let contentHeight = canvas.height
    // 一页pdf显示html页面生成的canvas高度;
    let pageHeight = contentWidth / 841.89 * 592.28
    // 未生成pdf的html页面高度
    let leftHeight = contentHeight
    // 页面偏移
    let position = 1
    // html页面生成的canvas在pdf中图片的宽高（本例为：横向a4纸[841.89,592.28]，纵向需调换尺寸）
    let imgWidth = 641.89
    let imgHeight = 841.89 / contentWidth * contentHeight
    let pageData = canvas.toDataURL('image/jpeg', 1.0)
    let PDF = new JsPDF('l', 'pt', 'a4')
    // 两个高度需要区分: 一个是html页面的实际高度，和生成pdf的页面高度
    // 当内容未超过pdf一页显示的范围，无需分页
    if ((leftHeight + 50) < pageHeight) {
      PDF.addImage(pageData, 'JPEG', 100, 50, imgWidth, imgHeight)
    } else {
      while (leftHeight > 0) {
        PDF.addImage(pageData, 'JPEG', 100, position, imgWidth, imgHeight)
        leftHeight -= pageHeight
        position -= 592.28
        // 避免添加空白页
        if (leftHeight > 0) {
          PDF.addPage()
        }
      }
    }
    PDF.save(talentInfo.name + '.pdf')
  })
}



onMounted(() => {
  getTalentInfo()
})


</script>

<template>

  <a-skeleton v-if="loading" />
  <template v-else >

    <!--  编辑人才基本信息-->
    <template>
      <div>
        <a-modal v-model:open="editBaseInfoShow" title="编辑人才基本信息" width="1000px" :footer="null" >
          <EditBaseInfo :id="talentInfo.talentId" @close="editBaseInfoClose" />
        </a-modal>
      </div>
    </template>

    <!--  删除人才-->
    <template>
      <div>
        <a-modal v-model:open="delTalentShow" title="删除人才" width="1000px" :footer="null" >
          <TalentDel :talentId="talentInfo.talentId" @close="delTalentClose" />
        </a-modal>
      </div>
    </template>

    <!--  编辑头像-->
    <template>
      <div>
        <a-modal v-model:open="editPhotoShow" title="编辑头像" width="1000px" :footer="null" >
          <EditPhoto :talentId="talentInfo.talentId" @close="editPhotoClose" />
        </a-modal>
      </div>
    </template>

    <a-divider>基本信息</a-divider>
    <a-row>
      <a-col :span="6" align="center">
        <a-button @click="editBaseInfoBtn">编辑基本信息</a-button>
      </a-col>
      <a-col :span="12" align="center">
        <a-button type="primary" @click="exportImg">导出为PDF</a-button>
      </a-col>
      <a-col :span="6" align="center">
        <a-button v-if="talentInfo.delStatus == 1" danger type="primary" @click="delTalentBtn">删除该人才</a-button>
        <a-button v-else danger type="primary" disabled>删除审核中</a-button>
      </a-col>
    </a-row>
    <hr>
    <div id="canvasToPic" ref="canvasToPic">
      <a-descriptions :title="'人才姓名：' + talentInfo.name" bordered>
        <a-descriptions-item label="个人照片">
          <a-image
              :width="100"
              :src="talentInfo.photo"
          />
          <a-button style="margin-left: 10px" @click="editPhotoBtn">更换照片</a-button>
        </a-descriptions-item>
        <a-descriptions-item label="面试岗位"><a-tag color="#2db7f5">{{talentInfo.post?.postName}}</a-tag></a-descriptions-item>
        <a-descriptions-item label="学历"> <a-tag color="#2db7f5">{{ talentInfo.education?.educationName }}</a-tag></a-descriptions-item>
        <a-descriptions-item label="联系方式"><a-tag color="#2db7f5">{{ talentInfo.phone }}</a-tag></a-descriptions-item>
        <a-descriptions-item label="性别"><a-tag color="#2db7f5">{{ SEX_STATUS_ENU[talentInfo.sex] }}</a-tag></a-descriptions-item>
        <a-descriptions-item label="出生年月日"><a-tag color="#2db7f5">{{ talentInfo.age }}</a-tag></a-descriptions-item>
        <a-descriptions-item label="婚育情况"><a-tag color="#2db7f5">{{ MARRIAGE_STATUS_ENU[talentInfo.marriage] }}</a-tag></a-descriptions-item>
        <a-descriptions-item label="当前薪资"><a-tag color="#2db7f5">{{ talentInfo.currentSalary }}</a-tag></a-descriptions-item>
        <a-descriptions-item label="期望薪资"><a-tag color="#2db7f5">{{ talentInfo.expectedSalary }}</a-tag></a-descriptions-item>
        <a-descriptions-item label="工作状态"><a-tag color="#2db7f5">{{ JOB_STATUS_ENU[talentInfo.jobStatus] }}</a-tag></a-descriptions-item>
        <a-descriptions-item label="工作年限"><a-tag color="#2db7f5">{{ talentInfo.jobYear }}年</a-tag></a-descriptions-item>
        <a-descriptions-item label="地址"><a-tag color="#2db7f5">{{ talentInfo.address }}</a-tag></a-descriptions-item>
        <a-descriptions-item label="目前岗位"><a-tag color="#2db7f5">{{ talentInfo.currentPost }}</a-tag></a-descriptions-item>
        <a-descriptions-item label="离职原因"><a-tag color="#2db7f5">{{ talentInfo.reasonForLeaving }}</a-tag></a-descriptions-item>
        <a-descriptions-item label="面试结果"><a-tag color="#2db7f5">{{ RESULT_STATUS_ENU[talentInfo.result] }}</a-tag></a-descriptions-item>
        <a-descriptions-item label="下次面试时间"><a-tag color="red">{{DateUtil.formatDate(talentInfo.nextInterviewTime)}}</a-tag></a-descriptions-item>
        <a-descriptions-item label="创建时间"><a-tag color="#2db7f5">{{ DateUtil.formatDate(talentInfo.createTime) }}</a-tag></a-descriptions-item>
        <a-descriptions-item label="编辑时间"><a-tag color="#2db7f5">{{ DateUtil.formatDate(talentInfo.editTime) }}</a-tag></a-descriptions-item>
        <a-descriptions-item label="优劣势">
          <b>优势:</b> {{ talentInfo.advantage }}
          <br />
          <br />
          <b>劣势:</b> {{ talentInfo.disadvantage }}

        </a-descriptions-item>
      </a-descriptions>
      <a-divider>沟通记录</a-divider>

      <!--  新增沟通-->
      <template>
        <div>
          <a-modal v-model:open="addInteractShow" title="新增沟通记录" width="1000px" :footer="null" >
            <AddInteract :talentId="talentInfo.talentId" @close="addInteractClose" />
          </a-modal>
        </div>
      </template>

      <!--  编辑沟通-->
      <template>
        <div>
          <a-modal v-model:open="editInteractShow" title="编辑沟通记录" width="1000px" :footer="null" >
            <EditInteract :key="editInteractId" :interactId="editInteractId" @close="editInteractClose" />
          </a-modal>
        </div>
      </template>

      <a-button type="primary" @click="addInteractBtn">新增沟通记录</a-button> <hr>
      <a-timeline>
        <a-timeline-item v-for="item in talentInfo.interactList" :key="item.interactId">
          沟通内容：{{item.interactContent}} <br>
          沟通人：{{item.interactStaff}} <br>
          沟通日期：{{DateUtil.formatDate(item.interactTime)}} <br>
          创建时间：{{DateUtil.formatDate(item.createTime) }}
          <br>
          <a-button @click="editInteractBtn(item.interactId)">编辑</a-button>&nbsp;&nbsp;
          <a-popconfirm title="是否删除该条记录？"
                        @confirm="deleteInteractBtn(item.interactId)"
          >
            <template #icon><question-circle-outlined style="color: red" /></template>
            <a-button type="primary" danger >删除</a-button>
          </a-popconfirm>
        </a-timeline-item>
      </a-timeline>
      <a-divider>面试记录</a-divider>
      <!--  新增面试-->
      <template>
        <div>
          <a-modal v-model:open="addInterviewShow" title="新增面试记录" width="1000px" :footer="null" >
            <InterviewAdd :talentId="talentInfo.talentId" @close="addInterviewClose" />
          </a-modal>
        </div>
      </template>

      <!--  编辑面试-->
      <template>
        <div>
          <a-modal v-model:open="editInterviewShow" title="编辑面试记录" width="1000px" :footer="null" >
            <InterviewEdit :key="editInterviewId" :interviewId="editInterviewId" @close="editInterviewClose" />
          </a-modal>
        </div>
      </template>

      <a-button type="primary" @click="addInterviewBtn">新增面试记录</a-button> <hr>
      <a-timeline>
        <a-timeline-item v-for="item in talentInfo.interviewList" :key="item.interviewId">
          面试评估：{{item.interviewAccess}} <br>
          面试官：{{item.interviewer}} <br>
          面试日期：{{DateUtil.formatDate(item.interviewTime)}} <br>
          创建时间：{{DateUtil.formatDate(item.createTime) }}
          <br>
          <a-button @click="editInterviewBtn(item.interviewId)">编辑</a-button>&nbsp;&nbsp;
          <a-popconfirm title="是否删除该条记录？"
                        @confirm="deleteInterviewBtn(item.interviewId)"
          >
            <template #icon><question-circle-outlined style="color: red" /></template>
            <a-button type="primary" danger>删除</a-button>
          </a-popconfirm>
        </a-timeline-item>
      </a-timeline>
      <a-divider>简历信息</a-divider>

      <!--  添加简历-->
      <template>
        <div>
          <a-modal v-model:open="addResumeShow" title="添加简历" width="1000px" :footer="null" >
            <ResumeAdd :talentId="talentInfo.talentId" @close="addResumeClose" />
          </a-modal>
        </div>
      </template>
      <a-button type="primary" @click="addResumeBtn">新增简历</a-button> <hr>
      <div class="resume">
        <div class="resume-item" v-for="item in talentInfo.resumeList">
          <a-space :size="10">
            <a-popconfirm title="是否删除该条记录？"
                          @confirm="deleteResumeBtn(item.resumeId)"
            >
              <template #icon><question-circle-outlined style="color: red" /></template>
              <a-button type="primary" danger >删除</a-button>
            </a-popconfirm>
            <a-button type="dashed" @click="generateQuestionBtn(item.resumeId)">生成面试题</a-button>
          </a-space>
          <hr />
          <a-row>
            <a-col :span="12">
              <a-image
                  width="100%"
                  :src="item.resumeUrl"
              />
            </a-col>
            <a-col :span="12">
              <a-card  title="相关面试题" style="width: 100%">
                <div v-html="item.resumeRequestion?.content">
                </div>
              </a-card>
            </a-col>
          </a-row>


          <br><br>
        </div>
      </div>
    </div>
  </template>

</template>

<style scoped lang="scss">
.resume {
  display: flex;
  align-items: flex-start;
  flex-wrap: wrap;
}
</style>
