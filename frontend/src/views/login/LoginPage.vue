<template>
  <div ref="weChatCodeLogin" class="weChatCodeLogin"></div>
</template>

<script setup>
import * as ww from '@wecom/jssdk';
import {onMounted, ref} from "vue";
import { wxLoginUsingPost } from "@/api/loginController.js";
import {useRouter} from "vue-router";
import {useLoginStaffStore} from "@/stores/useLoginStaffStore.js";

const router = useRouter()
const loginStaffStore = useLoginStaffStore()


const weChatCodeLogin = ref(null);

onMounted(()=>{
  // 初始化登录组件
  const wwLogin = ww.createWWLoginPanel({
    el: weChatCodeLogin.value,
    params: {
      login_type: 'CorpApp',
      appid: 'xxx', // 替换为实际的CorpApp的appid
      agentid: 'xxx', // 替换为实际的CorpApp的agentid
      redirect_uri: 'xxx', // 替换为实际的回调地址
      state: 'loginState', // 登录状态
      redirect_type: 'callback', // 回调类型
    },
    onCheckWeComLogin({ isWeComLogin }) {
      console.log(isWeComLogin)
    },
    onLoginSuccess({ code }) {
      console.log({ code })
      handleLoginCallback(code)
    },
    onLoginFail(err) {
      console.log(err)
    },
  })

})


const handleLoginCallback = async (code) => {
  try {
    const res = await wxLoginUsingPost({ code });
    if (res.data.code === 0) {
      await loginStaffStore.fetchLoginStaff()
      router.push({
        path: '/',
        replace: true
      })
    }
  } catch (error) {
    console.error('登录失败：', error);
  }
};





</script>

<style scoped>
.weChatCodeLogin {
  display: flex;
  justify-content: center;
}
</style>
