package cn.webwss.backend.service.impl;

import cn.webwss.backend.mapper.DataMapper;
import cn.webwss.backend.mapper.EducationMapper;
import cn.webwss.backend.mapper.PostMapper;
import cn.webwss.backend.mapper.TalentMapper;
import cn.webwss.backend.model.entity.Education;
import cn.webwss.backend.model.entity.Post;
import cn.webwss.backend.model.entity.Staff;
import cn.webwss.backend.model.entity.Talent;
import cn.webwss.backend.model.vo.data.AntVO;
import cn.webwss.backend.service.DataService;
import cn.webwss.backend.service.StaffService;
import cn.webwss.backend.utils.GetSevenDate;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataServiceImpl implements DataService {

    @Resource
    private StaffService staffService;
    @Resource
    private TalentMapper talentMapper;
    @Resource
    private DataMapper dataMapper;

    @Resource
    private PostMapper postMapper;
    @Resource
    private EducationMapper educationMapper;


    /**
     * 获取人才数量
     * @param request
     * @return
     */
    @Override
    public Long talentCount(HttpServletRequest request) {
        Staff staff = staffService.checkToken(request);
        Integer staffId = staff.getStaffId();
        QueryWrapper<Talent> talentQueryWrapper = new QueryWrapper<>();
        talentQueryWrapper.eq("create_staff_id", staffId);
        return talentMapper.selectCount(talentQueryWrapper);
    }

    /**
     * 获取人才总数
     * @return
     */
    @Override
    public Long totalTalentCount() {
        return talentMapper.selectCount(null);
    }

    /**
     * 获取各岗位人才数量（hr）
     * @param request
     * @return
     */
    @Override
    public List<AntVO> postTalentCount(HttpServletRequest request) {
        Staff staff = staffService.checkToken(request);
        Integer staffId = staff.getStaffId();
        List<Map<String,Object>> postTalentCount = dataMapper.postTalentCount(staffId);
        return postTalentCount.stream().map(item -> {
            AntVO antVO = new AntVO();
            antVO.setName((String) item.get("postName"));  // 映射岗位名称
            antVO.setValue(Integer.parseInt(String.valueOf(item.get("talentCount"))));
            return antVO;
        }).collect(Collectors.toList());
    }

    /**
     * 获取各岗位人才数量（管理员）
     * @return
     */
    @Override
    public List<AntVO> totalPostTalentCount() {
        List<Map<String,Object>> postTalentCount = dataMapper.totalPostTalentCount();
        return postTalentCount.stream().map(item -> {
            AntVO antVO = new AntVO();
            antVO.setName((String) item.get("postName"));  // 映射岗位名称
            antVO.setValue(Integer.parseInt(String.valueOf(item.get("talentCount"))));
            return antVO;
        }).collect(Collectors.toList());
    }

    /**
     * 性别及人才数量（hr）
     * @param request
     * @return
     */
    @Override
    public List<AntVO> sexTalentCount(HttpServletRequest request) {
        Staff staff = staffService.checkToken(request);
        Integer staffId = staff.getStaffId();
        List<AntVO> result = new ArrayList<>();
        QueryWrapper<Talent> talentQueryWrapper = new QueryWrapper<>();
        talentQueryWrapper.eq("create_staff_id", staffId);
        talentQueryWrapper.eq("sex", 1);
        Long maleCount = talentMapper.selectCount(talentQueryWrapper);
        talentQueryWrapper.clear();
        talentQueryWrapper.eq("create_staff_id", staffId);
        talentQueryWrapper.eq("sex", 2);
        Long femaleCount = talentMapper.selectCount(talentQueryWrapper);
        System.out.println("maleCount = " + maleCount);
        System.out.println("femaleCount = " + femaleCount);
        AntVO  male = new AntVO();
        male.setName("男");
        male.setValue(maleCount.intValue());
        result.add(male);
        AntVO female = new AntVO();
        female.setName("女");
        female.setValue(femaleCount.intValue());
        result.add(female);
        return result;
    }

    @Override
    public List<AntVO> totalSexTalentCount() {
        List<AntVO> result = new ArrayList<>();
        QueryWrapper<Talent> talentQueryWrapper = new QueryWrapper<>();
        talentQueryWrapper.eq("sex", 1);
        Long maleCount = talentMapper.selectCount(talentQueryWrapper);
        talentQueryWrapper.clear();
        talentQueryWrapper.eq("sex", 2);
        Long femaleCount = talentMapper.selectCount(talentQueryWrapper);
        System.out.println("maleCount = " + maleCount);
        System.out.println("femaleCount = " + femaleCount);
        AntVO  male = new AntVO();
        male.setName("男");
        male.setValue(maleCount.intValue());
        result.add(male);
        AntVO female = new AntVO();
        female.setName("女");
        female.setValue(femaleCount.intValue());
        result.add(female);
        return result;
    }

    /**
     * 学历及人才数量（hr）
     * @param request
     * @return
     */
    @Override
    public List<AntVO> educationTalentCount(HttpServletRequest request) {
        Staff staff = staffService.checkToken(request);
        Integer staffId = staff.getStaffId();
        List<Map<String,Object>> educationTalentCount = dataMapper.educationTalentCount(staffId);
        return educationTalentCount.stream().map(item -> {
            AntVO antVO = new AntVO();
            antVO.setName((String) item.get("educationName"));  // 映射岗位名称
            antVO.setValue(Integer.parseInt(String.valueOf(item.get("talentCount"))));
            return antVO;
        }).collect(Collectors.toList());
    }

    /**
     * 学历及人才数量（管理员）
     * @return
     */
    @Override
    public List<AntVO> totalEducationTalentCount() {
        List<Map<String,Object>> educationTalentCount = dataMapper.totalEducationTalentCount();
        return educationTalentCount.stream().map(item -> {
            AntVO antVO = new AntVO();
            antVO.setName((String) item.get("educationName"));  // 映射岗位名称
            antVO.setValue(Integer.parseInt(String.valueOf(item.get("talentCount"))));
            return antVO;
        }).collect(Collectors.toList());
    }


    /**
     * 新增人才数量（hr）
     * @param request
     * @return
     */
    @Override
    public List<AntVO> newTalentCount(HttpServletRequest request) {
        Staff staff = staffService.checkToken(request);
        Integer staffId = staff.getStaffId();
        List<String> date = new ArrayList<>();
        List<Long> count = new ArrayList<>();
        List<String> sevenDate = GetSevenDate.getSevenDate();
        System.out.println("sevenDate" + sevenDate);
        LinkedHashMap<String, Long> map = new LinkedHashMap<>();
        for (int i = sevenDate.size() - 1; i >= 0; i--) {
            date.add(sevenDate.get(i));
            QueryWrapper<Talent> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("create_staff_id", staffId);
//           根据日期查询新增人才数量
            queryWrapper.like("create_time", sevenDate.get(i));
            Long aLong = talentMapper.selectCount(queryWrapper);
            count.add(aLong);
        }
        System.out.println("date = " + date);
        System.out.println("count = " + count);
        for (int i = 0; i < date.size(); i++) {
            map.put(date.get(i), count.get(i));
        }
        List<AntVO> antVOS = new ArrayList<>();
//       遍历map
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            AntVO antVO = new AntVO();
            antVO.setName(entry.getKey().substring(5));
            antVO.setValue(entry.getValue().intValue());
            antVOS.add(antVO);
        }
        return antVOS;
    }


    /**
     * 新增人才数量（管理员）
     * @return
     */
    @Override
    public List<AntVO> totalNewTalentCount() {
        List<String> date = new ArrayList<>();
        List<Long> count = new ArrayList<>();
        List<String> sevenDate = GetSevenDate.getSevenDate();
        LinkedHashMap<String, Long> map = new LinkedHashMap<>();
        for (int i = sevenDate.size() - 1; i >= 0; i--) {
            date.add(sevenDate.get(i));
            QueryWrapper<Talent> queryWrapper = new QueryWrapper<>();
//           根据日期查询新增人才数量
            queryWrapper.like("create_time", sevenDate.get(i));
            Long aLong = talentMapper.selectCount(queryWrapper);
            count.add(aLong);
        }
        for (int i = 0; i < date.size(); i++) {
            map.put(date.get(i), count.get(i));
        }
        List<AntVO> antVOS = new ArrayList<>();
//       遍历map
        for (Map.Entry<String, Long> entry : map.entrySet()) {
            AntVO antVO = new AntVO();
            antVO.setName(entry.getKey().substring(5));
            antVO.setValue(entry.getValue().intValue());
            antVOS.add(antVO);
        }
        return antVOS;
    }

    @Override
    public HashMap<String, Object> uploadBathData() {

        // 获取基础数据
        List<Post> posts = postMapper.selectList(null);
        List<Education> educations = educationMapper.selectList(null);

        // 构建选项数据（复用方法）
        List<AntVO> sex = buildOptions("男", "女");
        List<AntVO> workStatus = buildOptions("在职", "离职");
        List<AntVO> maritalStatus = buildOptions("已婚已育", "已婚未育", "未婚未育");

        // 放入结果集
        HashMap<String, Object> result = new HashMap<>(new HashMap<String, Object>() {{
            put("posts", posts);
            put("educations", educations);
            put("sex", sex);
            put("workStatus", workStatus);
            put("maritalStatus", maritalStatus); // 确保已定义
        }});

        return result;
    }

    // 通用构建 AntVO 列表的方法
    private List<AntVO> buildOptions(String... names) {
        List<AntVO> list = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            AntVO vo = new AntVO();
            vo.setName(names[i]);
            vo.setValue(i + 1); // 假设 value 是从 1 开始递增的整数
            list.add(vo);
        }
        return list;
    }
}
