package com.example.cmswebapp.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Page {
    /**
     * Unique identifier for Page object.
     */
    private Long id;

    /**
     * Field slug is created from id and field title.
     * If title has more than one word or has space, put '-' instead of it.
     * For instance: id = 1, title = 'This is title', then slug = '1-This-is-title'.
     */
    private String slug;

    /**
     * Field title is used in <h1> of HTML page.
     */
    @NotEmpty(message = "Title must not be empty.")
    private String title;

    /**
     * Field description is used as meta information of HTML page.
     */
    @NotEmpty(message = "Description must not be empty.")
    private String description;

    /**
     * HTML content.
     */
    @NotEmpty(message = "HTML Content must not be empty.")
    private String htmlContent;

    /**
     * Field publishedAt consists of date and time when the page was published.
     * If publishedAt less than current date and time, then the page has been published.
     */
    private Date publishedAt;

    /**
     * Value true for published pages, false - for unpublished.
     */
    private Boolean publishedFl = false;

    /**
     * Field priority is a value for ordering the list of pages. Use the descending sort.
     */
    @Min(value = 0, message = "Priority must be from 0 till 10.")
    @Max(value = 10, message = "Priority must be from 0 till 10.")
    private int priority;

    /**
     * Last date and time modification of Page.
     */
    private Date updateAt;

    public Page(Long id, String slug, String title, String description, String htmlContent, String content, Date publishedAt, Boolean publishedFl, int priority, Date updateAt) {
        this.id = id;
        this.slug = slug;
        this.title = title;
        this.description = description;
        this.htmlContent = htmlContent;
        this.publishedAt = publishedAt;
        this.publishedFl = publishedFl;
        this.priority = priority;
        this.updateAt = updateAt;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public Boolean getPublishedFl() {
        return publishedFl;
    }

    public void setPublishedFl(Boolean publishedFl) {
        this.publishedFl = publishedFl;
    }

    public Page() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }


    public String getPublishedAt() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        return dateFormat.format(publishedAt);
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return Objects.equals(id, page.id) && Objects.equals(slug, page.slug);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, slug);
    }

    @Override
    public String toString() {
        return "page{" +
                "id=" + id +
                ", slug='" + slug + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", htmlContent='" + htmlContent + '\'' +
                ", publishedAt=" + publishedAt +
                ", publishedFl=" + publishedFl +
                ", priority=" + priority +
                ", updateAt=" + updateAt +
                '}';
    }
}
