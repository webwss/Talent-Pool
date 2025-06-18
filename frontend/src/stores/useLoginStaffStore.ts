import { defineStore } from "pinia";
import { ref } from "vue";
import {checkTokenUsingPost} from "@/api/loginController.ts";

export const useLoginStaffStore = defineStore("loginStaff", () => {
  //    登录用户信息
  const loginStaff = ref<API.Staff>({
    name: "",
  });


  //    获取登录用户信息
  async function fetchLoginStaff() {
    const res = await checkTokenUsingPost();
    if (res.data.code === 0 && res.data.data) {
      loginStaff.value = res.data.data;
    }
  }

  //    设置登录用户信息
  function setLoginStaff(newLoginStaff: any) {
    loginStaff.value = newLoginStaff;
  }

  return { loginStaff, setLoginStaff, fetchLoginStaff };
});
