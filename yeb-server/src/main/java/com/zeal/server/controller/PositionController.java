package com.zeal.server.controller;

import com.zeal.server.entity.Position;
import com.zeal.server.entity.vo.RespBean;
import com.zeal.server.entity.vo.RespBeanEnum;
import com.zeal.server.service.IPositionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.PrimitiveIterator;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zeal
 * @since 2023-07-11
 */
@Controller
@RequestMapping("/system/basic/pos")
public class PositionController {

    private final IPositionService positionService;

    public PositionController(IPositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping("/")
    public List<Position> getAllPositions(){
        return positionService.list();
    }
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        position.setCreateDate(LocalDateTime.now());
        if (positionService.save(position)) {
            return RespBean.success("添加成功！");
        }
        return RespBean.error(RespBeanEnum.ADD_FILED);
    }

    @PutMapping("/")
    public RespBean updatePosition(){
        if(positionService.updateById(new Position())){
            return RespBean.success("更新成功！");
        }
        return RespBean.error(RespBeanEnum.UPDATE_FILED);
    }

    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id){
        if(positionService.removeById(id)){
            return RespBean.success("删除成功！");
        }
        return RespBean.error(RespBeanEnum.DELETE_FILED);
    }

    public RespBean deletePositions(@RequestBody Integer[] ids){
        if(positionService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除成功！");
        }
        return RespBean.error(RespBeanEnum.DELETE_FILED);
    }

}
