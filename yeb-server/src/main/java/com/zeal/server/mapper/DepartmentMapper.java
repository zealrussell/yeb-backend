package com.zeal.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zeal.server.entity.Department;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> getAllDepartments(int parentId);

    void addDep(Department dep);

    void deleteDep(Department dep);
}
