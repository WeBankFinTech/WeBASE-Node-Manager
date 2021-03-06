package com.webank.webase.node.mgr.deploy.mapper;

import com.webank.webase.node.mgr.deploy.entity.TbConfig;
import org.apache.ibatis.jdbc.SQL;

public class TbConfigSqlProvider {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table tb_config
     *
     * @mbg.generated
     */
    public static final String ALL_COLUMN_FIELDS = "id,config_name,config_type,config_value,create_time,modify_time";

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_config
     *
     * @mbg.generated
     */
    public String insertSelective(TbConfig record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("tb_config");
        
        if (record.getConfigName() != null) {
            sql.VALUES("config_name", "#{configName,jdbcType=VARCHAR}");
        }
        
        if (record.getConfigType() != null) {
            sql.VALUES("config_type", "#{configType,jdbcType=SMALLINT}");
        }
        
        if (record.getConfigValue() != null) {
            sql.VALUES("config_value", "#{configValue,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            sql.VALUES("modify_time", "#{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        return sql.toString();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_config
     *
     * @mbg.generated
     */
    public String updateByPrimaryKeySelective(TbConfig record) {
        SQL sql = new SQL();
        sql.UPDATE("tb_config");
        
        if (record.getConfigName() != null) {
            sql.SET("config_name = #{configName,jdbcType=VARCHAR}");
        }
        
        if (record.getConfigType() != null) {
            sql.SET("config_type = #{configType,jdbcType=SMALLINT}");
        }
        
        if (record.getConfigValue() != null) {
            sql.SET("config_value = #{configValue,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=TIMESTAMP}");
        }
        
        if (record.getModifyTime() != null) {
            sql.SET("modify_time = #{modifyTime,jdbcType=TIMESTAMP}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }
}