<script setup lang="ts">

import { Chart } from '@antv/g2';
import {onMounted, ref} from "vue";
import {educationTalentCountUsingPost, totalEducationTalentCountUsingGet} from "@/api/dataController.ts";
// 加载中
const loading = ref(true);

// 学历人才数
const educationTalentCount = ref([]);

// 获取学历人才数
const getEducationTalentCount = async () => {
  const res = await totalEducationTalentCountUsingGet();
  if (res.data.code === 0) {
    educationTalentCount.value = res.data.data;
    init()
    loading.value = false;
  }
}

const init = () => {
  const chart = new Chart({
    container: 'container3',
    autoFit: true,
  });

  chart.interval().data(educationTalentCount.value);
  chart.axis('x', { title: "学历" });
  chart.axis('y', { title: "人数" });
  chart.encode('x', 'name').encode('y', 'value');
  chart.encode('color', 'name');
  chart.render();
}

onMounted(()=>{

  getEducationTalentCount()

})



</script>

<template>
  <h1>学历--人才数量</h1>
  <a-spin v-if="loading" />
  <div id="container3"></div>
</template>

<style scoped>
#container3 {
  width: 100%;
  height: 320px;
}

</style>
