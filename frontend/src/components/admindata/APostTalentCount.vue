<script setup lang="ts">

import { Chart } from '@antv/g2';
import {onMounted, ref} from "vue";
import {postTalentCountUsingPost, totalPostTalentCountUsingGet} from "@/api/dataController.ts";
// 加载中
const loading = ref(true);

// 不同岗位人才数
const postTalentCount = ref([]);

// 获取不同岗位人才数
const getPostTalentCount = async () => {
  const res = await totalPostTalentCountUsingGet();
  if (res.data.code === 0) {
    postTalentCount.value = res.data.data;
    init()
    loading.value = false;
  }
}

const init = () => {
  const chart = new Chart({
    container: 'container1',
    autoFit: true,
  });
  chart.interval().data(postTalentCount.value);
  chart.axis('x', { title: "岗位名称" });
  chart.axis('y', { title: "人数" });
  chart.encode('x', 'name').encode('y', 'value');
  chart.encode('color', 'name');


  chart.render();
}

onMounted(()=>{
  getPostTalentCount()
})



</script>

<template>
  <h1>岗位--人才数数量</h1>
  <a-spin v-if="loading" />
  <div id="container1"></div>
</template>

<style scoped>
#container1 {
  width: 100%;
  height: 320px;
}

</style>
