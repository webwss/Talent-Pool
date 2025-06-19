<script setup lang="ts">

import {useLoginStaffStore} from "@/stores/useLoginStaffStore.ts";
import {logoutUsingGet} from "@/api/loginController.ts";
import {useRouter} from "vue-router";
const router = useRouter()

const loginStaffStore = useLoginStaffStore()


const doLogout = async () => {
  console.log("退出登录")
  const res = await logoutUsingGet()
  if (res.data.code == 0) {
    router.push({
      path: '/login'
    })
  }
}

</script>

<template>
  <a-row>
    <a-col flex="auto">
      <div class="title">企业人才库系统</div>
    </a-col>
    <a-col flex="120px">
      <div class="user-login-status">
        <div v-if="loginStaffStore.loginStaff.staffId">
          <a-dropdown>
            <ASpace>
              {{ loginStaffStore.loginStaff.name ?? '无名' }}
            </ASpace>
            <template #overlay>
              <a-menu>
                <a-menu-item @click="doLogout">
                  退出登录
                </a-menu-item>
              </a-menu>
            </template>
          </a-dropdown>
        </div>
        <div v-else>
          <a-button type="primary" href="/user/login">登录</a-button>
        </div>
      </div>
    </a-col>
  </a-row>
</template>

<style scoped>

.title {
  font-size: 22px;
  text-align: center;
  color: #cab474;
  font-weight: 600;
}

</style>
