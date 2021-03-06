/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.weinyc.sa.app.dao.impl;

import org.springframework.jdbc.core.RowMapper;

import com.weinyc.sa.app.dao.ClientDAO;
import com.weinyc.sa.app.model.Client;
import com.weinyc.sa.core.dao.impl.AbstractModelDAOImpl;

/**
 *
 * @author ronghai
 */
public class ClientDAOImpl extends AbstractModelDAOImpl<Client> implements ClientDAO {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public RowMapper<Client> createRowMapper() {
        return Client._getModelMeta().getRowMapper();

        /*return new RowMapper() {
         @Override
         public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
         Client bean  = new Client();
         bean.setId(rs.getLong("ID"));
         bean.setAddTime(rs.getDate("add_time"));
         bean.setDisabled(rs.getBoolean("disabled"));
         bean.setUpdateTime(rs.getDate("update_time"));
         bean.setNote(rs.getString("note"));
         bean.setBirthday(rs.getDate("birthday"));
         bean.setGender(rs.getString("gender"));
         bean.setName(rs.getString("name"));
         bean.setPhone(rs.getString("phone"));
         bean.setQq(rs.getString("qq"));
         bean.setQqName(rs.getString("qq_name"));
         bean.setWangwang(rs.getString("wangwang"));
         return bean;
         }
         } ;*/
    }
}
