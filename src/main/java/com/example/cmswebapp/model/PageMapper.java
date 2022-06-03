package com.example.cmswebapp.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PageMapper implements RowMapper<Page> {

    @Override
    public Page mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Page page = new Page();

        page.setId(resultSet.getLong("id"));
        page.setSlug(resultSet.getString("slug"));
        page.setTitle(resultSet.getString("title"));
        page.setDescription(resultSet.getString("description"));
        page.setHtmlContent(resultSet.getString("html_content"));
        page.setContent(resultSet.getString("content"));
        page.setPublishedAt(resultSet.getTimestamp("published_at"));
        page.setPublishedFl(resultSet.getBoolean("published_fl"));
        page.setPriority(resultSet.getInt("priority"));
        page.setUpdateAt(resultSet.getTimestamp("update_at"));

        return page;
    }
}
