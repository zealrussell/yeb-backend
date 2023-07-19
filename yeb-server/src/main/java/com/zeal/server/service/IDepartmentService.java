package com.zeal.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zeal.server.entity.Department;
import com.zeal.server.entity.vo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
public interface IDepartmentService extends IService<Department> {

    List<Department> getAllDepartments();

    RespBean addDep(Department dep);

    RespBean deleteDep(Integer id);
}
