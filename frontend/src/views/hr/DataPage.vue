<script setup lang="ts">

</script>

<template>
  <a-row>
    <a-col :span="12">
      <!--  岗位人才数量-->
      <PostTalentCount />
    </a-col>
    <a-col :span="12">
      <!--  性别人才数量-->
      <SexTalentCount />
    </a-col>
  </a-row>

  <a-row>
    <a-col :span="12">
      <!--  学历人才数量-->
      <EducationTalentCount />
    </a-col>
    <a-col :span="12">
      <!--  近7日新增人才-->
      <NewTalentCount />
    </a-col>
  </a-row>


</template>

<style scoped>

</style>
