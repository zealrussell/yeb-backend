package com.zeal.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zeal.server.entity.Department;
import com.zeal.server.entity.vo.RespBean;
import com.zeal.server.entity.vo.RespBeanEnum;
import com.zeal.server.mapper.DepartmentMapper;
import com.zeal.server.service.IDepartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    private final DepartmentMapper departmentMapper;

    public DepartmentServiceImpl(DepartmentMapper departmentMapper) {
        this.departmentMapper = departmentMapper;
    }

    @Override
    public List<Department> getAllDepartments() {
        return departmentMapper.getAllDepartments(-1);
    }

    @Override
    public RespBean addDep(Department dep) {
        if (dep.getName() == null) {
            return RespBean.error(RespBeanEnum.ADD_FILED);
        }
        dep.setEnabled(true);
        departmentMapper.addDep(dep);
        if (1 == dep.getResult()) {
            return RespBean.success(dep);
        }
        return RespBean.error(RespBeanEnum.ADD_FILED);
    }

    @Override
    public RespBean deleteDep(Integer id) {
        Department dep = new Department();
        dep.setId(id);
        departmentMapper.deleteDep(dep);
        if (-2 == dep.getResult()) {
            return RespBean.error(RespBeanEnum.DELETE_FILED);
        } else if (-1 == dep.getResult()) {
            return RespBean.error(RespBeanEnum.DELETE_FILED);
        } else if (1 == dep.getResult()) {
            return RespBean.success("删除成功");
        }
        return RespBean.error(RespBeanEnum.DELETE_FILED);
    }
}
