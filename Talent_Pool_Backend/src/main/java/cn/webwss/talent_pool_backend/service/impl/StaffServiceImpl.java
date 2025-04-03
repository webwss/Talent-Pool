package cn.webwss.talent_pool_backend.service.impl;

import cn.webwss.talent_pool_backend.service.StaffService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cn.webwss.talent_pool_backend.model.entity.Staff;
import cn.webwss.talent_pool_backend.mapper.StaffMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【staff】的数据库操作Service实现
* @createDate 2025-04-03 13:23:59
*/
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff>
    implements StaffService {

}




