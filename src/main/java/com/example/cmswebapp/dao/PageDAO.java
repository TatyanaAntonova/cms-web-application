package com.example.cmswebapp.dao;

import com.example.cmswebapp.model.*;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Database access object.
 */
@Component
public class PageDAO implements DAO<Page> {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PageDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Page> getList() {
        String sqlQuery = "SELECT * FROM PagesInfo ORDER BY priority DESC, published_at;";

        return jdbcTemplate.query(sqlQuery, new PageMapper());
    }

    /**
     * @return published Page list.
     * Page is published if field publishedAt is less or equal current date.
     * Display order depends on priority value. Higher priority Pages are first,
     * if priorities are equal, sort by publishedAt value.
     */
    public List<Page> getPublishedList() {
        String sqlQuery = "select * from PagesInfo where date(published_at) <= date(now()) " +
                "order by priority desc, published_at;";

        return jdbcTemplate.query(sqlQuery, new PageMapper());
    }

    /**
     * @return unpublished Page list.
     * Page is unpublished if field publishedAt is bigger than current date.
     * Display order depends on priority and publishedAt values.
     */
    public List<Page> getUnpublishedList() {
        String sqlQuery = "select * from PagesInfo where date(published_at) > date(now()) " +
                "order by priority desc, published_at;";
        return jdbcTemplate.query(sqlQuery, new PageMapper());
    }

    @Override
    public Page get(String slug) {
        String sqlQuery = "select * from PagesInfo where slug = ?;";
        return jdbcTemplate.query(sqlQuery, new PageMapper(), slug).stream().findFirst().orElse(new Page());
    }

    /**
     * @param page Object needs to be created in DB.
     *             Method adds the Object to DB.
     * String slug is generated from title. After insertion a new Page into DB, Slug needs to be updated and made unique.
     * DB has id auto increment. Adding id make slug unique.
     * String content is text has been parsed from HTML.
     * Update operation needs to be synchronized because of the risk of updating wrong DB object.
     */
    @Override
    public void create(Page page) {
        String slug = new Slug().makeSlug(page.getTitle());

        String sqlQuery = "INSERT INTO PagesInfo(slug, title, description, html_content, published_at, published_fl, priority, update_at)" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, NOW()); commit;" +
                //make slug unique
                "UPDATE pagesinfo set slug = concat(cast(id as varchar), '-', slug) " +
                "where id = (select max(id) from pagesInfo); commit;";

        synchronized (jdbcTemplate) {
            jdbcTemplate.update(sqlQuery,
                    slug,
                    page.getTitle(),
                    page.getDescription(),
                    page.getHtmlContent(),
                    page.getPublishedFl() ? LocalDateTime.now() : LocalDateTime.MAX,
                    page.getPublishedFl(),
                    page.getPriority());
        }
    }

    /**
     * @param slug unique identifier of Object.
     * @param page Object needs to be updated in DB.
     *             Method updates the Object.
     * If user changed title slug need to be changed.
     * Update operation is synchronized.
     */
    @Override
    public void update(String slug, Page page) {
        String newSlug = new Slug().makeSlug(page.getTitle());

        String sqlQuery =
                "UPDATE PagesInfo " +
                        "SET title = ?, " +
                        "slug = concat(id, '-', ?), " +
                        "description = ?, " +
                        "html_content = ?," +
                        "priority = ?," +
                        "published_at = ?," +
                        "published_fl = ?," +
                        "update_at = NOW() " +
                        "WHERE slug = ?; commit;";

        synchronized (jdbcTemplate) {
            jdbcTemplate.update(sqlQuery,
                    page.getTitle(),
                    newSlug,
                    page.getDescription(),
                    page.getHtmlContent(),
                    page.getPriority(),
                    page.getPublishedFl() ? LocalDateTime.now() : LocalDateTime.MAX,
                    page.getPublishedFl(),
                    slug);
        }
    }

    @Override
    public void delete(String slug) {
        String sqlQuery = "delete from PagesInfo where slug = ?; commit;";
        jdbcTemplate.update(sqlQuery, slug);
    }
}
