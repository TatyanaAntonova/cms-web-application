drop table if exists PagesInfo; commit;

create table PagesInfo(
                              id SERIAL,
                              slug varchar,
                              title varchar,
                              description varchar,
                              html_content varchar,
                              published_at timestamp,
                              published_fl boolean,
                              priority int,
                              update_at timestamp
);
commit;

insert into PagesInfo(slug, title, description, html_content, published_at, published_fl, priority, update_at)
select '1-New-title',
       'New title*',
       'This is description for HTML meta info',
       '<p><b>What is Lorem Ipsum?</b></p>',
       '2222-01-01',
       false,
       10,
       now();
commit;

insert into PagesInfo(slug, title, description, html_content, published_at, published_fl, priority, update_at)
select '2-New-title',
       'New title*',
       'This is description for HTML meta info',
       '<p>What is Lorem Ipsum?</p>',
       now(),
       true,
       5,
       now();
commit;

insert into PagesInfo(slug, title, description, html_content, published_at, published_fl, priority, update_at)
select '3-New-title',
       'New title*',
       'This is description for HTML meta info',
       '<p>What is Lorem Ipsum?</p>',
       now(),
       true,
       6,
       now();
commit;