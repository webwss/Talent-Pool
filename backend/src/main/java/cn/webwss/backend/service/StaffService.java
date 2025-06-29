package cn.webwss.backend.service;

import cn.webwss.backend.model.dto.staff.StaffEditDTO;
import cn.webwss.backend.model.entity.Staff;
import cn.webwss.backend.model.vo.data.SendAdminVO;
import cn.webwss.backend.model.vo.data.SendStaffVO;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @author admin
* @description 针对表【staff】的数据库操作Service
* @createDate 2025-06-16 10:39:29
*/
public interface StaffService extends IService<Staff> {

    /**
     * 微信登录
     * @param code
     * @param request
     * @return
     */
    Staff wxLogin(String code, HttpServletRequest request) throws Exception;

    /**
     * 校验是否登录
     * @param request
     * @return
     */
    Staff checkToken(HttpServletRequest request);

    /**
     * 登出
     * @param request
     * @return
     */
    Boolean logout(HttpServletRequest request);


    /**
     * 编辑员工信息
     * @param staffEditDTO
     * @return
     */
    boolean editStaff(StaffEditDTO staffEditDTO);

    /**
     * 删除员工
     * @param staffId
     * @return
     */
    boolean deleteStaff(Integer staffId);

    /**
     * 获取管理员列表
     * @return
     */
    List<Staff> getAdminList();

    /**
     * 发送管理员数据
     * @return
     */
    List<SendAdminVO> sendAdminData();

    /**
     * 发送员工数据
     * @return
     */
    List<SendStaffVO> sendStaffData();
}
