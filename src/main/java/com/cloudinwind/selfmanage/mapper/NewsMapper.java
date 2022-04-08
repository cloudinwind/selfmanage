package com.cloudinwind.selfmanage.mapper;

import com.cloudinwind.selfmanage.entity.forum.News;
import com.cloudinwind.selfmanage.entity.forum.NewsExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface NewsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    long countByExample(NewsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int deleteByExample(NewsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int deleteByPrimaryKey(Long newsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int insert(News record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int insertSelective(News record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    List<News> selectByExampleWithRowbounds(NewsExample example, RowBounds rowBounds);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    List<News> selectByExample(NewsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    News selectByPrimaryKey(Long newsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int updateByExampleSelective(@Param("record") News record, @Param("example") NewsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int updateByExample(@Param("record") News record, @Param("example") NewsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int updateByPrimaryKeySelective(News record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table news
     *
     * @mbg.generated Wed Sep 23 18:14:48 CST 2020
     */
    int updateByPrimaryKey(News record);
}