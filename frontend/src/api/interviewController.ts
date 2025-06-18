// @ts-ignore
/* eslint-disable */
import request from "@/request";

/** addInterview POST /api/interview/add */
export async function addInterviewUsingPost(
  body: API.InterviewAddDTO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/interview/add", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** deleteInterview POST /api/interview/delete */
export async function deleteInterviewUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.deleteInterviewUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/interview/delete", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** getInterviewDetail POST /api/interview/detail */
export async function getInterviewDetailUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getInterviewDetailUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseInterview_>("/api/interview/detail", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** editInterview POST /api/interview/edit */
export async function editInterviewUsingPost(
  body: API.InterviewEditDTO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/interview/edit", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** getListByTalentId POST /api/interview/list */
export async function getListByTalentIdUsingPost1(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getListByTalentIdUsingPOST1Params,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseListInterview_>("/api/interview/list", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}
