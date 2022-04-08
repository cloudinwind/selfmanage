package com.cloudinwind.selfmanage.mapper;

import com.cloudinwind.selfmanage.entity.forum.Thumb;
import com.cloudinwind.selfmanage.entity.forum.ThumbExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface ThumbMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumb
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    long countByExample(ThumbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumb
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int deleteByExample(ThumbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumb
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumb
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int insert(Thumb record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumb
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int insertSelective(Thumb record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumb
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    List<Thumb> selectByExampleWithRowbounds(ThumbExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumb
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    List<Thumb> selectByExample(ThumbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumb
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    Thumb selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumb
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int updateByExampleSelective(@Param("record") Thumb record, @Param("example") ThumbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumb
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int updateByExample(@Param("record") Thumb record, @Param("example") ThumbExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumb
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int updateByPrimaryKeySelective(Thumb record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table thumb
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int updateByPrimaryKey(Thumb record);
}