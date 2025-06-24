<script setup lang="ts">

import {computed, onMounted, ref} from "vue";
import {getListUsingPost1} from "@/api/talentController.ts";
import {useRouter} from "vue-router";
import {getListUsingGet} from "@/api/postController.ts";

const talentList = ref<API.Talent>()
const loading = ref(true)
const postList = ref<API.Post>()

const getPostList = async () => {
  const res = await getListUsingGet()
  if(res.data.code === 0) {
    postList.value = res.data.data
  }
}

// 搜索字段
const searchText = ref('')
// 分页参数
const total = ref(0);
const current = ref(1);
const pageSize = ref(8);

const getTalentList = async () => {
  const res = await getListUsingPost1({
    current: current.value,
    pageSize: pageSize.value,
    name: searchText.value
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


// 触发搜索
const onSearch = async () => {
  current.value = 1
  getTalentList()
}

const router = useRouter()

// 去详情
const goDetail = (talentId) => {
  router.push({
    path: '/talent/'+talentId
  })
}



onMounted(()=>{
  getPostList()
  getTalentList()
})

</script>

<template>

  <a-skeleton v-if="loading" />
  <template v-else>
    <a-input-search
        v-model:value="searchText"
        placeholder="可搜索：姓名"
        enter-button
        @search="onSearch"
    />
    <a-empty v-if="total === 0" description="暂无数据" />
    <div class="talent">
      <a-row v-for="item in talentList" :key="item.talentId" class="item">
        <a-col :span="10">
          <div class="top">
            <a-tag color="#2db7f5">面试岗位：
              <span v-for="i in postList">
              <span  v-if="i.postId === item.postId">{{i.postName}}</span>
            </span>
            </a-tag>
          </div>
          <div class="center">
            <div class="left">
              <a-avatar :size="64" :src="item.photo"></a-avatar>
            </div>
            <div class="right">
              <div class="name">{{item.name}}</div>
              <div class="info">
                <a-tag class="t" color="#108ee9">本科</a-tag>
                <a-tag class="t" color="#108ee9">{{item.age}}</a-tag>
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
            <a-tag class="tag" >未婚未育</a-tag>
            <a-tag class="tag" >当前薪资：<b>{{item.currentSalary}}</b></a-tag>
            <a-tag class="tag">期望薪资：<b>{{item.expectedSalary}}</b></a-tag>
          </div>
        </a-col>
        <a-col :span="8">
          <div class="right-right">
            <a-button type="primary" @click="goDetail(item.talentId)">查看详情</a-button>

          </div>
        </a-col>
      </a-row>
    </div>

    <div class="pag">
      <a-pagination v-model:current="current" v-model:pageSize="pageSize" :total="total" @change="change" show-less-items />
      <br>
      <a-tag color="cyan">共 {{ total }} 条数据</a-tag>
    </div>

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
      justify-content: space-evenly;
      padding-top: 50px;
    }


  }

}

.pag {
  padding-top: 20px;
  text-align: center;
}

</style>
