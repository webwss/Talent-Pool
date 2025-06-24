<script setup lang="ts">

import {totalTalentCountUsingGet} from "@/api/dataController.ts";
import {onMounted, ref} from "vue";

const count = ref(0)
const getCount = async () => {
  const res = await totalTalentCountUsingGet()
  if(res.data.code === 0) {
    count.value = res.data.data
  }
}

onMounted((()=>{
  getCount()
}))


</script>

<template>

  <!--  系统总人才数量-->
  <a-row>
    <a-col :span="24">
      <a-statistic title="系统总人才数" :value="count" style="text-align: center;" />
    </a-col>
  </a-row>
  <hr>

  <a-row>
    <a-col :span="12">
      <!--  岗位人才数量-->
      <APostTalentCount />
    </a-col>
    <a-col :span="12">
      <!--  性别人才数量-->
      <ASexTalentCount />
    </a-col>
  </a-row>

  <a-row>
    <a-col :span="12">
      <!--  学历人才数量-->
      <AEducationTalentCount />
    </a-col>
    <a-col :span="12">
      <!--  近7日新增人才-->
      <ANewTalentCount />
    </a-col>
  </a-row>




</template>

<style scoped>

</style>
