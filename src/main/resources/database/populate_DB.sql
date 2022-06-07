insert into PagesInfo(slug, title, description, html_content, published_at, published_fl, priority, update_at)
select '1-New-title',
       'New title',
       'This is description for HTML meta info',
       '<p><b>What is Lorem Ipsum?</b></p>',
       '2222-01-01',
       false,
       10,
       now();
commit;

insert into PagesInfo(slug, title, description, html_content, published_at, published_fl, priority, update_at)
select '2-New-title',
       'New title',
       'This is description for HTML meta info',
       '<p><b>What is Lorem Ipsum?</b></p>',
       now(),
       true,
       5,
       now();
commit;