<script setup lang="ts">

import { Chart } from '@antv/g2';
import {onMounted, ref} from "vue";
import {newTalentCountUsingPost} from "@/api/dataController.ts";

// 加载中
const loading = ref(true);
// 近7日新增人才趋势
const newTalentCount = ref([]);

// 获取近7日新增人才趋势
const getNewTalentCount = async () => {
  const res = await newTalentCountUsingPost();
  if(res.data.code === 0) {
    newTalentCount.value = res.data.data;
    init()
    loading.value = false
  }
}

const init = () => {
  const chart = new Chart({
    container: 'container4',
    autoFit: true,
  });

  chart
      .data(newTalentCount.value)
      .encode('x', 'name')
      .encode('y', 'value')
      .scale('x', {
        range: [0, 1],
      })
      .scale('y', {
        domainMin: 0,
        nice: true,
      });

  chart.line().label({
    text: 'value',
    style: {
      dx: -10,
      dy: -12,
    },
  });

  chart.axis('x', { title: "日期" });
  chart.axis('y', { title: "人数" });
  chart.point().style('fill', 'white').tooltip(false);

  chart.render();
}

onMounted(()=>{
  getNewTalentCount()
})



</script>

<template>
  <h1>近7日新增人才趋势</h1>
  <a-spin v-if="loading" />
  <div id="container4"></div>
</template>

<style scoped>
#container4 {
  width: 100%;
  height: 320px;
}

</style>
