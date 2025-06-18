// @ts-ignore
/* eslint-disable */
import request from "@/request";

/** addInteract POST /api/interact/add */
export async function addInteractUsingPost(
  body: API.InteractAddDTO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/interact/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** deleteInteract POST /api/interact/delete */
export async function deleteInteractUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteInteractUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/interact/delete", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getInteractDetail POST /api/interact/detail */
export async function getInteractDetailUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getInteractDetailUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseInteract_>("/api/interact/detail", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** editInteract POST /api/interact/edit */
export async function editInteractUsingPost(
  body: API.InteractEditDTO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/interact/edit", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** getListByTalentId POST /api/interact/list */
export async function getListByTalentIdUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getListByTalentIdUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseListInteract_>("/api/interact/list", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}
