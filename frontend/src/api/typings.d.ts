declare namespace API {
  type addEducationUsingPOSTParams = {
    /** educationName */
    educationName: string;
  };

  type addPostUsingPOSTParams = {
    /** postName */
    postName: string;
  };

  type addResumesUsingPOSTParams = {
    /** talentId */
    talentId: number;
  };

  type addTalentUsingPOSTParams = {
    address?: string;
    advantage?: string;
    age?: string;
    currentPost?: string;
    currentSalary?: string;
    disadvantage?: string;
    educationId?: number;
    expectedSalary?: string;
    jobStatus?: number;
    jobYear?: number;
    marriage?: number;
    name?: string;
    nextInterviewTime?: string;
    phone?: string;
    postId?: number;
    reasonForLeaving?: string;
    result?: number;
    sex?: number;
  };

  type agreeDeleteUsingPOSTParams = {
    /** talentId */
    talentId: number;
  };

  type AntVO = {
    name?: string;
    value?: number;
  };

  type BaseResponseBoolean_ = {
    code?: number;
    data?: boolean;
    message?: string;
  };

  type BaseResponseHashMapStringObject_ = {
    code?: number;
    data?: Record<string, any>;
    message?: string;
  };

  type BaseResponseInteract_ = {
    code?: number;
    data?: Interact;
    message?: string;
  };

  type BaseResponseInterview_ = {
    code?: number;
    data?: Interview;
    message?: string;
  };

  type BaseResponseListAntVO_ = {
    code?: number;
    data?: AntVO[];
    message?: string;
  };

  type BaseResponseListEducation_ = {
    code?: number;
    data?: Education[];
    message?: string;
  };

  type BaseResponseListInteract_ = {
    code?: number;
    data?: Interact[];
    message?: string;
  };

  type BaseResponseListInterview_ = {
    code?: number;
    data?: Interview[];
    message?: string;
  };

  type BaseResponseListPost_ = {
    code?: number;
    data?: Post[];
    message?: string;
  };

  type BaseResponseListResume_ = {
    code?: number;
    data?: Resume[];
    message?: string;
  };

  type BaseResponseListStaff_ = {
    code?: number;
    data?: Staff[];
    message?: string;
  };

  type BaseResponseLong_ = {
    code?: number;
    data?: number;
    message?: string;
  };

  type BaseResponsePageTalent_ = {
    code?: number;
    data?: PageTalent_;
    message?: string;
  };

  type BaseResponsePost_ = {
    code?: number;
    data?: Post;
    message?: string;
  };

  type BaseResponseResumeRequestion_ = {
    code?: number;
    data?: ResumeRequestion;
    message?: string;
  };

  type BaseResponseStaff_ = {
    code?: number;
    data?: Staff;
    message?: string;
  };

  type BaseResponseTalentVO_ = {
    code?: number;
    data?: TalentVO;
    message?: string;
  };

  type deleteEducationUsingPOSTParams = {
    /** educationId */
    educationId: number;
  };

  type deleteInteractUsingPOSTParams = {
    /** interactId */
    interactId: number;
  };

  type deleteInterviewUsingPOSTParams = {
    /** interviewId */
    interviewId: number;
  };

  type deletePostUsingPOSTParams = {
    /** postId */
    postId: number;
  };

  type deleteResumeUsingPOSTParams = {
    /** resumeId */
    resumeId: number;
  };

  type deleteStaffUsingPOSTParams = {
    /** staffId */
    staffId: number;
  };

  type editPhotoUsingPOSTParams = {
    /** talentId */
    talentId: number;
  };

  type Education = {
    educationId?: number;
    educationName?: string;
  };

  type EducationEditDTO = {
    educationId?: number;
    educationName?: string;
  };

  type generateQuestionsUsingPOSTParams = {
    /** resumeId */
    resumeId: number;
  };

  type getInteractDetailUsingPOSTParams = {
    /** interactId */
    interactId: number;
  };

  type getInterviewDetailUsingPOSTParams = {
    /** interviewId */
    interviewId: number;
  };

  type getListByTalentIdUsingPOST1Params = {
    /** talentId */
    talentId: number;
  };

  type getListByTalentIdUsingPOSTParams = {
    /** talentId */
    talentId: number;
  };

  type getListUsingPOSTParams = {
    /** talentId */
    talentId: number;
  };

  type getPostUsingGETParams = {
    /** postId */
    postId: number;
  };

  type getQuestionsUsingPOSTParams = {
    /** resumeId */
    resumeId: number;
  };

  type getStaffDetailUsingPOSTParams = {
    /** staffId */
    staffId: number;
  };

  type getTalentDetailUsingPOSTParams = {
    /** talentId */
    talentId: number;
  };

  type HashMapStringObject_ = true;

  type Interact = {
    createTime?: string;
    interactContent?: string;
    interactId?: number;
    interactStaff?: string;
    interactTime?: string;
    talentId?: number;
  };

  type InteractAddDTO = {
    interactContent?: string;
    interactStaff?: string;
    interactTime?: string;
    talentId?: number;
  };

  type InteractEditDTO = {
    interactContent?: string;
    interactId?: number;
    interactStaff?: string;
    interactTime?: string;
  };

  type Interview = {
    createTime?: string;
    interviewAccess?: string;
    interviewId?: number;
    interviewTime?: string;
    interviewer?: string;
    talentId?: number;
  };

  type InterviewAddDTO = {
    interviewAccess?: string;
    interviewTime?: string;
    interviewer?: string;
    talentId?: number;
  };

  type InterviewEditDTO = {
    interviewAccess?: string;
    interviewId?: number;
    interviewTime?: string;
    interviewer?: string;
  };

  type PageTalent_ = {
    current?: number;
    pages?: number;
    records?: Talent[];
    size?: number;
    total?: number;
  };

  type Post = {
    postId?: number;
    postName?: string;
  };

  type PostEditDTO = {
    postId?: number;
    postName?: string;
  };

  type refuseDeleteUsingPOSTParams = {
    /** talentId */
    talentId: number;
  };

  type Resume = {
    resumeId?: number;
    resumeRequestion?: ResumeRequestion;
    resumeUrl?: string;
    talentId?: number;
  };

  type ResumeAddDTO = {
    resumeUrl?: string;
    talentId?: number;
  };

  type ResumeRequestion = {
    content?: string;
    resumeId?: number;
    resumeRequestionId?: number;
  };

  type Staff = {
    createTime?: string;
    name?: string;
    phone?: string;
    role?: string;
    staffId?: number;
    wxUserId?: string;
  };

  type StaffEditDTO = {
    name?: string;
    phone?: string;
    role?: string;
    staffId?: number;
  };

  type Talent = {
    address?: string;
    advantage?: string;
    age?: string;
    createStaffId?: number;
    createTime?: string;
    currentPost?: string;
    currentSalary?: string;
    delReason?: string;
    delStatus?: number;
    disadvantage?: string;
    editSum?: number;
    editTime?: string;
    educationId?: number;
    expectedSalary?: string;
    jobStatus?: number;
    jobYear?: number;
    marriage?: number;
    name?: string;
    nextInterviewTime?: string;
    phone?: string;
    photo?: string;
    postId?: number;
    reasonForLeaving?: string;
    result?: number;
    sex?: number;
    talentId?: number;
  };

  type TalentDeleteDTO = {
    delReason?: string;
    talentId?: number;
  };

  type TalentEditBaseInfoDTO = {
    address?: string;
    advantage?: string;
    age?: string;
    currentPost?: string;
    currentSalary?: string;
    disadvantage?: string;
    educationId?: number;
    expectedSalary?: string;
    jobStatus?: number;
    jobYear?: number;
    marriage?: number;
    name?: string;
    nextInterviewTime?: string;
    phone?: string;
    postId?: number;
    reasonForLeaving?: string;
    result?: number;
    sex?: number;
    talentId?: number;
  };

  type TalentRequestDTO = {
    current?: number;
    deleteStatus?: number;
    educationId?: number;
    jobStatus?: number;
    marriage?: number;
    name?: string;
    pageSize?: number;
    phone?: string;
    postId?: number;
    result?: number;
    searchText?: string;
    sex?: number;
    sortField?: string;
    sortOrder?: string;
  };

  type TalentVO = {
    address?: string;
    advantage?: string;
    age?: string;
    createStaffId?: number;
    createTime?: string;
    currentPost?: string;
    currentSalary?: string;
    delReason?: string;
    delStatus?: number;
    disadvantage?: string;
    editSum?: number;
    editTime?: string;
    education?: Education;
    educationId?: number;
    expectedSalary?: string;
    interactList?: Interact[];
    interviewList?: Interview[];
    jobStatus?: number;
    jobYear?: number;
    marriage?: number;
    name?: string;
    nextInterviewTime?: string;
    phone?: string;
    photo?: string;
    post?: Post;
    postId?: number;
    reasonForLeaving?: string;
    result?: number;
    resumeList?: Resume[];
    sex?: number;
    staff?: Staff;
    talentId?: number;
  };

  type wxLoginUsingPOSTParams = {
    /** code */
    code: string;
  };
}
