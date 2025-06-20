<script setup lang="ts">

import { Chart } from '@antv/g2';
import {onMounted, ref} from "vue";
import {sexTalentCountUsingPost} from "@/api/dataController.ts";
// 加载中
const loading = ref(true);
// 性别人才数
const sexTalentCount = ref([]);


// 获取性别人才数
const getSexTalentCount = async () => {
  const res = await sexTalentCountUsingPost();
  if (res.data.code === 0) {
    sexTalentCount.value = res.data.data;
    init();
    loading.value = false;
  }
}

const init = () => {
  const chart = new Chart({
    container: 'container2',
    autoFit: true,
  });

  chart.interval().data(sexTalentCount.value);
  chart.axis('x', { title: "性别" });
  chart.axis('y', { title: "人数" });
  chart.encode('x', 'name').encode('y', 'value');
  chart.encode('color', 'name');
  chart.render();
}

onMounted(()=>{
  getSexTalentCount()
})



</script>

<template>
  <h1>性别--人才数数量</h1>
  <a-spin v-if="loading" />
  <div id="container2"></div>
</template>

<style scoped>
#container2 {
  width: 100%;
  height: 320px;
}

</style>
