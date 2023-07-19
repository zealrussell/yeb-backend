package com.zeal.server.controller;

import com.zeal.server.entity.Joblevel;
import com.zeal.server.entity.vo.RespBean;
import com.zeal.server.entity.vo.RespBeanEnum;
import com.zeal.server.service.IJoblevelService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *  职称管理
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
@Controller
@RequestMapping("/system/basic/joblevel")
public class JoblevelController {
    private final IJoblevelService joblevelService;

    public JoblevelController(IJoblevelService joblevelService) {
        this.joblevelService = joblevelService;
    }

    /**
     * 获取所有职称
     * @return List<Joblevel>
     */
    public List<Joblevel> getAllJobLevels(){
        return joblevelService.list();

    }

    /**
     * 添加职称
     * @return RespBean
     */
    @PostMapping("/")
    public RespBean addJobLevel(@RequestBody Joblevel joblevel){
        joblevel.setCreateDate(LocalDateTime.now());
        if(joblevelService.save(joblevel)){
            return RespBean.success("添加成功！");
        }
        return RespBean.error(RespBeanEnum.ADD_FILED);
    }

    /**
     * 更新职称
     * @return RespBean
     */
    @PutMapping("/")
    public RespBean updateJobLevel(@RequestBody Joblevel joblevel) {
        if (joblevelService.updateById(joblevel)) {
            return RespBean.success("更新成功！");
        }
        return RespBean.error(RespBeanEnum.UPDATE_FILED);
    }
    /**
     * 删除职称
     * @return RespBean
     */
    @DeleteMapping("/{id}")
    public RespBean deleteJobLevel(@PathVariable Integer id){
        if(joblevelService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error(RespBeanEnum.DELETE_FILED);
    }

    /**
     * 批量删除职称
     * @return RespBean
     */
    @DeleteMapping("/")
    public RespBean deleteJobLevelByIds(Integer[] ids){
        if(joblevelService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功！");
        }
        return RespBean.error(RespBeanEnum.DELETE_FILED);
    }
}
