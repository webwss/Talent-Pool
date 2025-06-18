// @ts-ignore
/* eslint-disable */
import request from "@/request";

/** wxLogin POST /api/login */
export async function wxLoginUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.wxLoginUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseStaff_>("/api/login", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** checkToken POST /api/login/check */
export async function checkTokenUsingPost(options?: { [key: string]: any }) {
  return request<API.BaseResponseStaff_>("/api/login/check", {
    method: "POST",
    ...(options || {}),
  });
}

/** logout GET /api/login/logout */
export async function logoutUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseBoolean_>("/api/login/logout", {
    method: "GET",
    ...(options || {}),
  });
}
