// @ts-ignore
/* eslint-disable */
import request from "@/request";

/** generateQuestions POST /api/resumeRequestion/generateQuestions */
export async function generateQuestionsUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.generateQuestionsUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>(
    "/api/resumeRequestion/generateQuestions",
    {
      method: "POST",
      params: {
        ...params,
      },
      ...(options || {}),
    }
  );
}

/** getQuestions POST /api/resumeRequestion/getQuestions */
export async function getQuestionsUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getQuestionsUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseResumeRequestion_>(
    "/api/resumeRequestion/getQuestions",
    {
      method: "POST",
      params: {
        ...params,
      },
      ...(options || {}),
    }
  );
}
