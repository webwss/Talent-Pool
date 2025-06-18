// @ts-ignore
/* eslint-disable */
import request from "@/request";

/** addPost POST /api/post/add */
export async function addPostUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.addPostUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/post/add", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** deletePost POST /api/post/delete */
export async function deletePostUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deletePostUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/post/delete", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** editPost POST /api/post/edit */
export async function editPostUsingPost(
  body: API.PostEditDTO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/post/edit", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** getPost GET /api/post/get */
export async function getPostUsingGet(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getPostUsingGETParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponsePost_>("/api/post/get", {
    method: "GET",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getList GET /api/post/list */
export async function getListUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseListPost_>("/api/post/list", {
    method: "GET",
    ...(options || {}),
  });
}
