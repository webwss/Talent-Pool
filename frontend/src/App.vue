<script setup lang="ts">
import BasicLayout from "@/layouts/BasicLayout.vue";
import {useLoginStaffStore} from "@/stores/useLoginStaffStore.ts";
import LoginPage from "@/views/login/LoginPage.vue";

const loginStaffStore = useLoginStaffStore()


</script>

<template>
  <!--  登录-->
  <LoginPage  v-if="!loginStaffStore.loginStaff.staffId" />
  <div id="app" v-else>
    <a-watermark :content="loginStaffStore.loginStaff.name">
      <BasicLayout />
    </a-watermark>
  </div>
</template>

<style scoped>

</style>
