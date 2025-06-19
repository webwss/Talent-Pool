import router from '@/router'
import {useLoginStaffStore} from "@/stores/useLoginStaffStore.ts";

// 是否为首次获取登录用户
let firstFetchLoginStaff = true;

/**
 * 全局权限校验
 */
router.beforeEach(async (to, from, next) => {
  const loginStaffStore = useLoginStaffStore()
  let loginStaff = loginStaffStore.loginStaff
  // 确保页面刷新，首次加载时，能够等后端返回用户信息后再校验权限
  if (firstFetchLoginStaff) {
    await loginStaffStore.fetchLoginStaff()
    loginStaff = loginStaffStore.loginStaff
    firstFetchLoginStaff = false;
  }
  const toUrl = to.fullPath
  if (toUrl.startsWith('/admin')) {
    if (!loginStaff || loginStaff.role !== 'admin') {
      alert("无权限")
      next(`/login?redirect=${to.fullPath}`)
      return
    }
  }
  next()
})
