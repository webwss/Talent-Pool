package cn.webwss.backend.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.webwss.backend.exception.BusinessException;
import cn.webwss.backend.exception.ErrorCode;
import cn.webwss.backend.mapper.TalentMapper;
import cn.webwss.backend.model.dto.staff.StaffEditDTO;
import cn.webwss.backend.model.entity.Talent;
import cn.webwss.backend.model.vo.login.WxUserInfoVO;
import cn.webwss.backend.utils.WechatUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.webwss.backend.model.entity.Staff;
import cn.webwss.backend.service.StaffService;
import cn.webwss.backend.mapper.StaffMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import static cn.webwss.backend.constant.StaffConstant.STAFF_LOGIN_STATE;

/**
* @author admin
* @description 针对表【staff】的数据库操作Service实现
* @createDate 2025-06-16 10:39:29
*/
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff>
    implements StaffService{


    @Resource
    private WechatUtil wechatUtil;

    @Resource
    private TalentMapper talentMapper;

    /**
     * 微信登录
     * @param code
     * @param request
     * @return
     */
    @Override
    public Staff wxLogin(String code, HttpServletRequest request) throws Exception {
        //        获取用户详细信息
        WxUserInfoVO userInfo = wechatUtil.getUserInfo(code);
        Staff staff = new Staff();
        staff.setWxUserId(userInfo.getUserid());
        staff.setName(userInfo.getName());
        staff.setRole("hr");
//        查询数据库中是否有该记录
        QueryWrapper<Staff> staffQueryWrapper = new QueryWrapper<>();
        staffQueryWrapper.eq("wx_user_id", staff.getWxUserId());
        Staff staff1 = getOne(staffQueryWrapper);
        if (staff1 == null) {
            boolean save = save(staff);
            if (!save) {
                throw new BusinessException(ErrorCode.OPERATION_ERROR, "员工信息保存失败");
            }
            request.getSession().setAttribute(STAFF_LOGIN_STATE, staff);
            return staff;
        }
        request.getSession().setAttribute(STAFF_LOGIN_STATE, staff1);
        return staff1;
    }

    /**
     * 校验是否登录
     * @param request
     * @return
     */
    @Override
    public Staff checkToken(HttpServletRequest request) {
        Object staff = request.getSession().getAttribute(STAFF_LOGIN_STATE);
        if (staff == null) {
            throw new BusinessException(ErrorCode.NOT_LOGIN_ERROR);
        }
        return (Staff) staff;
    }

    /**
     * 登出
     * @param request
     * @return
     */
    @Override
    public Boolean logout(HttpServletRequest request) {
        // 先判断是否已登录
        Object userObj = request.getSession().getAttribute(STAFF_LOGIN_STATE);
        if (userObj == null) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "未登录");
        }
        // 移除登录态
        request.getSession().removeAttribute(STAFF_LOGIN_STATE);
        return true;
    }


    /**
     * 编辑员工信息
     * @param staffEditDTO
     * @return
     */
    @Override
    public boolean editStaff(StaffEditDTO staffEditDTO) {
        Integer staffId = staffEditDTO.getStaffId();
        String name = staffEditDTO.getName();
        String phone = staffEditDTO.getPhone();
        String role = staffEditDTO.getRole();
        if (StrUtil.hasBlank(name, phone, role)) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        if (staffId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Staff staff = new Staff();
        staff.setStaffId(staffId);
        staff.setName(name);
        staff.setPhone(phone);
        staff.setRole(role);
        boolean b = updateById(staff);
        if (!b) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR);
        }
        return true;
    }

    /**
     * 删除员工信息
     * @param staffId
     * @return
     */
    @Override
    public boolean deleteStaff(Integer staffId) {
        if (staffId == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
//        查询该员工是否有创建人才
        QueryWrapper<Talent> talentQueryWrapper = new QueryWrapper<>();
        talentQueryWrapper.eq("create_staff_id", staffId);
        Long l = talentMapper.selectCount(talentQueryWrapper);
        if (l > 0) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "该员工有创建人才，请勿删除");
        }
        boolean removeById = removeById(staffId);
        if (!removeById) {
            throw new BusinessException(ErrorCode.OPERATION_ERROR, "员工删除失败");
        }
        return true;
    }
}




