package com.xuecheng.media.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xuecheng.media.model.po.MediaProcess;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author itcast
 */
public interface MediaProcessMapper extends BaseMapper<MediaProcess> {
    /**
    * @Description:  执行器分片广播方式获取对应任务
    * @Param: [shareTotal, shareIndex, count]
    * @Author: stefanie
    * @Date: 2023/10/2~20:07
    */
    @Select("select * from media_process t where t.id%#{shareTotal}=#{shareIndex} and (t.status=1 or t.status=3) and t.fail_count<3 limit #{count}")
    List<MediaProcess> selectListByShareIndex(@Param("shareTotal")int shareTotal,
                                              @Param("shareIndex") int shareIndex,
                                              @Param("count") int count);

    /**
    * @Description:  数据库作为分布式锁
    * @Param: [id]
    * @Author: stefanie
    * @Date: 2023/10/2~20:07
    */
    @Update("update media_process m set m.status='4' where (m.status='1' or m.status='3') and m.fail_count<3 and m.id=#{id}")
    int startTask(@Param("id")Long id);
}
