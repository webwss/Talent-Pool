// @ts-ignore
/* eslint-disable */
import request from "@/request";

/** deleteStaff POST /api/staff/delete */
export async function deleteStaffUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteStaffUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/staff/delete", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getStaffDetail POST /api/staff/detail */
export async function getStaffDetailUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getStaffDetailUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseStaff_>("/api/staff/detail", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** editStaff POST /api/staff/edit */
export async function editStaffUsingPost(
  body: API.StaffEditDTO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/staff/edit", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** getList GET /api/staff/list */
export async function getListUsingGet1(options?: { [key: string]: any }) {
  return request<API.BaseResponseListStaff_>("/api/staff/list", {
    method: "GET",
    ...(options || {}),
  });
}
