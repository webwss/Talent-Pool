// @ts-ignore
/* eslint-disable */
import request from "@/request";

/** addResume POST /api/resume/add */
export async function addResumeUsingPost(
  body: API.ResumeAddDTO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/resume/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** addResumes POST /api/resume/addResume */
export async function addResumesUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.addResumesUsingPOSTParams,
  body: {
    /** resume */
    resume: any[];
  },
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/resume/addResume", {
    method: "POST",
    headers: {
      "Content-Type": "application/x-www-form-urlencoded",
    },
    params: {
      ...params,
    },
    data: body,
    ...(options || {}),
  });
}

/** deleteResume POST /api/resume/delete */
export async function deleteResumeUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteResumeUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/resume/delete", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getList POST /api/resume/list */
export async function getListUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getListUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseListResume_>("/api/resume/list", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}
