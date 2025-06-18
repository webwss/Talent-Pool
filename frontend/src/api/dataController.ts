// @ts-ignore
/* eslint-disable */
import request from "@/request";

/** educationTalentCount POST /api/data/educationTalentCount */
export async function educationTalentCountUsingPost(options?: {
  [key: string]: any;
}) {
  return request<API.BaseResponseListAntVO_>("/api/data/educationTalentCount", {
    method: "POST",
    ...(options || {}),
  });
}

/** newTalentCount POST /api/data/newTalentCount */
export async function newTalentCountUsingPost(options?: {
  [key: string]: any;
}) {
  return request<API.BaseResponseListAntVO_>("/api/data/newTalentCount", {
    method: "POST",
    ...(options || {}),
  });
}

/** postTalentCount POST /api/data/postTalentCount */
export async function postTalentCountUsingPost(options?: {
  [key: string]: any;
}) {
  return request<API.BaseResponseListAntVO_>("/api/data/postTalentCount", {
    method: "POST",
    ...(options || {}),
  });
}

/** sexTalentCount POST /api/data/sexTalentCount */
export async function sexTalentCountUsingPost(options?: {
  [key: string]: any;
}) {
  return request<API.BaseResponseListAntVO_>("/api/data/sexTalentCount", {
    method: "POST",
    ...(options || {}),
  });
}

/** talentCount POST /api/data/talentCount */
export async function talentCountUsingPost(options?: { [key: string]: any }) {
  return request<API.BaseResponseLong_>("/api/data/talentCount", {
    method: "POST",
    ...(options || {}),
  });
}

/** totalEducationTalentCount GET /api/data/totalEducationTalentCount */
export async function totalEducationTalentCountUsingGet(options?: {
  [key: string]: any;
}) {
  return request<API.BaseResponseListAntVO_>(
    "/api/data/totalEducationTalentCount",
    {
      method: "GET",
      ...(options || {}),
    }
  );
}

/** totalNewTalentCount GET /api/data/totalNewTalentCount */
export async function totalNewTalentCountUsingGet(options?: {
  [key: string]: any;
}) {
  return request<API.BaseResponseListAntVO_>("/api/data/totalNewTalentCount", {
    method: "GET",
    ...(options || {}),
  });
}

/** totalPostTalentCount GET /api/data/totalPostTalentCount */
export async function totalPostTalentCountUsingGet(options?: {
  [key: string]: any;
}) {
  return request<API.BaseResponseListAntVO_>("/api/data/totalPostTalentCount", {
    method: "GET",
    ...(options || {}),
  });
}

/** totalSexTalentCount GET /api/data/totalSexTalentCount */
export async function totalSexTalentCountUsingGet(options?: {
  [key: string]: any;
}) {
  return request<API.BaseResponseListAntVO_>("/api/data/totalSexTalentCount", {
    method: "GET",
    ...(options || {}),
  });
}

/** totalTalentCount GET /api/data/totalTalentCount */
export async function totalTalentCountUsingGet(options?: {
  [key: string]: any;
}) {
  return request<API.BaseResponseLong_>("/api/data/totalTalentCount", {
    method: "GET",
    ...(options || {}),
  });
}

/** uploadBathData GET /api/data/uploadBathData */
export async function uploadBathDataUsingGet(options?: { [key: string]: any }) {
  return request<API.BaseResponseHashMapStringObject_>(
    "/api/data/uploadBathData",
    {
      method: "GET",
      ...(options || {}),
    }
  );
}
