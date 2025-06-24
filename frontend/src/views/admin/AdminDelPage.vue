<script setup lang="ts">

import {onMounted, ref} from "vue";
import {
  agreeDeleteUsingPost,
  getDeleteListUsingPost,
  getListUsingPost1,
  refuseDeleteUsingPost
} from "@/api/talentController.ts";
import {useRouter} from "vue-router";
import {message} from "ant-design-vue";

const talentList = ref<API.Talent>()
const loading = ref(true)

// 分页参数
const total = ref(0);
const current = ref(1);
const pageSize = ref(8);

const getTalentList = async () => {
  const res = await getDeleteListUsingPost({
    current: current.value,
    pageSize: pageSize.value,
    deleteStatus: 2
  })
  if (res.data.code === 0) {
    talentList.value = res.data.data.records
    total.value = res.data.data.total
    loading.value = false
  }
}

// 翻页
const change = (value) => {
  current.value = value
  getTalentList()
}


const router = useRouter()

// 去详情
const goDetail = (talentId) => {
  router.push({
    path: '/talent/'+talentId
  })
}

// 同意删除
const agreeDelete = async (id) => {
  const res = await agreeDeleteUsingPost({
    talentId: id
  })
  if(res.data.code === 0) {
    message.success('删除成功')
    getTalentList()
  }else {
    message.error(res.data.message)
  }
}

// 拒绝删除
const refuseDelete = async (id) => {
  const res = await refuseDeleteUsingPost({
    talentId: id
  })
  if(res.data.code === 0) {
    message.success('拒绝成功')
    getTalentList()
  }
  else {
    message.error(res.data.message)
  }
}

onMounted(()=>{
  getTalentList()
})

</script>

<template>
  <a-skeleton v-if="loading" />
  <template  v-else>
    <div class="talent">
      <a-row v-for="item in talentList" :key="item.talentId" class="item">
        <a-col :span="10">
          <div class="top">
            <a-tag color="#2db7f5">面试岗位：全栈开发</a-tag>
          </div>
          <div class="center">
            <div class="left">
              <a-avatar :size="64" :src="item.photo"></a-avatar>
            </div>
            <div class="right">
              <div class="name">{{item.name}}</div>
              <div class="info">
                <a-tag class="t" color="#108ee9">本科</a-tag>
                <a-tag class="t" color="#108ee9">{{item.age}}岁</a-tag>
                <a-tag class="t" color="#108ee9">3年</a-tag>
                <a-tag class="t" color="#108ee9">昆山</a-tag>
              </div>
            </div>
          </div>
          <div class="bottom">
            <a-tag color="#f50">联系方式：{{item.phone}}</a-tag>
          </div>
        </a-col>

        <a-col :span="6">
          <div class="center-center">
            <b>删除原因：</b>{{item.delReason}}
          </div>
        </a-col>
        <a-col :span="8">
          <div class="right-right">
            <a-button class="bu" type="primary" @click="goDetail(item.talentId)">查看详情</a-button>
            <a-button class="bu"  type="primary" danger @click="agreeDelete(item.talentId)" >同意</a-button>
            <a-button class="bu" type="primary" ghost @click="refuseDelete(item.talentId)" >拒绝</a-button>

          </div>
        </a-col>
      </a-row>
    </div>

    <div class="pag">
      <a-pagination v-model:current="current" v-model:pageSize="pageSize" :total="total" @change="change" show-less-items />
      <br>
      <a-tag color="cyan">共 {{ total }} 条数据</a-tag>
    </div>
    <a-empty v-if="total === 0" description="暂无数据" />
  </template>



</template>

<style scoped lang="scss">

.talent {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-evenly;

  .item {
    width: 48%;
    padding: 10px;
    border-radius: 6px;
    margin-top: 10px;
    background-color: white;
    .top {
      margin-bottom: 10px;
    }
    .center {
      .name {
        padding-bottom: 4px;
        font-size: 18px;
      }
      display: flex;
      align-items: center;
      .right {
        margin-left: 20px;
        .info {
          .t {
            margin-top: 4px;
          }
        }
      }
    }
    .bottom {
      margin-top: 10px;
    }


    .center-center {
      width: 120px;
      display: flex;
      flex-direction: column;
      .tag {
        text-align: center;
        margin-top: 14px;
      }
    }

    .right-right {
      display: flex;
      margin-top: 10px;
      flex-wrap: wrap;
      flex-direction: column;
      align-content: space-around;
      justify-content: center;

      .bu {
        margin-top: 5px;
      }

    }


  }

}

.pag {
  padding-top: 20px;
  text-align: center;
}

</style>
