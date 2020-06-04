package com.yjzh.emergency.mapper;

import com.yjzh.emergency.po.albumInfo;
import com.yjzh.emergency.po.albumInfoExample;
import java.util.List;

public interface albumInfoMapper {
    int deleteByPrimaryKey(Integer albumId);

    int insert(albumInfo record);

    int insertSelective(albumInfo record);

    List<albumInfo> selectByExample(albumInfoExample example);

    albumInfo selectByPrimaryKey(Integer albumId);

    int updateByPrimaryKeySelective(albumInfo record);

    int updateByPrimaryKey(albumInfo record);
}