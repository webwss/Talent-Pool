// @ts-ignore
/* eslint-disable */
import request from "@/request";

/** addEducation POST /api/education/add */
export async function addEducationUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.addEducationUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/education/add", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** deleteEducation POST /api/education/delete */
export async function deleteEducationUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteEducationUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/education/delete", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** editEducation POST /api/education/edit */
export async function editEducationUsingPost(
  body: API.EducationEditDTO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/education/edit", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** getEducationList GET /api/education/list */
export async function getEducationListUsingGet(options?: {
  [key: string]: any;
}) {
  return request<API.BaseResponseListEducation_>("/api/education/list", {
    method: "GET",
    ...(options || {}),
  });
}
