// @ts-ignore
/* eslint-disable */
import request from "@/request";

/** addTalent POST /api/talent/add */
export async function addTalentUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.addTalentUsingPOSTParams,
  body: {
    /** resume */
    resume?: any[];
  },
  photo?: File,
  options?: { [key: string]: any }
) {
  const formData = new FormData();

  if (photo) {
    formData.append("photo", photo);
  }

  Object.keys(body).forEach((ele) => {
    const item = (body as any)[ele];

    if (item !== undefined && item !== null) {
      if (typeof item === "object" && !(item instanceof File)) {
        if (item instanceof Array) {
          item.forEach((f) => formData.append(ele, f || ""));
        } else {
          formData.append(ele, JSON.stringify(item));
        }
      } else {
        formData.append(ele, item);
      }
    }
  });

  return request<API.BaseResponseBoolean_>("/api/talent/add", {
    method: "POST",
    params: {
      ...params,
    },
    data: formData,
    requestType: "form",
    ...(options || {}),
  });
}

/** addTalentBatch POST /api/talent/addBatch */
export async function addTalentBatchUsingPost(
  body: {},
  file?: File,
  options?: { [key: string]: any }
) {
  const formData = new FormData();

  if (file) {
    formData.append("file", file);
  }

  Object.keys(body).forEach((ele) => {
    const item = (body as any)[ele];

    if (item !== undefined && item !== null) {
      if (typeof item === "object" && !(item instanceof File)) {
        if (item instanceof Array) {
          item.forEach((f) => formData.append(ele, f || ""));
        } else {
          formData.append(ele, JSON.stringify(item));
        }
      } else {
        formData.append(ele, item);
      }
    }
  });

  return request<API.BaseResponseBoolean_>("/api/talent/addBatch", {
    method: "POST",
    data: formData,
    requestType: "form",
    ...(options || {}),
  });
}

/** agreeDelete POST /api/talent/agreeDelete */
export async function agreeDeleteUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.agreeDeleteUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/talent/agreeDelete", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** deleteTalent POST /api/talent/delete */
export async function deleteTalentUsingPost(
  body: API.TalentDeleteDTO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/talent/delete", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** getTalentDetail POST /api/talent/detail */
export async function getTalentDetailUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.getTalentDetailUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseTalentVO_>("/api/talent/detail", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}

/** editBaseInfo POST /api/talent/editBaseInfo */
export async function editBaseInfoUsingPost(
  body: API.TalentEditBaseInfoDTO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/talent/editBaseInfo", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** editPhoto POST /api/talent/editPhoto */
export async function editPhotoUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.editPhotoUsingPOSTParams,
  body: {},
  photo?: File,
  options?: { [key: string]: any }
) {
  const formData = new FormData();

  if (photo) {
    formData.append("photo", photo);
  }

  Object.keys(body).forEach((ele) => {
    const item = (body as any)[ele];

    if (item !== undefined && item !== null) {
      if (typeof item === "object" && !(item instanceof File)) {
        if (item instanceof Array) {
          item.forEach((f) => formData.append(ele, f || ""));
        } else {
          formData.append(ele, JSON.stringify(item));
        }
      } else {
        formData.append(ele, item);
      }
    }
  });

  return request<API.BaseResponseBoolean_>("/api/talent/editPhoto", {
    method: "POST",
    params: {
      ...params,
    },
    data: formData,
    requestType: "form",
    ...(options || {}),
  });
}

/** getDeleteList POST /api/talent/getDeleteList */
export async function getDeleteListUsingPost(
  body: API.TalentRequestDTO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponsePageTalent_>("/api/talent/getDeleteList", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** getList POST /api/talent/list */
export async function getListUsingPost1(
  body: API.TalentRequestDTO,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponsePageTalent_>("/api/talent/list", {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    data: body,
    ...(options || {}),
  });
}

/** refuseDelete POST /api/talent/refuseDelete */
export async function refuseDeleteUsingPost(
  // 叠加生成的Param类型 (非body参数swagger默认没有生成对象)
  params: API.refuseDeleteUsingPOSTParams,
  options?: { [key: string]: any }
) {
  return request<API.BaseResponseBoolean_>("/api/talent/refuseDelete", {
    method: "POST",
    params: {
      ...params,
    },
    ...(options || {}),
  });
}
