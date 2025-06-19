<script lang="ts" setup>
import {ref, h, computed} from 'vue';
import { UserOutlined, UserAddOutlined, FundOutlined ,PieChartOutlined,ApartmentOutlined,TeamOutlined,SendOutlined,DeploymentUnitOutlined} from '@ant-design/icons-vue';
import GlobalHeader from "@/components/GlobalHeader.vue";
import type {MenuProps} from "ant-design-vue";
import {useLoginStaffStore} from "@/stores/useLoginStaffStore.ts";
import router from "@/router";

const loginStaffStore =  useLoginStaffStore();

const current = ref<string[]>(['home'])

const onCollapse = (collapsed: boolean, type: string) => {
  console.log(collapsed, type);
};

const onBreakpoint = (broken: boolean) => {
  console.log(broken);
};

// 导航菜单
const originItems = [
  {
    key: '/',
    icon: h(PieChartOutlined),
    label: '数据看板（HR）',
  },
  {
    key: '/talent',
    icon: h(UserOutlined),
    label: '人才库',
  },
  {
    key: '/talent/add',
    icon: h(UserAddOutlined),
    label: '新增人才',
  },
  {
    key: '/admin/data',
    icon: h(FundOutlined),
    label: '数据看板（管理员）',
  },
  {
    key: '/admin/post',
    icon: h(ApartmentOutlined),
    label: '岗位管理（管理员）',
  },
  {
    key: '/admin/staff',
    icon: h(TeamOutlined),
    label: '员工管理（管理员）',
  },
  {
    key: '/admin/del',
    icon: h(SendOutlined),
    label: '删除审批（管理员）',
  },
];

// 过滤菜单项
const filterMenus = (menus = [] as MenuProps['items']) => {
  return menus?.filter((menu) => {
    if (menu?.key?.startsWith('/admin')) {
      const loginStaff = loginStaffStore.loginStaff
      if (!loginStaff || loginStaff.role !== "admin") {
        return false
      }
    }
    return true
  })
}

// 展示在菜单的路由数组
const items = computed<MenuProps['items']>(() => filterMenus(originItems))

const selectedKeys = ref<string[]>(['1']);

// 监听路由变化，更新当前选中菜单
router.afterEach((to, from, next) => {
  current.value = [to.path];
});

// 路由跳转事件
const doMenuClick = ({key}: {key: string}) => {
  router.push({
    path: key
  })
}

</script>

<template>
  <a-layout style="min-height: 100vh;">
    <a-layout-sider
        breakpoint="lg"
        collapsed-width="0"
        @collapse="onCollapse"
        @breakpoint="onBreakpoint"
    >
      <div class="logo">
<!--        <img src="https://webwss.cn/usr/uploads/2025/02/1567932281.png"  alt="logo"/>-->
      </div>
      <a-menu
          v-model:selectedKeys="current"
          :items="items"
          @click="doMenuClick"
      />
    </a-layout-sider>
    <a-layout>
      <a-layout-header :style="{ background: '#fff', padding: 0 }">
        <GlobalHeader />
      </a-layout-header>
      <a-layout-content class="content">
          <router-view  />
      </a-layout-content>
      <a-layout-footer class="footer" style="text-align: center">
        爽爽学编程  Copyright © 2025
      </a-layout-footer>
    </a-layout>
  </a-layout>
</template>



<style scoped lang="scss">

.ant-layout-sider {
  background-color: #ffffff;
}

.logo {
  width: 200px;
  padding: 10px;
  img {
    width: 100%;
  }
}

.content {
  margin: 16px;
  padding: 14px;
  background: #fbfbfb;
  max-height: 84vh;
  overflow-y: auto;
}

.footer {
  background: #ffffff;
  padding: 16px;
  position: fixed;
  bottom: 0;
  left: 0;
  right: 0;
  text-align: center;
}

</style>
